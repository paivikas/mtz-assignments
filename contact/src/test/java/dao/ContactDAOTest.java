import com.monetize360.contactapp.dao.ContactDaoImpl;
import com.monetize360.contactapp.dto.ContactDto;
import com.monetize360.contactapp.util.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactDAOTest {

    private static Connection connection;
    private static ContactDaoImpl contactDao;
    private static int testContactId;

    @BeforeAll
    static void setup() {
        connection = ConnectionUtil.openConnection();
        contactDao = new ContactDaoImpl();
    }

    @AfterAll
    static void tearDown() {
        ConnectionUtil.closeConn(connection);
    }

    @Test

    void testCreateContact() {
        ContactDto contact = new ContactDto();
        contact.setName("John Doe");
        contact.setEmail("john@example.com");
        contact.setDob("1990-01-01");
        contact.setMobile("1234567890");

        contactDao.createContact(contact, connection);

        List<ContactDto> contacts = contactDao.searchContacts("John Doe", connection);
        assertFalse(contacts.isEmpty());

        testContactId = contacts.get(0).getId();
        assertEquals("John Doe", contacts.get(0).getName());
    }

    @Test

    void testUpdateContact() {
        ContactDto contact = new ContactDto();
        contact.setName("John Smith");
        contact.setEmail("johnsmith@example.com");
        contact.setDob("1990-01-01");
        contact.setMobile("0987654321");

        contactDao.updateContact(testContactId, contact, connection);

        ContactDto updatedContact = contactDao.getContactById(testContactId, connection);
        assertEquals("John Smith", updatedContact.getName());
        assertEquals("johnsmith@example.com", updatedContact.getEmail());
    }

    @Test

    void testSearchContacts() {
        List<ContactDto> contacts = contactDao.searchContacts("Smith", connection);
        assertFalse(contacts.isEmpty());
        assertEquals("Jane Smith", contacts.get(0).getName());
    }

    @Test
    void testGetContactById() {
        ContactDto contact = contactDao.getContactById(5, connection);
        assertNotNull(contact);
        assertEquals("Jane Smith", contact.getName());
    }

    @Test

    void testGetAllContacts() {
        List<ContactDto> contacts = contactDao.getAllContacts(connection);
        assertNotNull(contacts);
        assertFalse(contacts.isEmpty());
    }

    @Test

    void testDeleteContact() {
        contactDao.deleteContact(testContactId, connection);

        ContactDto contact = contactDao.getContactById(testContactId, connection);
        assertNull(contact);
    }
}
