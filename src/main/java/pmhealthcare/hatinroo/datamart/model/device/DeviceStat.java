package pmhealthcare.hatinroo.datamart.model.device;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "device_stats")
@Data
public class DeviceStat {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "device_stat_id", columnDefinition = "BINARY(16)")
    private UUID id;

    private long deviceNo;

    private Float avgDiff;
    private Float deepSleepHr;
    private Float deepSleepBr;

    private LocalDateTime regdate;


}
