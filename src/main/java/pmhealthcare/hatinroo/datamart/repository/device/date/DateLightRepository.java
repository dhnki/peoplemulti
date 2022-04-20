package pmhealthcare.hatinroo.datamart.repository.device.date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmhealthcare.hatinroo.datamart.model.device.date.DateLight;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface DateLightRepository extends JpaRepository<DateLight, Long>{

    @Query(value = "select * from hatinroo.date_lights where device_no = :deviceNo and regdate = :regdate", nativeQuery = true)
    DateLight findByDeviceNoAndRegDate(long deviceNo, LocalDateTime regdate);

}
