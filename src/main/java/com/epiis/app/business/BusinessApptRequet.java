/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.business;

import com.epiis.app.dataaccess.query.QAppt;
import com.epiis.app.dataaccess.query.QApptRequest;
import com.epiis.app.dataaccess.query.QSchedule;
import com.epiis.app.dto.DtoApptRequest;
import com.epiis.app.dto.DtoEmployee;
import com.epiis.app.dto.DtoPatient;
import com.epiis.app.repository.RepoApptRequest;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author LIZ
 */
public class BusinessApptRequet {
    DtoApptRequest dtoApptRequest =  null;

    public BusinessApptRequet() {
        this.dtoApptRequest = new DtoApptRequest();
    }
    
    public boolean insert(DtoPatient dtoPatient, DtoEmployee dtoEmployee, Date apptDate, Time apptTime, String reason, String status, String type) throws SQLException {

        RepoApptRequest repoApptRequest = new QApptRequest();

        if (dtoPatient == null || dtoPatient.getIdPatient() == null) {
            throw new IllegalArgumentException("El paciente no es válido.");
        }

        if (dtoEmployee == null || dtoEmployee.getIdEmployee() == null) {
            throw new IllegalArgumentException("El médico no es válido.");
        }

        if (apptDate == null) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }

        if (apptTime == null) {
            throw new IllegalArgumentException("La hora no puede estar vacía.");
        }

        if (reason == null || reason.isEmpty()) {
            throw new IllegalArgumentException("Debe especificar el motivo de la cita.");
        }

        LocalDate fecha = apptDate.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();

        if (fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser anterior a hoy.");
        }

        if (fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser anterior a hoy.");
        }

        LocalTime hora = apptTime.toLocalTime();
        QSchedule qSchedule = new QSchedule();
        boolean horarioValido = qSchedule.isInSchedule(
                dtoEmployee.getIdEmployee(), fecha, hora
        );

        if (!horarioValido) {
            throw new IllegalArgumentException("El médico NO atiende en ese horario.");
        }

        QAppt qAppt = new QAppt();
        if (qAppt.existsAppointment(dtoEmployee.getIdEmployee(), fecha, hora)) {
            throw new IllegalArgumentException("El médico ya tiene una cita en ese horario.");
        }

        if (qAppt.existsAppointmentForPatient(dtoPatient.getIdPatient(), fecha, hora)) {
            throw new IllegalArgumentException("El paciente ya tiene una cita a esa hora.");
        }


        this.dtoApptRequest.setIdRequest(UUID.randomUUID().toString());
        this.dtoApptRequest.setDtoPatient(dtoPatient);
        this.dtoApptRequest.setDtoEmployee(dtoEmployee);
        this.dtoApptRequest.setRequestDate(apptDate);
        this.dtoApptRequest.setRequestTime(apptTime);
        this.dtoApptRequest.setReason(reason);
        this.dtoApptRequest.setStatus(status);
        this.dtoApptRequest.setCreatedAt(new Date());
        
        boolean inserted = repoApptRequest.insert(dtoApptRequest) > 0;

        if (inserted) {
            BusinessSchedule businessSchedule = new BusinessSchedule();
            businessSchedule.markHourBusy(
                    dtoEmployee.getIdEmployee(), 
                    apptDate, 
                    apptTime
            );
        }

        return inserted;
    }

    public List<DtoApptRequest> getAll() throws SQLException{
        RepoApptRequest request = new QApptRequest();
        return request.getAll();
    }
    
    public List<DtoApptRequest> getAllPatient() throws SQLException{
        RepoApptRequest request = new QApptRequest();
        return request.getAllPatient();
    }
}
