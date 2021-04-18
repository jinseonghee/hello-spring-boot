package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //jpa가 관리하는 entity 라는 표시
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키(pk) 생성을 데이터베이스에 위임. 즉, id값을 null로 하면 db가 알아서 auto_increment해준다.
    private Long id;
    private String name;

    /*
    private Long id; //고객이 정한 id가 아닌 system이 정한 id
    private String name; // 고객이 등록을 할때 적는 name
*/
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
