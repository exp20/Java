package com.mycomp.services;


import com.mycomp.model.dao.DoctorDAO;
import com.mycomp.model.dao.PatientDAO;
import com.mycomp.model.dao.RecipeDAO;
import com.mycomp.model.entity.*;
import com.mycomp.services.JMS.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeDAO recipeDAO;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;
    private MessageProducer messageProducer;

    private String EMAIL = "an.test.lab.mail@yandex.ru";


    @Autowired
    public void setMessageProducer(MessageProducer  messageProducer) {
        this.messageProducer = messageProducer;
    }

    @Autowired
    public void setDocotrDAO(DoctorDAO docotrDAO) {
        this.doctorDAO = docotrDAO;
    }

    @Autowired
    public void setRecipeDAO(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    @Autowired
    public void setPatientDAO(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    /*
    @Transactional
    public long add(Recipe recipe) throws Exception {
      return recipeDAO.add(recipe);
    }*/


    @Transactional
    public List<Recipe> getAll() throws Exception {
       return recipeDAO.getAll();
    }

    @Transactional
    public Recipe findById(long id) throws Exception {
      return recipeDAO.findById(id);
    }

    @Transactional
    public void update(Recipe recipe) throws Exception {
       recipeDAO.update(recipe);
       messageProducer.sendMessage(new History(Long.toString(recipe.getId()), "Update", recipe.getClass().getName()));

    }

    @Transactional
    public void delete(Recipe recipe) throws Exception {
        String descript = recipe.getDescription(); // сохраняем поля для проверки на Email
        String id = Long.toString(recipe.getId());
        recipeDAO.delete(recipe);
        messageProducer.sendMessage(new History(id, "Delete", recipe.getClass().getName()));
        if (descript.equals("Email") || descript.equals("email")){
            messageProducer.sendMessage(new EmailHistory(EMAIL,"Email notification!  Recipe " + id +" has been deleted"));
        }
    }

    @Override
    @Transactional
    public long add(String doctor_id, String patient_id, String description, String priority) throws Exception{

        Doctor doctor = doctorDAO.findById(Long.parseLong(doctor_id));
        Patient patient = patientDAO.findById(Long.parseLong(patient_id));
        Recipe new_recipe  = new Recipe(description,priority, doctor,patient);
        long id  = recipeDAO.add(new_recipe);
        messageProducer.sendMessage(new History(Long.toString(id), "Insert", new_recipe.getClass().getName()));
        if (description.equals("Email") || description.equals("email")){
            messageProducer.sendMessage(new EmailHistory(EMAIL,"Email notification!  Recipe " + id +" has been inserted"));
        }
        return id;
    }

    @Override
    @Transactional
    public void delete(long id) throws Exception {
        Recipe delete_r = (recipeDAO.findById(id));
        String descript = delete_r.getDescription(); // сохраняем поля для проверки на Email
        recipeDAO.delete(delete_r);
        messageProducer.sendMessage(new History(Long.toString(id), "Delete", delete_r.getClass().getName()));
        if (descript.equals("Email") || descript.equals("email")){
            messageProducer.sendMessage(new EmailHistory(EMAIL,"Email notification!  Recipe " + id +" has been deleted"));
        }
    }


}
