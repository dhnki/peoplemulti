package pmhealthcare.hatinroo.datamart.repository.app;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmhealthcare.hatinroo.datamart.model.app.Push;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Repository
public interface PushRepository extends JpaRepository<Push, Long>{



    @Modifying
    @Query(value = " delete from hatinroo.pushs where date_format(regdate, '%Y-%m-%d') = date_format( :remDate , '%Y-%m-%d') " , nativeQuery = true)
    int removeByDate(LocalDateTime remDate);


}
