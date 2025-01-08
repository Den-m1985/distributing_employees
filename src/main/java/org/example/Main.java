package org.example;

import org.apache.poi.ss.usermodel.Workbook;
import org.example.readExel.open_file.OpenExcelFile;
import org.example.service.ProductionSimulator;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        String defaultPath = System.getProperty("user.home") + File.separator + "Downloads";
        String extension = "xlsx";
        Workbook workbook = new OpenExcelFile().openFile(extension, defaultPath);

        new ProductionSimulator(workbook).simulate(defaultPath + File.separator + "test.csv");
    }
}