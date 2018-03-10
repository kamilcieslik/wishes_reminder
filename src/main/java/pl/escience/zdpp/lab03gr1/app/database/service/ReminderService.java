package pl.escience.zdpp.lab03gr1.app.database.service;

import pl.escience.zdpp.lab03gr1.app.database.dao.*;
import pl.escience.zdpp.lab03gr1.app.database.entity.*;

import java.util.List;

public class ReminderService {
    private AddressDAO addressDAO;
    private PersonAnniversaryDAO personAnniversaryDAO;
    private RelationDAO relationDAO;
    private SentWishDAO sentWishDAO;
    private UserDAO userDAO;
    private WishTemplateDAO wishTemplateDAO;

    public ReminderService(AddressDAO addressDAO, PersonAnniversaryDAO personAnniversaryDAO, RelationDAO relationDAO, SentWishDAO sentWishDAO, UserDAO userDAO, WishTemplateDAO wishTemplateDAO) {
        this.addressDAO = addressDAO;
        this.personAnniversaryDAO = personAnniversaryDAO;
        this.relationDAO = relationDAO;
        this.sentWishDAO = sentWishDAO;
        this.userDAO = userDAO;
        this.wishTemplateDAO = wishTemplateDAO;
    }

    public List<Address> getAddresses() {
        return addressDAO.getEntities();
    }

    public void saveAddress(Address address) {
        addressDAO.saveEntity(address);
    }

    public Address getAddress(int id) {
        return addressDAO.getEntity(id);
    }

    public void deleteAddress(int id) {
        addressDAO.deleteEntity(id);
    }

    public List<User> getUsers() {
        return userDAO.getEntities();
    }

    public void saveUsers(User user) {
        userDAO.saveEntity(user);
    }

    public User getUser(int id) {
        return userDAO.getEntity(id);
    }

    public void deleteUser(int id) {
        userDAO.deleteEntity(id);
    }


    public List<SentWish> getSentWishes() {
        return sentWishDAO.getEntities();
    }

    public void saveUsers(SentWish sentWish) {
        sentWishDAO.saveEntity(sentWish);
    }

    public SentWish getSentWish(int id) {
        return sentWishDAO.getEntity(id);
    }

    public void deleteSentWish(int id) {
        sentWishDAO.deleteEntity(id);
    }

    public List<Relation> getRalations() {
        return relationDAO.getEntities();
    }

    public void saveRelation(Relation relation) {
        relationDAO.saveEntity(relation);
    }

    public Relation getRelation(int id) {
        return relationDAO.getEntity(id);
    }

    public void deleteRelation(int id) {
        relationDAO.deleteEntity(id);
    }

    public List<WishTemplate> getWishesTemplates() {
        return wishTemplateDAO.getEntities();
    }

    public void saveWishTemplate(WishTemplate wishTemplate) {
        wishTemplateDAO.saveEntity(wishTemplate);
    }

    public WishTemplate getWishTemplate(int id) {
        return wishTemplateDAO.getEntity(id);
    }

    public void deleteWishTemplate(int id) {
        wishTemplateDAO.deleteEntity(id);
    }

    public List<PersonAnniversary> getPersonAnniversaries() {
        return personAnniversaryDAO.getEntities();
    }

    public void savePersonAnniversary(PersonAnniversary personAnniversary) {
        personAnniversaryDAO.saveEntity(personAnniversary);
    }

    public PersonAnniversary getPersonAnniversary(int id) {
        return personAnniversaryDAO.getEntity(id);
    }

    public void deletePersonAnniversary(int id) {
        personAnniversaryDAO.deleteEntity(id);
    }

}
