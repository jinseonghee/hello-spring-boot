package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository { // 아직 데이터 저장소가 선정되지 않았음의 가상의 시나리오를 썼기 떄문에 class를 변경가능하게 설계하기 위해 interface를 씀
    //저장소에 있는 모든 기능 만들기
    Member save(Member member); //회원을 저장하면 저장된 회원이 반환
    Optional<Member> findById(Long id); //id 로 회원을 찾는 것
    Optional<Member> findByName(String name); //Optional은 findbyid나 findbyname 으로 값을 가져올때 값이 없을 경우 null로 반환될 때 null을 그대로 반환하는 것보다 optional로 감싸서 반환하는 걸 선호
    List<Member> findAll();


}
