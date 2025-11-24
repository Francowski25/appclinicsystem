/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.query;

import com.epiis.app.connection.DbConnection;
import com.epiis.app.dataaccess.entity.EntityDoctorSpecialty;
import com.epiis.app.dataaccess.entity.EntityEmployee;
import com.epiis.app.dataaccess.entity.EntitySpecialty;
import com.epiis.app.dto.DtoDoctorSpecialty;
import com.epiis.app.dto.DtoEmployee;
import com.epiis.app.dto.DtoSpecialty;
import com.epiis.app.repository.RepoDoctorSpecialty;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LIZ
 */
public class QDoctorSpecialty implements RepoDoctorSpecialty{
    private DbConnection dbc = null;
    private String script = null;
    private EntityDoctorSpecialty entityDoctorSpecialty = null;
    private int rowsQuantityAfected = 0;

    @Override
    public int insert(DtoDoctorSpecialty dto) throws SQLException {
        this.dbc = new DbConnection();
        this.script = "INSERT INTO tdoctorSpecialty VALUES (?, ?)";

        this.entityDoctorSpecialty = new EntityDoctorSpecialty();
        
        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        
        EntitySpecialty p = new EntitySpecialty();
        p.setIdSpecialty(dto.getDtoSpecialty().getIdSpecialty());
        entityDoctorSpecialty.setEntitySpecialty(p);

        EntityEmployee e = new EntityEmployee();
        e.setIdEmployee(dto.getDtoEmployee().getIdEmployee());
        entityDoctorSpecialty.setEntityEmployee(e);
        
        ps.setString(1, entityDoctorSpecialty.getEntityEmployee().getIdEmployee());
        ps.setString(2, entityDoctorSpecialty.getEntitySpecialty().getIdSpecialty());

        this.rowsQuantityAfected = ps.executeUpdate();
        this.dbc.connection.close();
        return rowsQuantityAfected;
    }
    
    @Override
    public List<DtoDoctorSpecialty> getAll() throws SQLException {
        this.dbc = new DbConnection();

        List<DtoDoctorSpecialty> listDto = new ArrayList<>();

        this.script = """
            SELECT 
                e.idEmployee,
                e.firstName,
                sp.name AS specialtyName
            FROM tdoctorSpecialty ds
            INNER JOIN temployee e ON ds.idEmployee = e.idEmployee
            INNER JOIN tspecialty sp ON ds.idSpecialty = sp.idSpecialty
        """;

        PreparedStatement ps = this.dbc.connection.prepareStatement(script);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            DtoEmployee dtoEmployee = new DtoEmployee();
            dtoEmployee.setIdEmployee(rs.getString("idEmployee"));
            dtoEmployee.setFirstName(rs.getString("firstName"));

            DtoSpecialty dtoSpecialty = new DtoSpecialty();
            dtoSpecialty.setName(rs.getString("specialtyName"));

            DtoDoctorSpecialty dto = new DtoDoctorSpecialty();
            dto.setDtoEmployee(dtoEmployee);
            dto.setDtoSpecialty(dtoSpecialty);

            listDto.add(dto);
        }

        this.dbc.connection.close();
        return listDto;
    }

}
