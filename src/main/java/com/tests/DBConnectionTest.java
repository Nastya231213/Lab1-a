package com.tests;

import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import org.junit.Test;
import com.DB.DBConnect;

public class DBConnectionTest {

    @Test
    public void testGetConn() {
        Connection conn = DBConnect.getConn();
        assertNotNull("Connection shouldn't not be null", conn);
    }
}
