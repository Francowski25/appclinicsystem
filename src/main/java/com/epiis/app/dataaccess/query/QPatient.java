/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.query;

import com.epiis.app.connection.DbConnection;
import com.epiis.app.dataaccess.entity.EntityPatient;
import com.epiis.app.dto.DtoPatient;
import com.epiis.app.repository.RepoPatient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LIZ
 */
public class QPatient implements RepoPatient{
    private DbConnection dbc = null;
    private String script = null;
    private EntityPatient entityPatient = null;
    private int rowsQuantityAfected = 0;
    
    @Override
    public DtoPatient getByEmailAndPasssword(String email, String password) throws SQLException {
        this.dbc = new DbConnection();
        DtoPatient dto = null;

        String sql = "SELECT * FROM tpatient WHERE email = ? AND password = ?";
        PreparedStatement ps = this.dbc.connection.prepareStatement(sql);

        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            dto = new DtoPatient();
            dto.setIdPatient(rs.getString("idPatient"));
            dto.setDni(rs.getInt("dni"));
            dto.setFirstName(rs.getString("firstName"));
            dto.setSurNameP(rs.getString("surNameP"));
            dto.setSurNameM(rs.getString("surNameM"));
            dto.setGender(rs.getString("gender"));
            dto.setEmail(rs.getString("email"));
            dto.setPhone(rs.getString("phone"));
            dto.setPassword(rs.getString("password"));
            dto.setCreatedAt(rs.getTimestamp("createdAt"));
            dto.setStatus(rs.getString("status"));
        }

