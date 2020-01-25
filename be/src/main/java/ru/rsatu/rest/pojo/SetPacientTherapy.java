/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest.pojo;

import java.util.List;

/**
 *
 * @author emedvedev
 */
public class SetPacientTherapy {
    
    private Integer doctorVkId;
    private Integer time;
    private Integer numProc;
    
    private List<SetPacientTherapyElement> lspte;

    public Integer getDoctorVkId() {
        return doctorVkId;
    }

    public List<SetPacientTherapyElement> getLspte() {
        return lspte;
    }

    public Integer getNumProc() {
        return numProc;
    }

    public Integer getTime() {
        return time;
    }

    public void setDoctorVkId(Integer doctorVkId) {
        this.doctorVkId = doctorVkId;
    }

    public void setLspte(List<SetPacientTherapyElement> lspte) {
        this.lspte = lspte;
    }

    public void setNumProc(Integer numProc) {
        this.numProc = numProc;
    }

    public void setTime(Integer time) {
        this.time = time;
    }   
}
