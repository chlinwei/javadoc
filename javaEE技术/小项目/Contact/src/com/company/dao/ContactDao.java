package com.company.dao;

import com.company.entity.Contact;

import java.util.List;

public interface ContactDao {
    public void addContact(Contact contact);
    public void updateContact(Contact contact);//包含修改的id
    public void deleteContact(String id);
    public List<Contact> findAll();
    public Contact findById(String id);
}
