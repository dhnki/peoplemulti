package pmhealthcare.hatinroo.datamart.model.device;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "member_daily_stats")
@Getter
@Setter
public class MemberDailyStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    private long memberNo;

    private long deviceNo;

    private LocalDate regdate;

    private Float hr;

    private Float br;

    private Float temp;

    private Float humi;

    private Float tvoc;

    private Float light;


    private Integer stay;

    private Integer absence;

    private Integer activity;

    private Integer sleep;


    public void create(Object[] dailyStats) {
        this.memberNo =  Long.valueOf(String.valueOf(dailyStats[ E_DAILY_STATS.E_MEMBER_NO.getValue() ])).longValue();
        this.deviceNo = Long.valueOf(String.valueOf(dailyStats[ E_DAILY_STATS.E_DEVICE_NO.getValue() ])).longValue();
        this.regdate = LocalDate.parse(String.valueOf(dailyStats[ E_DAILY_STATS.E_REGDATE.getValue() ]));
        this.hr = Float.valueOf(String.valueOf(dailyStats[ E_DAILY_STATS.E_HR.getValue() ])).floatValue();
        this.br = Float.valueOf(String.valueOf(dailyStats[ E_DAILY_STATS.E_BR.getValue() ])).floatValue();
        this.temp = Float.valueOf(String.valueOf(dailyStats[ E_DAILY_STATS.E_TEMP.getValue() ])).floatValue();
        this.humi = Float.valueOf(String.valueOf(dailyStats[ E_DAILY_STATS.E_HUMI.getValue() ])).floatValue();
        this.tvoc = Float.valueOf(String.valueOf(dailyStats[ E_DAILY_STATS.E_LIGHT.getValue() ])).floatValue();
        this.light = Float.valueOf(String.valueOf(dailyStats[ E_DAILY_STATS.E_TVOC.getValue() ])).floatValue();
        this.stay = Integer.valueOf(String.valueOf(dailyStats[ E_DAILY_STATS.E_STAY.getValue() ])).intValue();
        this.absence = Integer.valueOf(String.valueOf(dailyStats[ E_DAILY_STATS.E_ABSENCE.getValue() ])).intValue();
        this.activity = Integer.valueOf(String.valueOf(dailyStats[ E_DAILY_STATS.E_ACTIVITY.getValue() ])).intValue();
        this.sleep = Integer.valueOf(String.valueOf(dailyStats[ E_DAILY_STATS.E_SLEEP.getValue() ])).intValue();
    }


    public enum E_DAILY_STATS
    {
        E_DEVICE_NO(0),
        E_MEMBER_NO(1),
        E_REGDATE(2),
        E_HR(3),
        E_BR(4),
        E_TEMP(5),
        E_HUMI(6),
        E_LIGHT(7),
        E_TVOC(8),
        E_SLEEP(9),
        E_STAY(10),
        E_ABSENCE(11),
        E_ACTIVITY(12);

        private int value;

        private E_DAILY_STATS(int value)
        {
            this.value = value;
        }

        public  int getValue()
        {
            return value;
        }
    }

}

