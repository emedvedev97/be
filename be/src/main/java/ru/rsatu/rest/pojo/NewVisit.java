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
public class NewVisit {

    private Integer doctorVkId;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer time;
    private Integer pacientVkId;
    private String procedureName;
    private Integer numProc;

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


/*


doctorVkId: this.selectedDoc.id,
      procedureName: this.selectedProc,
      year: this.thirdFormGroup.get('thirdCtrl').value._i.year,
      month: this.thirdFormGroup.get('thirdCtrl').value._i.month,
      day: this.thirdFormGroup.get('thirdCtrl').value._i.date,
      time: this.fourFormGroup.get('fourCtrl').value.time,
      pacientVkId: this.vkacoount.id


*/
