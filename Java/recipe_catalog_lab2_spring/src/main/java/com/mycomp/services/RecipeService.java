package com.mycomp.services;






import com.mycomp.model.entity.Recipe;

import java.util.List;


public interface RecipeService {

    public long add(Recipe recipe) throws Exception;

    public List<Recipe> getAll() throws Exception;

   public Recipe findById(long id) throws Exception;

    public void update(Recipe doctor) throws Exception;

   public void delete(Recipe doctor) throws Exception;

   public long add(String doctor_id, String patient_id, String description, String priority) throws Exception;

   public void delete(long id) throws Exception;
}
