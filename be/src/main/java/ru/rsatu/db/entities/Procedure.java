/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author emedvedev
 */
@Entity
public class Procedure extends PanacheEntity implements Serializable {

    //@Id
    //@GeneratedValue
    //private Long id;
    @ManyToMany(mappedBy = "procedure")
    @JsonbTransient
    private List<Doctor> doctor;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private Integer time;

    @JsonbTransient
    @OneToMany(mappedBy = "procedure", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<VisitJournal> visitJournal;

    @ManyToOne(optional = true)
    //@JsonbTransient
    private GroupProcedure groupProcedure;

    public GroupProcedure getGroupProcedure() {
        return groupProcedure;
    }

    public void setGroupProcedure(GroupProcedure groupProcedure) {
        this.groupProcedure = groupProcedure;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Doctor> getDoctor() {
        return doctor;
    }

    public List<VisitJournal> getVisitJournal() {
        return visitJournal;
    }

    public void setDoctor(List<Doctor> doctor) {
        this.doctor = doctor;
    }

    public void setVisitJournal(List<VisitJournal> visitJournal) {
        this.visitJournal = visitJournal;
    }

    @Override
    public int hashCode() {
        return 15;
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
        Procedure other = (Procedure) obj;
        return id != null && id.equals(other.getId());
    }
}
