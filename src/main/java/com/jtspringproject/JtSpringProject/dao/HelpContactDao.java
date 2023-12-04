package com.jtspringproject.JtSpringProject.dao;

import com.jtspringproject.JtSpringProject.models.Contact;
import com.jtspringproject.JtSpringProject.models.Help;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HelpContactDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Help> getAllHelp() {
        return entityManager.createQuery("SELECT h FROM Help h", Help.class).getResultList();
    }

    public Help saveHelp(Help help) {
        entityManager.persist(help);
        return help;
    }

    public Help getHelpById(long id) {
        return entityManager.find(Help.class, id);
    }

    public List<Contact> getAllContact() {
        return entityManager.createQuery("SELECT c FROM Contact c", Contact.class).getResultList();
    }

    public Contact saveContact(Contact contact) {
        entityManager.persist(contact);
        return contact;
    }

    public Contact getContactById(long id) {
        return entityManager.find(Contact.class, id);
    }
}