/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.entity;

import java.util.Date;

/**
 *
 * @author jhona
 */
public class EntityAppt {
    private String idAppt;
    private EntityPatient entityPatient;
    private EntityEmployee entityEmployee;
    private Date apptDate;
    private Date apptTime;
    private String reason;
    private String status;
    private String type;
    private Date createdAt;

    public String getIdAppt() {
        return idAppt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public EntityEmployee getEntityEmployee() {
        return entityEmployee;
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

    public void setEntityPatient(EntityPatient entityPatient) {
        this.entityPatient = entityPatient;
    }

    public EntityPatient getEntityPatient() {
        return entityPatient;
    }


    public void setEntityEmployee(EntityEmployee entityEmployee) {
        this.entityEmployee = entityEmployee;
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
