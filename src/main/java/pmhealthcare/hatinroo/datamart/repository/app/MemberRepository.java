package pmhealthcare.hatinroo.datamart.repository.app;


import pmhealthcare.hatinroo.datamart.model.app.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByNameAndBirthdayAndPhone(String name, LocalDate birthday, String phone);

    Member findByEmailAndBirthday(String email, LocalDate birthday);

    Member findByEmail(String email);

}
