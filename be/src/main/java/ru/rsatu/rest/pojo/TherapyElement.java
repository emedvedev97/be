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
public class TherapyElement {
    private JournalStatus status;
    private String name;
    private Integer day;
    private Integer month;
    private Integer year;
    private Integer time;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getMonth() {
        return month;
    }

    public JournalStatus getStatus() {
        return status;
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

    public void setStatus(JournalStatus status) {
        this.status = status;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
