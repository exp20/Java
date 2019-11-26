package com.mycom.services;

import com.mycom.dao.RecipeDAO;
import com.mycom.dao.RecipeDAOInterface;
import com.mycom.entity.Recipe;
import com.mycom.utils.HibernateSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeService {
    private RecipeDAOInterface recipeDAO;
    private SessionFactory sessionFactory;


    public RecipeService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    public RecipeService() throws Exception{

        try {
            this.sessionFactory = HibernateSession.getSessionFactory();
        } catch (Exception e) {
            throw e;
        }
    }


    public long add(Recipe recipe) throws Exception {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            recipeDAO = new RecipeDAO(session);
            long id  = recipeDAO.add(recipe);
            transaction.commit();
            return id;
        }
        catch (Exception e){
            throw e;
        }
    }

    public List<Recipe> getAll() throws Exception {
        try(Session session = sessionFactory.openSession()){
            recipeDAO = new RecipeDAO(session);
            return recipeDAO.getAll();
        }
        catch (Exception e){
            throw e;
        }
    }


    public Recipe findById(long id) throws Exception {
        try(Session session = sessionFactory.openSession()){
            recipeDAO = new RecipeDAO(session);
            return recipeDAO.findById(id);
        }
        catch (Exception e){
            throw e;
        }
    }

    public void update(Recipe recipe) throws Exception {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            recipeDAO = new RecipeDAO(session);
            recipeDAO.update(recipe);
            transaction.commit();
        }
        catch (Exception e){
            throw e;
        }
    }


    public void delete(Recipe recipe) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            recipeDAO = new RecipeDAO(session);
            recipeDAO.delete(recipe);
            transaction.commit();
        } catch (Exception e) {
            throw e;
        }
    }
}
