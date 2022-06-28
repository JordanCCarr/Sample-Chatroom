package app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.service.ChatroomService;

@RequestMapping("/chatrooms")
@RestController
public class ChatroomController {
   
    @Autowired
    private ChatroomService service;

    private static final Logger logger = LoggerFactory.getLogger(ChatroomController.class);

    /**
     * Gets all chatrooms on the server in order of most recently active
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity getAllChatrooms() {
        final List<String> chatrooms = service.getAllChatroomsInOrderOfActivity();
        logger.info("Returning chatrooms {}", chatrooms);
        return ResponseEntity.ok(chatrooms);
    }

    /**
     * Creates a new chatroom on the server
     * @param chatroom the name of the chatroom
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void createNewChattoom(@RequestBody final String chatroom) {
        service.createNewChattoom(chatroom);
    }
}
