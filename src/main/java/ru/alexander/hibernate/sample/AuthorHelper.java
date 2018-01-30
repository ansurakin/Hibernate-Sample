package ru.alexander.hibernate.sample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import org.hibernate.stat.Statistics;
import ru.alexander.hibernate.sample.entity.Author;

public class AuthorHelper {

    private static Statistics stat;

    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
        stat = sessionFactory.getStatistics();
        stat.clear();
        stat.setStatisticsEnabled(true);
    }

    public List<Author> getAuthorList() {

        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();

        Author author = session.get(Author.class, 1L); // получение объекта по id

        System.out.println(author.getName());

        // этап подготовки запроса
        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

        CriteriaQuery cq = cb.createQuery(Author.class);

        Root<Author> root = cq.from(Author.class);// первостепенный, корневой entity (в sql запросе - from)

        root.fetch("books", JoinType.INNER);
        
        cq.select(root);// необязательный оператор, если просто нужно получить все значения

        // этап выполнения запроса
        Query query = session.createQuery(cq);

        List<Author> authorList = query.getResultList();

        session.close();
        
        printStat();

        return authorList;

    }

    // добавляют нового автора в таблица Author
    public Author addAuthor(Author author) {

        Session session = sessionFactory.openSession();

        Author a1 = session.get(Author.class, 1L);
        a1.setName("Лермонтов99");
        a1.setSecondName("12314");

        session.beginTransaction();

        session.save(a1); // сгенерит ID и вставит в объект

        session.getTransaction().commit();

        session.close();

        return author;

    }

    public void delete() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        
        CriteriaDelete<Author> criteriaDelete = cb.createCriteriaDelete(Author.class);
        Root<Author> root = criteriaDelete.from(Author.class);
        ParameterExpression<String> nameParam = cb.parameter(String.class, "name");
        criteriaDelete.where(cb.like(root.get("name"), nameParam));
        
//        Author author = session.get(Author.class, 1L);
        
        Query query = session.createQuery(criteriaDelete);
        query.setParameter("name", "%111%");
        query.executeUpdate();
        
        session.getTransaction().commit();
        session.close();
    }

    public Author getAuthor(long id){
        Session session = sessionFactory.openSession();

        Author author = session.get(Author.class, id);

        author.getBooks().get(0).getName();

        return author;
    }
    
    private static void printStat() {
        System.out.println("Выполнено запросов в БД: "+stat.getQueryExecutionCount());
        System.out.println("Найдено в кэше: "+stat.getSecondLevelCacheHitCount());
        System.out.println("Добавлено в кэш: "+stat.getSecondLevelCachePutCount());
        stat.clear();
    }

}
