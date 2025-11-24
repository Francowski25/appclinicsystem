/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.epiis.app.presentation;

import com.epiis.app.business.BusinessAppt;
import com.epiis.app.business.BusinessApptRequet;
import com.epiis.app.dto.DtoAppt;
import com.epiis.app.dto.DtoApptRequest;
import com.epiis.app.dto.DtoPatient;
import com.epiis.app.config.Message;
import com.epiis.app.config.ReportUtils;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LAB306PC10
 */
public class FrmManageAppt extends javax.swing.JInternalFrame {

    private BusinessAppt businessAppt;
    private BusinessApptRequet businessApptRequet;

    private List<DtoAppt> allAppts;
    private List<DtoApptRequest> allApptRequests;

    private final DefaultTableModel dtmTablePerson = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Solo la columna "Estado" será editable si se desea
            return false;
        }
    };

    public FrmManageAppt() {
        initComponents();
        initBusinessObjects();
        setupTable();
        setupCombos();
        loadAllData();
        setupListeners();
          setupExportButton();
    }
    
    private void setupExportButton() {
        jButton1.addActionListener(e -> {
            String jrxmlPath = "E:\\jackzinho\\appclinicsystem\\src\\main\\resources\\reports\\manageAppt.jrxml";

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String outputPdf = "E:\\jackzinho\\appclinicsystem\\reports\\citas\\manageAppt_" + timestamp + ".pdf";
            ReportUtils.exportTableToPDF(tableAppt, jrxmlPath, outputPdf);

            com.epiis.app.config.ReportUtils.exportTableToPDF(tableAppt, jrxmlPath, outputPdf);
        });
    }

    private void initBusinessObjects() {
        this.businessAppt = new BusinessAppt();
        this.businessApptRequet = new BusinessApptRequet();
    }

    private void setupTable() {
        tableAppt.setModel(dtmTablePerson);
        dtmTablePerson.setRowCount(0);
        dtmTablePerson.setColumnIdentifiers(new Object[]{
            "Dni", "Nombre completo", "Médico", "Razón", "Estado", "Tipo"
        });
    }

    private void setupCombos() {
        comboType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Todos", "offline", "online"}));
        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Todos", "pendiente", "aprobada", "rechazada", "confirmada", "cancelada", "atendida", "reprogramada"}));
    }

    private void loadAllData() {
        try {
            allAppts = businessAppt.getAllPatient();
            allApptRequests = businessApptRequet.getAllPatient();
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
            Message.exception(this, "Error al cargar citas: " + e.getMessage());
        }
    }

    private String fullName(DtoPatient patient) {
        return patient.getFirstName() + " " + patient.getSurNameP() + " " + patient.getSurNameM();
    }

    private void addApptToTable(DtoAppt item) {
        addRowToTable(item.getDtoPatient().getDni(), fullName(item.getDtoPatient()),
                      item.getDtoEmployee().getFirstName(), item.getReason(),
                      item.getStatus(), item.getType());
    }

    private void addApptRequestToTable(DtoApptRequest item) {
        // Siempre offline para solicitudes
        addRowToTable(item.getDtoPatient().getDni(), fullName(item.getDtoPatient()),
                      item.getDtoEmployee().getFirstName(), item.getReason(),
                      item.getStatus(), "offline");
    }

    private void addRowToTable(Integer dni, String nombreCompleto, String medico, String razon, String estado, String tipo) {
        dtmTablePerson.addRow(new Object[]{dni, nombreCompleto, medico, razon, estado, tipo});
    }

    private void refreshTable() {
        dtmTablePerson.setRowCount(0);

        String selectedType = comboType.getSelectedItem().toString();
        String selectedStatus = comboStatus.getSelectedItem().toString();

        // Filtrar citas confirmadas
        allAppts.stream()
                .filter(a -> (selectedType.equals("Todos") || a.getType().equalsIgnoreCase(selectedType)) &&
                             (selectedStatus.equals("Todos") || a.getStatus().equalsIgnoreCase(selectedStatus)))
                .forEach(this::addApptToTable);

        // Filtrar solicitudes (tipo siempre "offline")
        allApptRequests.stream()
                .filter(a -> (selectedType.equals("Todos") || "offline".equalsIgnoreCase(selectedType)) &&
                             (selectedStatus.equals("Todos") || a.getStatus().equalsIgnoreCase(selectedStatus)))
                .forEach(this::addApptRequestToTable);
    }

    private void setupListeners() {
        comboType.addActionListener(e -> refreshTable());
        comboStatus.addActionListener(e -> refreshTable());
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableAppt = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboType = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comboStatus = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestionar cita");

        jScrollPane1.setViewportView(tableAppt);

        jLabel1.setText("Listar de citas");

        jLabel2.setText("Listar por tipo de cita");

        comboType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "offline", "online" }));

        jLabel3.setText("Por estado de cita");

        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Exportar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboType, javax.swing.GroupLayout.Alignment.LEADING, 0, 150, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboStatus, 0, 150, Short.MAX_VALUE))
                        .addGap(87, 87, 87)
                        .addComponent(jButton1)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JComboBox<String> comboType;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableAppt;
    // End of variables declaration//GEN-END:variables
}
