package com.mycomp.servlets.doctors;

import com.mycomp.domain.Doctor;
import com.mycomp.ejb.DoctorService;
import org.hibernate.exception.ConstraintViolationException;

import javax.ejb.EJB;
import javax.lang.model.element.AnnotationValue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteDoctor")
public class DeleteDoctorServlet extends HttpServlet {

    @EJB(mappedName = "doctorService")
    private DoctorService doctorService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id.length() == 0) {
            throw new ServletException("doctor delete id  length = 0");
        }
        try {
            //////// Важный момент неободимо перезагрузить удаляемый объект в сессии удаления чтобы он закэшировался и был виден hhibernane
            /// иначе будет java.lang.IllegalArgumentException: Removing a detached instance
             Doctor del_doctor = doctorService.findById(Long.parseLong(id));
            doctorService.delete(del_doctor); //
            resp.sendRedirect(req.getContextPath() + "/doctors");
        } catch (Exception e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                throw new ServletException("Нельзя удалить врача имеющего рецепт");
            }
            else
            {
                throw new ServletException(e);
            }

        }


    }
}
