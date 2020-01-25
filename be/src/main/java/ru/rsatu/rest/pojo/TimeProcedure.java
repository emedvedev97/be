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
public class TimeProcedure {
    private Integer doctorVkId;
    private String procedureName;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer pacientVkId;

    public Integer getPacientVkId() {
        return pacientVkId;
    }

    public void setPacientVkId(Integer pacientVkId) {
        this.pacientVkId = pacientVkId;
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

    public String getProcedureName() {
        return procedureName;
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

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
