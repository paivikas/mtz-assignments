package com.monetize360.contactapp.service;


import com.monetize360.contactapp.dto.ContactDto;
import com.monetize360.contactapp.util.ConnectionUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;


import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class ContactService implements ContactServiceInterface {

    @Override
    public void createContact(ContactDto contact, Connection connection) {
        String sql = "INSERT INTO contacts (name, email, dob, mobile) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getEmail());


            if (contact.getDob() != null && !contact.getDob().isEmpty()) {
                try {
                    stmt.setDate(3, Date.valueOf(contact.getDob()));
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid date format for DOB: " + contact.getDob());
                    return;
                }
            } else {
                stmt.setNull(3, Types.DATE);
            }


            try {
                stmt.setLong(4, Long.parseLong(contact.getMobile()));
            } catch (NumberFormatException e) {
                System.err.println("Invalid mobile number: " + contact.getMobile());
                return;
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContact(int id, ContactDto contact,Connection connection) {
        String sql = "UPDATE contacts SET name = ?, email = ?, dob = ?, mobile = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getEmail());
            stmt.setDate(3, Date.valueOf(contact.getDob()));
            stmt.setLong(4, Long.parseLong(contact.getMobile()));
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ContactDto> searchContacts(String query,Connection connection) {
        List<ContactDto> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts WHERE name ILIKE ? OR email ILIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ContactDto contact = new ContactDto();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setDob(rs.getDate("dob").toString());
                contact.setMobile(rs.getLong("mobile") + "");
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public void deleteContact(int id,Connection connection) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ContactDto getContactById(int id,Connection connection) {
        ContactDto contact = null;
        String sql = "SELECT * FROM contacts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                contact = new ContactDto();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setDob(rs.getDate("dob").toString());
                contact.setMobile(rs.getLong("mobile") + "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }

    @Override
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
    public boolean exportContactsToExcel(OutputStream outputStream,Connection connection){
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
    @Override
    public List<ContactDto> getAllContacts(Connection conn) {
        String query = "SELECT * FROM contacts";
        List<ContactDto> contacts = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                assert false;
                ModelMapper modelMapper=new ModelMapper();
                ContactDto contact = modelMapper.map(rs, ContactDto.class);
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setDob(rs.getDate("dob").toString());
                contact.setMobile(rs.getString("mobile"));

                contacts.add(modelMapper.map(contact, ContactDto.class));
            }
        }
        catch (SQLException s){
            s.printStackTrace();
        }
        return contacts;
    }
}