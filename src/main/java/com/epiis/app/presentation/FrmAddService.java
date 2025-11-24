/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.epiis.app.presentation;

import com.epiis.app.business.BusinessService;
import com.epiis.app.business.BusinessSpecialty;
import com.epiis.app.dto.DtoService;
import com.epiis.app.dto.DtoSpecialty;
import com.epiis.app.config.Message;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author LAB306PC04
 */
public class FrmAddService extends javax.swing.JInternalFrame {
    BusinessService businessService = null;
    BusinessSpecialty businessSpecialty = null;
    
    private final DefaultTableModel dtmTablePerson = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 3;
        }
    };

    private class ButtonRendererEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
        private final JButton renderButton = new JButton();
        private final JButton editorButton = new JButton();
        private String text;
        private ActionListener actionListener;
        private JTable table;

        public ButtonRendererEditor(JTable table, ActionListener actionListener) {
            this.table = table;
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
     * Creates new form FrmGetAllServicio
     */
    public FrmAddService() {
        initComponents();
        this.init();
    }
    private void init() {
        try {
            this.businessService = new BusinessService();
            this.businessSpecialty = new BusinessSpecialty();

            List<DtoService> listService = this.businessService.getAll();

            this.tableService.setModel(dtmTablePerson);
            dtmTablePerson.addColumn("ID");          
            dtmTablePerson.addColumn("Servicio");
            dtmTablePerson.addColumn("Descripción");
            dtmTablePerson.addColumn("Eliminar");   

            for (DtoService item : listService) {
                dtmTablePerson.addRow(new Object[]{
                        item.getIdService(),
                        item.getName(),
                        item.getDescription(),
                        "Eliminar"
                });
            }

            // Ocultar columna ID (solo de la vista, el modelo sigue teniendo los datos)
            tableService.removeColumn(tableService.getColumnModel().getColumn(0));

            // Llenar combo de especialidades
            comboSpe.removeAllItems();
            List<DtoSpecialty> listSpecialty = businessSpecialty.getAll();
            for (DtoSpecialty spe : listSpecialty) {
                comboSpe.addItem(spe.getName());
            }

            ButtonRendererEditor eliminarButton = new ButtonRendererEditor(tableService, e -> {
                int row = tableService.getSelectedRow(); // fila seleccionada
                if (row >= 0) {
                    int modelRow = tableService.convertRowIndexToModel(row); // convertir a fila del modelo
                    try {
                        String idService = dtmTablePerson.getValueAt(modelRow, 0).toString(); // ID oculto

                        // Pregunta de confirmación
                        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                                this,
                                "¿Está seguro que desea eliminar este servicio?",
                                "Confirmar eliminación",
                                javax.swing.JOptionPane.YES_NO_OPTION
                        );

                        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                            if (businessService.delete(idService)) {
                                dtmTablePerson.removeRow(modelRow);
                                Message.success(this, "Servicio eliminado correctamente.");
                            } else {
                                Message.warning(this, "No se pudo eliminar el servicio.");
                            }
                        }
                    } catch (SQLException ex) {
                        Message.exception(this, "Error al eliminar el servicio: " + ex.getMessage());
                    }
                }
            });
            tableService.getColumn("Eliminar").setCellRenderer(eliminarButton);
            tableService.getColumn("Eliminar").setCellEditor(eliminarButton);

        } catch (SQLException ex) {
            Message.exception(this, "Error al cargar datos: " + ex.getMessage());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tableService = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        comboSpe = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gestionar servicio");

        jScrollPane1.setViewportView(tableService);

        jLabel3.setText("Lista de servicios");

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

        jLabel4.setText("Especialidad");

        comboSpe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName)
                    .addComponent(txtDescription)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addComponent(comboSpe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboSpe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButton1))
                            .addComponent(jButton2)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String serviceName = txtName.getText().trim();
            String description = txtDescription.getText().trim();
            String specialtyName = (String) comboSpe.getSelectedItem();

            if(this.businessService.insert(specialtyName, serviceName, description)) {
                Message.success(this, "Servicio agregado correctamente.");

                txtName.setText("");
                txtDescription.setText("");
                comboSpe.setSelectedIndex(0);
            } else {
                Message.warning(this, "No se pudo agregar el servicio. Verifique los datos.");
            }

        } catch (IllegalArgumentException ex) {
            Message.warning(this, ex.getMessage());
        } catch (SQLException ex) {
            Message.exception(this, "Error al acceder a la base de datos: " + ex.getMessage());
        } catch (Exception ex) {
            Message.exception(this, "Ocurrió un error inesperado: " + ex.getMessage());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboSpe;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableService;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
