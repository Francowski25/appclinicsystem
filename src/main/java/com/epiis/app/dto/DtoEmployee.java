/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dto;

import java.util.Date;

/**
 *
 * @author LAB306PC01
 */
public class DtoEmployee {
    private String idEmployee;
    private int dni;
    private String firstName;
    private String surNameP;
    private String surNameM;
    private String role;
    private String phone;
    private String email;
    private String password;
    private Date createdAt;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public int getDni() {
        return dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurNameP() {
        return surNameP;
    }

    public String getSurNameM() {
        return surNameM;
    }

    public String getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurNameP(String surNameP) {
        this.surNameP = surNameP;
    }

    public void setSurNameM(String surNameM) {
        this.surNameM = surNameM;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
}
