package pmhealthcare.hatinroo.datamart.model.device.date;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table( name = "date_temperatures" )
@Getter
@NoArgsConstructor
public class DateTemperature {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_temperature_no")
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

    public DateTemperature(Long memberNo, long deviceNo, Float min, Float max, Float avg, LocalDateTime regdate) {
        this.memberNo = memberNo;
        this.deviceNo = deviceNo;
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.regdate = regdate;
    }





    public void updateDailyData(DateTemperature dateTemperature){
        this.min = dateTemperature.getMin();
        this.max = dateTemperature.getMax();
        this.avg = dateTemperature.getAvg();
    }


}
