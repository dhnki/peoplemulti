package pmhealthcare.hatinroo.datamart.repository.device.date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmhealthcare.hatinroo.datamart.model.device.date.DateHumidity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface DateHumidityRepository extends JpaRepository<DateHumidity, Long>{

    @Query(value = "select * from hatinroo.date_humiditys where device_no = :deviceNo and regdate = :regdate LIMIT 0, 1", nativeQuery = true)
    DateHumidity findByDeviceNoAndRegDate(long deviceNo, LocalDateTime regdate);

}
