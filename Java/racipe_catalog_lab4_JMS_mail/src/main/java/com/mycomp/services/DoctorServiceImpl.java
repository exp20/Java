package com.mycomp.services;


import com.mycomp.model.dao.DoctorDAO;
import com.mycomp.model.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorDAO doctorDAO;


    @Autowired
    public void setDocotrDAO(DoctorDAO docotrDAO) {
    this.doctorDAO = docotrDAO;
    }

    @Override
    @Transactional
    public List<Doctor> getAll() throws Exception {
           return doctorDAO.getAll();
    }
    @Transactional
    public long add(Doctor doctor) throws Exception {
        return doctorDAO.add(doctor);
    }

    @Transactional
    public Doctor findById(long id) throws Exception {
      return doctorDAO.findById(id);
    }
    @Transactional
    public void update(Doctor doctor) throws Exception {
       doctorDAO.update(doctor);
    }
    @Transactional
    public void delete(Doctor doctor) throws Exception {
        //////// Важный момент неободимо перезагрузить удаляемый объект в сессии удаления чтобы он закэшировался и был виден hhibernane
        /// иначе будет java.lang.IllegalArgumentException: Removing a detached instance

        Doctor delete_d = findById(doctor.getId());
        doctorDAO.delete(delete_d);
    }
/*
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
    }*/
}
