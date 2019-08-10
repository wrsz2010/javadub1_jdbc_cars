package javadub1.jdbc.auta;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcConnect {
    private JdbcSettings jdbcSettings;

    public JdbcConnect() throws IOException {
        this.jdbcSettings = new JdbcSettings();
    }

    private MysqlDataSource getDataSource(){
        //Stworzenie polaczenia bazodanowego.
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName(jdbcSettings.getJdbcHost());
        mysqlDataSource.setPort(Integer.parseInt(jdbcSettings.getJdbcPort()));
        mysqlDataSource.setUser(jdbcSettings.getJdbcUsername());
        mysqlDataSource.setPassword(jdbcSettings.getJdbcPassword());
        mysqlDataSource.setDatabaseName(jdbcSettings.getJdbcDatabaseName());

        try {
            mysqlDataSource.setUseSSL(false);
            mysqlDataSource.setServerTimezone("UTC");
        } catch (SQLException e) {
            System.err.println("ERROR, unable to set ssl/timezone. !");
            JOptionPane.showMessageDialog(null, "Unable to connect.");
            System.exit(1);
        }
        return mysqlDataSource;
    }

    public Connection getConnection() throws SQLException {
        Connection mysqlConnection = getDataSource().getConnection();
        return mysqlConnection;
    }
}
