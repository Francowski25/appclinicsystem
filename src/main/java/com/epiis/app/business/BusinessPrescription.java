/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.business;

import com.epiis.app.dataaccess.query.QPrescription;
import com.epiis.app.dto.DtoPrescription;
import com.epiis.app.repository.RepoPrescription;
import java.sql.SQLException;
import java.util.UUID;

/**
 *
 * @author LIZ
 */
public class BusinessPrescription {
    DtoPrescription dtoPrescription = null;

    public BusinessPrescription() {
        this.dtoPrescription = new DtoPrescription();
    }
    
    public boolean insert(String medicine, String dosage, String frequency, String duration, String instructions) throws SQLException{
        RepoPrescription repoPrescription = new QPrescription();
 
        if (medicine == null || medicine.isBlank()) {
            throw new IllegalArgumentException("El nombre del medicamento es obligatorio.");
        }
        if (dosage == null || dosage.isBlank()) {
            throw new IllegalArgumentException("La dosis es obligatoria.");
        }

        this.dtoPrescription.setIdPrescription(UUID.randomUUID().toString());
        this.dtoPrescription.setMedicine(medicine);
        this.dtoPrescription.setDosage(dosage);
        this.dtoPrescription.setFrequency(frequency);
        this.dtoPrescription.setDuration(duration);
        this.dtoPrescription.setInstructions(instructions);

        
        return repoPrescription.insert(dtoPrescription) > 0;
    }
}
