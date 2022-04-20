package pmhealthcare.hatinroo.datamart.model.device.date;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table( name = "date_brs" )
@Getter
@NoArgsConstructor
public class DateBr {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_br_no")
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


    public DateBr(Long memberNo, long deviceNo, Float min, Float max, Float avg, LocalDateTime regdate) {
        this.memberNo = memberNo;
        this.deviceNo = deviceNo;
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.regdate = regdate;
    }

    public void updateDailyData(DateBr dateBr){
        this.min = dateBr.getMin();
        this.max = dateBr.getMax();
        this.avg = dateBr.getAvg();
    }



}
