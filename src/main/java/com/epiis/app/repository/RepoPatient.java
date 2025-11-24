/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.epiis.app.repository;

import com.epiis.app.dto.DtoPatient;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LIZ
 */
public interface RepoPatient {
    DtoPatient getByEmailAndPasssword(String email, String password) throws SQLException;
    int insert(DtoPatient dtoPatient) throws SQLException;
    int update(DtoPatient dtoPatient) throws SQLException;
    DtoPatient getByDni(int dni) throws SQLException;
    List<DtoPatient> getAll() throws SQLException;
}
