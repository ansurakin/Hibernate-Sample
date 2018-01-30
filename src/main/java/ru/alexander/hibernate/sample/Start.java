package ru.alexander.hibernate.sample;

import org.hibernate.Session;
import ru.alexander.hibernate.sample.entity.Author;

public class Start {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        for (Author item : new AuthorHelper().getAuthorList()) {
            System.out.println(item.getName());
        }
    }
}
