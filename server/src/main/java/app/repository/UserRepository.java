package app.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private static Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository() {
        try {
            connection = DriverManager.getConnection(
                DatabaseConfig.url, 
                DatabaseConfig.user, 
                DatabaseConfig.pw
            );
        } catch (SQLException e) {
            logger.error("Error connecting to db", e);
        }
    }

    /**
     * Checks if a user exists in the database
     * @param userName the user name to check for
     * @return true if the user is in the database, false otherwise
     */
    public boolean checkUserExistence(final String userName) {
        try {
            logger.info("Search db for user {}", userName);
            final PreparedStatement statement = connection.prepareStatement(searchUserQuery);
            statement.setString(1, userName);
            final ResultSet results = statement.executeQuery();
            logger.info("Checking results: {}", results.toString());
            return results.next();
        } catch (SQLException e) {
            logger.error("Unable to check for user {}, {}", userName, e);
            return false;
        }
    }

    /**
     * Inserts a new user in the database
     * @param userName the username to create
     */
    public void createUser(final String userName) {
        try {
            final PreparedStatement statement = connection.prepareStatement(createUserQuery);
            statement.setString(1, userName);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unable to create user {}, {}", userName, e);
        }
    }

    private static final String createUserQuery = "INSERT INTO users(username) "
        + "VALUES(?)";

    private static final String searchUserQuery = "SELECT user_id FROM users WHERE "
        + "username = ?";
}
