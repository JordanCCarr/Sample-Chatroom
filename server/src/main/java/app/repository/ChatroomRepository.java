package app.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import app.dao.Message;

@Repository
public class ChatroomRepository {
    private static Connection connection;

    private static final Logger logger = LoggerFactory.getLogger(ChatroomRepository.class);

    public ChatroomRepository() {
        try {
            connection = DriverManager.getConnection(
                DatabaseConfig.url, 
                DatabaseConfig.user, 
                DatabaseConfig.pw
            );
        } catch (SQLException e) {
            logger.error("Error connecting to database", e);
        }
    }

    /**
     * Inserts a new chatroom into the database
     * @param chatroom the name of the chatroom to create
     */
    public void insertNewChatroom(final String chatroom) {
        try {
            final PreparedStatement statement = connection.prepareStatement(insertChatroomQuery);
            statement.setString(1, chatroom);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting new chatroom", e);
        }
    }

    /**
     * Gets a list of all chatrooms
     * @return a list of all chatroom names
     */
    public List<String> getChatroomNames() {
        try {
            final PreparedStatement statement = connection.prepareStatement(getChatroomsQuery);
            final ResultSet results = statement.executeQuery();
            final List<String> chatrooms = new ArrayList<>();
            MessageRepository messageRepo = new MessageRepository();
            List<Message> recentMessages = new ArrayList<>();
            while(results.next()) {
                String chatroom = results.getString("room_name");
                chatrooms.add(results.getString("room_name")); 
            }
            return chatrooms;
        } catch (SQLException e) {
            logger.error("Error retrieving chatrooms", e);
            return new ArrayList<>();
        }
    }

    private static final String insertChatroomQuery = "INSERT INTO chatrooms(room_name) "
     + "VALUES(?)";

    private static final String getChatroomsQuery = "SELECT chatrooms.room_name FROM chatrooms";
}
