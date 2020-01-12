package com.mycomp.ejb;





import com.mycomp.domain.Recipe;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RecipeService {

    public long add(Recipe recipe) throws Exception;

    public List<Recipe> getAll() throws Exception;

   public Recipe findById(long id) throws Exception;

    public void update(Recipe doctor) throws Exception;

   public void delete(Recipe doctor) throws Exception;

   public long add (String doctor_id, String patient_id, String description, String priority) throws Exception;

   public void delete (long id) throws Exception;
}
