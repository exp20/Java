package com.mycom.services;

import com.mycom.dao.DoctorDAO;
import com.mycom.dao.DoctorDAOInterface;
import com.mycom.entity.Doctor;


import com.mycom.utils.HibernateSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorService  {
    private  DoctorDAOInterface doctorDAO;
    private  SessionFactory sessionFactory;


    public DoctorService() throws Exception{
        try {
            this.sessionFactory = HibernateSession.getSessionFactory();
        } catch (Exception e) {
            throw e;
        }
    }
    public DoctorService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    public long add(Doctor doctor) throws Exception {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            doctorDAO = new DoctorDAO(session);
            long id  = doctorDAO.add(doctor);
            transaction.commit();
            return id;
        }
        catch (Exception e){
            throw e;
        }
    }

    public List<Doctor> getAll() throws Exception {
        try(Session session = sessionFactory.openSession()){
            doctorDAO = new DoctorDAO(session);
            return doctorDAO.getAll();
        }
        catch (Exception e){
            throw e;
        }
    }


    public Doctor findById(long id) throws Exception {
        try(Session session = sessionFactory.openSession()){
            doctorDAO = new DoctorDAO(session);
            return doctorDAO.findById(id);
        }
        catch (Exception e){
            throw e;
        }
}

    public void update(Doctor doctor) throws Exception {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            doctorDAO = new DoctorDAO(session);
            doctorDAO.update(doctor);
            transaction.commit();
        }
        catch (Exception e){
            throw e;
        }
    }


    public void delete(Doctor doctor) throws Exception {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            doctorDAO = new DoctorDAO(session);
            doctorDAO.delete(doctor);
            transaction.commit();
        }

        catch (Exception e){
            throw e;
        }
    }

    //возвращает количество рецептов у соответствующего доктора
    public Object[] getDoctorRecipesStatistic(){
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> list = session.createSQLQuery("select doctor_id, COUNT(recipe_id) as recipe_count " +
                    "from( " +
                    "select PUBLIC.\"doctors\".\"id\" as doctor_id, PUBLIC.\"recipes\".\"id\" as recipe_id " +
                    "from ( PUBLIC.\"doctors\" LEFT JOIN PUBLIC.\"recipes\" " +
                    "ON \"doctors\".\"id\"= \"recipes\".\"doctor_id\")) " +
                    "group by doctor_id;").list();
            Map<BigInteger,BigInteger> result_1 = new HashMap<>();
            BigInteger recipe_count= BigInteger.valueOf(0);
            for( Object[] obj_row:list){
                System.out.println((BigInteger) obj_row[0]);
                System.out.println((BigInteger) obj_row[1]);
                result_1.put((BigInteger) obj_row[0],(BigInteger) obj_row[1]);
               recipe_count =  recipe_count.add((BigInteger) obj_row[1]); //bigInteger неизменяемый
            }
            Object[] result = new Object[]{result_1,recipe_count};
            return result;
        } catch (Exception e) {
            throw e;
        }
    }
}
