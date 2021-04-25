package hello.hellospring.service;


import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.swing.*;

@Configuration //spring 이 뜰 때 @configuration을 읽고 밑에있는 @Bean 을 spring에 등록하라는 뜻으로 알아서 bean을 등록함.
public class SpringConfig {   //@configuration은 한 개 이상의 @Bean 을 가지고 있다는 뜻

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
        private final EntityManager em;

        public SpringConfig(
            EntityManager em
        ) {
            this.em = em;
        }
    */
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
/*
    @Bean
    public MemberRepository memberRepository() {
        /*return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);*/
    //return new JpaMemberRepository(em);

    /*@Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/
}





