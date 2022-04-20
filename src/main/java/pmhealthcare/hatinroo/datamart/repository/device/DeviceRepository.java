package pmhealthcare.hatinroo.datamart.repository.device;

import pmhealthcare.hatinroo.datamart.model.device.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>{

    Device findByNo(long deviceNo);

}
