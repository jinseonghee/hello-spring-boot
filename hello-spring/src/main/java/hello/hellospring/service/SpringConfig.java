package hello.hellospring.service;


import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //spring 이 뜰 때 @configuration을 읽고 밑에있는 @Bean 을 spring에 등록하라는 뜻으로 알아서 bean을 등록함.
public class SpringConfig {

    @Bean //@Service 없이 스프링에 빈 직접 등록하는 법
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
