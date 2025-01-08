package org.example.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.example.model.ProductionCenter;
import org.example.readExel.open_file.OpenExcelFile;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class GetDataFromExcelTest {

    @Test
    public void testReadInputData() throws IOException {
        String defaultPath = System.getProperty("user.home") + File.separator + "Downloads";
        String extension = "xlsx";
        Workbook workbook = new OpenExcelFile().openFile(extension, defaultPath);

        GetDataFromExcel dataFromExcel = new GetDataFromExcel(workbook);

        // Проверяем общее количество сотрудников и деталей
        assertEquals(3, dataFromExcel.getWorkersCount());
        assertEquals(500, dataFromExcel.getDetailsCount());

        // Проверяем количество производственных центров
        assertEquals(5, dataFromExcel.getProductionCenters().size());

        // Проверяем параметры конкретного производственного центра
        ProductionCenter center1 = dataFromExcel.getProductionCenters().get(0);
        assertEquals("Производственный центр №1", center1.getName());
        assertEquals(2.2, center1.getPerformance());
        assertEquals(2, center1.getMaxWorkers());
    }
}
