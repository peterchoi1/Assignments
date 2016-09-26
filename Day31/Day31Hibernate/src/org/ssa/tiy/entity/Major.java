package org.ssa.tiy.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "major")
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "req_sat")
    private int sat;

    public Major() {

    }

    public Major(String description) {
        this.description = description;
        this.sat = sat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    static void InsertMajor(String description) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Major.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            Major major = new Major(description);
            session.beginTransaction();
            session.save(major);
            session.getTransaction().commit();
            System.out.println("Inserted Major: " + major.getDescription());

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            factory.close();
        }

    }

    static void DeleteMajor(String description) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Major.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            String sql = "delete from Major where description = :aString";
            session.beginTransaction();
            Query query = session.createQuery(sql);
            query.setParameter("aString", description);
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Deleted Major: " + description);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            factory.close();
        }

    }

    static void UpdateMajorDescription(String description, String changeDescription) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Major.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();
            session.createQuery("update Major set description = '" + changeDescription + "' where description = '"
                    + description + "'").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Updated Major: " + description + " to " + changeDescription);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            factory.close();
        }

    }

    static void DisplayAllMajors() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Major.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            List<Major> majors = session.createQuery("from Major").list();
            for (Major major : majors) {
                System.out.println("Description: " + major.getDescription() + "\n\t Req SAT: " + major.getSat());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
