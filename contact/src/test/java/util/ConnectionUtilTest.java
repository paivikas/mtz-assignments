package util;


import com.monetize360.contactapp.util.ConnectionUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionUtilTest {
    private static Connection conn;
    private static Statement stmt;

    @BeforeAll
    static  void setup() {
        conn = ConnectionUtil.openConnection();
        assertNotNull(conn, "DB connection is established");
        stmt = ConnectionUtil.getStatement(conn);
        assertNotNull(stmt, "STmt should be established");

    }
    @Test
    void getConnection() {
        assertNotNull(conn,"DB connection should not be null");
        try {
            assertFalse(conn.isClosed(),"Connection should be open");
        } catch (SQLException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    void getStatement() {
        assertNotNull(stmt,"Stmt should not be null");
    }

    @Test
    void closeStatement() {
        ConnectionUtil.closeStatement(stmt);
        try {
            assertTrue(stmt.isClosed(),"Stmt should be closed");
        } catch (SQLException e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    void closeConn() {
        ConnectionUtil.closeConn(conn);
        try {
            assertTrue(conn.isClosed(),"Connection should be closed");
        } catch (SQLException e)
        {
            fail(e.getMessage());
        }
    }

    @AfterAll
    static void tearDown() {
        ConnectionUtil.closeStatement(stmt);
        ConnectionUtil.closeConn(conn);
    }
}