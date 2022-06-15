package impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.PGConnection;


public class DaoPostgres extends DaoBase{

    private PGConnection connection = null;


    public DaoPostgres() {
        super("org.postgresql.Driver");
    }

    @Override
    public void setURL(String host, String database, int port) {
        if (database.length() > 0)
            this.url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
        else
            this.url = "jdbc:postgresql://" + host + ":" + port;
    }

    @Override
    public Connection getConnection() {
        return (java.sql.Connection) connection;
    }

    @Override
    public void connect (String login, String password) {
        super.connect(login, password);
        try {
            connection = (PGConnection) DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            connection = null;
        }
    }


}
