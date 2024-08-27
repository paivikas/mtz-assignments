package com.monetize360.contactapp.service;

import com.monetize360.contactapp.dao.ContactDaoImpl;
import com.monetize360.contactapp.dto.ContactDto;
import com.monetize360.contactapp.util.ConnectionUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

public class ContactService implements ContactServiceInterface{

    private final ContactDaoImpl contactDao;

    public ContactService() {
        this.contactDao = new ContactDaoImpl();
    }

    public boolean createContact(ContactDto contact, Connection connection) {
        try {
            contactDao.createContact(contact, connection);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateContact(int id, ContactDto contact, Connection connection) {
        try {
            contactDao.updateContact(id, contact, connection);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ContactDto> searchContacts(String query, Connection connection) {
        return contactDao.searchContacts(query, connection);
    }

    public boolean deleteContact(int id, Connection connection) {
        try {
            contactDao.deleteContact(id, connection);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ContactDto getContactById(int id, Connection connection) {
        return contactDao.getContactById(id, connection);
    }

    public List<ContactDto> getAllContacts(Connection connection) {
        return contactDao.getAllContacts(connection);
    }
    public boolean importContactsFromExcel(Connection conn) {
        try {
            InputStream input = ConnectionUtil.class.getClassLoader().getResourceAsStream("contact.xlsx");
            assert input != null;
            Workbook workbook = new XSSFWorkbook(input);
            Sheet sheet = workbook.getSheetAt(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                ContactDto contactDTO = new ContactDto();

                if (row.getCell(0) != null) {
                    contactDTO.setName(getCellValueAsString(row.getCell(0)));
                }

                if (row.getCell(1) != null) {
                    contactDTO.setEmail(getCellValueAsString(row.getCell(1)));
                }

                if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.NUMERIC) {
                    contactDTO.setDob(dateFormat.format(row.getCell(2).getDateCellValue()));
                }

                if (row.getCell(3) != null) {
                    contactDTO.setMobile(getCellValueAsString(row.getCell(3)));
                }

                createContact(contactDTO, conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    return dateFormat.format(cell.getDateCellValue());
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }


    @Override
    public boolean exportContactsToExcel(OutputStream outputStream, Connection connection){
        List<ContactDto> contacts = getAllContacts(connection);
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Contacts");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Name");
            header.createCell(1).setCellValue("Email");
            header.createCell(2).setCellValue("DOB");
            header.createCell(3).setCellValue("Mobile");

            int rowNum = 1;
            for (ContactDto contact : contacts) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(contact.getName());
                row.createCell(1).setCellValue(contact.getEmail());
                row.createCell(2).setCellValue(contact.getDob());
                row.createCell(3).setCellValue(contact.getMobile());
            }

            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
