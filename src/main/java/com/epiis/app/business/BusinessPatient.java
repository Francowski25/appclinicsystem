/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.business;

import com.epiis.app.dataaccess.query.QPatient;
import com.epiis.app.dto.DtoPatient;
import com.epiis.app.repository.RepoPatient;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author LIZ
 */
public class BusinessPatient {
    DtoPatient dtoPatient = null;
    BusinessEmployee businessEmployee = null;
    
    public BusinessPatient() {
        this.dtoPatient = new DtoPatient();
    }
    
    public boolean insert(String dni, String firstName, String surNameP, String surNameM,
                          String gender, String email, String phone,
                          String password, String status) throws SQLException {

        RepoPatient repoPatient = new QPatient();
        businessEmployee = new BusinessEmployee();


        if (dni.isBlank()) {
            throw new IllegalArgumentException("El campo \"DNI\" es obligatorio.");
        }

        if (dni.length() != 8) {
            throw new IllegalArgumentException("El campo \"DNI\" debe contener exactamente 8 dígitos numéricos.");
        }

        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("El campo \"Nombre\" es obligatorio.");
        }

        if (!firstName.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("El campo \"Nombre\" solo puede contener letras.");
        }

        if (surNameP == null || surNameP.isBlank()) {
            throw new IllegalArgumentException("El campo \"Apellido paterno\" es obligatorio.");
        }

        if (!surNameP.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("El campo \"Apellido paterno\" solo puede contener letras.");
        }

        if (surNameM == null || surNameM.isBlank()) {
            throw new IllegalArgumentException("El campo \"Apellido materno\" es obligatorio.");
        }

        if (!surNameM.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("El campo \"Apellido materno\" solo puede contener letras.");
        }

        if (gender == null || gender.isBlank()) {
            throw new IllegalArgumentException("Debe seleccionar un valor en \"Género\".");
        }
        
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("El campo \"Teléfono\" es obligatorio.");
        }

        if (!phone.matches("\\d{7,15}")) {
            throw new IllegalArgumentException("El campo \"Teléfono\" debe contener solo números (entre 7 y 15 dígitos).");
        }
        
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El campo \"Email\" es obligatorio.");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("El campo \"Email\" debe tener un formato válido (ejemplo: usuario@dominio.com).");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("El campo \"Contraseña\" es obligatorio.");
        }

        if (password.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }

        if (!password.matches(".*[@#$%^&+=].*")) {
            throw new IllegalArgumentException("La contraseña debe contener al menos un carácter especial (@, #, $, %, etc).");
        }
        
        if (repoPatient.getByDni(Integer.parseInt(dni)) != null) {
            throw new IllegalArgumentException("El DNI ingresado ya está registrado.");
        }

        if (businessEmployee.getByDni(Integer.parseInt(dni)) != null) {
            throw new IllegalArgumentException("El DNI ingresado ya está registrado.");
        }


        this.dtoPatient.setIdPatient(UUID.randomUUID().toString());
        this.dtoPatient.setDni(Integer.parseInt(dni));
        this.dtoPatient.setFirstName(firstName);
        this.dtoPatient.setSurNameP(surNameP);
        this.dtoPatient.setSurNameM(surNameM);
        this.dtoPatient.setGender(gender);
        this.dtoPatient.setEmail(email);
        this.dtoPatient.setPhone(phone);
        this.dtoPatient.setPassword(password);
        this.dtoPatient.setCreatedAt(new Date());
        this.dtoPatient.setStatus(status);

        return repoPatient.insert(dtoPatient) > 0;
    }

    public boolean update(String idPatient, int dni, String firstName, String surNameP, String surNameM, String gender, String email, String phone, String password, String status) throws SQLException {
        RepoPatient repoPatient = new QPatient();
        
        if(firstName.isEmpty() || surNameM.isEmpty() || surNameP.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("Los campos obligatorios están vacíos");
        }

        this.dtoPatient.setIdPatient(idPatient);
        this.dtoPatient.setDni(dni);
        this.dtoPatient.setFirstName(firstName);
        this.dtoPatient.setSurNameP(surNameP);
        this.dtoPatient.setSurNameM(surNameM);
        this.dtoPatient.setGender(gender);
        this.dtoPatient.setEmail(email);
        this.dtoPatient.setPhone(phone);
        this.dtoPatient.setPassword(password);
        this.dtoPatient.setCreatedAt(new Date());
        this.dtoPatient.setStatus(status);

        return repoPatient.update(dtoPatient) > 0;
    }
        
    public List<DtoPatient> getAll() throws SQLException {
        RepoPatient repoPerson = new QPatient();
        return repoPerson.getAll();
    }
    
    public DtoPatient getByDni(String dni) throws SQLException {
        RepoPatient repoPatient = new QPatient();

        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("El campo \"DNI\" es obligatorio.");
        }

        if (!dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("El campo \"DNI\" debe contener exactamente 8 dígitos numéricos.");
        }

        int dniInt = Integer.parseInt(dni);
        return repoPatient.getByDni(dniInt);
    }

    public DtoPatient getByEmailAndPasssword(String email, String password) throws SQLException{
        RepoPatient repoPatient = new QPatient();
        if(email.isBlank()){
            throw new IllegalArgumentException("El campo \"Emial\" es obligatorio.");
        }
        if(password.isBlank()){
            throw new IllegalArgumentException("El campo \"Contraseña\" es obligatorio.");
        }
        return repoPatient.getByEmailAndPasssword(email, password);
    }
}
