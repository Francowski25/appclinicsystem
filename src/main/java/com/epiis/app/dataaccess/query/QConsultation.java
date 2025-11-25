/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.query;

import com.epiis.app.connection.DbConnection;
import com.epiis.app.dataaccess.entity.EntityConsultation;
import com.epiis.app.dto.DtoAppt;
import com.epiis.app.dto.DtoConsultation;
import com.epiis.app.dto.DtoEmployee;
import com.epiis.app.dto.DtoPatient;
import com.epiis.app.dto.DtoPrescription;
import com.epiis.app.repository.RepoConsultation;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author LIZ
 */
public class QConsultation implements RepoConsultation {
    private DbConnection dbc = new DbConnection();
    private String script = null;
    private EntityConsultation entityConsultation = null;
    private int rowsQuantityAffected = 0;

    @Override
    public int insert(DtoConsultation dtoConsultation) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DtoConsultation> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DtoConsultation> getByPatient(String idPatient) throws SQLException {
        List<DtoConsultation> list = new ArrayList<>();
        this.script = """
            SELECT 
                c.idConsultation, c.createdAt, c.diagnosis, c.treatment,

                a.idAppt, a.apptDate, a.apptTime, a.reason, a.status, a.type,

                p.idPatient, p.firstName AS pName, p.surNameP AS pSurname, p.surNameM,

                e.idEmployee, e.firstName AS docName, e.surNameP AS docSurname, e.surNameM,

                pr.idPrescription, pr.medicine, pr.dosage, pr.frequency, 
                pr.duration, pr.instructions

            FROM tconsultation c
            INNER JOIN tappointment a ON c.idAppt = a.idAppt
            INNER JOIN tpatient p ON a.idPatient = p.idPatient
            INNER JOIN temployee e ON c.idEmployee = e.idEmployee
            INNER JOIN tprescription pr ON c.idPrescription = pr.idPrescription
            WHERE p.idPatient = ?
            ORDER BY c.createdAt DESC
        """;

        try (Connection conn = this.dbc.connection;
            PreparedStatement ps = conn.prepareStatement(this.script)) {

            ps.setString(1, idPatient);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    DtoConsultation c = new DtoConsultation();
                    c.setIdConsultation(rs.getString("idConsultation"));
                    c.setCreatedAt(rs.getTimestamp("createdAt"));
                    c.setDiagnosis(rs.getString("diagnosis"));
                    c.setTreatment(rs.getString("treatment"));

                    DtoAppt appt = new DtoAppt();
                    appt.setIdAppt(rs.getString("idAppt"));
                    appt.setApptDate(rs.getDate("apptDate"));
                    appt.setApptTime(rs.getTime("apptTime"));
                    appt.setReason(rs.getString("reason"));
                    appt.setStatus(rs.getString("status"));
                    appt.setType(rs.getString("type"));

                    DtoPatient pat = new DtoPatient();
                    pat.setIdPatient(rs.getString("idPatient"));
                    pat.setFirstName(rs.getString("pName"));
                    pat.setSurNameP(rs.getString("pSurname"));

                    appt.setDtoPatient(pat);

                    DtoEmployee doc = new DtoEmployee();
                    doc.setIdEmployee(rs.getString("idEmployee"));
                    doc.setFirstName(rs.getString("docName"));
                    doc.setSurNameP(rs.getString("docSurname"));

                    DtoPrescription pr = new DtoPrescription();
                    pr.setIdPrescription(rs.getString("idPrescription"));
                    pr.setMedicine(rs.getString("medicine"));
                    pr.setDosage(rs.getString("dosage"));
                    pr.setFrequency(rs.getString("frequency"));
                    pr.setDuration(rs.getString("duration"));
                    pr.setInstructions(rs.getString("instructions"));

                    c.setDtoAppt(appt);
                    c.setDtoEmployee(doc);
                    c.setDtoPrescription(pr);

                    list.add(c);
                }
            }
        }

        return list;
    }
}

