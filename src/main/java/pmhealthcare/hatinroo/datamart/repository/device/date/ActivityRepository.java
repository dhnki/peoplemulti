package pmhealthcare.hatinroo.datamart.repository.device.date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmhealthcare.hatinroo.datamart.model.device.date.Activity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{

//
//    //시간 데이터
//    @Query(value = "SELECT * FROM hatinroo.activitys WHERE deviceId = :deviceId ORDER BY regdate desc LIMIT 0, 1", nativeQuery = true)
//    Activity findByDeviceIdAndRecent(String deviceId);
//
//
//    @Query(value = "select * from hatinroo.activitys where device_no = :deviceNo and date_format(regdate, '%Y-%m-%d') = date_format( :regdate, '%Y-%m-%d') order by activity_no LIMIT 0, 1", nativeQuery = true)
//    Activity findByDeviceNoAndRegDate(long deviceNo, LocalDate regdate);


    @Query(value = "select * from hatinroo.activitys where device_no = :deviceNo and regdate = :regdate order by activity_no", nativeQuery = true)
    List<Activity> getToday(long deviceNo, LocalDate regdate);
}
