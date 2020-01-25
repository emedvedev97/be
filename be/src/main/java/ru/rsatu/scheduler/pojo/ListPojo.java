/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rsatu.scheduler.pojo;

import java.util.List;

/**
 *
 * @author emedvedev
 */
public class ListPojo {
    private Integer id;
    private String role;
    private List<String> permissions;

    public Integer getId() {
        return id;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public String getRole() {
        return role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
