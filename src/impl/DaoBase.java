package impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class DaoBase {
    protected String driver;
    protected String url = null;

    protected Properties properties = null;

    public DaoBase(String driver) {

        this.driver = driver;
    }

    protected void registerDriverManager() {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void setURL(String host, String database, int port);

    public abstract Connection getConnection();

    public void connect(String login, String password) {
        registerDriverManager();

        properties = new Properties();
        properties.setProperty("password", password);
        properties.setProperty("user", login);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "utf8");
    }

    public void disconnect(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean execSQL(final String sql) {
        boolean result = false;
        try {
            if (getConnection() != null) {
                Statement statement = getConnection().createStatement();
                if (statement.execute(sql)) {
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet != null && resultSet.next()) {
                        System.out.println(resultSet.getString(2) + "  " + resultSet.getString(3));
                    }
                }
                statement.close();
                result = true;
            }
        } catch (SQLException e) {
            System.err.println("SQLException : code = " + e.getErrorCode() +
                    " - " + e.getMessage());
            System.err.println("\tSQL : " + sql);

        }
        return result;
    }
}
