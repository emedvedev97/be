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
public class GetPayment {

    private Long id;
    private Integer count;
    private String procName;
    private String timedata;

    public Integer getCount() {
        return count;
    }

    public Long getId() {
        return id;
    }

    public String getProcName() {
        return procName;
    }

    public String getTimedata() {
        return timedata;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public void setTimedata(String timedata) {
        this.timedata = timedata;
    }
}
