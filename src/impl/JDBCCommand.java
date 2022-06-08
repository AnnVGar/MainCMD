package impl;

import core.ICommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class JDBCCommand extends Command implements ICommand {
    final static String NAME = "jdb";
    final static String KEYS = "crud";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/test_db";
    public static final String DB_Driver = "org.postgresql.Driver";

    @Override
    public String getName() {
        return NAME;

    }

    @Override
    String getLegalKeys() {
        return KEYS;
    }

    @Override
    void execute(Map<String, ArrayList<String>> parameterMap) {
        System.out.println(parameterMap);
        try {
            Class.forName(DB_Driver);
            Connection connection = DriverManager.getConnection(DB_URL, "admin", "admin");
            if (connection != null) {
                String query = switch (parameterMap.get("key").get(0)) {
                    case "r" -> "SELECT * FROM "+parameterMap.get("file").get(0);
                    case "c" -> "INSERT INTO "+parameterMap.get("file").get(0)+" VALUES( "+parameterMap.get("file").get(1)+")";
                    case "d" -> "DELETE FROM "+parameterMap.get("file").get(0)+" WHERE surname = "+parameterMap.get("file").get(1);
                    case "u" -> "UPDATE "+parameterMap.get("file").get(0)+" SET name ="+ parameterMap.get("file").get(2)+" WHERE surname = "+parameterMap.get("file").get(1);
                    default -> "";
                };
                System.out.println(query);
                Statement statement = connection.createStatement();
                statement.executeQuery(query);
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(2) + "  " + resultSet.getString(3));
                }
                connection.close();
            } else {
                System.out.println("FAILED");
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }

}
