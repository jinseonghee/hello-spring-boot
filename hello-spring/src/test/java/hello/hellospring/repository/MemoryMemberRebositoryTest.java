package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


public class MemoryMemberRebositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //callback Method (test case를 작성하고 모두 한꺼번에 실행 할 때 이 method를 써주지 않으면 순서가 보장되지 않아서, 예를들어 findAll tescase가 먼저 실행 될 수 있는데, 그럼 객체가 먼저 생성되어 위에 다른 testcase를 실행할때 객체가 이미 만들어 져 있어 error가 남. testcase가 끝날때마다 이 method를 한번씩 와서 실행해서 clear해주면 error가 나지 않음)
    @AfterEach
    public void afterEach(){
        repository.clearStore(); // test를 한번 끝낼때마다 Repository 저장소를 한번씩 지움,

    }


    @Test //이 어노테이션은 test로 밑에있는 method를 실행시켜 줌
    public void save(){
        Member member = new Member();
        member.setName("spring"); //member에 spring이라는 name을 넣어줌

        repository.save(member); //repository에 member를 save 시켜줌

        //제대로 name이 들어갔는지 검증
        Member result  = repository.findById(member.getId()).get();
        //저장할 때 id 세팅은 MemoryMemberRepository의 save method에서 넣어줌
        //반환타입이 optional로 지정해 줬기 때문에 get이라고 써줘서 값을 바로 꺼낼수 있음(좋은 방법은 아님)
        //검증
        //new에서 member를 저장한거랑, db에서 member를 꺼낸거랑 둘이 똑같으면 참
        //System.out.println("result = " + (result == member)); //결과값 result = true
         assertThat(member).isEqualTo(result);
        //Assertions.assertEquals(member,result); //결과값을 계속 문자로 볼순 없으니 Assertions를 사용(expected, result) 값을 넣어줌

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

        }
}
