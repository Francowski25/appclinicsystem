/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.epiis.app.repository;

import com.epiis.app.dto.DtoSpecialty;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LIZ
 */
public interface RepoSpecialty {
    int insert(DtoSpecialty dtoSpecialty) throws SQLException;
    DtoSpecialty getByName(String name)throws SQLException;
    List<DtoSpecialty> getAll() throws SQLException;
    int delete(String idSpecialty) throws SQLException;
}
