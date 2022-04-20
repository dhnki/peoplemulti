package pmhealthcare.hatinroo.datamart.repository.device.date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmhealthcare.hatinroo.datamart.model.device.date.DateTvoc;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface DateTvocRepository extends JpaRepository<DateTvoc, Long>{

    @Query(value = "select * from hatinroo.date_tvocs where device_no = :deviceNo and regdate = :regdate LIMIT 0, 1", nativeQuery = true)
    DateTvoc findByDeviceNoAndRegDate(long deviceNo, LocalDateTime regdate);

}
