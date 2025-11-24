/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.dataaccess.entity;

/**
 *
 * @author jhona
 */
public class EntityService {
    private String idService;
    private EntitySpecialty entitySpecialty;
    private String name;
    private String description;

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public void setEntitySpecialty(EntitySpecialty entitySpecialty) {
        this.entitySpecialty = entitySpecialty;
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

    public EntitySpecialty getEntitySpecialty() {
        return entitySpecialty;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    
}
