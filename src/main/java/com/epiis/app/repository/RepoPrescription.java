/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.epiis.app.repository;

import com.epiis.app.dto.DtoPrescription;
import java.sql.SQLException;

/**
 *
 * @author LIZ
 */
public interface RepoPrescription {
    int insert(DtoPrescription dtoPrescription) throws SQLException;
}
