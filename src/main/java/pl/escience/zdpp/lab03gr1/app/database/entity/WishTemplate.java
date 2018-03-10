package pl.escience.zdpp.lab03gr1.app.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "wish_template")
public class WishTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User UserId;

    public WishTemplate() {
    }

    public WishTemplate(String text) {
        this.text = text;
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

    public User getUserId() {
        return UserId;
    }

    public void setUserId(User userId) {
        UserId = userId;
    }

    @Override
    public String toString() {
        return "WishTemplate{" + "id=" + id + ", text='" + text + '\'' + '}';
    }
}
