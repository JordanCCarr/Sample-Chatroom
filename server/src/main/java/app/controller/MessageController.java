package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.dao.Message;
import app.service.MessageService;

@RequestMapping("/messages")
@RestController
public class MessageController {
    
    @Autowired
    private MessageService service;

    /**
     * Gets most recent messages for a chatroom
     * @param chatroom the chatrooms to get messages for
     * @return a list of messages
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{chatroom}")
    public List<Message> getMessagesForChatroom(@PathVariable final String chatroom) {
        return service.getMessages(chatroom);
    }

    /**
     * Posts a new message to the chatroom
     * @param chatroom the name of the chatroom to post to
     * @param message the message to post
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{chatroom}")
    public void postMessage(@PathVariable final String chatroom, @RequestBody final Message message) {
        service.postMessage(message, chatroom);
    }
}
