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
public class NextVisit {

    private Integer doctorVkId;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer time;
    private Integer pacientVkId;
    private String procedureName;
    private Integer numProc;
    private Integer tjyear;
    private Integer tjmonth;
    private Integer tjday;
    private Integer tjtime;

    public void setTjday(Integer tjday) {
        this.tjday = tjday;
    }

    public void setTjmonth(Integer tjmonth) {
        this.tjmonth = tjmonth;
    }

    public void setTjtime(Integer tjtime) {
        this.tjtime = tjtime;
    }

    public void setTjyear(Integer tjyear) {
        this.tjyear = tjyear;
    }
    
    

    public Integer getTjday() {
        return tjday;
    }

    public Integer getTjmonth() {
        return tjmonth;
    }

    public Integer getTjtime() {
        return tjtime;
    }

    public Integer getTjyear() {
        return tjyear;
    }
    
    public Integer getNumProc() {
        return numProc;
    }

    public void setNumProc(Integer numProc) {
        this.numProc = numProc;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getDoctorVkId() {
        return doctorVkId;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getPacientVkId() {
        return pacientVkId;
    }

    public String getProcedureName() {
        return procedureName;
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

    public void setDoctorVkId(Integer doctorVkId) {
        this.doctorVkId = doctorVkId;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setPacientVkId(Integer pacientVkId) {
        this.pacientVkId = pacientVkId;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