        this.dbc.connection.close();
        return dto;
    }

    @Override
    public int insert(DtoPatient dtoPatient) throws SQLException {
        this.dbc = new DbConnection();

        this.script = "INSERT INTO tpatient VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);

        this.entityPatient = new EntityPatient();

        entityPatient.setIdPatient(dtoPatient.getIdPatient());
        entityPatient.setDni(dtoPatient.getDni());
        entityPatient.setFirstName(dtoPatient.getFirstName());
        entityPatient.setSurNameP(dtoPatient.getSurNameP());
        entityPatient.setSurNameM(dtoPatient.getSurNameM());
        entityPatient.setGender(dtoPatient.getGender());
        entityPatient.setEmail(dtoPatient.getEmail());
        entityPatient.setPhone(dtoPatient.getPhone());
        entityPatient.setPassword(dtoPatient.getPassword());
        entityPatient.setCreatedAt(dtoPatient.getCreatedAt());
        entityPatient.setStatus(dtoPatient.getStatus());

        ps.setString(1, entityPatient.getIdPatient());
        ps.setInt(2, entityPatient.getDni());
        ps.setString(3, entityPatient.getFirstName());
        ps.setString(4, entityPatient.getSurNameP());
        ps.setString(5, entityPatient.getSurNameM());
        ps.setString(6, entityPatient.getGender());
        ps.setString(7, entityPatient.getEmail());
        ps.setString(8, entityPatient.getPhone());
        ps.setString(9, entityPatient.getPassword());
        ps.setTimestamp(10, new java.sql.Timestamp(entityPatient.getCreatedAt().getTime()));
        ps.setString(11, entityPatient.getStatus());

        this.rowsQuantityAfected = ps.executeUpdate();
        this.dbc.connection.close();
        return rowsQuantityAfected;
    }

    @Override
    public DtoPatient getByDni(int dni) throws SQLException {

        this.dbc = new DbConnection();

        this.script = "SELECT * FROM tpatient WHERE dni = ?";
        PreparedStatement ps = this.dbc.connection.prepareStatement(script);
        ps.setInt(1, dni);

        ResultSet rs = ps.executeQuery();

        EntityPatient entity = null;
        if (rs.next()) {

            entity = new EntityPatient();
            entity.setIdPatient(rs.getString("idPatient"));
            entity.setDni(rs.getInt("dni"));
            entity.setFirstName(rs.getString("firstName"));
            entity.setSurNameP(rs.getString("surNameP"));
            entity.setSurNameM(rs.getString("surNameM"));
            entity.setGender(rs.getString("gender"));
            entity.setEmail(rs.getString("email"));
            entity.setPhone(rs.getString("phone"));
            entity.setPassword(rs.getString("password"));
            entity.setCreatedAt(rs.getTimestamp("createdAt"));
            entity.setStatus(rs.getString("status"));
        }

        this.dbc.connection.close();

        if (entity == null) return null;

        DtoPatient dto = new DtoPatient();
        dto.setIdPatient(entity.getIdPatient());
        dto.setDni(entity.getDni());
        dto.setFirstName(entity.getFirstName());
        dto.setSurNameP(entity.getSurNameP());
        dto.setSurNameM(entity.getSurNameM());
        dto.setGender(entity.getGender());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setPassword(entity.getPassword());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    @Override
    public List<DtoPatient> getAll() throws SQLException {

        this.dbc = new DbConnection();
        List<EntityPatient> listEntity = new java.util.ArrayList<>();
        List<DtoPatient> listDto = new java.util.ArrayList<>();

        this.script = "SELECT * FROM tpatient";
        PreparedStatement ps = this.dbc.connection.prepareStatement(script);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            EntityPatient entity = new EntityPatient();
            entity.setIdPatient(rs.getString("idPatient"));
            entity.setDni(rs.getInt("dni"));
            entity.setFirstName(rs.getString("firstName"));
            entity.setSurNameP(rs.getString("surNameP"));
            entity.setSurNameM(rs.getString("surNameM"));
            entity.setGender(rs.getString("gender"));
            entity.setEmail(rs.getString("email"));
            entity.setPhone(rs.getString("phone"));
            entity.setPassword(rs.getString("password"));
            entity.setCreatedAt(rs.getTimestamp("createdAt"));
            entity.setStatus(rs.getString("status"));

            listEntity.add(entity);
        }

        for (EntityPatient e : listEntity) {

            DtoPatient dto = new DtoPatient();
            dto.setIdPatient(e.getIdPatient());
            dto.setDni(e.getDni());
            dto.setFirstName(e.getFirstName());
            dto.setSurNameP(e.getSurNameP());
            dto.setSurNameM(e.getSurNameM());
            dto.setGender(e.getGender());
            dto.setEmail(e.getEmail());
            dto.setPhone(e.getPhone());
            dto.setPassword(e.getPassword());
            dto.setCreatedAt(e.getCreatedAt());
            dto.setStatus(e.getStatus());

            listDto.add(dto);
        }

        this.dbc.connection.close();
        return listDto;
    }


    @Override
    public int update(DtoPatient dto) throws SQLException {

        this.dbc = new DbConnection();

        EntityPatient entity = new EntityPatient();
        entity.setIdPatient(dto.getIdPatient());
        entity.setDni(dto.getDni());
        entity.setFirstName(dto.getFirstName());
        entity.setSurNameP(dto.getSurNameP());
        entity.setSurNameM(dto.getSurNameM());
        entity.setGender(dto.getGender());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setStatus(dto.getStatus());

        this.script = """
            UPDATE tpatient SET 
                firstName = ?, 
                surNameP = ?, 
                surNameM = ?, 
                gender = ?, 
                email = ?, 
                phone = ?, 
                password = ?, 
                status = ?
            WHERE idPatient = ?
        """;

        PreparedStatement ps = this.dbc.connection.prepareStatement(script);

        ps.setString(1, entity.getFirstName());
        ps.setString(2, entity.getSurNameP());
        ps.setString(3, entity.getSurNameM());
        ps.setString(4, entity.getGender());
        ps.setString(5, entity.getEmail());
        ps.setString(6, entity.getPhone());
        ps.setString(7, entity.getPassword());
        ps.setString(8, entity.getStatus());
        ps.setString(9, entity.getIdPatient());

        int rows = ps.executeUpdate();
        this.dbc.connection.close();

        return rows;
    }

}
