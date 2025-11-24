/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.epiis.app.repository;

import com.epiis.app.dto.DtoScheduleDetail;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LIZ
 */
public interface RepoScheduleDetail {
    int insert(DtoScheduleDetail dtoScheduleDetail) throws SQLException;
    int updateStatus(String idDetail, String status) throws SQLException;
    DtoScheduleDetail getByEmployeeDateTime(String idEmployee, Date date, Time time) throws SQLException;
    List<DtoScheduleDetail> getAll() throws SQLException;
    List<String> getFreeHours(String idSchedule, Date date) throws SQLException;
}
