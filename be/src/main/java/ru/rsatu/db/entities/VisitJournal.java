/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import ru.rsatu.enums.JournalStatus;

/**
 *
 * @author emedvedev
 */
@Entity
public class VisitJournal extends PanacheEntity implements Serializable {

    //@Id
    //@GeneratedValue

    @ManyToOne(optional = true)
    @JsonbTransient
    private Doctor doctor;

    @ManyToOne(optional = false)
    @JsonbTransient
    private TherapyJournal therapyJournal;

    @ManyToOne(optional = false)
    @JsonbTransient
    private Procedure procedure;

    private java.sql.Date date;

    //@Column(nullable = false)
    private Integer time;

    @Enumerated(EnumType.STRING)
    private JournalStatus status;

    @Column(columnDefinition = "TEXT")
    private String commentary;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Integer notLater;

    @OneToOne(mappedBy = "visitJournal", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = false, optional = true)
    @JsonbTransient
    private Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public TherapyJournal getTherapyJournal() {
        return therapyJournal;
    }

    public void setTherapyJournal(TherapyJournal therapyJournal) {
        this.therapyJournal = therapyJournal;
    }

    public String getCommentary() {
        return commentary;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public Integer getNotLater() {
        return notLater;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public void setNotLater(Integer notLater) {
        this.notLater = notLater;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public JournalStatus getStatus() {
        return status;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setStatus(JournalStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return 17;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        VisitJournal other = (VisitJournal) obj;
        return id != null && id.equals(other.getId());
    }
}
