package com.mycomp.model.dao;



import com.mycomp.model.entity.Recipe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeDAO implements RecipeDAOInterface {
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long add(Recipe recipe) {
        Session session = sessionFactory.getCurrentSession();
        return (long)  session.save(recipe);
    }

    @Override
    public List<Recipe> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Recipe> recipes = (List<Recipe>) session.createQuery("from Recipe").list();
        return  recipes;
    }

    @Override
    public Recipe findById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Recipe.class, id);
    }

    @Override
    public void update(Recipe recipe) {
        Session session = sessionFactory.getCurrentSession();
        //session.update(recipe);
        session.refresh(recipe.getPatient());
        session.refresh(recipe.getDoctor());
        session.update(recipe);
    }

    @Override
    public void delete(Recipe recipe) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(recipe);
    }

}
