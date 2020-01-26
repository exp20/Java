package com.mycomp.services;



import com.mycomp.model.dao.DoctorDAO;
import com.mycomp.model.dao.PatientDAO;
import com.mycomp.model.dao.RecipeDAO;
import com.mycomp.model.entity.Doctor;
import com.mycomp.model.entity.Patient;
import com.mycomp.model.entity.Recipe;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeDAO recipeDAO;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;


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

    }

    @Transactional
    public void delete(Recipe recipe) throws Exception {
       recipeDAO.delete(recipeDAO.findById(recipe.getId()));
    }

    @Override
    @Transactional
    public long add(String doctor_id, String patient_id, String description, String priority) throws Exception{

        Doctor doctor = doctorDAO.findById(Long.parseLong(doctor_id));
        Patient patient = patientDAO.findById(Long.parseLong(patient_id));
        long id  = recipeDAO.add(new Recipe(description,priority, doctor,patient));
        return id;
    }

    @Override
    @Transactional
    public void delete(long id) throws Exception {
        recipeDAO.delete(recipeDAO.findById(id));
    }

}
