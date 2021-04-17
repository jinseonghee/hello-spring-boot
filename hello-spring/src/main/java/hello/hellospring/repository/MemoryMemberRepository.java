package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//구현하는 class
public class MemoryMemberRepository implements MemberRepository {

    //밑에 save를 어딘가 저장해야 하기 때문에
    private static Map<Long, Member> store = new HashMap<>(); //map 형식으로 key는 Id의 long value는 member
    private static long sequence = 0L; //sequence는 0,1,2 .. key 값을 생성해주는 역할 , 실무에서는 동시성 문제로 hashmap 이나 sequence 그대로 사용하면 안됨

    @Override
    public Member save(Member member) { //이름은 member에서 넘어온 상태
        member.setId(++sequence); //store에 값을 저장하기 전 member에 id값을 넣어주는데 id는 시스템이 지정해주는 것
        store.put(member.getId(), member); //store에 저장되면 Map에 저장
        return member; //spec에 따라 저장된 값을 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //findbyid는 store에서 꺼낸다. 근데 id 값 null이  반환될 경우를 생각해 optional써줌

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //람다 사용해서 loop로 계속 돌림
                .filter(member -> member.getName().equals(name)) //member.getName이 parameter로 넘어온 name이랑 같은지 확인,
                .findAny(); //같은 경우엔 filtering , 그 중에서 찾으면 반환
                            //findAny는 하나로 찾는것, 이거의 결과는 optional로 반환, loop를 다 돌면서 찾아지면 반환, 다 돌려도 없으면 optional에 null이 포함되어 반환
    }

    @Override
    public List<Member> findAll() { //store을 지정할 땐 map이엇는데 , 뽑아낼 땐 list를 많이 씀
        return new ArrayList<>(store.values()); //store에 있는 values를 반환(store에 있는 value는 위에서 map<Long, member>를 지정해준 뒤에 value 값인 member 임)
    }

    //store을 싹 비워줌
    public void clearStore(){
        store.clear();
    }
}
