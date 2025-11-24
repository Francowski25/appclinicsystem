/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dto;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author LAB306PC01
 */
public class DtoApptRequest {
    private String idRequest;
    private DtoPatient dtoPatient;
    private DtoEmployee dtoEmployee;
    private Date requestDate;
    private Time requestTime;
    private String reason;
    private String status;
    private Date createdAt;

    public String getIdRequest() {
        return idRequest;
    }

    public DtoPatient getDtoPatient() {
        return dtoPatient;
    }

    public DtoEmployee getDtoEmployee() {
        return dtoEmployee;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Time getRequestTime() {
        return requestTime;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setIdRequest(String idRequest) {
        this.idRequest = idRequest;
    }

    public void setDtoPatient(DtoPatient dtoPatient) {
        this.dtoPatient = dtoPatient;
    }

    public void setDtoEmployee(DtoEmployee dtoEmployee) {
        this.dtoEmployee = dtoEmployee;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public void setRequestTime(Time requestTime) {
        this.requestTime = requestTime;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
