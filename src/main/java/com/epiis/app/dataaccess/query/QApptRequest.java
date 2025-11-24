/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.query;

import com.epiis.app.connection.DbConnection;
import com.epiis.app.dataaccess.entity.EntityApptRequest;
import com.epiis.app.dataaccess.entity.EntityEmployee;
import com.epiis.app.dataaccess.entity.EntityPatient;
import com.epiis.app.dto.DtoApptRequest;
import com.epiis.app.dto.DtoEmployee;
import com.epiis.app.dto.DtoPatient;
import com.epiis.app.repository.RepoApptRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LIZ
 */
public class QApptRequest implements RepoApptRequest{

    private DbConnection dbc = null;
    private EntityApptRequest entityApptRequest = null;    
    private String script = null;
    private int rowsQuantityAfected = 0;
    private List<EntityApptRequest> listApptRequest = null;
    private List<DtoApptRequest> listDtoApptRequest = null;
    
    @Override
    public int insert(DtoApptRequest dtoApptRequest) throws SQLException {
        this.dbc = new DbConnection();
        
        this.script = "INSERT INTO tappointmentRequest VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);

        this.entityApptRequest = new EntityApptRequest();
        
        entityApptRequest.setIdRequest(dtoApptRequest.getIdRequest());
        EntityPatient p = new EntityPatient();
        p.setIdPatient(dtoApptRequest.getDtoPatient().getIdPatient());
        entityApptRequest.setEntityPatient(p);
        
        EntityEmployee e = new EntityEmployee();
        e.setIdEmployee(dtoApptRequest.getDtoEmployee().getIdEmployee());
        entityApptRequest.setEntityEmployee(e);

        entityApptRequest.setRequestDate(dtoApptRequest.getRequestDate());
        entityApptRequest.setRequestTime(dtoApptRequest.getRequestTime());
        entityApptRequest.setReason(dtoApptRequest.getReason());
        entityApptRequest.setStatus(dtoApptRequest.getStatus());
        entityApptRequest.setCreatedAt(dtoApptRequest.getCreatedAt());
        
        ps.setString(1, entityApptRequest.getIdRequest());
        ps.setString(2, entityApptRequest.getEntityPatient().getIdPatient());
        ps.setString(3, entityApptRequest.getEntityEmployee().getIdEmployee());
        ps.setDate(4, new java.sql.Date(entityApptRequest.getRequestDate().getTime()));
        ps.setTime(5, entityApptRequest.getRequestTime());
        ps.setString(6, entityApptRequest.getReason());
        ps.setString(7, entityApptRequest.getStatus());
        ps.setDate(8, new java.sql.Date(entityApptRequest.getCreatedAt().getTime()));
        
        this.rowsQuantityAfected = ps.executeUpdate();
        this.dbc.connection.close();
        return rowsQuantityAfected;
    }


    @Override
    public List<DtoApptRequest> getAll() throws SQLException {

        this.dbc = new DbConnection();
        this.listApptRequest = new ArrayList<>();
        this.listDtoApptRequest = new ArrayList<>();

        this.script = "SELECT * FROM tappointmentRequest";
        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            EntityApptRequest appTemp = new EntityApptRequest();

            appTemp.setIdRequest(rs.getString("idRequest"));
            appTemp.setRequestDate(rs.getDate("requestDate"));
            appTemp.setRequestTime(rs.getTime("requestTime"));
            appTemp.setReason(rs.getString("reason"));
            appTemp.setStatus(rs.getString("status"));
            appTemp.setCreatedAt(rs.getTimestamp("createdAt"));

            EntityPatient p = new EntityPatient();
            p.setIdPatient(rs.getString("idPatient"));
            appTemp.setEntityPatient(p);

            EntityEmployee e = new EntityEmployee();
            e.setIdEmployee(rs.getString("idEmployee"));
            appTemp.setEntityEmployee(e);

            this.listApptRequest.add(appTemp);
        }

        for (EntityApptRequest item : this.listApptRequest) {

            DtoApptRequest dtoApptRequest = new DtoApptRequest();

            dtoApptRequest.setIdRequest(item.getIdRequest());
            dtoApptRequest.setRequestDate(item.getRequestDate());
            dtoApptRequest.setRequestTime(item.getRequestTime());
            dtoApptRequest.setReason(item.getReason());
            dtoApptRequest.setStatus(item.getStatus());
            dtoApptRequest.setCreatedAt(item.getCreatedAt());

            DtoPatient p = new DtoPatient();
            p.setIdPatient(item.getEntityPatient().getIdPatient());
            dtoApptRequest.setDtoPatient(p);

            DtoEmployee e = new DtoEmployee();
            e.setIdEmployee(item.getEntityEmployee().getIdEmployee());
            dtoApptRequest.setDtoEmployee(e);

            this.listDtoApptRequest.add(dtoApptRequest);
        }

        this.dbc.connection.close();
        return this.listDtoApptRequest;
    }

    @Override
    public List<DtoApptRequest> getAllPatient() throws SQLException {
        this.dbc = new DbConnection();
        this.listDtoApptRequest = new ArrayList<>();

        this.script = "SELECT ar.idRequest, ar.requestDate, ar.requestTime, ar.reason, ar.status, ar.createdAt, " +
                      "p.dni, p.firstName, p.surNameP, p.surNameM, " +
                      "e.firstName AS empFirst, e.surNameP AS empSurP, e.surNameM AS empSurM " +
                      "FROM tappointmentRequest ar " +
                      "INNER JOIN tpatient p ON ar.idPatient = p.idPatient " +
                      "INNER JOIN temployee e ON ar.idEmployee = e.idEmployee";

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            DtoApptRequest dto = new DtoApptRequest();
            dto.setIdRequest(rs.getString("idRequest"));
            dto.setRequestDate(rs.getDate("requestDate"));
            dto.setRequestTime(rs.getTime("requestTime"));
            dto.setReason(rs.getString("reason"));
            dto.setStatus(rs.getString("status"));
            dto.setCreatedAt(rs.getTimestamp("createdAt"));

            DtoPatient p = new DtoPatient();
            p.setDni(rs.getInt("dni")); // Corregido a String
            p.setFirstName(rs.getString("firstName"));
            p.setSurNameP(rs.getString("surNameP"));
            p.setSurNameM(rs.getString("surNameM"));
            dto.setDtoPatient(p);

            DtoEmployee e = new DtoEmployee();
            e.setFirstName(rs.getString("empFirst"));
            e.setSurNameP(rs.getString("empSurP"));
            e.setSurNameM(rs.getString("empSurM"));
            dto.setDtoEmployee(e);

            this.listDtoApptRequest.add(dto);
        }

        this.dbc.connection.close();
        return this.listDtoApptRequest;
    }

}
