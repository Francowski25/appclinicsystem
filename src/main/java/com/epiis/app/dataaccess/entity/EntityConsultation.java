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
public class EntityConsultation {
    private String idConsultation;
    private EntityAppt entityAppt;
    private EntityEmployee entityEmployee;
    private EntityPrescription entityPrescription;
    private Date createdAt;
    private String diagnosis;
    private String treatment;

    public String getIdConsultation() {
        return idConsultation;
    }

    public EntityAppt getEntityAppt() {
        return entityAppt;
    }

    public EntityEmployee getEntityEmployee() {
        return entityEmployee;
    }

    public EntityPrescription getEntityPrescription() {
        return entityPrescription;
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

    public void setEntityAppt(EntityAppt entityAppt) {
        this.entityAppt = entityAppt;
    }

    public void setEntityEmployee(EntityEmployee entityEmployee) {
        this.entityEmployee = entityEmployee;
    }

    public void setEntityPrescription(EntityPrescription entityPrescription) {
        this.entityPrescription = entityPrescription;
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
