package com.mycomp.servlets.recipes;




import com.mycomp.domain.Recipe;
import com.mycomp.ejb.RecipeService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createRecipe")
public class CreateRecipeServlet extends HttpServlet {
        @EJB (mappedName = "recipeService")
        private RecipeService recipeService;
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String doctor_id = req.getParameter("doctor_id");
            String patient_id = req.getParameter("patient_id");
            String description = req.getParameter("description");
            String priority = req.getParameter("priority");
            if (doctor_id.length()==0 ||patient_id.length()==0 || priority.length()==0 || description.length()==0) {
                throw new ServletException("Add recipe exception: enter all fields");
            }

            try {
                recipeService.add(doctor_id,patient_id,description,priority);
                resp.sendRedirect(req.getContextPath() + "/recipes");
            } catch (Exception e) {
                throw new ServletException("Error add a recipe: "+e);
            }


        }
}
