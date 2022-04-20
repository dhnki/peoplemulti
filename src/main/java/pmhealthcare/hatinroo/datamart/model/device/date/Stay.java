package pmhealthcare.hatinroo.datamart.model.device.date;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table( name = "stays" )
@Getter
@NoArgsConstructor
@ToString
public class Stay {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stay_no")
    private long no;

    private Long memberNo;

    private long deviceNo;

    private String deviceId;

    private byte stayYn;

    private LocalDateTime regdate;

    private LocalDateTime start;

    private LocalDateTime end;

    private long stayTime;


    public Stay(Long memberNo, long deviceNo, String deviceId, byte stayYn, LocalDateTime start, LocalDateTime end, long stayTime, LocalDateTime regdate) {
        this.memberNo = memberNo;
        this.deviceNo = deviceNo;
        this.deviceId = deviceId;
        this.stayYn = stayYn;
        this.start = start;
        this.end = end;
        this.stayTime = stayTime;
        this.regdate = regdate;
    }


    public void addTime(int sec){
        this.end = end.plusSeconds(sec);
        this.stayTime = this.stayTime + sec;
    }

    public void setId(long no){
        this.no = no;
    }

    public void setStayYn(byte stayYn) {
        this.stayYn = stayYn;
    }
}
