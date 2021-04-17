package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping("hello") //Webapplication 에서 /hello 라고 들어오면 이 method를 호출
    public String hello(Model model) { //이 model은 MVC의 m 인 model , 이렇게 선언하면 spring이 model 이란걸 만들어줘서 넣어줌
        model.addAttribute("data", "hello!!"); //Model에 addAttribute 해서 data(key)를 hello!!(value) 로 넘
        return "hello"; //model이라는 데이터를 넘기면서 hello를 찾아서 lendering 하라는 뜻
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) { // 외부에서 parameter를 받을 경 @RequestParame을 쓴다
        // 또 model 에 담으면 view 에서 lendering할때 사용되기 떄문에 model을 넣어줌
        //commamd +p 해서 parameter의 option 값을 보고 value , required 넣어주기
        //required의 default는 true기 때문에 무조건 값을 넣어줘야 하는데 false로 바꿔주면 안넘겨도 된다.
        //여기서 String name을 써준 이유는 url에서 ?name=spring 을 쳐줬을때 이 String값 name이 spring 으로 바뀌
        model.addAttribute("name", name);                      //여기 있는 두번째로 쓰여져 있는 name 값도 spring 으로 바뀐후 model에 담긴다.
        return "hello-template"; //그런 후 hello-template로 넘어가면 {name}값이 spring 으로 치환
    }

    @GetMapping("hello-string")
    @ResponseBody //http 통신의 header와 body부분의 body에 "hello " + name이라는 데이터를 직접 넣어주겠다는 뜻
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;  //예를들어 name에 spring 을 넣으면 template 을 거치지 않고 문자 그대로 hello spring 을 출력, html 소스를 봐도 hellospring 만 적혀있음
        /*HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("jinsunghee name",
                "value spring");
        return ResponseEntity.ok().headers(responseHeaders).body("자 바디에는 값이 들어갈까?");*/
    }

    @GetMapping("hello-api")
    @ResponseBody //Json으로 반환하는게 기본 default로 세팅
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); //객체 생성
        hello.setName(name); //Parameter로 넘어온 name을 넣어줌.
        hello.setAge("31");
        return hello; //문자가 아닌 위에서 만든 객체로 넘겨줌
        //이렇게 넘겨주면 Json 형식으로 화면에 {"name":"spring!!!"} key value의 구조로 나옴
    }

    static class Hello { //이렇게 만들어도 HelloController class 안에서 Hello라는 class를 또 사용 가능. 여기 class의 Hello는 key의 선언된 String name  value는 직접 넣어준 spirng
        private String name; //외부에서 접근 못하도록 선언해 놓았기 떄문에 getter , setter method를 public을 통해서 넣(java bena 표준 방식)
        private String age;
        private List<String> stringList = Arrays.asList("12345", "1234", "123");

        public List<String> getStringList() {
            return stringList;
        }

        /*public void setStringList(List<String> stringList) {
            this.stringList = stringList;
        }*/

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getName() { //꺼낼때는 getname
            return name;
        }

        public void setName(String name) { //넣을 때는 setname
            this.name = name;
        }
    }
}
