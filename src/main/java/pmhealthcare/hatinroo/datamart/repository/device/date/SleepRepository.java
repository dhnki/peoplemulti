package pmhealthcare.hatinroo.datamart.repository.device.date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmhealthcare.hatinroo.datamart.model.device.date.Sleep;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SleepRepository extends JpaRepository<Sleep, Long>{

//    @Query(value = "select * from hatinroo.sleeps where device_no = :deviceNo and date_format(regdate, '%Y-%m-%d') = date_format( :regdate, '%Y-%m-%d') order by sleep_no LIMIT 0, 1", nativeQuery = true)
//    Sleep findByDeviceNoAndRegDate(long deviceNo, LocalDate regdate);


    @Query(value = "select * from hatinroo.sleeps where device_no = :deviceNo and regdate= :regdate order by sleep_no", nativeQuery = true)
    List<Sleep> getToday(long deviceNo, LocalDate regdate);


    @Query(value = "select * fr", nativeQuery = true)
    List<Object[]> getAvgDiff(long deviceNo, LocalDate regdate);

}
