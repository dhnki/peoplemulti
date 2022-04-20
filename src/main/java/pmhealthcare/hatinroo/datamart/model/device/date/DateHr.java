package pmhealthcare.hatinroo.datamart.model.device.date;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table( name = "date_hrs" )
@Getter
@NoArgsConstructor
public class DateHr {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_hr_no")
    private long no;

    private Long memberNo;

    private long deviceNo;

    private Float min;

    private Float max;

    private Float avg;

    private int sleep;

//    private int dayDifference;
//
//    private int weekDifference;
//
//    private int monthDifference;

    private LocalDateTime regdate;


    public DateHr(Long memberNo, long deviceNo,  Float min, Float max, Float avg, LocalDateTime regdate) {
        this.memberNo = memberNo;
        this.deviceNo = deviceNo;
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.regdate = regdate;
    }


    public void updateDailyData(DateHr dateHr){
        this.min = dateHr.getMin();
        this.max = dateHr.getMax();
        this.avg = dateHr.getAvg();
    }


}
