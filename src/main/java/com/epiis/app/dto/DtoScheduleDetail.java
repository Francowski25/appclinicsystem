/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dto;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author LIZ
 */
public class DtoScheduleDetail {
    private String idDetail;
    private DtoSchedule dtoSchedule;
    private Date scheduleDate;
    private Time startTime;
    private Time endTime;
    private String status;

    public String getIdDetail() {
        return idDetail;
    }

    public DtoSchedule getDtoSchedule() {
        return dtoSchedule;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

    public void setDtoSchedule(DtoSchedule dtoSchedule) {
        this.dtoSchedule = dtoSchedule;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
