/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modelinglab2.helperModel.ExcelHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 */
public class ExcelHelper {

    /**
     * distance = x higth = y
     */
    public void formExcelFile(List distance, List higth) {

        File file = new File("C:\\Users\\Roman\\Desktop\\Experiment.xls");
        // Создаем новый документ
        Workbook wb = new HSSFWorkbook();
        try {
            Sheet sheet = wb.createSheet("Laboratory 2 (Modeling balls)");
            // установим шрифт
            Font font = wb.createFont();
            font.setBoldweight(font.ANSI_CHARSET);

            // заполняем значениями из листа
            Row row = sheet.createRow(0);

            row.createCell(0).setCellValue("Расстояние");
            row.createCell(1).setCellValue("Высота");

            for (int i = 0; i < distance.size(); i++) {
                row = sheet.createRow(i + 1);
                Cell distCell = row.createCell(0);
                Cell higthCell = row.createCell(1);
                distCell.setCellValue(distance.get(i).toString().replace('.', ','));
                higthCell.setCellValue(higth.get(i).toString().replace('.', ','));
            }
            // выравниваем по ширине
            sheet.autoSizeColumn(0);
            // записываем в эксел
            wb.write(new FileOutputStream(file));
            wb.close();
        } catch (IOException ex) {
            Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
