package com.mycom.services;

import com.mycom.dao.RecipeDAO;
import com.mycom.dao.RecipeDAOInterface;
import com.mycom.entity.Recipe;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class RecipeService {
    private RecipeDAOInterface recipeDAO;
    private SessionFactory sessionFactory;
    public RecipeService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    public long add(Recipe recipe) throws HibernateException {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            recipeDAO = new RecipeDAO(session);
            long id  = recipeDAO.add(recipe);
            transaction.commit();
            return id;
        }
        catch (HibernateException e){
            throw e;
        }
    }

    public List<Recipe> getAll() {
        try(Session session = sessionFactory.openSession()){
            recipeDAO = new RecipeDAO(session);
            return recipeDAO.getAll();
        }
        catch (HibernateException e){
            throw e;
        }
    }


    public Recipe findById(long id) {
        try(Session session = sessionFactory.openSession()){
            recipeDAO = new RecipeDAO(session);
            return recipeDAO.findById(id);
        }
        catch (HibernateException e){
            throw e;
        }
    }

    public void update(Recipe recipe) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            recipeDAO = new RecipeDAO(session);
            recipeDAO.update(recipe);
            transaction.commit();
        }
        catch (HibernateException e){
            throw e;
        }
    }


    public void delete(Recipe recipe) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            recipeDAO = new RecipeDAO(session);
            recipeDAO.delete(recipe);
            transaction.commit();
        } catch (HibernateException e) {
            throw e;
        }
    }
}
