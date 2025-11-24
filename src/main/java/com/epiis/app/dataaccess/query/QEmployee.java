/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.query;

import com.epiis.app.connection.DbConnection;
import com.epiis.app.dataaccess.entity.EntityEmployee;
import com.epiis.app.dto.DtoEmployee;
import com.epiis.app.repository.RepoEmployee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LIZ
 */
public class QEmployee implements RepoEmployee {

    private DbConnection dbc = null;
    private String script = null;
    private EntityEmployee entityEmployee = null;
    private int rowsQuantityAfected = 0;

    @Override
    public DtoEmployee getByEmailAndPassword(String email, String password) throws SQLException {
        this.dbc = new DbConnection();
        DtoEmployee dto = null;

        String sql = "SELECT * FROM temployee WHERE email = ? AND password = ?";
        PreparedStatement ps = this.dbc.connection.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            dto = new DtoEmployee();
            dto.setIdEmployee(rs.getString("idEmployee"));
            dto.setDni(rs.getInt("dni"));
            dto.setFirstName(rs.getString("firstName"));
            dto.setSurNameP(rs.getString("surNameP"));
            dto.setSurNameM(rs.getString("surNameM"));
            dto.setRole(rs.getString("role"));
            dto.setPhone(rs.getString("phone"));
            dto.setEmail(rs.getString("email"));
            dto.setPassword(rs.getString("password"));
            dto.setCreatedAt(rs.getTimestamp("createdAt"));
            dto.setStatus(rs.getString("status"));
        }

