/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.epiis.app.repository;

import com.epiis.app.dto.DtoApptRequest;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LIZ
 */
public interface RepoApptRequest {
     int insert(DtoApptRequest dtoApptRequest) throws SQLException;
     List<DtoApptRequest> getAll() throws SQLException;
     List<DtoApptRequest> getAllPatient() throws SQLException;

}
