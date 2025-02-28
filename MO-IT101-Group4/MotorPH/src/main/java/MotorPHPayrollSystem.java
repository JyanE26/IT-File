/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/** Contributors:
 * Kirk Martin Sevillano
 * Jyan O Estanislao
 *
 */
package com.mycompany.motorph;

import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class MotorPHPayrollSystem {

    // Employee class to store employee details
    static class Employee {
        String employeeNumber;
        String employeeName;
        String birthday;
        double hourlyRate;

        public Employee(String employeeNumber, String employeeName, String birthday, double hourlyRate) {
            this.employeeNumber = employeeNumber;
            this.employeeName = employeeName;
            this.birthday = birthday;
            this.hourlyRate = hourlyRate;
        }

        @Override
        public String toString() {
            return "Employee Number: " + employeeNumber + "\n" +
                   "Employee Name: " + employeeName + "\n" +
                   "Birthday: " + birthday + "\n" +
                   "Hourly Rate: " + hourlyRate;
        }
    }

    // Payroll calculator class
    static class PayrollCalculator {
        private static final double SSS_RATE = 0.045; // 4.5%
        private static final double PHILHEALTH_RATE = 0.04; // 4%
        private static final double PAGIBIG_RATE = 0.02; // 2%
        private static final double TAX_RATE = 0.15; // 15%

        public static double calculateGrossSalary(double hoursWorked, double hourlyRate) {
            return hoursWorked * hourlyRate;
        }

        public static double calculateNetSalary(double grossSalary) {
            double sssDeduction = grossSalary * SSS_RATE;
            double philhealthDeduction = grossSalary * PHILHEALTH_RATE;
            double pagibigDeduction = grossSalary * PAGIBIG_RATE;
            double taxDeduction = grossSalary * TAX_RATE;

            return grossSalary - (sssDeduction + philhealthDeduction + pagibigDeduction + taxDeduction);
        }
    }

    // Main application
    public static void main(String[] args) {
        String csvFile = "employee_data.csv"; // Replace with the actual path to your CSV file

        try (FileReader fileReader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            System.out.println("MotorPH Payroll System\n");

            for (CSVRecord csvRecord : csvParser) {
                String employeeNumber = csvRecord.get("EmployeeNumber");
                String employeeName = csvRecord.get("EmployeeName");
                String birthday = csvRecord.get("Birthday");
                double hourlyRate = Double.parseDouble(csvRecord.get("HourlyRate"));

                Employee employee = new Employee(employeeNumber, employeeName, birthday, hourlyRate);
                System.out.println(employee);

                double hoursWorked = 40; // Example: 40 hours worked in a week
                double grossSalary = PayrollCalculator.calculateGrossSalary(hoursWorked, hourlyRate);
                double netSalary = PayrollCalculator.calculateNetSalary(grossSalary);

                System.out.println("Gross Salary: " + grossSalary);
                System.out.println("Net Salary: " + netSalary);
                System.out.println("-----------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }
}
