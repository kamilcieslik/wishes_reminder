package pl.escience.zdpp.lab03gr1.database.view;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Immutable
@Table(name = "view_extended_person_anniversary")
public class ViewExtendedPersonAnniversary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer personAnniversaryId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "displayed_anniversary_date")
    private Date displayedAnniversaryDate;

    @Column(name = "anniversary_date")
    private Date anniversaryDate;

    @Column(name = "anniversary_kind")
    private String anniversaryKind;

    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "relation_id")
    private Integer relationId;

    @Column(name = "relation_name")
    private String relationName;

    @Column(name = "number_of_sent_wishes")
    private Integer numberOfSentWishes;

    @Transient
    private Integer numberOfDaysToNextAnniversary;

    @Transient
    private Date nextAnniversaryDate;

    public ViewExtendedPersonAnniversary() {
    }

    public ViewExtendedPersonAnniversary(Integer userId, String firstName, String lastName, String email,
                                         Date displayedAnniversaryDate, Date anniversaryDate, String anniversaryKind,
                                         Integer addressId, String street, String city, String postalCode,
                                         String country, Integer relationId, String relationName,
                                         Integer numberOfSentWishes) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.displayedAnniversaryDate = displayedAnniversaryDate;
        this.anniversaryDate = anniversaryDate;
        this.anniversaryKind = anniversaryKind;
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.relationId = relationId;
        this.relationName = relationName;
        this.numberOfSentWishes = numberOfSentWishes;
    }

    public Integer getPersonAnniversaryId() {
        return personAnniversaryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getDisplayedAnniversaryDate() {
        return displayedAnniversaryDate;
    }

    public Date getAnniversaryDate() {
        return anniversaryDate;
    }

    public String getAnniversaryKind() {
        return anniversaryKind;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public String getRelationName() {
        return relationName;
    }

    public Integer getNumberOfSentWishes() {
        return numberOfSentWishes;
    }

    public Integer getNumberOfDaysToNextAnniversary() {
        return numberOfDaysToNextAnniversary;
    }

    public Date getNextAnniversaryDate() {
        return nextAnniversaryDate;
    }

    public void calculateNextAnniversaryFields() {
        Calendar anniversaryDate = Calendar.getInstance();
        anniversaryDate.setTime(this.anniversaryDate);

        int anniversaryDateMonth = anniversaryDate.get(Calendar.MONTH) + 1;
        int anniversaryDateDay = anniversaryDate.get(Calendar.DAY_OF_MONTH);

        Calendar dateNow = Calendar.getInstance();
        int dateNowYear = dateNow.get(Calendar.YEAR);
        int dateNowMonth = dateNow.get(Calendar.MONTH) + 1;
        int dateNowDay = dateNow.get(Calendar.DAY_OF_MONTH);

        Calendar nextAnniversaryDate = Calendar.getInstance();
        nextAnniversaryDate.set(Calendar.DAY_OF_MONTH, anniversaryDateDay);
        nextAnniversaryDate.set(Calendar.MONTH, anniversaryDateMonth - 1);

        if ((dateNowMonth == anniversaryDateMonth && dateNowDay <= anniversaryDateDay)
                || (dateNowMonth < anniversaryDateMonth))
            nextAnniversaryDate.set(Calendar.YEAR, dateNowYear);
        else
            nextAnniversaryDate.set(Calendar.YEAR, dateNowYear + 1);
        this.nextAnniversaryDate = nextAnniversaryDate.getTime();

        long numberOfDaysToNextAnniversary = nextAnniversaryDate.getTime().getTime() - dateNow.getTime().getTime();
        this.numberOfDaysToNextAnniversary = Math.toIntExact(numberOfDaysToNextAnniversary / 1000 / 60 / 60 / 24);
    }


    @Override
    public String toString() {
        return "ViewExtendedPersonAnniversary{" +
                "personAnniversaryId=" + personAnniversaryId +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", displayedAnniversaryDate=" + displayedAnniversaryDate +
                ", anniversaryDate=" + anniversaryDate +
                ", anniversaryKind='" + anniversaryKind + '\'' +
                ", addressId=" + addressId +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", relationId=" + relationId +
                ", relationName='" + relationName + '\'' +
                ", numberOfSentWishes=" + numberOfSentWishes +
                ", numberOfDaysToNextAnniversary=" + numberOfDaysToNextAnniversary +
                ", nextAnniversaryDate=" + nextAnniversaryDate +
                '}';
    }
}
