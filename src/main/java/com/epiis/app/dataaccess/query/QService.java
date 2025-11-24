/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.query;

import com.epiis.app.connection.DbConnection;
import com.epiis.app.dataaccess.entity.EntityService;
import com.epiis.app.dataaccess.entity.EntitySpecialty;
import com.epiis.app.dto.DtoService;
import com.epiis.app.dto.DtoSpecialty;
import com.epiis.app.repository.RepoService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LIZ
 */
public class QService implements RepoService {

    private DbConnection dbc = null;
    private EntityService entityService = null;    
    private String script = null;
    private int rowsQuantityAfected = 0;
    private List<EntityService> listServices = null;
    private List<DtoService> listDtoServices = null;
    
    @Override
    public int insert(DtoService dtoService) throws SQLException {
        this.dbc = new DbConnection();

        this.script = "INSERT INTO tservice VALUES (?, ?, ?, ?)";
        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);

        this.entityService = new EntityService();
        entityService.setIdService(dtoService.getIdService());
        entityService.setName(dtoService.getName());
        entityService.setDescription(dtoService.getDescription());

        EntitySpecialty entitySpecialty = new EntitySpecialty();
        entitySpecialty.setIdSpecialty(dtoService.getDtoSpecialty().getIdSpecialty());
        entityService.setEntitySpecialty(entitySpecialty);

        ps.setString(1, entityService.getIdService());
        ps.setString(2, entityService.getEntitySpecialty().getIdSpecialty());
        ps.setString(3, entityService.getName());
        ps.setString(4, entityService.getDescription());

        this.rowsQuantityAfected = ps.executeUpdate();
        this.dbc.connection.close();

        return rowsQuantityAfected;
    }

   @Override
    public List<DtoService> getAll() throws SQLException {
        this.dbc = new DbConnection();

        this.listServices = new ArrayList<>();
        this.listDtoServices = new ArrayList<>();

        this.script = "SELECT * FROM tservice";
        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            EntityService serviceTemp = new EntityService();
            serviceTemp.setIdService(rs.getString("idService"));
            serviceTemp.setName(rs.getString("name"));
            serviceTemp.setDescription(rs.getString("description"));

            EntitySpecialty specialtyTemp = new EntitySpecialty();
            specialtyTemp.setIdSpecialty(rs.getString("idSpecialty"));
            serviceTemp.setEntitySpecialty(specialtyTemp);

            this.listServices.add(serviceTemp);
        }

        for (EntityService entity : this.listServices) {
            DtoService dto = new DtoService();
            dto.setIdService(entity.getIdService());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());

            DtoSpecialty dtoSpecialty = new DtoSpecialty();
            dtoSpecialty.setIdSpecialty(entity.getEntitySpecialty().getIdSpecialty());
            dto.setDtoSpecialty(dtoSpecialty);

            this.listDtoServices.add(dto);
        }

        this.dbc.connection.close();
        return this.listDtoServices;
    }

    @Override
    public int delete(String idService) throws SQLException {
        this.dbc = new DbConnection();
        this.script = "DELETE FROM tservice WHERE idService = ?";

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ps.setString(1, idService);

        this.rowsQuantityAfected = ps.executeUpdate();
        this.dbc.connection.close();
        return this.rowsQuantityAfected;
    }

    
}
