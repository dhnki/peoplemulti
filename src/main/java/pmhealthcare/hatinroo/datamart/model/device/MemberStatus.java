package pmhealthcare.hatinroo.datamart.model.device;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "member_status")
public class MemberStatus {

//    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Id
    @Column(name = "member_status_no")
    private long memberStatusNo;

    private long memberNo;

    private long deviceNo;

    private String deviceId;

    private LocalDateTime regdate;

//    private float hr;
//
//    private float br;
//
//    private byte stay;
//
//    private float mot;
//
//    private float r;
//
//    private float temperature;
//
//    private float humidity;
//
//    private float tvoc;
//
//    private float light;
//
//    private byte activity;
//
//    private byte sleep;

    //private byte meal;


}
