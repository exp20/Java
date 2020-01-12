package com.mycomp.servlets.patients;

import com.mycomp.domain.Doctor;
import com.mycomp.domain.Patient;
import com.mycomp.ejb.DoctorService;
import com.mycomp.ejb.PatientService;
import com.mycomp.servlets.AbstractHttpGetServlet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = "/patients")
public class PatientsMainServlet extends AbstractHttpGetServlet {
    @EJB(mappedName = "patientsServiceBean")
    private PatientService patientService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

        try {
            List<Patient> patients = patientService.getAll();
            req.setCharacterEncoding("UTF-8");
            req.setAttribute("patients", patients);
            forward(req, resp, "patientsMain.jsp");
        } catch (Exception e) {
            throw new ServletException(e.toString());
        }


    }
}