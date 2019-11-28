package com.mycom.services;

import com.mycom.dao.RecipeDAO;
import com.mycom.dao.RecipeDAOInterface;
import com.mycom.entity.Recipe;
import com.mycom.utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Recipe> filtering(List<Recipe> recipes, String filter_description, Long id_long, RecipePriorities filter_priority){
        RecipeFilter myfilter = new RecipeFilter(filter_description,id_long,filter_priority);
        List<Recipe> fileted = recipes.stream().filter( recipe -> myfilter.useFilter((Recipe)recipe)).collect(Collectors.toList());
        return fileted;
    }

    private class RecipeFilter {
        private String description;
        private Long id;
        private RecipePriorities priority;

         RecipeFilter (String description, Long id, RecipePriorities priority)
        {
            this.description=description;
            this.id = id;
            this.priority = priority;
        }

        public boolean useFilter(Recipe recipe){
            boolean result = true;
            //содержит ли описание символы из фильтра если символов описания в филтре нет то все записи подходят
            result &= ( (description == null || description.length()==0) ? true : (recipe.getDescription().contains(description)));
            result &= ( id == null ? true: (recipe.getPatient().getId()==id));
            result &= (priority == null ? true : (priority.toString().equals(recipe.getPriority())));
            return result;
        }
    }

}
