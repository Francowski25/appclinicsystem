/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epiis.app.business;

import com.epiis.app.dataaccess.query.QEmployee;
import com.epiis.app.dto.DtoEmployee;
import com.epiis.app.dto.DtoPatient;
import com.epiis.app.repository.RepoEmployee;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author LIZ
 */
public class BusinessEmployee {
    DtoEmployee dtoEmployee = null;
    BusinessPatient businessPatient = null;
    
    public BusinessEmployee(){
        this.dtoEmployee = new DtoEmployee();
    }
    
    public boolean insert(String dniEmployee, String firstName, String surNameP, String surNameM, String role, String email, String phone, String password, String status) throws SQLException {
        RepoEmployee repoEmployee = new QEmployee();
        
        businessPatient = new BusinessPatient();

        if (dniEmployee == null || dniEmployee.isBlank()) {
            throw new IllegalArgumentException("El campo \"DNI\" es obligatorio.");
        }

        if (!dniEmployee.matches("\\d{8}")) {
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

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El campo \"Email\" es obligatorio.");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("El campo \"Email\" debe tener un formato válido (ejemplo: usuario@dominio.com).");
        }

        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("El campo \"Teléfono\" es obligatorio.");
        }

        if (!phone.matches("\\d{7,15}")) {
            throw new IllegalArgumentException("El campo \"Teléfono\" debe contener solo números (entre 7 y 15 dígitos).");
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
        
        int dni = Integer.parseInt(dniEmployee);

        for(DtoEmployee employee : this.getAll()){
            if(employee.getDni() == dni){
                throw new IllegalArgumentException("En Dni ingresado ya existe en el sistema.");
            }
        }

        for (DtoPatient emp : businessPatient.getAll()) {
            if (emp.getDni() == dni) {
                throw new IllegalArgumentException("En Dni ingresado ya existe en el sistema.");
            }
        }

        this.dtoEmployee.setIdEmployee(UUID.randomUUID().toString());
        this.dtoEmployee.setDni(dni);
        this.dtoEmployee.setFirstName(firstName);
        this.dtoEmployee.setSurNameP(surNameP);
        this.dtoEmployee.setSurNameM(surNameM);
        this.dtoEmployee.setRole(role);
        this.dtoEmployee.setEmail(email);
        this.dtoEmployee.setPhone(phone);
        this.dtoEmployee.setPassword(password);
        this.dtoEmployee.setCreatedAt(new Date());
        this.dtoEmployee.setStatus(status);

        return repoEmployee.insert(dtoEmployee) > 0;
    }
    
    public boolean update(String idEmployee, int dni, String firstName, String surNameP, String surNameM, String role, String email, String phone, String password, String status) throws SQLException {
        RepoEmployee repoEmployee = new QEmployee();
        

        if(firstName.isEmpty() || surNameM.isEmpty() || surNameP.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("Los campos obligatorios están vacíos");
        }
        
        this.dtoEmployee.setIdEmployee(idEmployee);
        this.dtoEmployee.setDni(dni);
        this.dtoEmployee.setFirstName(firstName);
        this.dtoEmployee.setSurNameP(surNameP);
        this.dtoEmployee.setSurNameM(surNameM);
        this.dtoEmployee.setRole(role);
        this.dtoEmployee.setEmail(email);
        this.dtoEmployee.setPhone(phone);
        this.dtoEmployee.setPassword(password);
        this.dtoEmployee.setStatus(status);

        return repoEmployee.update(dtoEmployee) > 0;
    }
        
    public List<DtoEmployee> getAll() throws SQLException {
        RepoEmployee repoEmployee = new QEmployee();
        return repoEmployee.getAll();
    }
    
    public void deactivate(int dni) throws SQLException {
        QEmployee qEmployee = new QEmployee();
        DtoEmployee dto = qEmployee.getByDni(dni);
        if (dto != null) {
            dto.setStatus("desactivo");
            qEmployee.update(dto);
        }
    }
    
    public void activate(int dni) throws SQLException {
        QEmployee qEmployee = new QEmployee();
        DtoEmployee dto = qEmployee.getByDni(dni);
        if (dto != null) {
            dto.setStatus("activo");
            qEmployee.update(dto);
        }
    }
    
    public DtoEmployee getByDni(int dni) throws SQLException{
        RepoEmployee repoEmployee = new QEmployee();

        return repoEmployee.getByDni(dni); 
    }

    public DtoEmployee getByFirstName(String firstName) throws SQLException {
        RepoEmployee repoEmployee = new QEmployee();
        
        return repoEmployee.getByFirstName(firstName);
    }
    
    public DtoEmployee getByEmailAndPassword(String email, String password) throws SQLException{
        RepoEmployee repoEmployee = new QEmployee();
        
        if(email.isBlank()){
            throw new IllegalArgumentException("El campo \"Emial\" es obligatorio.");
        }
        if(password.isBlank()){
            throw new IllegalArgumentException("El campo \"Contraseña\" es obligatorio.");
        }

        
        return repoEmployee.getByEmailAndPassword(email, password);
    }
}
