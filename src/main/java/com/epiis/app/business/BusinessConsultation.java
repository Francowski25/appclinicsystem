/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.business;

import com.epiis.app.dataaccess.query.QConsultation;
import com.epiis.app.dto.DtoConsultation;
import com.epiis.app.repository.RepoConsultation;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author LIZ
 */
public class BusinessConsultation {
    DtoConsultation dtoConsultation = null;

    public BusinessConsultation() {
        this.dtoConsultation = new DtoConsultation();
    }
    
    public boolean insert(String idAppt, String idEmployee, String idPrescription, String diagnosis, String treatment) throws SQLException{
        RepoConsultation repoConsultation = new QConsultation();
    
        dtoConsultation.setIdConsultation(UUID.randomUUID().toString());
        //dtoConsultation.setIdAppt(idAppt);
        //dtoConsultation.setIdEmployee(idEmployee);
        //dtoConsultation.setIdPrescription(idPrescription);
        dtoConsultation.setCreatedAt(new Date());
        dtoConsultation.setDiagnosis(diagnosis);
        dtoConsultation.setTreatment(treatment);


        return repoConsultation.insert(dtoConsultation) > 0;
    }
    
    public List<DtoConsultation> getAll() throws SQLException{
        RepoConsultation repoConsultation = new QConsultation();
        return repoConsultation.getAll();
    }
}
