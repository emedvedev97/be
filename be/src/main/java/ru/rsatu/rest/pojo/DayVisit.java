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
public class DayVisit {
    private Integer pacientVkId;
    private String procName;
    private Integer time;
    private Integer numProc;

    public Integer getNumProc() {
        return numProc;
    }

    public void setNumProc(Integer numProc) {
        this.numProc = numProc;
    }

    public Integer getPacientVkId() {
        return pacientVkId;
    }

    public String getProcName() {
        return procName;
    }

    public Integer getTime() {
        return time;
    }

    public void setPacientVkId(Integer pacientVkId) {
        this.pacientVkId = pacientVkId;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
