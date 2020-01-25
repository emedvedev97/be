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
public class SetProcedure {
    private String name;
    private String description;
    private Integer time;
    private float price;
    private String groupProcedure;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGroupProcedure(String groupProcedure) {
        this.groupProcedure = groupProcedure;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getGroupProcedure() {
        return groupProcedure;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public Integer getTime() {
        return time;
    }
}

/*

 name: this.selected.value,
      description: this.procedure.get('description').value,
      price: this.procedure.get('price').value,
      time: this.procedure.get('time').value,
      groupProcedure: this.procedure.get('group').value


*/