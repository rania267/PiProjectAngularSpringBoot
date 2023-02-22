package  com.example.pi_project.entities; ;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Forbiden implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  int id_forbiden;

private String text;
}

