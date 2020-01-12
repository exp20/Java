package com.mycomp.servlets.recipes;



import com.mycomp.ejb.RecipeService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteRecipe")
public class DeleteRecipeServlet extends HttpServlet {

    @EJB(mappedName = "recipeService")
    private RecipeService recipeService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id.length() == 0) {
            throw new ServletException("recipe delete id  length = 0");
        }
        try {
            //////// Важный момент неободимо перезагрузить удаляемый объект в сессии удаления чтобы он закэшировался и был виден hhibernane
            /// иначе будет java.lang.IllegalArgumentException: Removing a detached instance
            recipeService.delete(Long.parseLong(id));
            resp.sendRedirect(req.getContextPath() + "/recipes");
        } catch (Exception e) {
            throw new ServletException(e);
        }


    }
}
