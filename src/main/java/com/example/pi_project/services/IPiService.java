package  com.example.pi_project.services;
import com.example.pi_project.entities.Comment;
import com.example.pi_project.entities.Message;
import com.example.pi_project.entities.MessageRecorder;

import java.util.List;

public interface IPiService {
    public Comment addComment (Comment  comment);
    public void deleteComment (int id);
    public Comment updateComment (Comment  comment);
    public List<Comment > getAllComment();
    public String AddCommentPub(Comment comment, int idForum );
    public Message addMsg (Message message);
    public void deletemsg ( int id);
    void save(String sender, String receiver, String messageContent);
    List<MessageRecorder> findAllByReceiverName(String receiverName);


}
