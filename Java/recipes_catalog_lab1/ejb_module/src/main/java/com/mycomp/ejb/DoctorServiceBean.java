package com.mycomp.ejb;

import com.mycomp.dao.DoctorDAO;
import com.mycomp.domain.Doctor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Stateless
public class DoctorServiceBean implements DoctorService{
    private DoctorDAO doctorDAO;

    @PersistenceContext(unitName = "myUnit") // работа с jpa.
    private EntityManager entityManager;




    public long add(Doctor doctor) throws Exception {
       Session session = entityManager.unwrap(Session.class);
        doctorDAO = new DoctorDAO(session);
            Transaction transaction = session.beginTransaction();
            long id  = doctorDAO.add(doctor);
            transaction.commit();
            return id;
    }

    public List<Doctor> getAll() throws Exception {
        Session session = entityManager.unwrap(Session.class);
            doctorDAO = new DoctorDAO(session);
            return doctorDAO.getAll();
    }


    public Doctor findById(long id) throws Exception {
        Session session = entityManager.unwrap(Session.class);
        doctorDAO = new DoctorDAO(session);
            return doctorDAO.findById(id);
    }

    public void update(Doctor doctor) throws Exception {
        Session session = entityManager.unwrap(Session.class);
        doctorDAO = new DoctorDAO(session);
            Transaction transaction = session.beginTransaction();
            doctorDAO.update(findById(doctor.getId()));
            transaction.commit();
    }

    public void delete(Doctor doctor) throws Exception {
        //////// Важный момент неободимо перезагрузить удаляемый объект в сессии удаления чтобы он закэшировался и был виден hhibernane
        /// иначе будет java.lang.IllegalArgumentException: Removing a detached instance
       // session = entityManager.unwrap(Session.class);
        Session session = entityManager.unwrap(Session.class);
        doctorDAO = new DoctorDAO(session);
        Transaction transaction = session.beginTransaction();
        Doctor delete_d = findById(doctor.getId());
        doctorDAO.delete(delete_d);
        transaction.commit();
    }

    //возвращает количество рецептов у соответствующего доктора
    public Object[] getDoctorRecipesStatistic(){
        try{
            Session session = entityManager.unwrap(Session.class);
            List<Object[]> list = session.createSQLQuery("select doctor_id, COUNT(recipe_id) as recipe_count " +
                    "from( " +
                    "select PUBLIC.\"doctors\".\"id\" as doctor_id, PUBLIC.\"recipes\".\"id\" as recipe_id " +
                    "from ( PUBLIC.\"doctors\" LEFT JOIN PUBLIC.\"recipes\" " +
                    "ON \"doctors\".\"id\"= \"recipes\".\"doctor_id\")) " +
                    "group by doctor_id;").list();
            Map<BigInteger,BigInteger> result_1 = new HashMap<>();
            BigInteger recipe_count= BigInteger.valueOf(0);
            for( Object[] obj_row:list){
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
