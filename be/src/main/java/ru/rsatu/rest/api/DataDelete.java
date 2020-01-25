/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest.api;

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
import ru.rsatu.db.entities.Procedure;
import ru.rsatu.db.entities.VisitJournal;
import ru.rsatu.enums.JournalStatus;
import ru.rsatu.rest.pojo.ActualVisitComponent;

/**
 *
 * @author emedvedev
 */

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataDelete {
    
    @POST
    @Path("/delete/procedure")
    @Transactional
    public Response procedures(String name) {
       // if (name.length() > 0) {
        Procedure p = Procedure.find("name", name).firstResult();
        List<Doctor> ld = p.getDoctor();
        for (Doctor d: ld) {
            d.getProcedure().remove(p);
            d.persist();
        }
        
        Long l = Procedure.delete("name", name);
        
        
        return Response.ok(l).build();
      // } else {
       //     return   Response.ok(0).build();
      // }
    }
    
    @POST
    @Path("/delete/visit")
    @Transactional
    public Response visit(ActualVisitComponent avc) {
        java.sql.Date date = new java.sql.Date(avc.getYear(), avc.getMonth(), avc.getDay());

        List<VisitJournal> lvj = VisitJournal.find("date = ?1 and time = ?2", date, avc.getTime()).list();
        
        for (VisitJournal vj : lvj) {
            if (Objects.equals(vj.getTherapyJournal().getPacient().getVkId(), avc.getVkid())) {
                if (vj.getNumber() > 0) {
                    vj.setDate(null);
                    vj.setTime(null);
                    vj.setDoctor(null);
                    vj.setStatus(JournalStatus.AWAIT);
                    vj.persist();
                } else {
                    vj.getTherapyJournal().delete();
                    vj.delete();
                }
            }
        }
        
        return Response.ok(true).build();
      // } else {
       //     return   Response.ok(0).build();
      // }
    }
    
    @POST
    @Path("/delete/groupprocedure")
    @Transactional
    public Response groupprocedure(String name) {
        GroupProcedure gp = GroupProcedure.find("name", name).firstResult();
        gp.delete();
        return Response.ok(true).build();
    }
    
}
