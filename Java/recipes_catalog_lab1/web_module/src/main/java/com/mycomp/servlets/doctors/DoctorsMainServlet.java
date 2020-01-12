package com.mycomp.servlets.doctors;

import com.mycomp.domain.Doctor;
import com.mycomp.ejb.DoctorService;
import com.mycomp.servlets.AbstractHttpGetServlet;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/doctors")
public class DoctorsMainServlet extends AbstractHttpGetServlet {
    @EJB (mappedName = "doctorServiceBean")
    private DoctorService doctorServiceBean;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {


        try {
            List<Doctor> doctors = doctorServiceBean.getAll();
            req.setCharacterEncoding("UTF-8");
            req.setAttribute("doctors", doctors);
            forward(req, resp, "doctorsMain.jsp");
        } catch (Exception e) {
            throw new ServletException(e.toString());
        }


    }
}