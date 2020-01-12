package com.mycomp.servlets.patients;


import com.mycomp.domain.Patient;
import com.mycomp.ejb.PatientService;
import org.hibernate.exception.ConstraintViolationException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deletePatient")
public class DeletePatientServlet extends HttpServlet {

    @EJB(mappedName = "patientService")
    private PatientService patientService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id.length() == 0) {
            throw new ServletException("patient delete id  length = 0");
        }
        try {
            //////// Важный момент неободимо перезагрузить удаляемый объект в сессии удаления чтобы он закэшировался и был виден hhibernane
            /// иначе будет java.lang.IllegalArgumentException: Removing a detached instance
             Patient del_patient = patientService.findById(Long.parseLong(id));
            patientService.delete(del_patient); //
            resp.sendRedirect(req.getContextPath() + "/patients");
        } catch (Exception e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                throw new ServletException("Нельзя удалить пициента имеющего рецепт");
            }
            else
            {
                throw new ServletException(e);
            }

        }

    }
}
