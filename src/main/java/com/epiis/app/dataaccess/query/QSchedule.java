/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.query;

import com.epiis.app.connection.DbConnection;
import com.epiis.app.dataaccess.entity.EntityEmployee;
import com.epiis.app.dataaccess.entity.EntitySchedule;
import com.epiis.app.dto.DtoSchedule;
import com.epiis.app.repository.RepoSchedule;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author LIZ
 */
public class QSchedule implements RepoSchedule {
    private DbConnection dbc = null;
    private EntitySchedule entitySchedule = null;
    private String script = null;
    private int rowsQuantityAfected = 0;
    private List<EntitySchedule> listSchedules = null;
    private List<DtoSchedule> listDtoSchedules = null;
    
    @Override
    public int insert(DtoSchedule dto) throws SQLException {
        
        this.dbc = new DbConnection();

        this.script = "INSERT INTO tschedule VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        this.entitySchedule = new EntitySchedule();
        
        this.entitySchedule.setIdSchedule(dto.getIdSchedule());

        EntityEmployee emp = new EntityEmployee();
        emp.setIdEmployee(dto.getDtoEmployee().getIdEmployee());
        this.entitySchedule.setEntityEmployee(emp);

        this.entitySchedule.setStartDate(dto.getStartDate());
        this.entitySchedule.setEndDate(dto.getEndDate());
        this.entitySchedule.setStartTime(dto.getStartTime());
        this.entitySchedule.setEndTime(dto.getEndTime());

        ps.setString(1, this.entitySchedule.getIdSchedule());
        ps.setString(2, this.entitySchedule.getEntityEmployee().getIdEmployee());
        ps.setDate(3, new java.sql.Date(this.entitySchedule.getStartDate().getTime()));
        ps.setDate(4, new java.sql.Date(this.entitySchedule.getEndDate().getTime()));
        ps.setTime(5, this.entitySchedule.getStartTime());
        ps.setTime(6, this.entitySchedule.getEndTime());
        
        this.rowsQuantityAfected = ps.executeUpdate();
        this.dbc.connection.close();
        return rowsQuantityAfected;
    }

    
    @Override
    public boolean isInSchedule(String idEmployee, LocalDate date, LocalTime time) throws SQLException {
        this.dbc = new DbConnection();

        this.script = """
            SELECT * FROM tschedule
            WHERE idEmployee = ?
            AND ? BETWEEN startDate AND endDate
            AND ? BETWEEN startTime AND endTime
        """;

        PreparedStatement ps = dbc.connection.prepareStatement(this.script);
        ps.setString(1, idEmployee);
        ps.setDate(2, java.sql.Date.valueOf(date));
        ps.setTime(3, java.sql.Time.valueOf(time));

        ResultSet rs = ps.executeQuery();
        boolean valido = rs.next();

        dbc.connection.close();
        return valido;
    }

    @Override
    public List<DtoSchedule> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DtoSchedule getLastByEmployee(String idEmployee) throws SQLException {

        this.dbc = new DbConnection();

        this.script = """
            SELECT idSchedule, idEmployee, startDate, endDate, startTime, endTime
            FROM tschedule
            WHERE idEmployee = ?
            ORDER BY startDate DESC, startTime DESC
            LIMIT 1
        """;

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ps.setString(1, idEmployee);
        ResultSet rs = ps.executeQuery();

        DtoSchedule dto = null;

        if (rs.next()) {
            dto = new DtoSchedule();
            dto.setIdSchedule(rs.getString("idSchedule"));
            dto.setStartDate(rs.getDate("startDate"));
            dto.setEndDate(rs.getDate("endDate"));
            dto.setStartTime(rs.getTime("startTime"));
            dto.setEndTime(rs.getTime("endTime"));
        }

        rs.close();
        ps.close();
        this.dbc.connection.close();

        return dto;
    }
    
}
