package pmhealthcare.hatinroo.datamart.model.device;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "member_status_hist")
@Getter
@Setter
public class MemberStatusHist {

    @JsonIgnore
    @Id
    @Column(name = "member_status_hist_no")
    private long no;

    private long memberNo;

    private long deviceNo;

    private String deviceId;

    private LocalDateTime regdate;

    private Byte stay;

    private Byte activity;

    private Byte sleep;

    private Byte meal;

    private Float hr;

    private Float br;

    private Float mot;

    private Float r;

    private Float temperature;

    private Float humidity;

    private Float tvoc;

    private Float light;

    private Float avgDiff;


    public void createNull ( long memberNo, long deviceNo, String deviceId, LocalDateTime regdate ) {
        this.memberNo  = memberNo;
        this.deviceNo = deviceNo;
        this.deviceId = deviceId;
        this.regdate = regdate;
        this.stay = 3;
        this.activity = 3;
        this.sleep = 3;
    }


    public static String getColumn() {
        return "no" +
                ", member_no" +
                ", device_no" +
                ", device_id" +
                ", regdate" +
                ", stay" +
                ", activity" +
                ", sleep" +
                ", meal" +
                ", hr"+
                ", br"+
                ", mot" +
                ", r" +
                ", temperature"+
                ", humidity" +
                ", tvoc"+
                ", light"+
                '\n';
    }

    @Override
    public String toString() {
        return  no +
                ", " + memberNo +
                ", " + deviceNo +
                ", " + deviceId +
                ", " + regdate +
                ", " + stay +
                ", " + activity +
                ", " + sleep +
                ", " + meal +
                ", " + hr +
                ", " + br +
                ", " + mot +
                ", " + r +
                ", " + temperature +
                ", " + humidity +
                ", " + tvoc +
                ", " + light +
                '\n';
    }
}

