/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.epiis.app.presentation;

import com.epiis.app.business.BusinessEmployee;
import com.epiis.app.dto.DtoEmployee;
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
 * @author LAB306PC10
 */
public class FrmDeactive extends javax.swing.JInternalFrame {
    BusinessEmployee businessEmployee = null;
    String role = null;
    
    private final DefaultTableModel dtmTablePerson = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 5 || column == 6; // Editar y Desactivar
        }
    };

    // Renderer y Editor para botones en JTable
    private class ButtonActionRendererEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

        private final JButton renderButton = new JButton();
        private final JButton editorButton = new JButton();
        private String text;
        private ActionListener actionListener;
        private JTable table;

        public ButtonActionRendererEditor(JTable table, ActionListener actionListener) {
            this.table = table;
            this.actionListener = actionListener;

            editorButton.addActionListener(e -> {
                int row = table.getEditingRow();
                int col = table.getEditingColumn();

                fireEditingStopped();

                if (actionListener != null && row >= 0) {
                    actionListener.actionPerformed(new ActionEvent(table, ActionEvent.ACTION_PERFORMED, row + "," + col));
                }
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
     * Creates new form FrmDeactive 
     * @param role 
     */ 
    public FrmDeactive(String role) { 
        initComponents(); 
        this.role = role; 
        this.init(); 
    }

    private void init() {
        try {
            businessEmployee = new BusinessEmployee();
            List<DtoEmployee> listDtoPerson = businessEmployee.getAll();

            tableEmployee.setModel(dtmTablePerson);

            dtmTablePerson.addColumn("Dni");
            dtmTablePerson.addColumn("Nombre completo");
            dtmTablePerson.addColumn("Tipo");
            dtmTablePerson.addColumn("Phone");
            dtmTablePerson.addColumn("Estado");
            dtmTablePerson.addColumn("Editar");
            dtmTablePerson.addColumn("Desactivar");

            for (DtoEmployee item : listDtoPerson) {
                if (item.getRole().equalsIgnoreCase(role)) { // mostramos todos los roles del mismo tipo
                    String accion = item.getStatus().equalsIgnoreCase("activo") ? "Desactivar" : "Activar";
                    dtmTablePerson.addRow(new Object[]{
                        item.getDni(),
                        item.getFirstName() + " " + item.getSurNameP() + " " + item.getSurNameM(),
                        item.getRole(),
                        item.getPhone(),
                        item.getStatus(),
                        "Editar",
                        accion
                    });
                }
            }

            // Botón Activar/Desactivar dinámico
            ButtonActionRendererEditor toggleButton = new ButtonActionRendererEditor(tableEmployee, e -> {
                String[] pos = e.getActionCommand().split(",");
                int viewRow = Integer.parseInt(pos[0]);
                int modelRow = tableEmployee.convertRowIndexToModel(viewRow);

                try {
                    int dni = Integer.parseInt(tableEmployee.getValueAt(viewRow, 0).toString());
                    String currentStatus = tableEmployee.getValueAt(viewRow, 4).toString();

                    int confirm = JOptionPane.showConfirmDialog(
                        this,
                        currentStatus.equalsIgnoreCase("activo") ? 
                            "¿Está seguro de desactivar este usuario?" : 
                            "¿Está seguro de activar este usuario?",
                        "Confirmar acción",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        if (currentStatus.equalsIgnoreCase("activo")) {
                            businessEmployee.deactivate(dni);
                            tableEmployee.setValueAt("inactivo", viewRow, 4);
                            tableEmployee.setValueAt("Activar", viewRow, 6);
                        } else {
                            businessEmployee.activate(dni);
                            tableEmployee.setValueAt("activo", viewRow, 4);
                            tableEmployee.setValueAt("Desactivar", viewRow, 6);
                        }
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            tableEmployee.getColumn("Desactivar").setCellRenderer(toggleButton);
            tableEmployee.getColumn("Desactivar").setCellEditor(toggleButton);

            // Botón Editar
            ButtonActionRendererEditor editarButton = new ButtonActionRendererEditor(tableEmployee, e -> {
                String[] pos = e.getActionCommand().split(",");
                int viewRow = Integer.parseInt(pos[0]);
                int modelRow = tableEmployee.convertRowIndexToModel(viewRow);
                try {
                    int dni = Integer.parseInt(tableEmployee.getValueAt(viewRow, 0).toString());
                    DtoEmployee dto = businessEmployee.getByDni(dni);
                    if (dto != null) {
                        FrmUpdateUser frmUpdate = new FrmUpdateUser(dto);
                        FrmDashboardAdmin.desktopPane.add(frmUpdate);
                        frmUpdate.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            tableEmployee.getColumn("Editar").setCellRenderer(editarButton);
            tableEmployee.getColumn("Editar").setCellEditor(editarButton);

        } catch (SQLException ex) {
            ex.printStackTrace();
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

        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmployee = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Desactivar usuarios");

        jLabel2.setText("Buscar por dni");

        jLabel4.setText("Lista de usuarios");

        jScrollPane1.setViewportView(tableEmployee);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableEmployee;
    // End of variables declaration//GEN-END:variables
}
