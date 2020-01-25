/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest.pojo;

import ru.rsatu.enums.JournalStatus;

/**
 *
 * @author emedvedev
 */
public class RecivePacientJournalElement {
    private String nameProcedure;
    private Integer holdTime;
    private Integer numProc;
    private JournalStatus status;
    private String comment;
    private Integer doctorVkId;
    private Integer time;
    private Integer day;
    private Integer month;
    private Integer year;

    public Integer getDay() {
        return day;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getTime() {
        return time;
    }

    public Integer getYear() {
        return year;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
            
    public String getComment() {
        return comment;
    }

    public Integer getDoctorVkId() {
        return doctorVkId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDoctorVkId(Integer doctorVkId) {
        this.doctorVkId = doctorVkId;
    }
    
    public JournalStatus getStatus() {
        return status;
    }

    public void setStatus(JournalStatus status) {
        this.status = status;
    }

    public Integer getHoldTime() {
        return holdTime;
    }

    public String getNameProcedure() {
        return nameProcedure;
    }

    public Integer getNumProc() {
        return numProc;
    }

    public void setHoldTime(Integer holdTime) {
        this.holdTime = holdTime;
    }

    public void setNameProcedure(String nameProcedure) {
        this.nameProcedure = nameProcedure;
    }

    public void setNumProc(Integer numProc) {
        this.numProc = numProc;
    }
}
