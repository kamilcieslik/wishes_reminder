package pl.escience.zdpp.lab03gr1.database.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sent_wish")
public class SentWish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "post_date")
    private Date postDate;

    @Column(name = "sent_by_letter")
    private Boolean sentByLetter;

    @Column(name = "sent_by_email")
    private Boolean sentByEmail;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "person_anniversary_id")
    private PersonAnniversary personAnniversary;

    public SentWish() {
    }

    public SentWish(String text, Date postDate, Boolean sentByLetter, Boolean sentByEmail) {
        this.text = text;
        this.postDate = postDate;
        this.sentByLetter = sentByLetter;
        this.sentByEmail = sentByEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Boolean getSentByLetter() {
        return sentByLetter;
    }

    public void setSentByLetter(Boolean sentByLetter) {
        this.sentByLetter = sentByLetter;
    }

    public Boolean getSentByEmail() {
        return sentByEmail;
    }

    public void setSentByEmail(Boolean sentByEmail) {
        this.sentByEmail = sentByEmail;
    }

    public PersonAnniversary getPersonAnniversary() {
        return personAnniversary;
    }

    public void setPersonAnniversary(PersonAnniversary personAnniversary) {
        this.personAnniversary = personAnniversary;
    }

    @Override
    public String toString() {
        return "SentWish{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", postDate=" + postDate +
                ", sentByLetter=" + sentByLetter +
                ", sentByEmail=" + sentByEmail +
                '}';
    }
}
