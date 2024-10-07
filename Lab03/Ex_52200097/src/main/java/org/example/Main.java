package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        PhoneDAO phoneDAO = new PhoneDAO();
        phoneDAO.getSession().beginTransaction();
//        for (int i = 0; i < 50; i++) {
//            Phone phone = new Phone("phone" + i, random.nextInt(1000) + 1, "Color" + i, "Country" + i, random.nextInt(100) + 1);
//            phoneDAO.add(phone);
//        }
        System.out.println(phoneDAO.getAll());
        Phone phone1 = phoneDAO.get(1);
        phone1.setName("New Name");
        phoneDAO.update(phoneDAO.get(1));
        phoneDAO.remove(phoneDAO.get(1));
        phoneDAO.remove(5);
        phoneDAO.getSession().getTransaction().commit();
        phoneDAO.getSession().close();

    }
}