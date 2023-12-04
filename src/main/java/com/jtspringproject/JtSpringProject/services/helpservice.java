package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.models.Contact;
import com.jtspringproject.JtSpringProject.models.Help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.JtSpringProject.dao.HelpContactDao;

import java.util.List;

@Service
public class HelpContactService {
    @Autowired
    private HelpContactDao helpContactDao;

    public List<Help> getHelpList() {
        return this.helpContactDao.getAllHelp();
    }

    public Help addHelp(Help help) {
        return this.helpContactDao.saveHelp(help);
    }

    public Help getHelpById(long id) {
        return this.helpContactDao.getHelpById(id);
    }

    public List<Contact> getContactList() {
        return this.helpContactDao.getAllContact();
    }

    public Contact addContact(Contact contact) {
        return this.helpContactDao.saveContact(contact);
    }

    public Contact getContactById(long id) {
        return this.helpContactDao.getContactById(id);
    }
}