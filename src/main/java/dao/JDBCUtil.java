package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
	public static Connection getConnection() {
		final String JDBC_CONFIG = "\\jdbc.properties";
		Properties properties = new Properties();
        InputStream inputStream = null;
        
        try {
            inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream(JDBC_CONFIG);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        String url = properties.getProperty("jdbc.url");

		Connection c = null;
		try {
			c = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public static void closeConnection(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void printInfo(Connection c) {
		try {
			if(c!=null) {
				DatabaseMetaData data = c.getMetaData();
				System.out.println(data.getDatabaseProductName());
				System.out.println(data.getDatabaseProductVersion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
