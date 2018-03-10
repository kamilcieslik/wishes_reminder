package pl.escience.zdpp.lab03gr1.app.database.entity;

import com.sun.jndi.cosnaming.IiopUrl;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;


    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WishTemplate> wishTemplates;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PersonAnniversary> personAnniversaries;

    public User() {
    }

    public User(String login, String password, String firstName, String lastName, String email) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<WishTemplate> getWishTemplates() {
        return wishTemplates;
    }

    public void setWishTemplates(List<WishTemplate> wishTemplates) {
        this.wishTemplates = wishTemplates;
    }

    public List<PersonAnniversary> getPersonAnniversaries() {
        return personAnniversaries;
    }

    public void setPersonAnniversaries(List<PersonAnniversary> personAnniversaries) {
        this.personAnniversaries = personAnniversaries;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", login='" + login + '\'' + ", password='" + password + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + '}';
    }
}
