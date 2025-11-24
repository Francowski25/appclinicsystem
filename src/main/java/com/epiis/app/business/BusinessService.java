/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.business;

import com.epiis.app.dataaccess.query.QService;
import com.epiis.app.dto.DtoService;
import com.epiis.app.dto.DtoSpecialty;
import com.epiis.app.repository.RepoService;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author LIZ
 */
public class BusinessService {
    DtoService dtoService = null;
    BusinessSpecialty businessSpecialty = null;
    
    public BusinessService() {
        this.dtoService = new DtoService();
    }
    
    public boolean insert(String specialtyName, String serviceName, String description) throws SQLException {
        RepoService repoService = new QService();
        this.businessSpecialty = new BusinessSpecialty();
        
        if (serviceName == null || serviceName.isBlank()) {
            throw new IllegalArgumentException("El nombre del servicio es obligatorio.");
        }
        if (specialtyName == null || specialtyName.isBlank()) {
            throw new IllegalArgumentException("Debe seleccionar una especialidad.");
        }
        
        DtoSpecialty selectedSpecialty = null;
        for (DtoSpecialty emp : businessSpecialty.getAll()) {
            if (emp.getName().equalsIgnoreCase(specialtyName)) {
                   selectedSpecialty = emp;
                break;
            }
        }

        if (selectedSpecialty == null) {
            throw new SQLException("La especialidad no existe: " + specialtyName);
        }
        this.dtoService.setIdService(UUID.randomUUID().toString());
        this.dtoService.setName(serviceName);
        this.dtoService.setDescription(description);
        this.dtoService.setDtoSpecialty(selectedSpecialty);

        return repoService.insert(dtoService) > 0;
    }
    
    public List<DtoService> getAll() throws SQLException{
        RepoService repoService = new QService();
        return repoService.getAll();
    }
    
    public boolean delete(String idService) throws SQLException {
        RepoService repoService = new QService();
        return repoService.delete(idService) > 0;
    }

}
