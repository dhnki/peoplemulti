package pmhealthcare.hatinroo.datamart.model.device.date;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table( name = "activitys" )
@Getter
@NoArgsConstructor
@ToString
public class Activity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_no")
    private long no;

    private Long memberNo;

    private long deviceNo;

    private String deviceId;

    private LocalDateTime start;

    private LocalDateTime end;

    private long activityTime;

//    private int weekDifference;
//
//    private int monthDifference;

    private LocalDateTime regdate;

    private byte validity;


    public Activity(Long memberNo, long deviceNo, String deviceId, LocalDateTime start, LocalDateTime end, long activityTime, LocalDateTime regdate, byte validity) {
        this.memberNo = memberNo;
        this.deviceNo = deviceNo;
        this.deviceId = deviceId;
        this.start = start;
        this.end = end;
        this.activityTime = activityTime;
        this.regdate = regdate;
        this.validity = validity;
    }

    public void addTime(int sec){
        this.end = end.plusSeconds(sec);
        this.activityTime = this.activityTime + sec;
    }

    public void setId(long no){
        this.no = no;
    }

    public void setValidity(byte validity){
        this.validity = validity;
    }

}
