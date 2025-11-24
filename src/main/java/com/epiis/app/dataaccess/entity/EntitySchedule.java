/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.entity;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author jhona
 */

public class EntitySchedule {
    private String idSchedule;
    private EntityEmployee entityEmployee;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;

    public String getIdSchedule() {
        return idSchedule;
    }

    public EntityEmployee getEntityEmployee() {
        return entityEmployee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setIdSchedule(String idSchedule) {
        this.idSchedule = idSchedule;
    }

    public void setEntityEmployee(EntityEmployee entityEmployee) {
        this.entityEmployee = entityEmployee;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    
}
