/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest.pojo;

import java.util.List;
import ru.rsatu.db.entities.Procedure;

/**
 *
 * @author emedvedev
 */
public class DoctorProcedure {
    private Integer vkid;
    private List<Procedure> procedures;

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public Integer getVkid() {
        return vkid;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }

    public void setVkid(Integer vkid) {
        this.vkid = vkid;
    }
}
