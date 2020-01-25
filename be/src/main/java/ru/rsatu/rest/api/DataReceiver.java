/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest.api;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.rsatu.db.entities.Doctor;
import ru.rsatu.db.entities.GroupProcedure;
import ru.rsatu.db.entities.Pacient;
import ru.rsatu.db.entities.Payment;
import ru.rsatu.db.entities.PaymentComplite;
import ru.rsatu.db.entities.Procedure;
import ru.rsatu.db.entities.TherapyJournal;
import ru.rsatu.db.entities.VisitJournal;
import ru.rsatu.enums.JournalStatus;
import ru.rsatu.enums.PaymentStatus;
import ru.rsatu.rest.pojo.DoctorProcedure;
import ru.rsatu.rest.pojo.GetPayment;
import ru.rsatu.rest.pojo.NewVisit;
import ru.rsatu.rest.pojo.NextVisit;
import ru.rsatu.rest.pojo.SetPacientTherapy;
import ru.rsatu.rest.pojo.SetPacientTherapyElement;
import ru.rsatu.rest.pojo.SetProcedure;
import ru.rsatu.rest.pojo.UpdateVisit;

/**
 *
 * @author emedvedev
 */
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataReceiver {

    @POST
    @Path("/set/procedure")
    @Transactional
    public Response procedure(SetProcedure proc) {
        Procedure p = Procedure.find("name", proc.getName()).firstResult();
        if (p == null) {
            p = new Procedure();
            p.setName(proc.getName());
        }
        p.setPrice(proc.getPrice());
        p.setTime(proc.getTime());
        p.setDescription(proc.getDescription());
        GroupProcedure gp = GroupProcedure.find("name", proc.getGroupProcedure()).firstResult();
        if (gp != null) {
            p.setGroupProcedure(gp);
        }
        ///System.out.println(p.getGroupProcedure().getName());

        p.persist();
        return Response.ok(true).build();
    }

    @POST
    @Path("/set/groupsprocedures")
    @Transactional
    public Response groupsprocedures(String name) {
        long count = GroupProcedure.find("name", name).count();
        if (count == 0) {
            GroupProcedure gp = new GroupProcedure();
            gp.setName(name);
            gp.persist();
            return Response.ok(true).build();
        } else {
            return Response.ok(false).build();
        }
    }

    @POST
    @Path("/set/payment")
    @Transactional
    public Response payment(List<GetPayment> lgp) {
        //System.out.println("LENGTH" + lgp.size());
        for (GetPayment gp : lgp) {
            Payment p = Payment.findById(gp.getId());
                PaymentComplite pc = new PaymentComplite();
                pc.setCount(gp.getCount());
                pc.setPayment(p);
                pc.setPaymentStatus(PaymentStatus.NEW);
                pc.setCalendar(new GregorianCalendar());
                pc.persist();
                //p.setPaymentStatus(PaymentStatus.PROCESS);
                /*
                List<PaymentComplite> lpc = p.getPaymentComplite();
                Integer sum = 0;
                for (PaymentComplite ppc : lpc) {
                    sum += ppc.getCount();
                }
                if (sum == p.getCount()) p.setPaymentStatus(PaymentStatus.COMPLITED);
                */
                
        }

        return Response.ok().build();
    }
    
    @POST
    @Path("/set/paymentcomplite")
    @Transactional
    public Response paymentcomplite(List<GetPayment> lgp) {
        //System.out.println("LENGTH" + lgp.size());
        for (GetPayment gp : lgp) {
            PaymentComplite pc = PaymentComplite.findById(gp.getId());
            pc.setPaymentStatus(PaymentStatus.COMPLITED);
            pc.persist();
                
        }

        return Response.ok().build();
    }

    @POST
    @Path("/set/doctorprocedure")
    @Transactional
    public Response doctorprocedure(DoctorProcedure dp) {
        Doctor d = Doctor.find("vkId", dp.getVkid()).firstResult();
        List<Procedure> lp = d.getProcedure();
        lp.clear();
        for (Procedure p : dp.getProcedures()) {
            lp.add(Procedure.find("name", p.getName()).firstResult());
        }
        d.setProcedure(lp);
        d.persist();
        return Response.ok(true).build();
    }

    @POST
    @Path("/set/newvisit")
    @Transactional
    public Response newvisit(NewVisit nv) {
        Pacient p = Pacient.find("vkId", nv.getPacientVkId()).firstResult();
        Doctor d = Doctor.find("vkId", nv.getDoctorVkId()).firstResult();
        Procedure pr = Procedure.find("name", nv.getProcedureName()).firstResult();

        if ((nv.getNumProc() == 0) && (p != null) && (d != null) && (pr != null)) {
            TherapyJournal tj = new TherapyJournal();
            tj.setPacient(p);
            tj.setFirstProcName(nv.getProcedureName());
            java.sql.Date selectDate = new java.sql.Date(nv.getYear(), nv.getMonth(), nv.getDay());
            tj.setDate(selectDate);
            tj.setTime(nv.getTime());
            tj.setStatus(JournalStatus.APPOINTMENT);
            VisitJournal vj = new VisitJournal();
            vj.setDate(selectDate);
            vj.setDoctor(d);
            vj.setNumber(0);
            vj.setTime(nv.getTime());
            vj.setProcedure(pr);
            vj.setStatus(JournalStatus.APPOINTMENT);
            vj.setTherapyJournal(tj);
            vj.setNotLater(0);
            tj.persist();
            vj.persist();
        }
        return Response.ok(true).build();
    }

    @POST
    @Path("/set/nextvisit")
    @Transactional
    public Response nextvisit(NextVisit nv) {
        Pacient p = Pacient.find("vkId", nv.getPacientVkId()).firstResult();
        Doctor d = Doctor.find("vkId", nv.getDoctorVkId()).firstResult();
        java.sql.Date date = new java.sql.Date(nv.getTjyear(), nv.getTjmonth(), nv.getTjday());

        List<TherapyJournal> ltj = TherapyJournal.find("date = ?1 and time = ?2", date, nv.getTjtime()).list();
        for (TherapyJournal tj : ltj) {
            if (Objects.equals(tj.getPacient().getVkId(), nv.getPacientVkId())) {
                List<VisitJournal> cvjl = tj.getVisitJournal();
                for (VisitJournal cvj : cvjl) {
                    if (Objects.equals(cvj.getNumber(), nv.getNumProc())) {
                        java.sql.Date date1 = new java.sql.Date(nv.getYear(), nv.getMonth(), nv.getDay());
                        cvj.setDate(date1);
                        cvj.setDoctor(d);
                        cvj.setTime(nv.getTime());
                        cvj.setStatus(JournalStatus.APPOINTMENT);
                        cvj.persist();
                    }
                }
            }
        }
        return Response.ok(true).build();
    }

    @POST
    @Path("/set/updatevisit")
    @Transactional
    public Response updatevisit(UpdateVisit uv) {

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        date.setYear(date.getYear() + 1900);

        //Doctor d = Doctor.find("vkId", uv.getDoctorVkId()).firstResult();
        List<VisitJournal> vjl = VisitJournal.find("date = ?1 and time = ?2 and number = ?3", date, uv.getTime(), uv.getNumProc()).list();

        //List<VisitJournal> vjl = VisitJournal.find("date", date).list();
        for (VisitJournal vj : vjl) {
            if (Objects.equals(vj.getDoctor().getVkId(), uv.getDoctorVkId())) {
                vj.setCommentary(uv.getCommentary());
                vj.setStatus(JournalStatus.COMPLETED);
                TherapyJournal tj = vj.getTherapyJournal();
                if (tj.getVisitJournal().size() <= (vj.getNumber() + 1)) {
                    tj.setStatus(JournalStatus.COMPLETED);
                    tj.persist();
                }
                Payment p = new Payment();
                p.setCount(uv.getCount());
                p.setPaymentStatus(PaymentStatus.NEW);
                p.setVisitJournal(vj);
                p.persist();
                vj.setPayment(p);
                vj.persist();
            }
        }

        return Response.ok(true).build();
    }

    @POST
    @Path("/set/pacienttherapy")
    @Transactional
    public Response pacienttherapy(SetPacientTherapy spt) {

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        date.setYear(date.getYear() + 1900);

        List<SetPacientTherapyElement> lspte = spt.getLspte();

        List<VisitJournal> vjl = VisitJournal.find("date = ?1 and time = ?2 and number = ?3", date, spt.getTime(), spt.getNumProc()).list();

        for (VisitJournal vj : vjl) {
            if (Objects.equals(vj.getDoctor().getVkId(), spt.getDoctorVkId())) {
                TherapyJournal tj = vj.getTherapyJournal();
                //tj.getVisitJournal().clear();

                List<VisitJournal> lcvj = tj.getVisitJournal();

                Iterator<VisitJournal> lcvjIterator = lcvj.iterator();
                while (lcvjIterator.hasNext()) {
                    VisitJournal curVisits = lcvjIterator.next();
                    if (curVisits.getStatus() == JournalStatus.AWAIT) {
                        lcvjIterator.remove();
                    }

                }

                for (SetPacientTherapyElement spte : lspte) {
                    if (spte.getStatus() == JournalStatus.AWAIT) {
                        VisitJournal cvj = new VisitJournal();
                        cvj.setNumber(spte.getNum());
                        Procedure p = Procedure.find("name", spte.getName()).firstResult();
                        cvj.setProcedure(p);
                        cvj.setStatus(spte.getStatus());
                        cvj.setNotLater(spte.getHold());
                        cvj.setTherapyJournal(tj);
                        lcvj.add(cvj);
                    }
                    //System.out.println(spte.getName());
                }

                tj.setVisitJournal(lcvj);
                tj.persist();

            }
        }

        return Response.ok(true).build();
    }

}
