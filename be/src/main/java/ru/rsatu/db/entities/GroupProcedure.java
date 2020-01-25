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
import javax.persistence.OneToMany;

/**
 *
 * @author emedvedev
 */

@Entity
public class GroupProcedure extends PanacheEntity implements Serializable {
    @Column(nullable = false, unique = true)
    private String name;
    
    @OneToMany(mappedBy = "groupProcedure", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    @JsonbTransient
    private List<Procedure> procedure;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Procedure> getProcedure() {
        return procedure;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProcedure(List<Procedure> procedure) {
        this.procedure = procedure;
    }
    
    @Override
    public int hashCode() {
        return 12;
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
        GroupProcedure other = (GroupProcedure) obj;
        return id != null && id.equals(other.getId());
    }
}
