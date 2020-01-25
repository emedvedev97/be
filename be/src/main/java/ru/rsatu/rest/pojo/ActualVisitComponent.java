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
public class ActualVisitComponent {

    private Integer time;
    private Integer day;
    private Integer month;
    private Integer year;
    private String name;
    private Integer vkid;

    public Integer getVkid() {
        return vkid;
    }

    public void setVkid(Integer vkid) {
        this.vkid = vkid;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getMonth() {
        return month;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
