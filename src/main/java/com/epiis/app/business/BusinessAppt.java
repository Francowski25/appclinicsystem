/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.business;

import com.epiis.app.dataaccess.query.QAppt;
import com.epiis.app.dataaccess.query.QSchedule;
import com.epiis.app.dto.DtoAppt;
import com.epiis.app.dto.DtoEmployee;
import com.epiis.app.dto.DtoPatient;
import com.epiis.app.repository.RepoAppt;
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
public class BusinessAppt {
    DtoAppt dtoAppt = null;

    public BusinessAppt() {
        this.dtoAppt = new DtoAppt();
    }
    
    public boolean insert(String dni, String medico, Date apptDate, Time apptTime, 
                          String reason, String status, String type) throws SQLException {

        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un DNI.");
        }

        if (medico == null || medico.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionar un médico.");
        }

        if (apptDate == null) {
            throw new IllegalArgumentException("Debe seleccionar una fecha.");
        }

        if (apptTime == null) {
            throw new IllegalArgumentException("Debe seleccionar una hora.");
        }

        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar el motivo de la cita.");
        }

        BusinessPatient businessPatient = new BusinessPatient();
        DtoPatient dtoPatient = businessPatient.getByDni(dni);

        if (dtoPatient == null || dtoPatient.getIdPatient() == null) {
            throw new IllegalArgumentException("El DNI no existe en el sistema.");
        }

        BusinessEmployee businessEmployee = new BusinessEmployee();
        DtoEmployee dtoEmployee = businessEmployee.getByFirstName(medico);

        if (dtoEmployee == null || dtoEmployee.getIdEmployee() == null) {
            throw new IllegalArgumentException("El médico no existe en el sistema.");
        }

        LocalDate fecha = apptDate.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();

        if (fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser anterior a hoy.");
        }

        LocalTime hora = apptTime.toLocalTime();

        QSchedule qSchedule = new QSchedule();
        boolean horarioValido = qSchedule.isInSchedule(
                dtoEmployee.getIdEmployee(), fecha, hora);

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

        RepoAppt repoAppt = new QAppt();

        this.dtoAppt.setIdAppt(UUID.randomUUID().toString());
        this.dtoAppt.setDtoPatient(dtoPatient);
        this.dtoAppt.setDtoEmployee(dtoEmployee);
        this.dtoAppt.setApptDate(apptDate);
        this.dtoAppt.setApptTime(apptTime);
        this.dtoAppt.setReason(reason);
        this.dtoAppt.setStatus(status);
        this.dtoAppt.setType(type);
        this.dtoAppt.setCreatedAt(new Date());

        return repoAppt.insert(dtoAppt) > 0;
    }

    public List<DtoAppt> getAll() throws SQLException{
        RepoAppt repoAppt = new QAppt();
        return repoAppt.getAll();
    }
    
    public List<DtoAppt> getAllPatient() throws SQLException{
        RepoAppt repoAppt = new QAppt();
        return repoAppt.getAllPatient();
    }
}
