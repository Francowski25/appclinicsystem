/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.business;

import com.epiis.app.dataaccess.query.QSchedule;
import com.epiis.app.dataaccess.query.QScheduleDetail;
import com.epiis.app.dto.DtoEmployee;
import com.epiis.app.dto.DtoSchedule;
import com.epiis.app.dto.DtoScheduleDetail;
import com.epiis.app.repository.RepoSchedule;
import com.epiis.app.repository.RepoScheduleDetail;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author LIZ
 */
public class BusinessSchedule {
    DtoSchedule dtoSchedule = null;
    
    public BusinessSchedule() {
        this.dtoSchedule = new DtoSchedule();
    }
    
    public boolean insert(DtoEmployee employee,
                          Date startDate, Date endDate,
                          Time startTime, Time endTime) throws SQLException {

        RepoSchedule repo = new QSchedule();

        if (employee == null || employee.getIdEmployee() == null) {
            throw new IllegalArgumentException("No se pudo obtener el empleado insertado.");
        }

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Debe seleccionar ambas fechas.");
        }

        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("La fecha final no puede ser menor a la fecha de inicio.");
        }

        if (!startTime.before(endTime)) {
            throw new IllegalArgumentException("La hora de inicio debe ser menor que la hora de fin.");
        }

        this.dtoSchedule.setIdSchedule(UUID.randomUUID().toString());
        this.dtoSchedule.setDtoEmployee(employee);
        this.dtoSchedule.setStartDate(startDate);
        this.dtoSchedule.setEndDate(endDate);
        this.dtoSchedule.setStartTime(startTime);
        this.dtoSchedule.setEndTime(endTime);

        return repo.insert(dtoSchedule) > 0;
    }
    
    public boolean generarIntervalos(DtoSchedule schedule) throws SQLException {

        if (schedule == null || schedule.getIdSchedule() == null) {
            throw new IllegalArgumentException("El horario no existe.");
        }

        Date startDate = schedule.getStartDate();
        Date endDate = schedule.getEndDate();
        Time startTime = schedule.getStartTime();
        Time endTime = schedule.getEndTime();

        RepoScheduleDetail repoDetail = new QScheduleDetail();

        Calendar calDate = Calendar.getInstance();
        calDate.setTime(startDate);

        Calendar calEndDate = Calendar.getInstance();
        calEndDate.setTime(endDate);

        while (!calDate.after(calEndDate)) {

            Date fechaActual = calDate.getTime();

            Calendar calInicio = Calendar.getInstance();
            calInicio.setTime(startTime);

            Calendar calFinHorario = Calendar.getInstance();
            calFinHorario.setTime(endTime);

            while (calInicio.before(calFinHorario)) {

                Time horaInicio = new Time(calInicio.getTimeInMillis());

                calInicio.add(Calendar.HOUR_OF_DAY, 1);
                Time horaFin = new Time(calInicio.getTimeInMillis());

                DtoScheduleDetail detail = new DtoScheduleDetail();
                detail.setIdDetail(UUID.randomUUID().toString());
                detail.setDtoSchedule(schedule);
                detail.setScheduleDate(fechaActual);
                detail.setStartTime(horaInicio);
                detail.setEndTime(horaFin);
                detail.setStatus("libre");

                repoDetail.insert(detail);
            }

            calDate.add(Calendar.DAY_OF_MONTH, 1);
        }

        return true;
    }

    public DtoSchedule getLastByEmployee(String idEmployee) throws SQLException {
        RepoSchedule repoSchedule = new QSchedule();
        return repoSchedule.getLastByEmployee(idEmployee);
    }
    
    public List<String> getFreeHours(String idSchedule, Date date) throws SQLException {
        RepoScheduleDetail repoDetail = new QScheduleDetail();
        return repoDetail.getFreeHours(idSchedule, date);
    }
    
    public boolean markHourBusy(String idEmployee, Date date, Time time) throws SQLException {

        QScheduleDetail repo = new QScheduleDetail();

        DtoScheduleDetail detail = repo.getByEmployeeDateTime(idEmployee, date, time);

        if (detail == null) {
            throw new SQLException("No existe un horario generado para esa fecha y hora.");
        }

        int updated = repo.updateStatus(detail.getIdDetail(), "ocupado");

        return updated > 0;
    }
    

}
