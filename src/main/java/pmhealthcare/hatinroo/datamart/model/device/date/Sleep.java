package pmhealthcare.hatinroo.datamart.model.device.date;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table( name = "sleeps" )
@Getter
@NoArgsConstructor
@ToString
public class Sleep {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sleep_no")
    private long no;

    private long memberNo;

    private long deviceNo;

    private String deviceId;

    private LocalDateTime start;

    private LocalDateTime end;

    private long sleepTime;

    private byte validity;

//    private float minDiffR;

//    private int weekDifference;
//
//    private int monthDifference;

    private LocalDateTime regdate;

    public Sleep(long memberNo, long deviceNo, String deviceId, LocalDateTime start, LocalDateTime end, long sleepTime, LocalDateTime regdate, byte validity) {
        this.memberNo = memberNo;
        this.deviceNo = deviceNo;
        this.deviceId = deviceId;
        this.start = start;
        this.end = end;
        this.sleepTime = sleepTime;
//        this.minDiffR = minDiffR;
        this.regdate = regdate;
        this.validity = validity;
    }

    public void updateDailyData(Sleep sleep, int min){
        this.end = sleep.getEnd().plusMinutes(min);
        this.sleepTime = sleep.getSleepTime() + ( min * 1000 );
    }


    public void addTime(int sec){
        this.end = end.plusSeconds(sec);
        this.sleepTime = this.sleepTime + sec;
    }

    public void setId(long no){
        this.no = no;
    }

    public void setValidity(byte validity){
        this.validity = validity;
    }

}
