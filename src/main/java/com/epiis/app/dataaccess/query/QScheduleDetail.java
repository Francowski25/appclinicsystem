/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.query;

import com.epiis.app.connection.DbConnection;
import com.epiis.app.dataaccess.entity.EntitySchedule;
import com.epiis.app.dataaccess.entity.EntityScheduleDetail;
import com.epiis.app.dto.DtoSchedule;
import com.epiis.app.dto.DtoScheduleDetail;
import com.epiis.app.repository.RepoScheduleDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LIZ
 */
public class QScheduleDetail implements RepoScheduleDetail{
    private DbConnection dbc = null;
    private String script = null;
    private EntityScheduleDetail entityScheduleDetail = null;
    private int rowsQuantityAfected = 0;
    private List<EntityScheduleDetail> listDetails = null;
    private List<DtoScheduleDetail> listDtoDetails = null;

    @Override
    public int insert(DtoScheduleDetail dto) throws SQLException {

        this.dbc = new DbConnection();

        this.script = """
            INSERT INTO tscheduleDetail
            (idDetail, idSchedule, scheduleDate, startTime, endTime, status)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);

        this.entityScheduleDetail = new EntityScheduleDetail();
        this.entityScheduleDetail.setIdDetail(dto.getIdDetail());

        EntitySchedule entSchedule = new EntitySchedule();
        entSchedule.setIdSchedule(dto.getDtoSchedule().getIdSchedule());
        this.entityScheduleDetail.setEntitySchedule(entSchedule);

        this.entityScheduleDetail.setScheduleDate(dto.getScheduleDate());
        this.entityScheduleDetail.setStartTime(dto.getStartTime());
        this.entityScheduleDetail.setEndTime(dto.getEndTime());
        this.entityScheduleDetail.setStatus(dto.getStatus());

        ps.setString(1, this.entityScheduleDetail.getIdDetail());
        ps.setString(2, this.entityScheduleDetail.getEntitySchedule().getIdSchedule());
        ps.setDate(3, new java.sql.Date(this.entityScheduleDetail.getScheduleDate().getTime()));
        ps.setTime(4, this.entityScheduleDetail.getStartTime());
        ps.setTime(5, this.entityScheduleDetail.getEndTime());
        ps.setString(6, this.entityScheduleDetail.getStatus());

        this.rowsQuantityAfected = ps.executeUpdate();

        ps.close();
        this.dbc.connection.close();

        return this.rowsQuantityAfected;
    }

    @Override
    public List<DtoScheduleDetail> getAll() throws SQLException {

        this.dbc = new DbConnection();

        this.listDetails = new ArrayList<>();
        this.listDtoDetails = new ArrayList<>();

        this.script = "SELECT * FROM tscheduleDetail";

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ResultSet rs = ps.executeQuery();


        while (rs.next()) {

            EntityScheduleDetail ent = new EntityScheduleDetail();

            ent.setIdDetail(rs.getString("idDetail"));

            EntitySchedule entSchedule = new EntitySchedule();
            entSchedule.setIdSchedule(rs.getString("idSchedule"));
            ent.setEntitySchedule(entSchedule);

            ent.setScheduleDate(rs.getDate("scheduleDate"));
            ent.setStartTime(rs.getTime("startTime"));
            ent.setEndTime(rs.getTime("endTime"));
            ent.setStatus(rs.getString("status"));

            this.listDetails.add(ent);
        }

        for (EntityScheduleDetail ent : this.listDetails) {

            DtoScheduleDetail dto = new DtoScheduleDetail();

            dto.setIdDetail(ent.getIdDetail());
            dto.setScheduleDate(ent.getScheduleDate());
            dto.setStartTime(ent.getStartTime());
            dto.setEndTime(ent.getEndTime());
            dto.setStatus(ent.getStatus());

            DtoSchedule dtoSchedule = new DtoSchedule();
                dtoSchedule.setIdSchedule(ent.getEntitySchedule().getIdSchedule());
                dto.setDtoSchedule(dtoSchedule);

            this.listDtoDetails.add(dto);
        }

        return this.listDtoDetails;
    }
   
    @Override
    public List<String> getFreeHours(String idSchedule, Date date) throws SQLException {
        this.dbc = new DbConnection();
        List<String> freeHours = new ArrayList<>();

        this.script = "SELECT startTime, endTime FROM tscheduleDetail "
                    + "WHERE idSchedule = ? AND scheduleDate = ? "
                    + "AND status = 'libre' "
                    + "ORDER BY startTime";

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ps.setString(1, idSchedule);
        ps.setDate(2, new java.sql.Date(date.getTime()));

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String start = rs.getString("startTime");
            String end = rs.getString("endTime");

            freeHours.add(start + " - " + end);
        }

        this.dbc.connection.close();
        return freeHours;
    }

    @Override
    public DtoScheduleDetail getByEmployeeDateTime(String idEmployee, Date date, Time time) throws SQLException {
        this.dbc = new DbConnection();

        this.script = """
            SELECT sd.* 
            FROM tscheduleDetail sd
            INNER JOIN tschedule s ON sd.idSchedule = s.idSchedule
            WHERE s.idEmployee = ?
              AND sd.scheduleDate = ?
              AND sd.startTime = ?
        """;

        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ps.setString(1, idEmployee);
        ps.setDate(2, new java.sql.Date(date.getTime()));
        ps.setTime(3, time);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DtoScheduleDetail d = new DtoScheduleDetail();
            d.setIdDetail(rs.getString("idDetail"));
            d.setStatus(rs.getString("status"));
            d.setScheduleDate(rs.getDate("scheduleDate"));
            d.setStartTime(rs.getTime("startTime"));
            d.setEndTime(rs.getTime("endTime"));
            return d;
        }
        return null;
    }
    
    @Override
    public int updateStatus(String idDetail, String status) throws SQLException {
        this.dbc = new DbConnection();
        
        this.script = "UPDATE tscheduleDetail SET status=? WHERE idDetail=?";
        PreparedStatement ps = this.dbc.connection.prepareStatement(this.script);
        ps.setString(1, status);
        ps.setString(2, idDetail);
        return ps.executeUpdate();
    }

}
