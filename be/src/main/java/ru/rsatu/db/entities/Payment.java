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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import ru.rsatu.enums.PaymentStatus;

/**
 *
 * @author emedvedev
 */
@Entity
public class Payment extends PanacheEntity implements Serializable {

    //@Id
    //@GeneratedValue
    //private Long id;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonbTransient
    private PaymentStatus paymentStatus;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    @JsonbTransient
    private List<PaymentComplite> paymentComplite;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonbTransient
    private VisitJournal visitJournal;

    public VisitJournal getVisitJournal() {
        return visitJournal;
    }

    public void setVisitJournal(VisitJournal visitJournal) {
        this.visitJournal = visitJournal;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getCount() {
        return count;
    }

    public Long getId() {
        return id;
    }

    public List<PaymentComplite> getPaymentComplite() {
        return paymentComplite;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPaymentComplite(List<PaymentComplite> paymentComplite) {
        this.paymentComplite = paymentComplite;
    }
    
    
    @Override
    public int hashCode() {
        return 13;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Payment other = (Payment) obj;
        return id != null && id.equals(other.getId());
    }
}
