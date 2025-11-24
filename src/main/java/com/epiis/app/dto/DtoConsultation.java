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
public class DtoConsultation {
    private String idConsultation;
    private DtoAppt dtoAppt;
    private DtoEmployee dtoEmployee;
    private DtoPrescription dtoPrescription;
    private Date createdAt;
    private String diagnosis;
    private String treatment;

    public String getIdConsultation() {
        return idConsultation;
    }

    public DtoAppt getDtoAppt() {
        return dtoAppt;
    }

    public DtoEmployee getDtoEmployee() {
        return dtoEmployee;
    }

    public DtoPrescription getDtoPrescription() {
        return dtoPrescription;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setIdConsultation(String idConsultation) {
        this.idConsultation = idConsultation;
    }

    public void setDtoAppt(DtoAppt dtoAppt) {
        this.dtoAppt = dtoAppt;
    }

    public void setDtoEmployee(DtoEmployee dtoEmployee) {
        this.dtoEmployee = dtoEmployee;
    }

    public void setDtoPrescription(DtoPrescription dtoPrescription) {
        this.dtoPrescription = dtoPrescription;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
    
    
}
