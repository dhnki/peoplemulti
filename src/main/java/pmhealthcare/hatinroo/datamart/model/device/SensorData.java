package pmhealthcare.hatinroo.datamart.model.device;


import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Getter
@Entity
@Table(name = "sensor_datas")
public class SensorData {

    @Id
    @Column(name="sensor_id", columnDefinition = "char")
    private String sensorId;

    private String deviceId;

    private LocalDateTime regdate;

    private String chipId;

    private Float hr;

    private Float hrConv;

    private Float br;

    private Float brConv;

    private Float mot;

    private Float r;

    private Float temperature;

    private Float humidity;

    private Float tvoc;

    private Float light;

    private String dFw;

    private String rFw;

    private String d1;

    private String d2;

    private String d3;

    private String lteId;

    private LocalDateTime dt;

    private Short status;


    public static String getColumn(){
        return "sensor_id" +
                ", " + "device_id" +
                ", " + "regdate" +
                ", " + "chip_id" +
                ", " + "hr" +
                ", " + "hr_conv" +
                ", " + "br" +
                ", " + "br_conv" +
                ", " + "mot" +
                ", " + "r" +
                ", " + "temperature" +
                ", " + "humidity" +
                ", " + "tvoc" +
                ", " + "light" +
                ", " + "d_fw" +
                ", " + "r_fw" +
                ", " + "d1" +
                ", " + "d2" +
                ", " + "d3" +
                ", " + "lte_id" +
                ", " + "dt" +
                ", " + "status" +
                "\n";
    }

    @Override
    public String toString() {
        return  sensorId +
                ", " +  deviceId +
                ", " + regdate +
                ", " + chipId +
                ", " + hr +
                ", " + hrConv +
                ", " + br +
                ", " + brConv +
                ", " + mot +
                ", " + r +
                ", " + temperature +
                ", " + humidity +
                ", " + tvoc +
                ", " + light +
                ", " + dFw +
                ", " + rFw +
                ", " + d1 +
                ", " + d2 +
                ", " + d3 +
                ", " + lteId +
                ", " + dt +
                ", " + status +
                "\n";
    }
}
