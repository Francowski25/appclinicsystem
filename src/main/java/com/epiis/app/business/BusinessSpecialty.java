/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.business;

import com.epiis.app.dataaccess.query.QSpecialty;
import com.epiis.app.dto.DtoSpecialty;
import com.epiis.app.repository.RepoSpecialty;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author LIZ
 */
public class BusinessSpecialty {
    DtoSpecialty dtoSpecialty = null;
    
    public BusinessSpecialty() {
        this.dtoSpecialty = new DtoSpecialty();
    }
    
    public boolean insert(String name, String description) throws SQLException{
        
        if(name.isBlank() || description.isBlank()){
            return false;
        }
        RepoSpecialty repoSpecialty = new QSpecialty();
        
        this.dtoSpecialty.setIdSpecialty(UUID.randomUUID().toString());
        this.dtoSpecialty.setName(name);
        this.dtoSpecialty.setDescription(description);

        return repoSpecialty.insert(dtoSpecialty) > 0;
    }
    
    public List<DtoSpecialty> getAll() throws SQLException{
        RepoSpecialty repoSpecialty = new QSpecialty();
        return repoSpecialty.getAll();
    }
    
    public boolean delete(String idSpecialty) throws SQLException {
        RepoSpecialty repoSpecialty = new QSpecialty();
        return repoSpecialty.delete(idSpecialty) > 0;
    }
    
    public DtoSpecialty getByName(String name) throws SQLException{
        RepoSpecialty repoSpecialty = new QSpecialty();
        return repoSpecialty.getByName(name);
    }
}
