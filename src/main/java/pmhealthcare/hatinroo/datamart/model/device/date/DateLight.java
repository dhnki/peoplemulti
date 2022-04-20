package pmhealthcare.hatinroo.datamart.model.device.date;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table( name = "date_lights" )
@Getter
@NoArgsConstructor
public class DateLight {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_light_no")
    private long no;

    private Long memberNo;

    private long deviceNo;

    private Float min;

    private Float max;

    private Float avg;

//    private int dayDifference;
//
//    private int weekDifference;
//
//    private int monthDifference;

    private LocalDateTime regdate;


    public DateLight(Long memberNo, long deviceNo, Float min, Float max, Float avg, LocalDateTime regdate) {
        this.memberNo = memberNo;
        this.deviceNo = deviceNo;
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.regdate = regdate;
    }


    public void updateDailyData(DateLight dateLight){
        this.min = dateLight.getMin();
        this.max = dateLight.getMax();
        this.avg = dateLight.getAvg();
    }

}
