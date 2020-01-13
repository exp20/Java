package com.mycomp.services;



import com.mycomp.model.dao.DoctorDAO;
import com.mycomp.model.dao.PatientDAO;
import com.mycomp.model.dao.RecipeDAO;
import com.mycomp.model.entity.Doctor;
import com.mycomp.model.entity.Patient;
import com.mycomp.model.entity.Recipe;
import org.hibernate.Session;
import org.hibernate.Transaction;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

/*
public class RecipeServiceImpl implements RecipeService {
    private RecipeDAO recipeDAO;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;





    public long add(Recipe recipe) throws Exception {
        Session session = entityManager.unwrap(Session.class);
        recipeDAO = new RecipeDAO(session);
        Transaction transaction = session.beginTransaction();
        long id  = recipeDAO.add(recipe);
        transaction.commit();
        return id;
    }

    public List<Recipe> getAll() throws Exception {
        Session session = entityManager.unwrap(Session.class);
        recipeDAO = new RecipeDAO(session);
        return recipeDAO.getAll();
    }


    public Recipe findById(long id) throws Exception {
        Session session = entityManager.unwrap(Session.class);
        recipeDAO = new RecipeDAO(session);
        return recipeDAO.findById(id);
    }

    public void update(Recipe recipe) throws Exception {
        Session session = entityManager.unwrap(Session.class);
        recipeDAO = new RecipeDAO(session);
        Transaction transaction = session.beginTransaction();
        recipeDAO.update(recipe);
        transaction.commit();
    }

    public void delete(Recipe recipe) throws Exception {
        Session session = entityManager.unwrap(Session.class);
        recipeDAO = new RecipeDAO(session);
        Transaction transaction = session.beginTransaction();
        recipeDAO.delete(findById(recipe.getId()));
        transaction.commit();
    }

    @Override
    public long add(String doctor_id, String patient_id, String description, String priority) throws Exception{
        Session session = entityManager.unwrap(Session.class);
        doctorDAO = new DoctorDAO(session);
        recipeDAO = new RecipeDAO(session);
        patientDAO = new PatientDAO(session);

        Doctor doctor = doctorDAO.findById(Long.parseLong(doctor_id));
        Patient patient = patientDAO.findById(Long.parseLong(patient_id));
        Transaction transaction = session.beginTransaction();
        long id  = recipeDAO.add(new Recipe(description,priority, doctor,patient));
        transaction.commit();
        return id;
    }

    @Override
    public void delete(long id) throws Exception {
        Session session = entityManager.unwrap(Session.class);
        recipeDAO = new RecipeDAO(session);
        Transaction transaction = session.beginTransaction();
        recipeDAO.delete(findById(id));
        transaction.commit();
    }

}*/
