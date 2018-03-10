package pl.escience.zdpp.lab03gr1.app.database.entity;

import javax.management.relation.Relation;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person_anniversary")
public class PersonAnniversary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "anniversary_date")
    private Date anniversaryDate;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private Boolean birthday;

    @JoinColumn(name = "user_id")
    private User user;

    @JoinColumn(name = "address_id")
    private Address address;

    @JoinColumn(name = "relation_id")
    private Relation relation;

    @OneToMany(mappedBy = "sent_wish", cascade = CascadeType.ALL)
    private List<SentWish> sentWishes;

    public PersonAnniversary() {
    }

    public PersonAnniversary(String firstName, String lastName, Date anniversaryDate, String email, Boolean birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.anniversaryDate = anniversaryDate;
        this.email = email;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getAnniversaryDate() {
        return anniversaryDate;
    }

    public void setAnniversaryDate(Date anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getBirthday() {
        return birthday;
    }

    public void setBirthday(Boolean birthday) {
        this.birthday = birthday;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public List<SentWish> getSentWishes() {
        return sentWishes;
    }

    public void setSentWishes(List<SentWish> sentWishes) {
        this.sentWishes = sentWishes;
    }

    @Override
    public String toString() {
        return "PersonAnniversary{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", anniversaryDate=" + anniversaryDate + ", email='" + email + '\'' + ", birthday=" + birthday + '}';
    }

}
