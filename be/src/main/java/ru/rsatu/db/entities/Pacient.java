/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author emedvedev
 */
@Entity
public class Pacient extends PanacheEntity implements Serializable {
    //@Id
    //@GeneratedValue
    //private Long id;
    
    @Column(nullable = false, unique = true)
    private Integer vkId;
    
    @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<TherapyJournal> therapyJournal;

    public Long getId() {
        return id;
    }

    public List<TherapyJournal> getTherapyJournal() {
        return therapyJournal;
    }

    public Integer getVkId() {
        return vkId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTherapyJournal(List<TherapyJournal> therapyJournal) {
        this.therapyJournal = therapyJournal;
    }

    public void setVkId(Integer vkId) {
        this.vkId = vkId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(vkId);
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
        Pacient other = (Pacient) obj;
        return Objects.equals(vkId, other.getVkId());
    }
    
}
