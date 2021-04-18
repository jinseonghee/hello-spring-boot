package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; //엔티티 매니저 내부에 영속성 컨텍스트(persistence context)를 두어 엔티티 관리

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //insert에다 id를 다 자동으로 매핑해줌
        return member;
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member); //값이 없을수도 있기 때문에
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  em.createQuery("select m from Member m where m.name =:name" , Member.class)
        .setParameter("name", name)
                .getResultList();

         return result.stream().findAny(); //stream은 for문과 같은 의미
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //jpql 언어(객체(entity)를 대상(m=member)으로 쿼리를 날림 )
                .getResultList();                                               //Member.class는
    }



    /*
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

     */
}
