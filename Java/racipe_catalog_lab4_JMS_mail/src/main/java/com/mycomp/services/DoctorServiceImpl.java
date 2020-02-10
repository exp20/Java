package com.mycomp.services;


import com.mycomp.model.dao.DoctorDAO;
import com.mycomp.model.entity.Doctor;
import com.mycomp.model.entity.EmailHistory;
import com.mycomp.model.entity.History;
import com.mycomp.services.JMS.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;



import javax.transaction.Transactional;
import java.util.List;


@Service
public class DoctorServiceImpl implements DoctorService {

    private String EMAIL = "an.test.lab.mail@yandex.ru";

    private DoctorDAO doctorDAO;


    @Autowired
    public void setDoctorDAO(DoctorDAO doctorDAO) {
    this.doctorDAO = doctorDAO;
    }

    @Autowired
    private MessageProducer messageProducer;



    @Override
    @Transactional
    public List<Doctor> getAll() throws Exception {
           return doctorDAO.getAll();
    }
    @Transactional
    public long add(Doctor doctor) throws Exception {
      //  template.send(new EmailMessageCreator(new EmailHistory("79372009578@yandex.ru", "Name equals to Sergei. Teacher has been inserted.")));
        long id = doctorDAO.add(doctor);
        messageProducer.sendMessage(new History(Long.toString(id), "Insert", doctor.getClass().getName()));
        if (doctor.getName().equals("Email") || doctor.getName().equals("email")){
            messageProducer.sendMessage(new EmailHistory(EMAIL,"Email notification!  Doctor " + id + " has been inserted"));
        }
    return id;
    }

    @Transactional
    public Doctor findById(long id) throws Exception {
      return doctorDAO.findById(id);
    }
    @Transactional
    public void update(Doctor doctor) throws Exception {
       doctorDAO.update(doctor);
       messageProducer.sendMessage(new History(Long.toString(doctor.getId()), "Update", doctor.getClass().getName()));
    }
    @Transactional
    public void delete(Doctor doctor) throws Exception {
        //////// Важный момент неободимо перезагрузить удаляемый объект в сессии удаления чтобы он закэшировался и был виден hhibernane
        /// иначе будет java.lang.IllegalArgumentException: Removing a detached instance

        Doctor delete_d = findById(doctor.getId());
        doctorDAO.delete(delete_d);
        messageProducer.sendMessage(new History(Long.toString(delete_d.getId()), "Delete", doctor.getClass().getName()));
        if (doctor.getName().equals("Email") || doctor.getName().equals("email")){
            messageProducer.sendMessage(new EmailHistory(EMAIL,"Email notification!  Doctor " + delete_d.getId() +" has been deleted"));
        }
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
