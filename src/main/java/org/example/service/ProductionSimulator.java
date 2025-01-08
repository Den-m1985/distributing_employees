package org.example.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.example.model.Connection;
import org.example.model.Detail;
import org.example.model.ProductionCenter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ProductionSimulator {
    private final GetDataFromExcel dataFromExcel;

    public ProductionSimulator(Workbook workbook) {
        dataFromExcel = new GetDataFromExcel(workbook);
    }

    public void simulate(String outputCsvPath) {
        double time = 0.0;
        List<Detail> details = new ArrayList<>();
        for (int i = 0; i < dataFromExcel.getDetailsCount(); i++) {
            details.add(new Detail((long) i));
        }

        ProductionCenter startCenter = dataFromExcel.getProductionCenters().get(0);
        startCenter.addAllDetails(details);

        Path path = Paths.get(outputCsvPath);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write("Time, ProductionCenter, WorkersCount, BufferCount\n");

            while (true) {
                boolean isProcessing = false;
                for (ProductionCenter center : dataFromExcel.getProductionCenters()) {
                    int employeesSize = center.getEmployees().size();
                    int bufferCount = center.getBuffer().size();
                    writer.write(String.format("%.1f, %s, %d, %d\n", time, center.getName(), employeesSize, bufferCount));

                    for (int i = 0; i < employeesSize && !center.getBuffer().isEmpty(); i++) {
                        Detail detail = center.processDetail();
                        // Отправляем в следующий центр
                        String nextCenterId = getNextCenter(center.getIdFromExcel());
                        if (nextCenterId != null) {
                            ProductionCenter nextCenter = findCenterById(nextCenterId);
                            nextCenter.addDetail(detail);
                            isProcessing = true;
                        }
                    }
                }
                if (!isProcessing) {
                    break;
                }
                time += 1.0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getNextCenter(String currentId) {
        for (Connection conn : dataFromExcel.getConnections()) {
            if (conn.getSource().equals(currentId)) {
                return conn.getDest();
            }
        }
        return null;
    }

    private ProductionCenter findCenterById(String id) {
        return dataFromExcel.getProductionCenters().stream()
                .filter(pc -> pc.getIdFromExcel().equals(id))
                .findFirst()
                .orElse(null);
    }

}
