/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest.api;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.rsatu.db.entities.Doctor;
import ru.rsatu.db.entities.Pacient;

/**
 *
 * @author emedvedev
 */
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JoinMember {
    
    @POST
    @Path("/join/user")
    @Transactional
    public Response user(Pacient data) {
        Pacient p = Pacient.find("vkId", data.getVkId()).firstResult();
        if (p == null) {
            //data.setDocStatus(DocumentStatus.ALIVE);
            data.persist();
        }
        //System.out.println("ITS WORK USER");
        return Response.ok().build();
    }
    
    /*
    
    @POST
    @Path("/join/admin")
    @Transactional
    public Response admin(Doctor data) {
        Doctor d = Doctor.find("vkId", data.getVkId()).firstResult();
        if (d == null) {
            data.setDocStatus(DocumentStatus.ALIVE);
            data.persist();
        } else if (d.getDocStatus() == DocumentStatus.DELETED){
            d.setDocStatus(DocumentStatus.ALIVE);
            d.persist();
        }
        EntityManager em;
        //System.out.println("ITS WORK ADMIN");
        return Response.ok().build();
    }
    */
    @POST
    @Path("/join/admin")
    public Response admin(Integer vkid) {
        Integer adminLevel = 0;
        if (vkid != null) {
            Doctor d = Doctor.find("vkId", vkid).firstResult();
            if (d != null) {
                adminLevel = d.getAdminLevel();
            }
        }
        return Response.ok(adminLevel).build();
    }
    
    
}

