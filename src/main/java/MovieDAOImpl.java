import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class MovieDAOImpl implements MovieDAO {
    private final Connection connection;
    private static MovieDAOImpl instance;

    //Singleton
    public static MovieDAOImpl getInstance(Connection connection) {
        if (instance == null) { // (1)
            instance = new MovieDAOImpl(connection); // (2)
        }
        return instance;
    }

    private MovieDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS MOVIES\n" +
                    "(\n" +
                    "id INTEGER AUTO_INCREMENT,\n" +
                    "title VARCHAR(255),\n" +
                    "genre VARCHAR(255),\n" +
                    "yearOfRelease INTEGER,\n" +
                    "PRIMARY KEY (id)\n" +
                    ");");
        } catch (SQLException throwables) {
            throw new DataBaseActionException("Problem with query execution", throwables);
        }
    }

    @Override
    public void deleteTable() {
    }

    @Override
    public void createMovie(Movie movie) {
    }

    @Override
    public void deleteMovie(int id) {
    }

    @Override
    public void updateMoviesTitle(int id, String newTitle) {
    }

    @Override
    public Optional<Movie> findMovieById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Movie> findAll() {
        return null;
    }
}