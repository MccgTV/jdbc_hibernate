import java.sql.*;

public class jdbcTest {

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/jdbc_example", "michal", "admin");
        ) {
            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS movies (\n" +
                        "ID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                        "title VARCHAR(255) NOT NULL,\n" +
                        "genre VARCHAR(255) NOT NULL,\n" +
                        "yearOfRelease INTEGER NOT NULL);");
            }
            try(Statement statement = connection.createStatement()){
                final String insertIndianaJones = "insert into movies (title, genre, yearOfRelease)\n" +
                        "values\n" +
                        "('Indiana Jones',\n" +
                        "'Adventure',\n" +
                        "1996);";
                final String insertLOTR = "insert into movies (title, genre, yearOfRelease)\n" +
                        "values\n" +
                        "('Lord of the ring',\n" +
                        "'Fantasy',\n" +
                        "2001);";
                statement.execute(insertIndianaJones);
                statement.execute(insertLOTR);
            }

            // aktualizacja rekordu
            String updateQuery = "UPDATE MOVIES SET title = ?, genre = ? WHERE id = ?;";
            try(PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
                preparedStatement.setString(1, "Indiana Jones 2");
                preparedStatement.setString(2,"drama");
                preparedStatement.setInt(3, 2);
                preparedStatement.executeUpdate();
            }
            // kasowanie rekordu
            String deleteQuery = "DELETE FROM movies WHERE ID = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, 4);
                preparedStatement.execute();
            }

            // wyświetl dane
            try(Statement statement = connection.createStatement()) {
                String showTableQuery = "SELECT * FROM movies;";
                ResultSet resultSet = statement.executeQuery(showTableQuery);
                while(resultSet.next()) {
                    System.out.println(resultSet.getInt("ID") + ", ");
                    System.out.println(resultSet.getInt("title") + ", ");
                    System.out.println(resultSet.getInt("genre") + ", ");
                    System.out.println(resultSet.getInt("yearOfRelease") + ", ");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
