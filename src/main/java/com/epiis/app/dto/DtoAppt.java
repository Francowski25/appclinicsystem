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
public class DtoAppt {
    private String idAppt;
    private DtoPatient dtoPatient;
    private DtoEmployee dtoEmployee;
    private Date apptDate;
    private Date apptTime;
    private String reason;
    private String status;
    private String type;
    private Date createdAt;

    public String getIdAppt() {
        return idAppt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public DtoPatient getDtoPatient() {
        return dtoPatient;
    }

    public DtoEmployee getDtoEmployee() {
        return dtoEmployee;
    }

    public Date getApptDate() {
        return apptDate;
    }

    public Date getApptTime() {
        return apptTime;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public void setIdAppt(String idAppt) {
        this.idAppt = idAppt;
    }

    public void setDtoPatient(DtoPatient dtoPatient) {
        this.dtoPatient = dtoPatient;
    }

    public void setDtoEmployee(DtoEmployee dtoEmployee) {
        this.dtoEmployee = dtoEmployee;
    }

    public void setApptDate(Date apptDate) {
        this.apptDate = apptDate;
    }

    public void setApptTime(Date apptTime) {
        this.apptTime = apptTime;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }


}
