package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@service ,@controller, @repository 를 들어가보면 다  @component로 붙어있기 때문에 스프링이 객체를 생성해서 자동으로 등록한다.
//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { //인스턴스를 직접 만든게 아니라 외부에서 DI로 생성자 주입시켜서 만들어 줌

        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X

        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member); //중복회원 검증 통과하면 저장
        return member.getId(); //id만 반환해줌

    }
    //Join이라는 회원가입 함수에 중복되지 않은 회원임을 구분하는 기능을써서 하나의 함수에 기능이 두 개이기 때문에, 하나의 함수에 하나의 행위만을 해야해서, extract로 추출
    private void validateDuplicateMember(Member member) { //하나의 함수에는 하나의 행위만을 해야한다. 해서 Extract method로 함수를 추출
        memberRepository.findByName(member.getName()) //반환값 optional을 지울 경우
                .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        /*
        Optional<Member> result = memberRepository.findByName(member.getName()); memberRespository에서 findbyname으로 parameter로 넘어온 name을 찾는다.
        result.ifPresent(m -> { //ifpresent는 Null이 아닌 값이 만약 있으면 밑에있는문장이 동작 (반환 값이 Optional이기 때문에 null일 가능성이 있으면 optional로 감싸서 반환시켜준다. )
            //result.get() 바로 꺼내고 싶으면 get으로 꺼내도 되는데 Optional로 한번 감싸는걸 권장
            throw new IllegalStateException("이미 존재하는 회원입니다. ");
*/
        });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
      return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
