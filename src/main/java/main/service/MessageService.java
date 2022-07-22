package main.service;

import main.model.Message;
import main.model.User;
import main.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(User user, String message) {
        messageRepository.save(new Message(message, user, LocalDateTime.now(ZoneId.systemDefault())));
    }

    public List<Message> getMessages(String text) {
        int count = Integer.parseInt(text.trim().substring(text.indexOf(' ') + 1));
        if (count == 0) {
            return List.of(new Message());
        }
        return messageRepository.findMessages(
                PageRequest.of(0, count, Sort.by("id").descending())).toList();
    }

    public List<Message> getMyMessages(User user) {
        return messageRepository.findMessageByUserId(user.getUserId()).get();
    }
}
