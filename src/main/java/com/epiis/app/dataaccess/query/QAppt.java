/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.query;

import com.epiis.app.connection.DbConnection;
import com.epiis.app.dataaccess.entity.EntityAppt;
import com.epiis.app.dataaccess.entity.EntityEmployee;
import com.epiis.app.dataaccess.entity.EntityPatient;
import com.epiis.app.dto.DtoAppt;
import com.epiis.app.dto.DtoEmployee;
import com.epiis.app.dto.DtoPatient;
import com.epiis.app.repository.RepoAppt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LIZ
 */
public class QAppt implements RepoAppt{
    DbConnection dbc = null;
    private EntityAppt entityAppt = null;
    private List<EntityAppt> listAppt = null;
    private List<DtoAppt> listDtoAppt = null;
    private String script = null;
    private int rowsQuantityAfected = 0;

    @Override
    public int insert(DtoAppt dtoAppt) throws SQLException {
        this.dbc = new DbConnection();

        this.script = "INSERT INTO tappointment VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);

        this.entityAppt = new EntityAppt();

        entityAppt.setIdAppt(dtoAppt.getIdAppt());

        EntityPatient p = new EntityPatient();
        p.setIdPatient(dtoAppt.getDtoPatient().getIdPatient());
        entityAppt.setEntityPatient(p);

        EntityEmployee e = new EntityEmployee();
        e.setIdEmployee(dtoAppt.getDtoEmployee().getIdEmployee());
        entityAppt.setEntityEmployee(e);

        entityAppt.setApptDate(dtoAppt.getApptDate());
        entityAppt.setApptTime(dtoAppt.getApptTime());
        entityAppt.setReason(dtoAppt.getReason());
        entityAppt.setStatus(dtoAppt.getStatus());
        entityAppt.setType(dtoAppt.getType());
        entityAppt.setCreatedAt(dtoAppt.getCreatedAt());

        ps.setString(1, entityAppt.getIdAppt());
        ps.setString(2, entityAppt.getEntityPatient().getIdPatient());
        ps.setString(3, entityAppt.getEntityEmployee().getIdEmployee());
        ps.setDate(4, new java.sql.Date(entityAppt.getApptDate().getTime()));
        ps.setTime(5, new java.sql.Time(entityAppt.getApptTime().getTime()));
        ps.setString(6, entityAppt.getReason());
        ps.setString(7, entityAppt.getStatus());
        ps.setString(8, entityAppt.getType());
        ps.setDate(9, new java.sql.Date(entityAppt.getCreatedAt().getTime()));

        this.rowsQuantityAfected = ps.executeUpdate();
        this.dbc.connection.close();
        return rowsQuantityAfected;
    }


    @Override
    public List<DtoAppt> getAll() throws SQLException {
        this.dbc = new DbConnection();

        this.listAppt = new ArrayList<>();
        this.listDtoAppt = new ArrayList<>();

        this.script = "SELECT * FROM tappointment";

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ResultSet rows = ps.executeQuery();

        while (rows.next()) {
            EntityAppt appTemp = new EntityAppt();

            appTemp.setIdAppt(rows.getString("idAppt"));

            EntityPatient p = new EntityPatient();
            p.setIdPatient(rows.getString("idPatient"));
            appTemp.setEntityPatient(p);

            EntityEmployee e = new EntityEmployee();
            e.setIdEmployee(rows.getString("idEmployee"));
            appTemp.setEntityEmployee(e);

            appTemp.setApptDate(rows.getDate("apptDate"));
            appTemp.setApptTime(rows.getTime("apptTime"));
            appTemp.setReason(rows.getString("reason"));
            appTemp.setStatus(rows.getString("status"));
            appTemp.setType(rows.getString("type"));
            appTemp.setApptDate(rows.getDate("createdAt"));

            this.listAppt.add(appTemp);
        }

        for (EntityAppt item : this.listAppt) {
            DtoAppt dtoApptTemp = new DtoAppt();

            DtoPatient p = new DtoPatient();
            p.setIdPatient(item.getEntityPatient().getIdPatient());
            dtoApptTemp.setDtoPatient(p);

            DtoEmployee e = new DtoEmployee();
            e.setIdEmployee(item.getEntityEmployee().getIdEmployee());
            dtoApptTemp.setDtoEmployee(e);

            dtoApptTemp.setIdAppt(item.getIdAppt());
            dtoApptTemp.setApptDate(item.getApptDate());
            dtoApptTemp.setApptTime(item.getApptTime());
            dtoApptTemp.setReason(item.getReason());
            dtoApptTemp.setStatus(item.getStatus());
            dtoApptTemp.setType(item.getType());
            dtoApptTemp.setCreatedAt(item.getCreatedAt());

            this.listDtoAppt.add(dtoApptTemp);
        }

        this.dbc.connection.close();
        return this.listDtoAppt;
    }
    