        this.dbc.connection.close();
        return dto;
    }

    @Override
    public int insert(DtoEmployee dtoEmployee) throws SQLException {
        this.dbc = new DbConnection();
        this.script = "INSERT INTO temployee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        this.entityEmployee = new EntityEmployee();

        entityEmployee.setIdEmployee(dtoEmployee.getIdEmployee());
        entityEmployee.setDni(dtoEmployee.getDni());
        entityEmployee.setFirstName(dtoEmployee.getFirstName());
        entityEmployee.setSurNameP(dtoEmployee.getSurNameP());
        entityEmployee.setSurNameM(dtoEmployee.getSurNameM());
        entityEmployee.setRole(dtoEmployee.getRole());
        entityEmployee.setPhone(dtoEmployee.getPhone());
        entityEmployee.setEmail(dtoEmployee.getEmail());
        entityEmployee.setPassword(dtoEmployee.getPassword());
        entityEmployee.setCreatedAt(dtoEmployee.getCreatedAt());
        entityEmployee.setStatus(dtoEmployee.getStatus());

        ps.setString(1, entityEmployee.getIdEmployee());
        ps.setInt(2, entityEmployee.getDni());
        ps.setString(3, entityEmployee.getFirstName());
        ps.setString(4, entityEmployee.getSurNameP());
        ps.setString(5, entityEmployee.getSurNameM());
        ps.setString(6, entityEmployee.getRole());
        ps.setString(7, entityEmployee.getPhone());
        ps.setString(8, entityEmployee.getEmail());
        ps.setString(9, entityEmployee.getPassword());
        ps.setTimestamp(10, new java.sql.Timestamp(entityEmployee.getCreatedAt().getTime()));
        ps.setString(11, entityEmployee.getStatus());

        this.rowsQuantityAfected = ps.executeUpdate();
        this.dbc.connection.close();
        return rowsQuantityAfected;
    }

    @Override
    public DtoEmployee getByFirstName(String firstName) throws SQLException {
        this.dbc = new DbConnection();
        DtoEmployee dto = null;

        this.script = "SELECT * FROM temployee WHERE firstName = ?";
        PreparedStatement ps = this.dbc.connection.prepareStatement(script);
        ps.setString(1, firstName);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            entityEmployee = new EntityEmployee();
            entityEmployee.setIdEmployee(rs.getString("idEmployee"));
            entityEmployee.setDni(rs.getInt("dni"));
            entityEmployee.setFirstName(rs.getString("firstName"));
            entityEmployee.setSurNameP(rs.getString("surNameP"));
            entityEmployee.setSurNameM(rs.getString("surNameM"));
            entityEmployee.setRole(rs.getString("role"));
            entityEmployee.setPhone(rs.getString("phone"));
            entityEmployee.setEmail(rs.getString("email"));
            entityEmployee.setPassword(rs.getString("password"));
            entityEmployee.setCreatedAt(rs.getTimestamp("createdAt"));
            entityEmployee.setStatus(rs.getString("status"));

            dto = new DtoEmployee();
            dto.setIdEmployee(entityEmployee.getIdEmployee());
            dto.setDni(entityEmployee.getDni());
            dto.setFirstName(entityEmployee.getFirstName());
            dto.setSurNameP(entityEmployee.getSurNameP());
            dto.setSurNameM(entityEmployee.getSurNameM());
            dto.setRole(entityEmployee.getRole());
            dto.setPhone(entityEmployee.getPhone());
            dto.setEmail(entityEmployee.getEmail());
            dto.setPassword(entityEmployee.getPassword());
            dto.setCreatedAt(entityEmployee.getCreatedAt());
            dto.setStatus(entityEmployee.getStatus());
        }

        this.dbc.connection.close();
        return dto;
    }

    @Override
    public List<DtoEmployee> getAll() throws SQLException {
        this.dbc = new DbConnection();
        List<EntityEmployee> listEntity = new java.util.ArrayList<>();
        List<DtoEmployee> listDto = new java.util.ArrayList<>();

        this.script = "SELECT * FROM temployee";
        PreparedStatement ps = this.dbc.connection.prepareStatement(script);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            EntityEmployee entity = new EntityEmployee();
            entity.setIdEmployee(rs.getString("idEmployee"));
            entity.setDni(rs.getInt("dni"));
            entity.setFirstName(rs.getString("firstName"));
            entity.setSurNameP(rs.getString("surNameP"));
            entity.setSurNameM(rs.getString("surNameM"));
            entity.setRole(rs.getString("role"));
            entity.setPhone(rs.getString("phone"));
            entity.setEmail(rs.getString("email"));
            entity.setPassword(rs.getString("password"));
            entity.setCreatedAt(rs.getTimestamp("createdAt"));
            entity.setStatus(rs.getString("status"));

            listEntity.add(entity);
        }

        for (EntityEmployee e : listEntity) {
            DtoEmployee dto = new DtoEmployee();
            dto.setIdEmployee(e.getIdEmployee());
            dto.setDni(e.getDni());
            dto.setFirstName(e.getFirstName());
            dto.setSurNameP(e.getSurNameP());
            dto.setSurNameM(e.getSurNameM());
            dto.setRole(e.getRole());
            dto.setPhone(e.getPhone());
            dto.setEmail(e.getEmail());
            dto.setPassword(e.getPassword());
            dto.setCreatedAt(e.getCreatedAt());
            dto.setStatus(e.getStatus());

            listDto.add(dto);
        }

        this.dbc.connection.close();
        return listDto;
    }

    @Override
    public int update(DtoEmployee dto) throws SQLException {
        this.dbc = new DbConnection();

        entityEmployee = new EntityEmployee();
        entityEmployee.setIdEmployee(dto.getIdEmployee());
        entityEmployee.setDni(dto.getDni());
        entityEmployee.setFirstName(dto.getFirstName());
        entityEmployee.setSurNameP(dto.getSurNameP());
        entityEmployee.setSurNameM(dto.getSurNameM());
        entityEmployee.setRole(dto.getRole());
        entityEmployee.setPhone(dto.getPhone());
        entityEmployee.setEmail(dto.getEmail());
        entityEmployee.setPassword(dto.getPassword());
        entityEmployee.setCreatedAt(dto.getCreatedAt());
        entityEmployee.setStatus(dto.getStatus());

        this.script = """
            UPDATE temployee SET 
                firstName = ?, 
                surNameP = ?, 
                surNameM = ?, 
                role = ?, 
                phone = ?, 
                email = ?, 
                password = ?, 
                status = ?
            WHERE idEmployee = ?
        """;

        PreparedStatement ps = this.dbc.connection.prepareStatement(script);
        ps.setString(1, entityEmployee.getFirstName());
        ps.setString(2, entityEmployee.getSurNameP());
        ps.setString(3, entityEmployee.getSurNameM());
        ps.setString(4, entityEmployee.getRole());
        ps.setString(5, entityEmployee.getPhone());
        ps.setString(6, entityEmployee.getEmail());
        ps.setString(7, entityEmployee.getPassword());
        ps.setString(8, entityEmployee.getStatus());
        ps.setString(9, entityEmployee.getIdEmployee());

        rowsQuantityAfected = ps.executeUpdate();
        this.dbc.connection.close();

        return rowsQuantityAfected;
    }

    @Override
    public DtoEmployee getByDni(int dni) throws SQLException {
        this.dbc = new DbConnection();
        String sql = "SELECT * FROM temployee WHERE dni = ?";
        PreparedStatement pst = this.dbc.connection.prepareStatement(sql);
        pst.setInt(1, dni);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            DtoEmployee dto = new DtoEmployee();
            dto.setIdEmployee(rs.getString("idEmployee"));
            dto.setDni(rs.getInt("dni"));
            dto.setFirstName(rs.getString("firstName"));
            dto.setSurNameP(rs.getString("surNameP"));
            dto.setSurNameM(rs.getString("surNameM"));
            dto.setPhone(rs.getString("phone"));
            dto.setRole(rs.getString("role"));
            dto.setStatus(rs.getString("status"));
            dto.setEmail(rs.getString("email"));
            dto.setPassword(rs.getString("password"));
            return dto;
        }
        this.dbc.connection.close();
        return null;
    }

}
