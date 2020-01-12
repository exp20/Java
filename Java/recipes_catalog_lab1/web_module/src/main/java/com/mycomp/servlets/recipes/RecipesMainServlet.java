package com.mycomp.servlets.recipes;



import com.mycomp.domain.Recipe;
import com.mycomp.ejb.RecipeService;
import com.mycomp.servlets.AbstractHttpGetServlet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = "/recipes")
public class RecipesMainServlet extends AbstractHttpGetServlet {
    @EJB(mappedName = "recipeServiceBean")
    private RecipeService recipeService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            List<Recipe> recipes = recipeService.getAll();
            req.setCharacterEncoding("UTF-8");
            req.setAttribute("recipes", recipes);
            forward(req, resp, "recipesMain.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }


    }
}