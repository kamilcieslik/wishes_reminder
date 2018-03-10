package pl.escience.zdpp.lab03gr1.app.database.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wish_template")
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "relation_name")
    private String ralationName;

    @OneToMany(mappedBy = "person_anniversary", cascade = CascadeType.ALL)
    private List<PersonAnniversary> personAnniversaries;

    public Relation() {
    }

    public Relation(String ralationName) {
        this.ralationName = ralationName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRalationName() {
        return ralationName;
    }

    public void setRalationName(String ralationName) {
        this.ralationName = ralationName;
    }

    public List<PersonAnniversary> getPersonAnniversaries() {
        return personAnniversaries;
    }

    public void setPersonAnniversaries(List<PersonAnniversary> personAnniversaries) {
        this.personAnniversaries = personAnniversaries;
    }

    @Override
    public String toString() {
        return "Relation{" + "id=" + id + ", ralationName='" + ralationName + '\'' + ", personAnniversaries=" + personAnniversaries + '}';
    }
}
