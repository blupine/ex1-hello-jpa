package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table("USER") // Table 이름 지정은 이렇게 annotation 사
public class Member {

    @Id
    private Long id;

    //@Column("Username") // column 이름 지정은 이렇게
    private String name;

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
