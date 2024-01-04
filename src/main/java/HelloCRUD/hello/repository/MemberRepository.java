package HelloCRUD.hello.repository;

import HelloCRUD.hello.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByEmail(String email);

}
