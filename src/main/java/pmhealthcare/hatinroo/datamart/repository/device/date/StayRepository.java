package pmhealthcare.hatinroo.datamart.repository.device.date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmhealthcare.hatinroo.datamart.model.device.date.Sleep;
import pmhealthcare.hatinroo.datamart.model.device.date.Stay;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StayRepository extends JpaRepository<Stay, Long>{

//    @Query(value = "select * from hatinroo.stays where device_no = :deviceNo and date_format(regdate, '%Y-%m-%d') = date_format( :regdate, '%Y-%m-%d') order by stay_no LIMIT 0, 1", nativeQuery = true)
//    Stay findByDeviceNoAndRegDate(long deviceNo, LocalDateTime regdate);


    @Query(value = "select * from hatinroo.stays where device_no = :deviceNo and regdate= :regdate order by stay_no", nativeQuery = true)
    List<Stay> getToday(long deviceNo, LocalDate regdate);
}
