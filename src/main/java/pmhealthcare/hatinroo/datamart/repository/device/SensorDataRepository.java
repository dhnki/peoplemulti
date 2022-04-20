package pmhealthcare.hatinroo.datamart.repository.device;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmhealthcare.hatinroo.datamart.model.device.SensorData;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long>{

    @Query(value =
            " select * " +
            " from hatinroo.sensor_datas " +
            " where date_format(regdate,'%Y-%m-%d') = date_format(:sdate, '%Y-%m-%d') " +
            " order by regdate asc LIMIT :nbegin, :nrowscount ", nativeQuery = true)
    List<SensorData> getByDate(LocalDateTime sdate, int nbegin, int nrowscount);


    @Query(value = " SELECT count(*) FROM hatinroo.sensor_datas where date_format(regdate, '%Y-%m-%d') = date_format(:sdate, '%Y-%m-%d') ", nativeQuery = true)
    List<Object[]> getCount(LocalDateTime sdate);

    @Modifying
    @Query(value = " delete from hatinroo.sensor_datas where date_format(regdate,'%Y-%m-%d') = date_format(:searchDate, '%Y-%m-%d') ", nativeQuery = true)
    int deleteByDate(LocalDateTime searchDate);
}
