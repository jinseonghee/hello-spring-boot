package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //이 어노테이션을 쓰면 spring container가 membercontroller객체를 생성해서 관리
public class MemberController {

    private final MemberService memberService;

    //spring 이 실행되면 일단 @Controller를 읽고 @Autowired 하려고 service 단을 찾는다.
    //service단을 찾을 땐 spring container가 @service를 보고 찾는다. 찾아서 봤더니 또 repository가 @Autowired가 되있어서,
    //@repository를 찾아 또 스프링 빈에 등록을 해주면 스프링 컨테이너에 등록된 빈으로 spring 이 자동으로 autowired 시켜준다.

    @Autowired
    public MemberController(MemberService memberService) { //생성자 위에 autowired를 써주면 의존관계에 있는 service를 주입 시켜줌(=dependency injection)

        this.memberService = memberService;
    }
}
