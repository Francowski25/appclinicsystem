/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.business;

import com.epiis.app.dataaccess.query.QDoctorSpecialty;
import com.epiis.app.dto.DtoDoctorSpecialty;
import com.epiis.app.dto.DtoEmployee;
import com.epiis.app.dto.DtoSpecialty;
import com.epiis.app.repository.RepoDoctorSpecialty;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LIZ
 */
public class BusinessDoctorSpecialty {
    DtoDoctorSpecialty dtoDoctorSpecialty = null;
    
    public BusinessDoctorSpecialty(){
        this.dtoDoctorSpecialty = new DtoDoctorSpecialty();
    }
    
    public boolean insert(String idEmployee, String idSpecialty) throws SQLException {
        RepoDoctorSpecialty repoDoctorSpecialty = new QDoctorSpecialty();
                this.dtoDoctorSpecialty = new DtoDoctorSpecialty();

        DtoEmployee dtoEmployee = new DtoEmployee();
        dtoEmployee.setIdEmployee(idEmployee);

        DtoSpecialty dtoSpecialty = new DtoSpecialty();
        dtoSpecialty.setIdSpecialty(idSpecialty);
        System.out.println("idEspecialty: " + idSpecialty);
        this.dtoDoctorSpecialty.setDtoEmployee(dtoEmployee);
        this.dtoDoctorSpecialty.setDtoSpecialty(dtoSpecialty);

        return repoDoctorSpecialty.insert(dtoDoctorSpecialty) > 0;
    }
    
    public List<DtoDoctorSpecialty> getAll() throws SQLException{
        RepoDoctorSpecialty repoDoctorSpecialty = new QDoctorSpecialty();
        return repoDoctorSpecialty.getAll();
    }
}
