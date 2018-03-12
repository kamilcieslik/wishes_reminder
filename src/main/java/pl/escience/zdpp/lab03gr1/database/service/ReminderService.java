package pl.escience.zdpp.lab03gr1.database.service;

import org.hibernate.SessionFactory;
import pl.escience.zdpp.lab03gr1.database.dao.*;
import pl.escience.zdpp.lab03gr1.database.entity.*;
import pl.escience.zdpp.lab03gr1.database.exception.UniqueViolationException;
import pl.escience.zdpp.lab03gr1.database.view.ViewExtendedPersonAnniversary;

import java.util.List;

public class ReminderService {
    private AddressDAO addressDAO;
    private PersonAnniversaryDAO personAnniversaryDAO;
    private RelationDAO relationDAO;
    private SentWishDAO sentWishDAO;
    private UserDAO userDAO;
    private WishTemplateDAO wishTemplateDAO;
    private ViewExtendedPersonAnniversaryDAO viewExtendedPersonAnniversaryDAO;

    public ReminderService(SessionFactory sessionFactory) {
        addressDAO = new AddressDAO(sessionFactory);
        personAnniversaryDAO = new PersonAnniversaryDAO(sessionFactory);
        relationDAO = new RelationDAO(sessionFactory);
        sentWishDAO = new SentWishDAO(sessionFactory);
        userDAO = new UserDAO(sessionFactory);
        wishTemplateDAO = new WishTemplateDAO(sessionFactory);
        viewExtendedPersonAnniversaryDAO = new ViewExtendedPersonAnniversaryDAO(sessionFactory);
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

    public void saveUser(User user) throws UniqueViolationException {
        userDAO.saveEntity(user);
    }

    public User getUser(int id) {
        return userDAO.getEntity(id);
    }

    public User getUserByLogin(String login) {
        return userDAO.getEntityByLogin(login);
    }

    public void deleteUser(int id) {
        userDAO.deleteEntity(id);
    }

    public List<SentWish> getSentWishes() {
        return sentWishDAO.getEntities();
    }

    public void saveSentWish(SentWish sentWish) {
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

    public List<ViewExtendedPersonAnniversary> getViewExtendedContacts() {
        return viewExtendedPersonAnniversaryDAO.getEntities();
    }

    public List<ViewExtendedPersonAnniversary> getViewExtendedContactsByUserId(int userId) {
        return viewExtendedPersonAnniversaryDAO.getEntitiesByUserId(userId);
    }

    public ViewExtendedPersonAnniversary getViewExtendedContact(int id) {
        return viewExtendedPersonAnniversaryDAO.getEntity(id);
    }
}
