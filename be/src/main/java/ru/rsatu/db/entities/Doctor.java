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
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import ru.rsatu.enums.DocumentStatus;

/**
 *
 * @author emedvedev
 */
@Entity
public class Doctor extends PanacheEntity implements Serializable {

    @ManyToMany
    @JsonbTransient
    private List<Procedure> procedure;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<VisitJournal> visitJournal;

    @Column(nullable = false, unique = true)
    private Integer vkId;

    @Column(nullable = false)
    private Integer adminLevel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentStatus docStatus;

    private String profession;

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setProcedure(List<Procedure> procedure) {
        this.procedure = procedure;
    }

    public Integer getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(Integer adminLevel) {
        this.adminLevel = adminLevel;
    }

    public DocumentStatus getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(DocumentStatus docStatus) {
        this.docStatus = docStatus;
    }

    public Integer getVkId() {
        return vkId;
    }

    public Long getId() {
        return id;
    }

    public void setVkId(Integer vkId) {
        this.vkId = vkId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Procedure> getProcedure() {
        return procedure;
    }

    public List<VisitJournal> getVisitJournal() {
        return visitJournal;
    }

    public void setVisitJournal(List<VisitJournal> visitJournal) {
        this.visitJournal = visitJournal;
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
        Doctor other = (Doctor) obj;
        return Objects.equals(vkId, other.getVkId());
    }
}
