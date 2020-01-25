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
public class GetProcedure {
    private String name;
    private String group;
    private Float price;
    private Integer time;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getTime() {
        return time;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}


/*name: "Лечение кариеса"
price: 4000
time: 60
*/