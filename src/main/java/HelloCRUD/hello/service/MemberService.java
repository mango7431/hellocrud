package HelloCRUD.hello.service;

import HelloCRUD.hello.dto.MemberFormDto;
import HelloCRUD.hello.entity.Member;
import HelloCRUD.hello.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService{

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public Member findByEmail(String email){
        Member member = memberRepository.findByEmail(email);
        return member;
    }

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public Map<String,Integer> checkByEmail(String email){
        log.info(email);
        Map<String,Integer> result = new HashMap<>();
        boolean check = memberRepository.existsByEmail(email);

        if(check==true){
            result.put("result",1);
        }else{
            result.put("result",0);
        }
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Member member = memberRepository.findByEmail(email);
        if(member==null){
            throw new UsernameNotFoundException(email); // 로그인 할 유저의 이메일을 파라미터로 전달받는다.
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    public void updateMember(MemberFormDto memberFormDto) {

        /* 회원 찾기 */
        Member member = memberRepository.findByEmail(memberFormDto.getEmail());
        if(member==null){
            throw new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
        }

        /* 수정한 비밀번호 암호화 및 변경 */
        Member newmember = Member.createMember(memberFormDto,passwordEncoder);
        memberRepository.updateMember(newmember.getName(),newmember.getPassword(),newmember.getAddress(), newmember.getEmail());
    }

    public boolean checkPassword(String email, String checkPassword) {

        Member member = memberRepository.findByEmail(email);
        if(member==null){
            throw new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
        }
        String realPassword = member.getPassword();
        boolean matches = passwordEncoder.matches(checkPassword,realPassword);
        return matches;

    }

    public int deleteMember(Member member) {
        memberRepository.delete(member);

        Member member2 = memberRepository.findByEmail(member.getEmail());

        int result;
        if(member2==null){
            result = 0;
        }else{
            result = 1;
        }

        return result;
    }
}
