package com.mycomp.dao;


import com.mycomp.domain.Recipe;

import java.util.List;

public interface RecipeDAOInterface {
    //CRUD
    long add(Recipe recipe);
    List<Recipe> getAll();
    Recipe findById(long id);
    void update(Recipe recipe);
    void delete(Recipe recipe);
}