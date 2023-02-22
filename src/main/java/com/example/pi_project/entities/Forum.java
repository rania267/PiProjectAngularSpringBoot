package  com.example.pi_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Forum implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  int idForum;
    private String object;
    private String topic;
    @Temporal(TemporalType.DATE)
    private Date datepub;
    private int likes;
    private  int dislike ;
    @ManyToOne
    //@JsonIgnore
    private User user ;

    @OneToMany(mappedBy = "forum")
    @JsonIgnore
    Set<Comment> comments;
}
