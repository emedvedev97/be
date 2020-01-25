/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import ru.rsatu.enums.JournalStatus;

/**
 *
 * @author emedvedev
 */
@Entity
public class TherapyJournal extends PanacheEntity implements Serializable {

    //@Id
    //@GeneratedValue

    @ManyToOne(optional = false)
    @JsonbTransient
    private Pacient pacient;

    @OneToMany(mappedBy = "therapyJournal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonbTransient
    private List<VisitJournal> visitJournal;

    @Column(nullable = false)
    private java.sql.Date date;

    @Column(nullable = false)
    private Integer time;

    @Enumerated(EnumType.STRING)
    private JournalStatus status;

    @Column(nullable = false)
    private String firstProcName;

    public String getFirstProcName() {
        return firstProcName;
    }

    public void setFirstProcName(String firstProcName) {
        this.firstProcName = firstProcName;
    }

    public JournalStatus getStatus() {
        return status;
    }

    public void setStatus(JournalStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public Integer getTime() {
        return time;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<VisitJournal> getVisitJournal() {
        return visitJournal;
    }

    public void setVisitJournal(List<VisitJournal> visitJournal) {
        this.visitJournal = visitJournal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }
    
    @Override
    public int hashCode() {
        return 16;
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
        TherapyJournal other = (TherapyJournal) obj;
        return id != null && id.equals(other.getId());
    }
}
