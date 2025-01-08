package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
public class ProductionCenter {
    private String idFromExcel;
    private String name;
    private double performance;
    private int maxWorkers;
    private int maxEmployees;
    private double processingTime;
    private int currentWorkers;
    private List<Employee> employees;
    private Queue<Detail> buffer;
    private List<ProductionCenter> nextCenters;


    public ProductionCenter(String idFromExcel, String name, double performance, int maxWorkers) {
        this.idFromExcel = idFromExcel;
        this.name = name;
        this.performance = performance;
        this.maxWorkers = maxWorkers;
        this.currentWorkers = 0;
        this.employees = new ArrayList<>();
        this.buffer = new LinkedList<>();
        this.nextCenters = new LinkedList<>();
    }

    public void addDetail(Detail detail) {
        buffer.add(detail);
    }

    public void addAllDetails(List<Detail> details) {
        buffer.addAll(details);
    }

    public void addEmployee(Employee employee) {
        if (employees.size() < maxEmployees) {
            employees.add(employee);
        }
    }

    public Detail processDetail() {
        if (!buffer.isEmpty() && currentWorkers > 0) {
            return buffer.poll();
        }
        return null;
    }

}
