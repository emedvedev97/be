/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.scheduler.pojo;

import java.util.List;
import java.util.Set;

/**
 *
 * @author emedvedev
 */
public class ParsePojo {
    private int count;
    private List<ListPojo> items;

    public int getCount() {
        return count;
    }

    public List<ListPojo> getItems() {
        return items;
    }
    
    public void setCount(int count) {
        this.count = count;
    }

    public void setItems(List<ListPojo> items) {
        this.items = items;
    }
}
