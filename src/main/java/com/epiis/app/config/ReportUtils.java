package com.epiis.app.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Utilidades para exportar datos de JTable a PDF usando JasperReports.
 */
public class ReportUtils {

    /**
     * Exporta los datos de un JTable a PDF usando JasperReports.
     * Maneja nulls y es dinámico según el número de columnas (col1, col2, ...).
     *
     * @param table      JTable con los datos a exportar
     * @param jrxmlPath  Ruta completa al archivo JRXML
     * @param outputPdf  Ruta completa donde se guardará el PDF
     */
    public static void exportTableToPDF(JTable table, String jrxmlPath, String outputPdf) {
        try {
            // 1. Preparar lista de mapas a partir del JTable
            List<Map<String, Object>> dataList = new ArrayList<>();
            TableModel model = table.getModel();
            int columnCount = model.getColumnCount(); // se adapta al número de columnas

            for (int i = 0; i < model.getRowCount(); i++) {
                Map<String, Object> row = new HashMap<>();
                for (int j = 0; j < columnCount; j++) {
                    Object value = model.getValueAt(i, j);
                    row.put("col" + (j + 1), value != null ? value.toString() : ""); // evita nulls
                }
                dataList.add(row);
            }

            // 2. Crear JRDataSource
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

            // 3. Compilar el JRXML
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);

            // 4. Llenar el reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

            // 5. Crear carpeta si no existe
            File outputFile = new File(outputPdf);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }

            // 6. Exportar a PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputPdf);
            System.out.println("PDF generado en: " + outputPdf);

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
}
