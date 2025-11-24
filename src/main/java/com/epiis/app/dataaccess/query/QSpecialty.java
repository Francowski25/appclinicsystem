/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.query;

import com.epiis.app.connection.DbConnection;
import com.epiis.app.dataaccess.entity.EntitySpecialty;
import com.epiis.app.dto.DtoSpecialty;
import com.epiis.app.repository.RepoSpecialty;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LIZ
 */
public class QSpecialty implements RepoSpecialty {
    private DbConnection dbc = null;
    private EntitySpecialty entitySpecialty = null;    
    private String script = null;
    private int rowsQuantityAfected = 0;
    private List<EntitySpecialty> listSpecialtys = null;
    private List<DtoSpecialty> listDtoSpecialtys = null;
    
    @Override
    public int insert(DtoSpecialty dtoSpecialty) throws SQLException {
        this.dbc = new DbConnection();
        
        this.script = "INSERT INTO tspecialty VALUES (?, ?, ?)";
        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);

        this.entitySpecialty = new EntitySpecialty();
        
        entitySpecialty.setIdSpecialty(dtoSpecialty.getIdSpecialty());
        entitySpecialty.setName(dtoSpecialty.getName());
        entitySpecialty.setDescription(dtoSpecialty.getDescription());

        ps.setString(1, entitySpecialty.getIdSpecialty());
        ps.setString(2, entitySpecialty.getName());
        ps.setString(3, entitySpecialty.getDescription());

        this.rowsQuantityAfected = ps.executeUpdate();
        this.dbc.connection.close();
        return rowsQuantityAfected;
    }

    @Override
    public List<DtoSpecialty> getAll() throws SQLException {
                this.dbc = new DbConnection();

        this.listSpecialtys = new ArrayList<>();
        this.listDtoSpecialtys = new ArrayList<>();

        this.script = "SELECT * FROM tspecialty";

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ResultSet rows = ps.executeQuery();

        while (rows.next()) {
            EntitySpecialty specialtyTemp = new EntitySpecialty();

            specialtyTemp.setIdSpecialty(rows.getString("idSpecialty"));
            specialtyTemp.setName(rows.getString("name"));
            specialtyTemp.setDescription(rows.getString("description"));

            this.listSpecialtys.add(specialtyTemp);
        }

        for (EntitySpecialty item : this.listSpecialtys) {
            DtoSpecialty dtoSpecialtyTemp = new DtoSpecialty();

            dtoSpecialtyTemp.setIdSpecialty(item.getIdSpecialty());
            dtoSpecialtyTemp.setName(item.getName());
            dtoSpecialtyTemp.setDescription(item.getDescription());

            this.listDtoSpecialtys.add(dtoSpecialtyTemp);
        }

        this.dbc.connection.close();
        return this.listDtoSpecialtys;
    }

    @Override
    public int delete(String idSpecialty) throws SQLException {
        this.dbc = new DbConnection();
        this.script = "DELETE FROM tspecialty WHERE idSpecialty = ?";

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ps.setString(1, idSpecialty);

        this.rowsQuantityAfected = ps.executeUpdate();
        this.dbc.connection.close();
        return this.rowsQuantityAfected;
    }       

    @Override
    public DtoSpecialty getByName(String name) throws SQLException {
        this.dbc = new DbConnection();

        this.script = "SELECT * FROM tspecialty WHERE name = ?";
        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ps.setString(1, name);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DtoSpecialty dto = new DtoSpecialty();
            dto.setIdSpecialty(rs.getString("idSpecialty"));
            dto.setName(rs.getString("name"));
            dto.setDescription(rs.getString("description"));

            this.dbc.connection.close();
            return dto;
        }

        this.dbc.connection.close();
        return null;
    }

}
