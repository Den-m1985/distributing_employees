package org.example.service;

import lombok.Getter;
import org.apache.poi.ss.usermodel.*;
import org.example.model.Connection;
import org.example.model.ProductionCenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GetDataFromExcel {
    private int workersCount;
    private int detailsCount;
    private final List<ProductionCenter> productionCenters = new ArrayList<>();
    private final List<Connection> connections = new ArrayList<>();

    public GetDataFromExcel(Workbook workbook) {
        try {
            getDataFromExcel(workbook);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void getDataFromExcel(Workbook workbook) throws IOException {

        // Читаем лист 1
        Sheet sheet1 = workbook.getSheetAt(0);
        Row row = sheet1.getRow(2);
        workersCount = (int) row.getCell(0).getNumericCellValue();
        detailsCount = (int) row.getCell(1).getNumericCellValue();

        // Читаем лист 2
        Sheet sheet2 = workbook.getSheetAt(1);
        for (int i = 2; i <= sheet2.getLastRowNum(); i++) {
            Row centerRow = sheet2.getRow(i);
            String id = centerRow.getCell(0).getStringCellValue();
            String name = centerRow.getCell(1).getStringCellValue();
            double performance = centerRow.getCell(2).getNumericCellValue();
            int maxWorkers = (int) centerRow.getCell(3).getNumericCellValue();
            productionCenters.add(new ProductionCenter(id, name, performance, maxWorkers));
        }

        // Читаем лист 3
        Sheet sheet3 = workbook.getSheetAt(2);
        for (int i = 2; i <= sheet3.getLastRowNum(); i++) {
            Row connRow = sheet3.getRow(i);
            String source = connRow.getCell(0).getStringCellValue();
            String dest = connRow.getCell(1).getStringCellValue();
            connections.add(new Connection(source, dest));
        }

        workbook.close();
    }

}
