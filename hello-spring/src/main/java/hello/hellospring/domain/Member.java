package hello.hellospring.domain;

public class Member {

    private Long id; //고객이 정한 id가 아닌 system이 정한 id
    private String name; // 고객이 등록을 할때 적는 name

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
