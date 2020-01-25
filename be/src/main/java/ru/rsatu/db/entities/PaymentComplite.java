/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.io.Serializable;
import java.util.Calendar;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import ru.rsatu.enums.PaymentStatus;

/**
 *
 * @author emedvedev
 */
@Entity
public class PaymentComplite extends PanacheEntity implements Serializable {

    //@Id
    //@GeneratedValue
    //private Long id;
    
    @Column(nullable = false)
    private Integer count;

    @ManyToOne(optional=false)
    @JsonbTransient
    private Payment payment;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonbTransient
    private PaymentStatus paymentStatus;
    
    @Column(nullable = false)
    private Calendar calendar;

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
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

    public Payment getPayment() {
        return payment;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    @Override
    public int hashCode() {
        return 14;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PaymentComplite other = (PaymentComplite) obj;
        return id != null && id.equals(other.getId());
    }
}
