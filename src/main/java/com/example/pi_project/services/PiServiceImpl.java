package com.example.pi_project.services;
import com.example.pi_project.entities.*;
import com.example.pi_project.repositories.*;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PiServiceImpl implements IPiService {
    CommentRepository commentRepository;
   ForumRepository forumRepository;
    UserRepository userRepository;
    ForbidenRepository forbidenRepository;
    MessageRecorderRepository messageRecorderRepository;
    MessageRepository messageRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);


    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public String AddCommentPub(Comment comment, int idForum) {
        Forum f = forumRepository.findById(idForum).orElse(null);
        String textbody = comment.getContent();
        List<Forbiden> badwordlist = (List<Forbiden>) forbidenRepository.findAll();
        int compteur = 0;
        for (int i = 0; i < badwordlist.size(); i++) {
            if (textbody.contains(badwordlist.get(i).getText())) {
                compteur++;
            }
        }
        if (compteur > 0) {
            return "your Comment contains " + compteur + " bad words";

        } else {
            comment.setForum(f);
            commentRepository.save(comment);
            return "Comment added successfuly ";

        }


    }

    @Override
    public Message addMsg(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deletemsg(int id) {
        messageRepository.deleteById(id);

    }

    @Override
    public void save(String sender, String receiver, String messageContent) {
        MessageRecorder messageEntity = new MessageRecorder();
        messageEntity.setSenderName(sender);
        messageEntity.setReceiverName(receiver);
        messageEntity.setMessageContent(messageContent);
        messageRecorderRepository.save(messageEntity);


    }

    @Override
    public List<MessageRecorder> findAllByReceiverName(String receiverName) {
        return null;
    }


}
