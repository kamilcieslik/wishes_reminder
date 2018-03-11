package pl.escience.zdpp.lab03gr1.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "relation")
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "relation_name")
    private String ralationName;

    @OneToMany(mappedBy = "relation", cascade = CascadeType.ALL)
    private List<PersonAnniversary> personAnniversaries;

    public Relation() {
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

    public void addPersonAnniverasry(PersonAnniversary personAnniversary) {
        if (personAnniversaries == null)
            personAnniversaries = new ArrayList<>();

        personAnniversaries.add(personAnniversary);
        personAnniversary.setRelation(this);
    }

    @Override
    public String toString() {
        return ralationName;
    }
}
