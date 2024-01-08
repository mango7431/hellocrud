package HelloCRUD.hello.repository;

import HelloCRUD.hello.entity.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByEmail(String email);

    /**
     * 유효성 검사 - 중복체크
     * @param email 회원 이메일
     * @return
     */
    boolean existsByEmail(String email);


    @Query(value = "update Member set name = :#{#name}, password = :#{#password}, address = :#{#address} where email = :#{#email}")
    @Transactional
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    int updateMember(@Param(value="name")String name,@Param(value = "password")String password,@Param(value = "address")String address,@Param(value = "email")String email);

}
