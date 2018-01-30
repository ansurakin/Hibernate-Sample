package ru.alexander.hibernate.sample;

import org.hibernate.Session;
import org.jboss.logging.Logger;
import ru.alexander.hibernate.sample.entity.Author;

public class Start {

    private static final Logger LOG = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) {

//        Session session = HibernateUtil.getSessionFactory().openSession();
//        for (Author item : new AuthorHelper().getAuthorList()) {
//            LOG.debug(item.getName());
//        }
        
        Author author = new Author("тест3");
        new AuthorHelper().addAuthor(author);

        new AuthorHelper().delete();

        new BookHelper().getBookList();
        
        new AuthorHelper().getAuthorList();

    }
}
