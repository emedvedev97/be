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
public class GetVisitsPacient {
    private Integer vkId;
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

    public Integer getVkId() {
        return vkId;
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

    public void setVkId(Integer vkId) {
        this.vkId = vkId;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}


/*



vkId: this.vkacoount.id,
      time: this.selected.time,
      day: this.selected.day,
      month: this.selected.month,
      year: this.selected.year,
*/