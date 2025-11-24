/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.epiis.app.repository;

import com.epiis.app.dto.DtoDoctorSpecialty;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LIZ
 */
public interface RepoDoctorSpecialty {
    int insert(DtoDoctorSpecialty dtoDoctorSpecialty) throws SQLException;
    List<DtoDoctorSpecialty> getAll() throws SQLException;
}
