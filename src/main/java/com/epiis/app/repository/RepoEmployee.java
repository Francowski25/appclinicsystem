/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.epiis.app.repository;

import com.epiis.app.dto.DtoEmployee;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LIZ
 */
public interface RepoEmployee {
    DtoEmployee getByEmailAndPassword(String email, String password) throws SQLException;
    int insert(DtoEmployee dtoEmployee) throws SQLException;
    int update(DtoEmployee dtoEmployee) throws SQLException;
    DtoEmployee getByDni(int dni) throws SQLException;
    DtoEmployee getByFirstName(String firstName) throws SQLException;
    List<DtoEmployee> getAll() throws SQLException;
}
