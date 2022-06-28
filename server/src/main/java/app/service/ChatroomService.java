package app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.Message;
import app.repository.ChatroomRepository;
import app.repository.MessageRepository;

@Service
public class ChatroomService {

    @Autowired
    private ChatroomRepository repository;

    @Autowired
    private MessageRepository messageRepo;

    /**
     * Gets a list of chatrooms in order of most recently active
     * @return the list of chatrooms
     */
    public List<String> getAllChatroomsInOrderOfActivity() {
        List<String> chatrooms = repository.getChatroomNames();
        List<Map<String, List<Message>>> messages = new ArrayList<>();
        for(int i = 0; i < chatrooms.size(); i++) {
            HashMap<String, List<Message>> newMap = new HashMap<>();
            newMap.put(chatrooms.get(i), messageRepo.getXMostRecentMessagesFromChatroom(1, chatrooms.get(i)));
            messages.add(newMap);
        }
        messages.sort((x1, x2) -> {
            int x1Value = Integer.MIN_VALUE;
            int x2Value = Integer.MIN_VALUE;

            if (x1.get((String)x1.keySet().toArray()[0]).size() > 0) {
                x1Value = (int)(x1.get((String)x1.keySet().toArray()[0]).get(0).getTimestamp().getTime());
            }
            if (x2.get((String)x2.keySet().toArray()[0]).size() > 0) {
                x2Value = (int)(x2.get((String)x2.keySet().toArray()[0]).get(0).getTimestamp().getTime());
            }
            if (x1Value == Integer.MIN_VALUE && x2Value == Integer.MIN_VALUE) {
                return 0;
            }
            return x2Value - x1Value;
        });
        return messages.stream().map(x -> (String)x.keySet().toArray()[0]).collect(Collectors.toList());
    }

    /**
     * Creates a new chatroom
     * @param chatroom the name of the chatroom to create
     */
    public void createNewChattoom(final String chatroom) {
        repository.insertNewChatroom(chatroom);
    }
}
