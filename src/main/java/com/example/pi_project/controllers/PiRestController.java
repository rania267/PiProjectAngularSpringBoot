package com.example.pi_project.controllers;

import com.example.pi_project.entities.Comment;

import com.example.pi_project.entities.Message;
import com.example.pi_project.repositories.UserRepository;
import com.example.pi_project.services.IPiService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PiRestController {
    @Autowired
    IPiService piService;
    UserRepository userRepository;



    @PostMapping("/addComment")
    @ResponseBody
    public Comment addComment (@RequestBody Comment comment) {
        return piService.addComment(comment);
    }

    @PutMapping("/updateComment")
    @ResponseBody
    public Comment updateComment(@RequestBody Comment comment ){
        return piService.updateComment(comment);
    }
    @DeleteMapping("/deleteComment/{id}")
    @ResponseBody
    public void deleteComment(@PathVariable("id")int id) {piService.deleteComment(id);
    }
    @GetMapping("/AllComment")
    @ResponseBody
    public List<Comment> getAllComment(){
        return piService.getAllComment();
    }

    @PostMapping("/add-affectcomment/{idForum}")
    @ResponseBody
    public String ajouterEtAffceterCommentairePub( @RequestBody Comment comment ,@PathVariable("idForum") int idForum)
    {

        return piService.AddCommentPub(comment,idForum);
    }


    @PostMapping("/addMessage")
    @ResponseBody
    public Message updateMessage(@RequestBody Message message ){
        return piService.addMsg(message);
    }

    @DeleteMapping("/deleteMessage/{id}")
    @ResponseBody
    public void deleteMessage(@PathVariable("id")int id) {piService.deletemsg(id);
    }

}

