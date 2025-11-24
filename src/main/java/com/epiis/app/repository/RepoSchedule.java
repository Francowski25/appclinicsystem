/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.epiis.app.repository;

import com.epiis.app.dto.DtoSchedule;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author LIZ
 */
public interface RepoSchedule {
    int insert(DtoSchedule dtoSchedule) throws SQLException;
    DtoSchedule getLastByEmployee(String idEmployee) throws SQLException;
    boolean isInSchedule(String idEmployee, LocalDate date, LocalTime time) throws SQLException;
    List<DtoSchedule> getAll() throws SQLException;
}
