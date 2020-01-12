package com.mycomp.servlets.patients;

import com.mycomp.domain.Doctor;
import com.mycomp.domain.Patient;
import com.mycomp.ejb.DoctorService;
import com.mycomp.ejb.PatientService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createPatient")
public class CreatePatientServlet extends HttpServlet {
        @EJB (mappedName = "patientService")
        private PatientService patientService;
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String name = req.getParameter("name");
            String last_name = req.getParameter("last_name");
            String honestly = req.getParameter("honestly");
            String phone = req.getParameter("phone");
            if (name.length()==0 || last_name.length()==0 || honestly.length()==0 || phone.length()==0) {
                throw new ServletException("Add patient exception: enter all fields");
            }
            Patient new_patient = new Patient(name,last_name,honestly,phone);
            try {
                patientService.add(new_patient);
                resp.sendRedirect(req.getContextPath() + "/patients");
            } catch (Exception e) {
                throw new ServletException("Error add a patient: "+e.getMessage());
            }


        }
}
