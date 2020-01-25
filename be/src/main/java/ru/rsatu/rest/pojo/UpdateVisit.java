/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest.pojo;

/**
 *
 * @author emedvedev
 */
public class UpdateVisit {
    private Integer doctorVkId;
    private Integer time;
    private Integer numProc;
    private String commentary;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDoctorVkId() {
        return doctorVkId;
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

    public void setNumProc(Integer numProc) {
        this.numProc = numProc;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}


/*

doctorVkId: this.vkacoount.id,
        procedure: this.selected.procedure,
        time: this.selected.time,
        numProc: this.selected.numProc



*/
