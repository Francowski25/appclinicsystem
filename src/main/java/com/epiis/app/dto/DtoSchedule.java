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
public class DtoSchedule {
    private String idSchedule;
    private DtoEmployee dtoEmployee;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;

    public String getIdSchedule() {
        return idSchedule;
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

    public DtoEmployee getDtoEmployee() {
        return dtoEmployee;
    }

    public void setDtoEmployee(DtoEmployee dtoEmployee) {
        this.dtoEmployee = dtoEmployee;
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
