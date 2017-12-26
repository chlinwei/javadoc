package com.company.dao.impl;

import com.company.dao.ContactDao;
import com.company.entity.Contact;
import com.company.util.XMLUtil;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactDaoImpl implements ContactDao{
    @Override
    public void addContact(Contact contact) {
//<contact-list>
//    <contact id="001">
//        <name>张三</name>
//        <gender>男</gender>
//        <phone>2342342342</phone>
//        <email>234234@qq.com</email>
//        <address>广州天河</address>
//    </contact>
//</contact-list>


        try {
            Document doc = XMLUtil.getDocument();
            Element rootElem = doc.getRootElement();
            Element conElem = rootElem.addElement("contact");
            //添加属性
            conElem.addAttribute("id",UUID.randomUUID().toString());
            conElem.addElement("name").setText(contact.getName());
            conElem.addElement("gender").setText(contact.getGender());
            conElem.addElement("phone").setText(contact.getPhone());
            conElem.addElement("email").setText(contact.getEmail());
            conElem.addElement("address").setText(contact.getAddress());
            XMLUtil.write2xml(doc);
        }catch (Exception e){
            e.printStackTrace();
        }





    }

    @Override
    public void updateContact(Contact contact) {
        try{
            Document doc = XMLUtil.getDocument();
            Element conElem = (Element)doc.selectSingleNode("//contact[@id='"+contact.getId()+"']");
            if(conElem!=null) {
                conElem.element("name").setText(contact.getName());
                conElem.element("gender").setText(contact.getGender());
                conElem.element("phone").setText(contact.getPhone());
                conElem.element("email").setText(contact.getEmail());
                conElem.element("address").setText(contact.getAddress());
                XMLUtil.write2xml(doc);
            }else{
                throw new Exception("this contact not exist!!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /**
     * 删除联系人
     */
    public void deleteContact(String id) {
        Document doc = XMLUtil.getDocument();
        Element conElem = (Element)doc.selectSingleNode("//contact[@id='"+id+"']");
        //自杀
        conElem.detach();
        XMLUtil.write2xml(doc);
    }

    /**
     * 查询所有联系人
     */
    @Override
    public List<Contact> findAll() {
        Document doc = XMLUtil.getDocument();
        List<Element> conList = doc.getRootElement().selectNodes("//contact");
        List<Contact> list= new ArrayList<>();
        if(conList!=null){
            for(Element conElem : conList){
                Contact contact = new Contact();
                contact.setId(conElem.attributeValue("id"));
                contact.setName(conElem.elementText("name"));
                contact.setGender(conElem.elementText("gender"));
                contact.setPhone(conElem.elementText("phone"));
                contact.setEmail(conElem.elementText("email"));
                contact.setAddress(conElem.elementText("address"));
                list.add(contact);
            }
        }
        return list;
    }

    /**
     * 根据id查询对应的联系人
     */
    @Override
    public Contact findById(String id) {
        Document doc = XMLUtil.getDocument();
        Element conElem = (Element)doc.getRootElement().selectSingleNode("//contact[@id='"+id+"']");
        if(conElem==null){
            return null;
        }
        Contact contact = new Contact();
        contact.setId(conElem.attributeValue("id"));
        contact.setName(conElem.elementText("name"));
        contact.setGender(conElem.elementText("gender"));
        contact.setPhone(conElem.elementText("phone"));
        contact.setEmail(conElem.elementText("email"));
        contact.setAddress(conElem.elementText("address"));
        return contact;
    }
}


