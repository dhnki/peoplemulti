package pmhealthcare.hatinroo.datamart.repository.device;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmhealthcare.hatinroo.datamart.model.device.MemberStatus;

import java.util.List;

@Repository
public interface MemberStatusRepository extends JpaRepository<MemberStatus, Long>{

    @Query(value = " SELECT * FROM hatinroo.member_status WHERE regdate BETWEEN DATE_ADD(date_format( now() ,'%Y-%m-%d'), INTERVAL -1 DAY) AND now() ", nativeQuery = true)
    List<MemberStatus> findDailyUseDevice();


}
