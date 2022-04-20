package pmhealthcare.hatinroo.datamart.repository.device;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmhealthcare.hatinroo.datamart.model.device.MemberStatusHist;
import pmhealthcare.hatinroo.datamart.model.device.SensorData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MemberStatusHistRepository extends JpaRepository<MemberStatusHist, Long>{


    // 일별
    @Query(value = " SELECT device_no, date_format(regdate, '%Y-%m-%d') as regdate, " +
                        " ifnull(round(avg(nullif(hr, 0)), 1), 0) avg_hr,                   ifnull(round(max(hr), 1), 0) max_hr,                   ifnull(round(min(nullif(hr,0)), 1), 0) min_hr," +
                        " ifnull(round(avg(nullif(br, 0)), 1), 0) avg_br,                   ifnull(round(max(br), 1), 0) max_br,                   ifnull(round(min(nullif(br,0)), 1), 0) min_br," +
                        " ifnull(round(avg(nullif(temperature, 0)), 1), 0) avg_temperature, ifnull(round(max(temperature), 1), 0) max_temperature, ifnull(round(min(nullif(temperature,0)), 1), 0) min_temperature, " +
                        " ifnull(round(avg(nullif(humidity, 0)), 1), 0) avg_humidity,       ifnull(round(max(humidity), 1), 0) max_humidity,       ifnull(round(min(nullif(humidity,0)), 1), 0) min_humidity, " +
                        " ifnull(round(avg(nullif(tvoc, 0)), 1) ,0) avg_tvoc,               ifnull(round(max(tvoc), 1), 0) max_tvoc,               ifnull(round(min(nullif(tvoc,0)), 1), 0) min_tvoc, " +
                        " ifnull(round(avg(nullif(light, 0)), 1), 0) avg_light,             ifnull(round(max(light), 1), 0) max_light,             ifnull(round(min(nullif(light,0)), 1), 0) min_light " +
                    " FROM hatinroo.member_status_hist " +
                    " WHERE date_format(regdate, '%Y-%m-%d') = date_format( DATE_ADD(now(), INTERVAL -1 DAY) , '%Y-%m-%d') " +
                    " group by member_no, device_no, date_format(regdate, '%Y-%m-%d') ", nativeQuery = true)
    List<Object[]> getHistDaily();

    // 일별
    @Query(value = "SELECT * " +
            " FROM hatinroo.member_status_hist " +
            " WHERE date_format( regdate, '%Y-%m-%d') = date_format( DATE_ADD(now(), INTERVAL -1 DAY) , '%Y-%m-%d')" +
            " AND device_no = :deviceNo " +
            " order by regdate " , nativeQuery = true)
    List<MemberStatusHist> getHistDaily(long deviceNo);

    // 당일
    @Query(value = "SELECT device_no, date_format(regdate, '%Y-%m-%d') as regdate, " +
                        " ifnull(round(avg(nullif(hr, 0)), 1), 0) avg_hr,                   ifnull(round(max(hr), 1), 0) max_hr,                   ifnull(round(min(nullif(hr,0)), 1), 0) min_hr," +
                        " ifnull(round(avg(nullif(br, 0)), 1), 0) avg_br,                   ifnull(round(max(br), 1), 0) max_br,                   ifnull(round(min(nullif(br,0)), 1), 0) min_br," +
                        " ifnull(round(avg(nullif(temperature, 0)), 1), 0) avg_temperature, ifnull(round(max(temperature), 1), 0) max_temperature, ifnull(round(min(nullif(temperature,0)), 1), 0) min_temperature, " +
                        " ifnull(round(avg(nullif(humidity, 0)), 1), 0) avg_humidity,       ifnull(round(max(humidity), 1), 0) max_humidity,       ifnull(round(min(nullif(humidity,0)), 1), 0) min_humidity, " +
                        " ifnull(round(avg(nullif(tvoc, 0)), 1) ,0) avg_tvoc,               ifnull(round(max(tvoc), 1), 0) max_tvoc,               ifnull(round(min(nullif(tvoc,0)), 1), 0) min_tvoc, " +
                        " ifnull(round(avg(nullif(light, 0)), 1), 0) avg_light,             ifnull(round(max(light), 1), 0) max_light,             ifnull(round(min(nullif(light,0)), 1), 0) min_light " +
                    " FROM hatinroo.member_status_hist " +
                    " WHERE date_format(regdate, '%Y-%m-%d') = date_format( now() , '%Y-%m-%d') " +
                    " group by member_no, device_no, date_format(regdate, '%Y-%m-%d') ", nativeQuery = true)
    List<Object[]> getTodayStats();


    // 당일
    @Query(value = "SELECT * " +
            " FROM hatinroo.member_status_hist " +
            " WHERE date_format( regdate, '%Y-%m-%d' ) = date_format( now() , '%Y-%m-%d' ) " +
            " AND device_no = :deviceNo " +
            " order by regdate ", nativeQuery = true)
    List<MemberStatusHist> getTodayTimeStats(long deviceNo);


    //해당 날짜 조회
    @Query(value =
            " select * " +
                    " from hatinroo.member_status_hist " +
                    " where date_format(regdate,'%Y-%m-%d') = date_format(:sdate, '%Y-%m-%d') " +
                    " order by regdate asc LIMIT :nbegin, :nrowscount ", nativeQuery = true)
    List<MemberStatusHist> getByDate(LocalDateTime sdate, int nbegin, int nrowscount);


    //해당 날짜 총 row 수
    @Query(value = "SELECT count(*) FROM hatinroo.member_status_hist where date_format(regdate, '%Y-%m-%d') = date_format(:sdate, '%Y-%m-%d') ", nativeQuery = true)
    List<Object[]> getCount(LocalDateTime sdate);


    @Modifying
    @Query(value = " delete from hatinroo.member_status_hist where date_format(regdate,'%Y-%m-%d') = date_format(:searchDate, '%Y-%m-%d') ", nativeQuery = true)
    int deleteByDate(LocalDateTime searchDate);


    @Query(value =
            " select device_no, member_no " +
                " , date_format(regdate, '%Y-%m-%d') as regdate " +
                " , ifnull(round(avg(nullif(hr, 0)), 1), 0) hr " +
                " , ifnull(round(avg(nullif(br, 0)), 1), 0) br " +
                " , ifnull(round(avg(nullif(temperature, 0)), 1), 0) temp " +
                " , ifnull(round(avg(nullif(humidity, 0)), 1), 0) humi " +
                " , ifnull(round(avg(nullif(light, 0)), 1), 0) light " +
                " , ifnull(round(avg(nullif(tvoc, 0)), 1), 0) tvoc " +
                " , ifnull(sum(nullif( CASE WHEN sleep = 1 then 1 ELSE 0 END , 0)), 0) sleep " +
                " , ifnull(sum(nullif( CASE WHEN stay != 2 then 1 ELSE 0 END , 0)), 0) stay " +
                " , ifnull(sum(nullif( CASE WHEN stay = 2 then 1 ELSE 0 END , 0)), 0) absence " +
                " , ifnull(sum(nullif( CASE WHEN activity = 1 then 1 ELSE 0 END , 0)), 0) activity " +
            " from hatinroo.member_status_hist " +
            " where date_format(regdate ,'%Y-%m-%d') = date_format(:searchDate ,'%Y-%m-%d') " +
//                " AND member_no != 0 " +
            " group by device_no," +
//                    " member_no," +
                    " date_format(regdate, '%Y-%m-%d')"
            , nativeQuery = true)
    List<Object[]> getDailyAvgHist(LocalDate searchDate);


}
