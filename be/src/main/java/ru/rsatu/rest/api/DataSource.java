/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.rsatu.db.entities.Doctor;
import ru.rsatu.db.entities.GroupProcedure;
import ru.rsatu.db.entities.Pacient;
import ru.rsatu.db.entities.PaymentComplite;
import ru.rsatu.db.entities.Procedure;
import ru.rsatu.db.entities.TherapyJournal;
import ru.rsatu.db.entities.VisitJournal;
import ru.rsatu.enums.DocumentStatus;
import ru.rsatu.enums.JournalStatus;
import ru.rsatu.enums.PaymentStatus;
import ru.rsatu.rest.pojo.ActualVisitComponent;
import ru.rsatu.rest.pojo.DayVisit;
import ru.rsatu.rest.pojo.GetPacientJournal;
import ru.rsatu.rest.pojo.GetPayment;
import ru.rsatu.rest.pojo.GetProcedure;
import ru.rsatu.rest.pojo.GetVisitsPacient;
import ru.rsatu.rest.pojo.Procedures;
import ru.rsatu.rest.pojo.RecivePacientJournalElement;
import ru.rsatu.rest.pojo.TherapyElement;
import ru.rsatu.rest.pojo.TimeProcedure;

/**
 *
 * @author emedvedev
 */
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataSource {

    private static final Integer WOTK_TIME = 17;

    @POST
    @Path("/get/procedures")
    public Response procedures() {
        List<Procedure> prs = Procedure.findAll().list();
        List<Procedures> nprs = new ArrayList();
        prs.forEach((el) -> {
            Procedures p = new Procedures();
            if (el.getGroupProcedure() != null) {
                p.setGroup(el.getGroupProcedure().getName());
            } else {
                p.setGroup("");
            }
            p.setName(el.getName());
            nprs.add(p);

        });
        return Response.ok(nprs).build();
    }

    @POST
    @Path("/get/groupsprocedures")
    public Response groupsprocedures() {
        List<GroupProcedure> lgp = GroupProcedure.findAll().list();
        return Response.ok(lgp).build();
    }

    @POST
    @Path("/get/procedure")
    public Response procedure(String name) {
        Procedure pr = Procedure.find("name", name).firstResult();
        return Response.ok(pr).build();
    }

    @POST
    @Path("/get/doctors")
    public Response doctors() {
        List<Doctor> d = Doctor.findAll().list();
        List<Integer> nd = new ArrayList();
        d.forEach((el) -> {
            if (el.getDocStatus() == DocumentStatus.ALIVE) {
                nd.add(el.getVkId());
            }
        });
        return Response.ok(nd).build();
    }

    @POST
    @Path("/get/fullprocedures")
    public Response fullprocedures() {
        List<GetProcedure> lgp = new ArrayList();
        List<Procedure> prs = Procedure.findAll().list();
        for (Procedure p : prs) {
            GetProcedure gp = new GetProcedure();
            gp.setTime(p.getTime());
            gp.setName(p.getName());
            gp.setPrice(p.getPrice());
            gp.setDescription(p.getDescription());
            if (p.getGroupProcedure() != null) {
                gp.setGroup(p.getGroupProcedure().getName());
            } else {
                gp.setGroup("");
            }
            lgp.add(gp);
        }
        return Response.ok(lgp).build();
    }

    @POST
    @Path("/get/docprocedures")
    public Response docprocedures(Integer vkid) {
        Doctor d = Doctor.find("vkId", vkid).firstResult();
        return Response.ok(d.getProcedure()).build();
    }

    @POST
    @Path("/get/payment")
    public Response payment(Integer vkid) {
        Pacient p = Pacient.find("vkId", vkid).firstResult();
        List<TherapyJournal> ltj = p.getTherapyJournal();
        List<GetPayment> lgp = new ArrayList();
        for (TherapyJournal tj : ltj) {
            List<VisitJournal> lvj = tj.getVisitJournal();
            for (VisitJournal vj : lvj) {
                if (vj.getStatus() == JournalStatus.COMPLETED) {
                    if (vj.getPayment().getPaymentStatus() != PaymentStatus.COMPLITED) {
                        Integer count = vj.getPayment().getCount();
                        List<PaymentComplite> lpc = vj.getPayment().getPaymentComplite();
                        if (lpc != null) {
                            for (PaymentComplite pc : lpc) {
                                if (pc.getPaymentStatus() != PaymentStatus.ERROR) {
                                    count -= pc.getCount();
                                }
                            }
                        }

                        if (count > 0) {
                            GetPayment gp = new GetPayment();
                            gp.setCount(count);
                            gp.setId(vj.getPayment().getId());
                            gp.setProcName(vj.getProcedure().getName());
                            Integer time = vj.getTime();
                            Integer truncTime = (int) (time / 60);
                            String hour = String.valueOf(truncTime);
                            String min = String.valueOf((int) (60 * (time / 60 - truncTime)));
                            if (min.length() < 2) {
                                min = "0" + min;
                            }
                            if (hour.length() < 2) {
                                hour = "0" + hour;
                            }
                            java.sql.Date date = vj.getDate();
                            String timedata = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getYear() + " " + hour + ":" + min;
                            gp.setTimedata(timedata);
                            lgp.add(gp);
                        }

                    }
                }
            }
        }
        return Response.ok(lgp).build();
    }
    
    @POST
    @Path("/get/paymentcomplite")
    public Response paymentcomplite() {
        List<GetPayment> lgp = new ArrayList();
        
        List<PaymentComplite> lpc = PaymentComplite.find("paymentstatus", "NEW").list();
        for (PaymentComplite pc: lpc) {
            GetPayment gp = new GetPayment();
            gp.setId(pc.getId());
            gp.setCount(pc.getCount());
            Calendar c = pc.getCalendar();
            String timedata = c.get(Calendar.DAY_OF_MONTH) + "-" + c.get(Calendar.MONTH) + "-" 
                    + c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
            gp.setTimedata(timedata);
            lgp.add(gp);
        }
        
        return Response.ok(lgp).build();
    }
    
    /*
    
    
      id: number;
  timedata: string;
  count: number;
    */

    @POST
    @Path("/get/proceduredoctro")
    public Response proceduredoctro(String name) {
        //Doctor d = Doctor.find("vkId", vkid).firstResult();
        Procedure p = Procedure.find("name", name).firstResult();
        List<Doctor> ld = p.getDoctor();

        List<Integer> nd = new ArrayList();
        ld.forEach((el) -> {
            if (el.getDocStatus() == DocumentStatus.ALIVE) {
                nd.add(el.getVkId());
            }
        });

        return Response.ok(nd).build();
    }

    @POST
    @Path("/get/timeprocedure")
    public Response timeprocedure(TimeProcedure tp) {
        Set<Integer> times = new HashSet();
        Integer startTime = 8 * 60;
        Date d = new Date();
        if ((d.getDate() == tp.getDay()) && (d.getMonth() == tp.getMonth()) && ((1900 + d.getYear()) == tp.getYear())) {
            startTime = (d.getHours() + 1) * 60;
        }

        while (startTime <= WOTK_TIME * 60) {
            //while (startTime <= 17 * 60) {
            times.add(startTime);
            startTime += 15;
        }

        java.sql.Date selectDate = new java.sql.Date(tp.getYear(), tp.getMonth(), tp.getDay());

        List<VisitJournal> vjl = VisitJournal.find("date", selectDate).list();

        for (VisitJournal vj : vjl) {
            if ((Objects.equals(vj.getDoctor().getVkId(), tp.getDoctorVkId()))
                    || (Objects.equals(vj.getTherapyJournal().getPacient().getVkId(), tp.getPacientVkId()))) {
                Integer len = vj.getProcedure().getTime();
                Integer time = vj.getTime();
                while (len >= 0) {
                    len -= 15;
                    times.remove(time + len);
                }
            }

        }

        Procedure pr = Procedure.find("name", tp.getProcedureName()).firstResult();
        Integer fulllen = pr.getTime();
        Integer len = fulllen;
        while (len > 0) {
            times.remove(WOTK_TIME * 60 - fulllen + len);
            len -= 15;
        }

        for (Integer time : times) {
            boolean valid = true;
            Integer lenn = pr.getTime();
            while (len >= 0) {
                len -= 15;
                if (!times.contains(time + len)) {
                    valid = false;
                }
            }
            if (valid == false) {
                while (len >= 0) {
                    len -= 15;
                    times.remove(time + len);
                }
            }

        }

        return Response.ok(times).build();
    }

    @POST
    @Path("/get/dayvisit")
    public Response dayvisit(Integer vkid) {

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        date.setYear(date.getYear() + 1900);

        List<VisitJournal> vjl = VisitJournal.find("date", date).list();
        List<DayVisit> dvl = new ArrayList();

        for (VisitJournal vj : vjl) {
            if ((vj.getDoctor().getVkId().intValue() == vkid) && (vj.getStatus() == JournalStatus.APPOINTMENT)) {
                DayVisit dv = new DayVisit();
                dv.setProcName(vj.getProcedure().getName());
                dv.setPacientVkId(vj.getTherapyJournal().getPacient().getVkId());
                dv.setTime(vj.getTime());
                dv.setNumProc(vj.getNumber());
                dvl.add(dv);
            }
        }

        return Response.ok(dvl).build();
    }

    @POST
    @Path("/get/pacientjournal")
    public Response pacientjournal(GetPacientJournal gpj) {

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        date.setYear(date.getYear() + 1900);

        //Doctor d = Doctor.find("vkId", uv.getDoctorVkId()).firstResult();
        List<VisitJournal> vjl = VisitJournal.find("date = ?1 and time = ?2 and number = ?3", date, gpj.getTime(), gpj.getNumProc()).list();

        List<RecivePacientJournalElement> lrpje = new ArrayList();

        for (VisitJournal vj : vjl) {
            if (Objects.equals(vj.getDoctor().getVkId(), gpj.getDoctorVkId())) {
                List<VisitJournal> cvjl = vj.getTherapyJournal().getVisitJournal();
                for (VisitJournal cvj : cvjl) {
                    RecivePacientJournalElement rpje = new RecivePacientJournalElement();
                    rpje.setNumProc(cvj.getNumber());
                    if (cvj.getProcedure() != null) {
                        rpje.setNameProcedure(cvj.getProcedure().getName());
                    }
                    rpje.setHoldTime(cvj.getNotLater());
                    rpje.setStatus(cvj.getStatus());
                    if (cvj.getCommentary() != null) {
                        rpje.setComment(cvj.getCommentary());
                    } else {
                        rpje.setComment("");
                    }
                    if (cvj.getDoctor() != null) {
                        rpje.setDoctorVkId(cvj.getDoctor().getVkId());
                    }

                    if ((cvj.getDate() != null) && (cvj.getTime() != null)) {
                        rpje.setDay(date.getDate());
                        rpje.setMonth(date.getMonth());
                        rpje.setYear(date.getYear());
                        rpje.setTime(cvj.getTime());
                    }
                    lrpje.add(rpje);
                }
            }
        }

        return Response.ok(lrpje).build();
    }

    @POST
    @Path("/get/therapypacient")
    public Response therapypacient(Integer vkid) {

        Pacient p = Pacient.find("vkId", vkid).firstResult();
        List<TherapyJournal> ltj = p.getTherapyJournal();

        List<TherapyElement> lte = new ArrayList();

        for (TherapyJournal tj : ltj) {
            TherapyElement te = new TherapyElement();
            te.setStatus(tj.getStatus());
            te.setName(tj.getFirstProcName());
            java.sql.Date date = tj.getDate();
            te.setDay(date.getDate());
            te.setMonth(date.getMonth());
            te.setYear(date.getYear());
            te.setTime(tj.getTime());
            lte.add(te);
        }

        return Response.ok(lte).build();
    }

    @POST
    @Path("/get/visitspacient")
    public Response visitspacient(GetVisitsPacient gvp) {

        java.sql.Date date = new java.sql.Date(gvp.getYear(), gvp.getMonth(), gvp.getDay());

        List<TherapyJournal> ltj = TherapyJournal.find("date = ?1 and time = ?2", date, gvp.getTime()).list();
        List<RecivePacientJournalElement> lrpje = new ArrayList();

        for (TherapyJournal tj : ltj) {
            if (Objects.equals(tj.getPacient().getVkId(), gvp.getVkId())) {
                List<VisitJournal> cvjl = tj.getVisitJournal();
                for (VisitJournal cvj : cvjl) {
                    RecivePacientJournalElement rpje = new RecivePacientJournalElement();
                    rpje.setNumProc(cvj.getNumber());
                    if (cvj.getProcedure() != null) {
                        rpje.setNameProcedure(cvj.getProcedure().getName());
                    }
                    rpje.setHoldTime(cvj.getNotLater());
                    rpje.setStatus(cvj.getStatus());
                    if (cvj.getCommentary() != null) {
                        rpje.setComment(cvj.getCommentary());
                    } else {
                        rpje.setComment("");
                    }
                    if (cvj.getDoctor() != null) {
                        rpje.setDoctorVkId(cvj.getDoctor().getVkId());
                    }

                    if ((cvj.getDate() != null) && (cvj.getTime() != null)) {
                        rpje.setDay(date.getDate());
                        rpje.setMonth(date.getMonth());
                        rpje.setYear(date.getYear());
                        rpje.setTime(cvj.getTime());
                    }

                    lrpje.add(rpje);
                }
            }
        }

        return Response.ok(lrpje).build();
    }

    @POST
    @Path("/get/actualprocedure")
    public Response actualprocedure(Integer vkid) {

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        date.setYear(date.getYear() + 1900);

        List<VisitJournal> lvj = VisitJournal.find("status", JournalStatus.APPOINTMENT).list();
        List<ActualVisitComponent> lavc = new ArrayList();

        for (VisitJournal vj : lvj) {
            if (Objects.equals(vj.getTherapyJournal().getPacient().getVkId(), vkid)) {
                ActualVisitComponent avc = new ActualVisitComponent();
                avc.setName(vj.getProcedure().getName());
                java.sql.Date date1 = vj.getDate();
                avc.setDay(date1.getDate());
                avc.setMonth(date1.getMonth());
                avc.setYear(date1.getYear());
                avc.setTime(vj.getTime());
                avc.setVkid(vj.getDoctor().getVkId());
                lavc.add(avc);
            }
        }

        return Response.ok(lavc).build();
    }
}
