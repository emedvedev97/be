/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.scheduler;

/**
 *
 * @author emedvedev
 */
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import javax.enterprise.context.ApplicationScoped;

import io.quarkus.scheduler.Scheduled;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import ru.rsatu.db.entities.Doctor;
import ru.rsatu.enums.DocumentStatus;
import ru.rsatu.scheduler.pojo.ParsePojo;

@ApplicationScoped
public class MembersUpdate {
    private static final String CODE = "";
    private static final int GROUP_ID = 190294002;
    private static final String REQUEST_STRING = "return API.groups.getMembers({group_id: 190294002, filter : \"managers\"});";
    
    VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
    GroupActor actor = new GroupActor(GROUP_ID, CODE);
    

    @Transactional
    void baseUpdate(ParsePojo ps) {
        //Set<Integer> ids = getSet(ps);
        Map<Integer,Integer> doctors = getMap(ps);
        
        PanacheQuery<Doctor> allDoctor = Doctor.findAll();
        PanacheQuery<Doctor> pageDoctor = allDoctor.firstPage();
        boolean nextPage = false;
        do {
            if (pageDoctor.count() > 0) {
                List<Doctor> ld = pageDoctor.list();
                for(Doctor d : ld) {
                    Integer vkid = d.getVkId();
                    //if (!ids.contains(d.getVkId())) {
                    if (!doctors.containsKey(vkid)) {
                        d.setDocStatus(DocumentStatus.DELETED);
                        d.setAdminLevel(0);
                    } else {
                        d.setDocStatus(DocumentStatus.ALIVE);
                        d.setAdminLevel(doctors.get(vkid));
                    }
                    d.persist();
                    doctors.remove(vkid);
                }
            }
            
            if (pageDoctor.hasNextPage()) {
                nextPage = true;
                pageDoctor = pageDoctor.nextPage();
            } else {
                nextPage = false;
            }
        } while (nextPage);
        
        if (!doctors.isEmpty()) {
            doctors.entrySet().stream().map((doctor) -> {
                Doctor d = new Doctor();
                d.setDocStatus(DocumentStatus.ALIVE);
                d.setVkId(doctor.getKey());
                d.setAdminLevel(doctor.getValue());
                return d;
            }).forEachOrdered((d) -> {
                d.persist();
            });
        }
    }

    
    private Map<Integer,Integer> getMap(ParsePojo ps) {
        Map<Integer,Integer> doctors = new HashMap();
        ps.getItems().forEach((el) -> {
            String role = el.getRole();
            Integer adminLevel = 0;
            
            switch (role) {
                case "creator":
                    adminLevel = 4;
                    break;
                case "administrator":
                    adminLevel = 3;
                    break;
                case "editor":
                    adminLevel = 2;
                    break;
                case "moderator":
                    adminLevel = 1;
                    break;      
            }
            
            doctors.put(el.getId(), adminLevel);
        });
        return doctors;
    }
    
    @Scheduled(every="1m")
    public void increase() {
        try {
            JsonElement response = vk.execute().code(actor, REQUEST_STRING).execute();
            ParsePojo pojo = new Gson().fromJson(response.toString(), ParsePojo.class);
            baseUpdate(pojo);
        } catch (ApiException | ClientException ex) {
            Logger.getLogger(MembersUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
} 