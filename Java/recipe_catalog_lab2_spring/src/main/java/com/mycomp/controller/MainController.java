package com.mycomp.controller;

import com.mycomp.model.entity.Doctor;
import com.mycomp.services.DoctorService;
import com.mycomp.services.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller //сообщаем что ээто контрлллер через который будут прохожить зарпосы
public class MainController {
    private DoctorService doctorService;

    @Autowired
    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping(value = {"/", "/index"} , method = RequestMethod.GET)
    public ModelAndView watchIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public ModelAndView doctorsPage() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("doctorsMain");
            modelAndView.addObject("doctors",doctorService.getAll());
        } catch (Exception e) {

            modelAndView.setViewName("errorPage2");
            modelAndView.addObject("message",e.getStackTrace());
            modelAndView.addObject("err",e);
        }
        return modelAndView;
    }
}