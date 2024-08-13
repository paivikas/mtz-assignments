package com.monetize360.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class EmployeeSalaryFormatter {

    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream empStream = EmployeeSalaryFormatter.class.getResourceAsStream("/emp.json");
            if (empStream == null) {
                System.out.println("emp.json file not found in resources.");
                return;
            }
            ArrayNode employees = (ArrayNode) mapper.readTree(empStream);
            System.out.println("Employee data loaded successfully.");

            InputStream currencyStream = EmployeeSalaryFormatter.class.getResourceAsStream("/currency.json");
            if (currencyStream == null) {
                System.out.println("currency.json file not found in resources.");
                return;
            }
            ArrayNode currencies = (ArrayNode) mapper.readTree(currencyStream);
            System.out.println("Currency data loaded successfully.");

            Map<String, JsonNode> currencyMap = new HashMap<>();
            for (JsonNode currency : currencies) {
                currencyMap.put(currency.get("currency").asText(), currency);
            }

            for (JsonNode employee : employees) {
                String currencyCode = employee.get("currency").asText();
                JsonNode currencyDetails = currencyMap.get(currencyCode);

                if (currencyDetails != null) {
                    int decimals = currencyDetails.get("decimals").asInt();
                    String symbol = currencyDetails.get("symbol").asText();
                    double salary = employee.get("salary").asDouble();

                    String formattedSalary = formatSalary(salary, symbol, decimals);
                    ((ObjectNode) employee).put("formatted_salary", formattedSalary);
                }
            }

            File outputDir = new File("emp-details/src/main/resources");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            File outputFile = new File(outputDir, "emp_details.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, employees);

            System.out.println("Formatted employee details have been saved to " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatSalary(double salary, String symbol, int decimals) {
        StringBuilder pattern = new StringBuilder();
        pattern.append("#,##0");

        if (decimals > 0) {
            pattern.append(".");
            for (int i = 0; i < decimals; i++) {
                pattern.append("0");
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat(pattern.toString());
        return symbol + decimalFormat.format(salary);
    }
}
