/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.epiis.app.presentation;

import com.epiis.app.business.BusinessSpecialty;
import com.epiis.app.dto.DtoSpecialty;
import com.epiis.app.config.Message;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author LAB306PC04
 */
public class FrmAddSpecialty extends javax.swing.JInternalFrame {
    BusinessSpecialty businessSpecialty = null;
    
    private final DefaultTableModel dtmTablePerson = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 3;
        }
    };
    /**
     * Creates new form FrmGetAllEspecialidad
     */
    public FrmAddSpecialty() {
        initComponents();
        this.init();
    }
    
    private void init() {
        try {
            this.businessSpecialty = new BusinessSpecialty();
            List<DtoSpecialty> listDtoSpecialty = this.businessSpecialty.getAll();

            this.tableSpecialty.setModel(dtmTablePerson);
            
            dtmTablePerson.addColumn("ID");          
            this.dtmTablePerson.addColumn("Especialidad");
            this.dtmTablePerson.addColumn("Descripción");
            this.dtmTablePerson.addColumn("Eliminar");

            for (DtoSpecialty item : listDtoSpecialty) {
                this.dtmTablePerson.addRow(new Object[]{
                    item.getIdSpecialty(),
                    item.getName(),
                    item.getDescription(),
                    "Eliminar"
                });
            }
            tableSpecialty.removeColumn(tableSpecialty.getColumnModel().getColumn(0));

            ButtonRendererEditor eliminarButton = new ButtonRendererEditor(tableSpecialty, e -> {
                int row = tableSpecialty.getSelectedRow();
                if (row >= 0) {
                    int modelRow = tableSpecialty.convertRowIndexToModel(row);
                    String specialtyName = dtmTablePerson.getValueAt(modelRow, 0).toString();
                    
                    int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro que desea eliminar la especialidad \"" + specialtyName + "\"?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            if (businessSpecialty.delete(specialtyName)) {
                                dtmTablePerson.removeRow(modelRow);
                                Message.success(this, "Especialidad eliminada correctamente.");
                            } else {
                                Message.warning(this, "No se pudo eliminar la especialidad.");
                            }
                        } catch (SQLException ex) {
                            Message.exception(this, "Error al eliminar la especialidad: " + ex.getMessage());
                        }
                    }
                }
            });

            tableSpecialty.getColumn("Eliminar").setCellRenderer(eliminarButton);
            tableSpecialty.getColumn("Eliminar").setCellEditor(eliminarButton);

        } catch (SQLException ex) {
            Message.exception(this, "Error al cargar especialidades: " + ex.getMessage());
        }
    }

    // Clase interna para render/editor de botones
    private static class ButtonRendererEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
        private final JButton renderButton = new JButton();
        private final JButton editorButton = new JButton();
        private String text;
        private ActionListener actionListener;

        public ButtonRendererEditor(JTable table, ActionListener actionListener) {
            this.actionListener = actionListener;
            editorButton.addActionListener(e -> {
                fireEditingStopped();
                actionListener.actionPerformed(new ActionEvent(table, ActionEvent.ACTION_PERFORMED, text));
            });
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            text = (value == null) ? "" : value.toString();
            renderButton.setText(text);
            return renderButton;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            text = (value == null) ? "" : value.toString();
            editorButton.setText(text);
            return editorButton;
        }

        @Override
        public Object getCellEditorValue() {
            return text;
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSpecialty = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestionar especialidad");

        jLabel1.setText("Nombre");

        jLabel2.setText("Detalle");

        jButton1.setIcon(new javax.swing.ImageIcon("F:\\jackzinho\\appclinicsystem\\icons\\crear.png")); // NOI18N
        jButton1.setText("Crear");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon("F:\\jackzinho\\appclinicsystem\\icons\\salir.png")); // NOI18N
        jButton2.setText("Salir");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(tableSpecialty);

        jLabel3.setText("Lista de especialidades");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(136, 136, 136))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtName)
                            .addGap(18, 18, 18))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtDescription)
                            .addGap(18, 18, 18)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String specialty = txtName.getText().trim();
            String description = txtDescription.getText().trim();
            if(this.businessSpecialty.insert(specialty, description)){
                Message.success(this, "Servicio agregado correctamente.");
                txtName.setText("");
                txtDescription.setText("");
            } else {
                Message.warning(this, "No se pudo agregar el servicio. Verifique los datos.");

            }
        } catch (SQLException e) {
            Message.error(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableSpecialty;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
