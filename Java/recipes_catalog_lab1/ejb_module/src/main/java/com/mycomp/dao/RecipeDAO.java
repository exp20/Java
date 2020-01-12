package com.mycomp.dao;


import com.mycomp.domain.Recipe;
import org.hibernate.Session;

import java.util.List;

public class RecipeDAO implements RecipeDAOInterface {
    private Session session;
    public RecipeDAO(Session session){
        this.session = session;
    }
    @Override
    public long add(Recipe recipe) {
        return (long)  session.save(recipe);
    }

    @Override
    public List<Recipe> getAll() {
       List<Recipe> recipes = (List<Recipe>) session.createQuery("from Recipe").list();
        return  recipes;
    }

    @Override
    public Recipe findById(long id) {
        return session.get(Recipe.class, id);
    }

    @Override
    public void update(Recipe recipe) {
        session.update(recipe);
    }

    @Override
    public void delete(Recipe recipe) {
        session.delete(recipe);
    }

}