    @Override
    public List<DtoAppt> getAllPatient() throws SQLException {
        this.dbc = new DbConnection();

        this.listAppt = new ArrayList<>();
        this.listDtoAppt = new ArrayList<>();

        this.script = "SELECT a.*, p.dni, p.firstName, p.surNameP, p.surNameM " +
                      "FROM tappointment a " +
                      "INNER JOIN tpatient p ON a.idPatient = p.idPatient";

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ResultSet rows = ps.executeQuery();

        while (rows.next()) {
            EntityAppt appTemp = new EntityAppt();

            appTemp.setIdAppt(rows.getString("idAppt"));

            EntityPatient p = new EntityPatient();
            p.setIdPatient(rows.getString("idPatient"));
            p.setDni(rows.getInt("dni"));
            p.setFirstName(rows.getString("firstName"));
            p.setSurNameP(rows.getString("surNameP"));
            p.setSurNameM(rows.getString("surNameM"));
            appTemp.setEntityPatient(p);

            EntityEmployee e = new EntityEmployee();
            e.setIdEmployee(rows.getString("idEmployee"));
            appTemp.setEntityEmployee(e);

            appTemp.setApptDate(rows.getDate("apptDate"));
            appTemp.setApptTime(rows.getTime("apptTime"));
            appTemp.setReason(rows.getString("reason"));
            appTemp.setStatus(rows.getString("status"));
            appTemp.setType(rows.getString("type"));
            appTemp.setCreatedAt(rows.getDate("createdAt"));

            this.listAppt.add(appTemp);
        }

        for (EntityAppt item : this.listAppt) {
            DtoAppt dtoApptTemp = new DtoAppt();

            DtoPatient p = new DtoPatient();
            p.setIdPatient(item.getEntityPatient().getIdPatient());
            p.setDni(item.getEntityPatient().getDni());
            p.setFirstName(item.getEntityPatient().getFirstName());
            p.setSurNameP(item.getEntityPatient().getSurNameP());
            p.setSurNameM(item.getEntityPatient().getSurNameM());
            dtoApptTemp.setDtoPatient(p);

            DtoEmployee e = new DtoEmployee();
            e.setIdEmployee(item.getEntityEmployee().getIdEmployee());
            dtoApptTemp.setDtoEmployee(e);

            dtoApptTemp.setIdAppt(item.getIdAppt());
            dtoApptTemp.setApptDate(item.getApptDate());
            dtoApptTemp.setApptTime(item.getApptTime());
            dtoApptTemp.setReason(item.getReason());
            dtoApptTemp.setStatus(item.getStatus());
            dtoApptTemp.setType(item.getType());
            dtoApptTemp.setCreatedAt(item.getCreatedAt());

            this.listDtoAppt.add(dtoApptTemp);
        }

        this.dbc.connection.close();
        return this.listDtoAppt;
    }
    
    @Override
    public boolean existsAppointment(String idEmployee, LocalDate date, LocalTime time) throws SQLException {
        this.dbc = new DbConnection();

        String sql = """
            SELECT 1 FROM tappointment
            WHERE idEmployee = ?
            AND apptDate = ?
            AND apptTime = ?
            LIMIT 1
        """;

        PreparedStatement ps = dbc.connection.prepareStatement(sql);
        ps.setString(1, idEmployee);
        ps.setDate(2, java.sql.Date.valueOf(date));
        ps.setTime(3, java.sql.Time.valueOf(time));

        ResultSet rs = ps.executeQuery();
        boolean ocupado = rs.next();

        dbc.connection.close();
        return ocupado;
    }
    
    @Override
    public boolean existsAppointmentForPatient(String idPatient, LocalDate date, LocalTime time) throws SQLException {
        this.dbc = new DbConnection();

        String sql = """
            SELECT 1 FROM tappointment
            WHERE idPatient = ?
            AND apptDate = ?
            AND apptTime = ?
            LIMIT 1
        """;

        PreparedStatement ps = dbc.connection.prepareStatement(sql);
        ps.setString(1, idPatient);
        ps.setDate(2, java.sql.Date.valueOf(date));
        ps.setTime(3, java.sql.Time.valueOf(time));

        ResultSet rs = ps.executeQuery();
        boolean ocupado = rs.next();

        dbc.connection.close();
        return ocupado;
    }

}