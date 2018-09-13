/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author LucasNote
 */
public class DaoManagerHiber {

    private static final SessionFactory sessionFactory;
    private static Session s;

    static {

        try {

            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            s = sessionFactory.openSession();

        } catch (Throwable th) {

            System.err.println("Enitial SessionFactory creation failed" + th);

            throw new ExceptionInInitializerError(th);

        }

    }

    public static void persist(Object o) {
        if(!s.isOpen())
            s=sessionFactory.openSession();
            
        Transaction tr = s.beginTransaction();

        s.save(o);

        tr.commit();

        s.flush();
    }

    public static List recover(String hql) {
        
        if(!s.isOpen())
            s=sessionFactory.openSession();

        Transaction tr = s.beginTransaction();

        Query query = s.createQuery(hql);
        
        tr.commit();
        
        s.flush();

        return query.list();
    }

    public static List recover(Object o) {

        Criteria c = s.createCriteria(o.getClass());

        c.add(Example.create(o).enableLike(MatchMode.ANYWHERE).ignoreCase().excludeProperty("codigo"));

        List l = c.list();
        s.flush();
        
        
        return l;
    }
    
    
    public static void update(Object o) {
        if(!s.isOpen())
            s=sessionFactory.openSession();
        
        Transaction tr = s.beginTransaction();

        s.update(o);

        tr.commit();

        s.flush();
    }

    public static void delete(Object o) {
        if(!s.isOpen())
            s=sessionFactory.openSession();
        
        Transaction tr = s.beginTransaction();

        s.delete(o);

        tr.commit();

        s.flush();
    }
    
    public static void delete(String hql) {
        if(!s.isOpen())
            s=sessionFactory.openSession();

        Transaction tr = s.beginTransaction();

        s.delete(hql);

        tr.commit();

        s.flush();
    }

       

    public static void main(String args[]) {
        SchemaExport se = new SchemaExport(new AnnotationConfiguration().configure());
        se.create(true, true);

    }

}
