/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dto;

/**
 *
 * @author LAB306PC01
 */
public class DtoService {
    private String idService;
    private DtoSpecialty dtoSpecialty;
    private String name;
    private String description;

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdService() {
        return idService;
    }

    public void setDtoSpecialty(DtoSpecialty dtoSpecialty) {
        this.dtoSpecialty = dtoSpecialty;
    }

    public DtoSpecialty getDtoSpecialty() {
        return dtoSpecialty;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
}
