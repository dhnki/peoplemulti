package pmhealthcare.hatinroo.datamart.repository.device;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmhealthcare.hatinroo.datamart.model.device.MemberDailyStats;
import pmhealthcare.hatinroo.datamart.model.device.MemberStatus;

import java.util.List;

@Repository
public interface MemberDailyStatsRepository extends JpaRepository<MemberDailyStats, Long>{

}
