/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package com.epiis.app.presentation;

import com.epiis.app.business.BusinessAppt;
import com.epiis.app.business.BusinessPatient;
import com.epiis.app.dto.DtoAppt;
import com.epiis.app.dto.DtoEmployee;
import com.epiis.app.dto.DtoPatient;
import com.epiis.app.config.Message;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author USUARIO
 */
public class FrmDashboardMedico extends javax.swing.JFrame {
    BusinessPatient businessPatient = null;
    BusinessAppt businessAppt = null;
    DtoEmployee dtoEmployee = null;
        
    private final DefaultTableModel dtmTablePerson = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 6;
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
     * Creates new form FrmDashboardMedico
     * @param dtoEmployee
     */
    public FrmDashboardMedico(DtoEmployee dtoEmployee) {
        this.dtoEmployee = dtoEmployee;
        initComponents();
        this.init();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private void init() {
        try {
            this.businessAppt = new BusinessAppt(); 
            this.businessPatient = new BusinessPatient();
            List<DtoAppt> listAppt = this.businessAppt.getAllPatient();
            
            this.tableAppt.setModel(dtmTablePerson);
            dtmTablePerson.setRowCount(0);

            this.dtmTablePerson.addColumn("Dni");
            this.dtmTablePerson.addColumn("Nombre completo");
            this.dtmTablePerson.addColumn("Fecha cita");
            this.dtmTablePerson.addColumn("Motivo");
            this.dtmTablePerson.addColumn("Estado");
            this.dtmTablePerson.addColumn("tipo");
            this.dtmTablePerson.addColumn("Acción");

            for (DtoAppt item : listAppt) {
                if(item.getDtoEmployee().getIdEmployee().equals(this.dtoEmployee.getIdEmployee()) && item.getStatus().equalsIgnoreCase("confirmada")){
                    this.dtmTablePerson.addRow(new Object[]{
                        item.getDtoPatient().getDni(),
                        item.getDtoPatient().getFirstName() + " " + item.getDtoPatient().getSurNameP() + " " + item.getDtoPatient().getSurNameM(),
                        item.getApptDate() + " " + item.getApptTime(),
                        item.getReason(),
                        item.getStatus(),
                        item.getType(),
                        "Atender",
                    });
                }
            }
            tableAppt.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = tableAppt.rowAtPoint(evt.getPoint());
                    int column = tableAppt.columnAtPoint(evt.getPoint());

                    if (column == 6) {
                        String dni = tableAppt.getValueAt(row, 0).toString();
                            try {
                                DtoPatient dtoPatient = businessPatient.getByDni(dni);
                                if (dtoPatient != null) {
                                    FrmAttendPatient ap = new FrmAttendPatient(dtoPatient);
                                    desktopPane.add(ap);
                                    ap.setVisible(true);
                                } 
                            } catch (SQLException ex) {
                                //Message.warning(this, "Error al buscar paciente: " + ex.getMessage());
                            }
                    }
                }
            });

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this, "Error al cargar datos: " + ex.getMessage());
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

        desktopPane = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAppt = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel de control del médico");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Citas programadas del día");
        desktopPane.add(jLabel1);
        jLabel1.setBounds(20, 20, 620, 25);

        jScrollPane1.setViewportView(tableAppt);

        desktopPane.add(jScrollPane1);
        jScrollPane1.setBounds(20, 50, 620, 120);

        fileMenu.setIcon(new javax.swing.ImageIcon("E:\\jackzinho\\appclinicsystem\\icons\\perfil.png")); // NOI18N
        fileMenu.setMnemonic('f');
        fileMenu.setText("Perfil");
        fileMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fileMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Editar perfil");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Cerrar sesión");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        menuBar.add(fileMenu);

        editMenu.setIcon(new javax.swing.ImageIcon("E:\\jackzinho\\appclinicsystem\\icons\\historial.png")); // NOI18N
        editMenu.setMnemonic('e');
        editMenu.setText("Historial");
        editMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Historial clinico");
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(cutMenuItem);

        menuBar.add(editMenu);

        helpMenu.setIcon(new javax.swing.ImageIcon("E:\\jackzinho\\appclinicsystem\\icons\\cita.png")); // NOI18N
        helpMenu.setMnemonic('h');
        helpMenu.setText("Citas");
        helpMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        helpMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Citas programadas");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("Atender citas");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        jMenu2.setText("Horario");

        jMenuItem1.setText("Mi horario");
        jMenu2.add(jMenuItem1);

        menuBar.add(jMenu2);

        jMenu1.setIcon(new javax.swing.ImageIcon("E:\\jackzinho\\appclinicsystem\\icons\\reporte.png")); // NOI18N
        jMenu1.setText("Reportes");
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuBar.add(jMenu1);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        // TODO add your handling code here:
        FrmUpdateUser updateUser = new FrmUpdateUser(this.dtoEmployee);
        desktopPane.add(updateUser);
        updateUser.setVisible(true);
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        // TODO add your handling code here:
        FrmLogin login = new FrmLogin();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cutMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_aboutMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    public static javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTable tableAppt;
    // End of variables declaration//GEN-END:variables

}
