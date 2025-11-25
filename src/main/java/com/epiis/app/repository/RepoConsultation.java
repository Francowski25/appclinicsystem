/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.epiis.app.repository;

import com.epiis.app.dto.DtoConsultation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LIZ
 */
public interface RepoConsultation {
    int insert(DtoConsultation dtoConsultation) throws SQLException;
    List<DtoConsultation> getAll() throws SQLException;
    List<DtoConsultation> getByPatient(String idPatient) throws SQLException;
}
