/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.rest.pojo;

import ru.rsatu.enums.JournalStatus;

/**
 *
 * @author emedvedev
 */
public class SetPacientTherapyElement {
    private JournalStatus status;
    private Integer num;
    private Integer id;
    private String name;
    private Integer hold;

    public Integer getHold() {
        return hold;
    }

    public void setHold(Integer hold) {
        this.hold = hold;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNum() {
        return num;
    }

    public JournalStatus getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setStatus(JournalStatus status) {
        this.status = status;
    }
}


/*



  status: string;
  num: number;
  hold: number;
  name: string;
  id: number;


*/