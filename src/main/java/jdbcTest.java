import java.sql.*;

public class jdbcTest {

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/jdbc_example", "michal", "admin");
             Statement statement = connection.createStatement()) {
             statement.execute("CREATE TABLE IF NOT EXISTS movies (\n" +
                     "ID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                     "title VARCHAR(255) NOT NULL,\n" +
                     "genre VARCHAR(255) NOT NULL,\n" +
                     "yearOfRelease INTEGER NOT NULL);");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
