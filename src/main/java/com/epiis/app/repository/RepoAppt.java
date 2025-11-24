/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.epiis.app.repository;

import com.epiis.app.dto.DtoAppt;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author LIZ
 */
public interface RepoAppt {
    int insert(DtoAppt dtoAppt) throws SQLException;
    List<DtoAppt> getAll() throws SQLException;
    List<DtoAppt> getAllPatient() throws SQLException;
    boolean existsAppointmentForPatient(String idPatient, LocalDate date, LocalTime time) throws SQLException;
    boolean existsAppointment(String idEmployee, LocalDate date, LocalTime time) throws SQLException;
}
