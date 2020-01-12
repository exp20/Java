package com.mycomp.servlets.doctors;

import com.mycomp.domain.Doctor;
import com.mycomp.ejb.DoctorService;
import com.mycomp.servlets.AbstractHttpGetServlet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createDoctor")
public class CreateDoctorServlet extends HttpServlet {
        @EJB (mappedName = "doctorService")
        private DoctorService doctorService;
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String name = req.getParameter("name");
            String last_name = req.getParameter("last_name");
            String honestly = req.getParameter("honestly");
            String specialization = req.getParameter("specialization");
            if (name.length()==0 || last_name.length()==0 || honestly.length()==0 || specialization.length()==0) {
                throw new ServletException("Add doctor exception: enter all fields");
            }
            Doctor new_doctor = new Doctor(name,last_name,honestly,specialization);
            try {
                doctorService.add(new_doctor);
                req.setCharacterEncoding("UTF-8");
                resp.setCharacterEncoding("UTF-8");
                resp.sendRedirect(req.getContextPath() + "/doctors");
            } catch (Exception e) {
                throw new ServletException("Error add a doctor: "+e.getMessage());
            }


        }
}
