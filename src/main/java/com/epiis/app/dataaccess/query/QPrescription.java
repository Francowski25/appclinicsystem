/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.query;

import com.epiis.app.connection.DbConnection;
import com.epiis.app.dataaccess.entity.EntityPrescription;
import com.epiis.app.dto.DtoPrescription;
import com.epiis.app.repository.RepoPrescription;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author LIZ
 */
public class QPrescription implements RepoPrescription {
    private DbConnection dbc = null;
    private String script = null;
    private EntityPrescription entityPrescription = null;
    private int rowsQuantityAfected = 0;
    
    @Override
    public int insert(DtoPrescription dtoPrescription) throws SQLException {
                this.dbc = new DbConnection();

        this.script = "INSERT INTO tprescription VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);

        this.entityPrescription = new EntityPrescription();

        entityPrescription.setIdPrescription(dtoPrescription.getIdPrescription());
        entityPrescription.setMedicine(dtoPrescription.getMedicine());
        entityPrescription.setDosage(dtoPrescription.getDosage());
        entityPrescription.setFrequency(dtoPrescription.getFrequency());
        entityPrescription.setDuration(dtoPrescription.getDuration());
        entityPrescription.setInstructions(dtoPrescription.getInstructions());

        ps.setString(1, entityPrescription.getIdPrescription());
        ps.setString(2, entityPrescription.getMedicine());
        ps.setString(3, entityPrescription.getDosage());
        ps.setString(4, entityPrescription.getFrequency());
        ps.setString(5, entityPrescription.getDuration());
        ps.setString(6, entityPrescription.getInstructions());

        this.rowsQuantityAfected = ps.executeUpdate();
        this.dbc.connection.close();
        return rowsQuantityAfected;
    }
    
}
