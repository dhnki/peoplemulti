package pmhealthcare.hatinroo.datamart.model.device;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@Table( name = "devices" )
@Getter
@ToString
public class Device {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_no")
    private long no;

    private long memberNo;

    private String deviceId;

    private String deviceName;

    @JsonIgnore
    private String chip_id;

    @JsonIgnore
    private String modelCode;

    @JsonIgnore
    private String imei;

    @JsonIgnore
    private String wifiMac;

    @JsonIgnore
    private String blMac;


    private String fwVersion;

    private String wifiName;

    private String wifiPassword;

    private String wifiIp;

    @JsonIgnore
    private LocalDateTime makeDate;

    @JsonIgnore
    private LocalDateTime regDate;

    @JsonIgnore
    private byte validity;


    public void updateDeviceName(String deviceName){
        this.deviceName = deviceName;
    }

    public void setMember(long memberNo) {
        this.memberNo = memberNo;
    }

    public void setWifiInfo(String wifiName, String wifiIp){
        this.wifiName = wifiName;
        this.wifiIp = wifiIp;
    }
}
