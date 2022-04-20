package pmhealthcare.hatinroo.datamart.repository.device;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmhealthcare.hatinroo.datamart.model.device.DeviceStat;
import pmhealthcare.hatinroo.datamart.model.device.SensorData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceStatRepository extends JpaRepository<DeviceStat, UUID>{


}
