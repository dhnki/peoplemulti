package pmhealthcare.hatinroo.datamart.model.app;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pushs")
public class Push {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "push_no")
    private long no;

    private long memberNo;

    private byte messageType;

    private String title;

    private String message;

    private Integer beforeValue;

    private Integer currentValue;

    private byte readYn;

    private LocalDateTime regdate;

    private byte validity;


    public void readMessage(){
        this.readYn = (byte) 1;
    }

    public void delete() {
        this.validity = (byte) 0;
    }


}
