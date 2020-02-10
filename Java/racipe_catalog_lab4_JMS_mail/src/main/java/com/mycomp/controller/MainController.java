package com.mycomp.controller;

import com.mycomp.model.entity.Doctor;
import com.mycomp.model.entity.Patient;
import com.mycomp.model.entity.Recipe;
import com.mycomp.services.DoctorService;
import com.mycomp.services.PatientService;
import com.mycomp.services.RecipeService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller //сообщаем что ээто контрлллер через который будут прохожить зарпосы
public class MainController {
    private DoctorService doctorService;
    private PatientService patientService;
    private RecipeService recipeService;


    @Autowired
    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @Autowired
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView watchIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsp/index");
        return modelAndView;
    }

    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public ModelAndView doctorsPage() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("jsp/doctorsMain");
            modelAndView.addObject("doctors", doctorService.getAll());
        } catch (Exception e) {

            modelAndView.setViewName("jsp/errorPage2");
            // modelAndView.addObject("message",e.getStackTrace());
            modelAndView.addObject("err", e);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/addDoctor", method = RequestMethod.POST)
    public ModelAndView addDoctor(ModelMap model, @RequestParam("name") String name,
                                  @RequestParam("last_name") String last_name,
                                  @RequestParam("patronymic") String patronymic,
                                  @RequestParam("specialization") String specialization) {
        if (last_name.length() == 0 || patronymic.length() ==0 || name.length() == 0 || specialization.length() == 0){
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            modelAndView2.addObject("message", "Cannot create a doctor");
            modelAndView2.addObject("error_message", "Enter fields!");
            return modelAndView2;
        }
        Doctor new_doctor = new Doctor(name, last_name, patronymic, specialization);
        try {
            doctorService.add(new_doctor);
            ModelAndView modelAndView = new ModelAndView("redirect:/doctors", model);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            modelAndView2.addObject("message", "Cannot create a doctor");
            modelAndView2.addObject("error_message", e.toString());
            return modelAndView2;
        }
    }

    @RequestMapping(value = {"/updateDoctorPage"}, method = RequestMethod.POST)
    public ModelAndView updateDoctorPage(ModelMap model,
                                         @RequestParam("idDoctorUpdate") long id) {

        ModelAndView modelAndView = new ModelAndView("jsp/updateDoctorPage", model);
        try {
            modelAndView.addObject(doctorService.findById(id));
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            modelAndView2.addObject("message", "Cannot update a doctor");
            modelAndView2.addObject("error_message", e.toString());
            return modelAndView2;
        }
    }

    @RequestMapping(value = {"/updateDoctor"}, method = RequestMethod.POST)
    public ModelAndView updateDoctor(ModelMap model, @RequestParam("id") long id,
                                     @RequestParam("name") String name,
                                     @RequestParam("last_name") String last_name,
                                     @RequestParam("patronymic") String patronymic,
                                     @RequestParam("specialization") String specialization) {

        try {
            Doctor old_doc = doctorService.findById(id);
            old_doc.setLast_name(last_name);
            old_doc.setName(name);
            old_doc.setPatronymic(patronymic);
            old_doc.setSpecialization(specialization);
            doctorService.update(old_doc);
            ModelAndView modelAndView = new ModelAndView("redirect:/doctors", model);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            modelAndView2.addObject("message", "Cannot update a doctor");
            modelAndView2.addObject("error_message", e.toString());
            return modelAndView2;
        }

    }


    @RequestMapping(value = "/deleteDoctor", method = RequestMethod.POST)
    public ModelAndView deleteDoctor(ModelMap model, @RequestParam("id") long id) {
        try {
            doctorService.delete(doctorService.findById(id));
            ModelAndView modelAndView = new ModelAndView("redirect:/doctors", model);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                modelAndView2.addObject("message", "Нельзя удалить врача имеющего рецепт");
                return modelAndView2;
            } else {
                modelAndView2.addObject("message", "Cannot delete a doctor");
                modelAndView2.addObject("error_message", e);
                return modelAndView2;
            }

        }
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public ModelAndView patientsPage() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("jsp/patientsMain");
            modelAndView.addObject("patients", patientService.getAll());
        } catch (Exception e) {
            modelAndView.setViewName("jsp/errorPage2");
            // modelAndView.addObject("message",e.getStackTrace());
            modelAndView.addObject("message", e);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/addPatient", method = RequestMethod.POST)
    public ModelAndView addPatient(ModelMap model, @RequestParam("name") String name,
                                   @RequestParam("last_name") String last_name,
                                   @RequestParam("patronymic") String patronymic,
                                   @RequestParam("phone") String phone) {
        Patient new_patient = new Patient(name, last_name, patronymic, phone);
        if (last_name.length() == 0 || patronymic.length() ==0 || name.length() == 0 || phone.length() ==0){
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            modelAndView2.addObject("message", "Cannot create a patient");
            modelAndView2.addObject("error_message", "Enter fields!");
            return modelAndView2;
        }
        try {
            patientService.add(new_patient);
            ModelAndView modelAndView = new ModelAndView("redirect:/patients", model);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            modelAndView2.addObject("message", "Cannot create a patient");
            modelAndView2.addObject("error_message", e.toString());
            return modelAndView2;
        }
    }

    @RequestMapping(value = {"/updatePatientPage"}, method = RequestMethod.POST)
    public ModelAndView updatePatientPage(ModelMap model,
                                          @RequestParam("idPatientUpdate") long id) {

        ModelAndView modelAndView = new ModelAndView("jsp/updatePatientPage", model);
        try {
            modelAndView.addObject(patientService.findById(id));
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            modelAndView2.addObject("message", "Cannot update a patient");
            modelAndView2.addObject("error_message", e.toString());
            return modelAndView2;
        }
    }

    @RequestMapping(value = {"/updatePatient"}, method = RequestMethod.POST)
    public ModelAndView updatePatient(ModelMap model, @RequestParam("id") long id,
                                      @RequestParam("name") String name,
                                      @RequestParam("last_name") String last_name,
                                      @RequestParam("patronymic") String patronymic,
                                      @RequestParam("phone") String phone) {

        try {
            Patient old_patient = patientService.findById(id);
            old_patient.setLast_name(last_name);
            old_patient.setName(name);
            old_patient.setPatronymic(patronymic);
            old_patient.setPhone(phone);
            patientService.update(old_patient);
            ModelAndView modelAndView = new ModelAndView("redirect:/patients", model);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            modelAndView2.addObject("message", "Cannot update a patient");
            modelAndView2.addObject("error_message", e.toString());
            return modelAndView2;
        }

    }

    @RequestMapping(value = "/deletePatient", method = RequestMethod.POST)
    public ModelAndView deletePatient(ModelMap model, @RequestParam("id") long id) {
        try {
            patientService.delete(patientService.findById(id));
            ModelAndView modelAndView = new ModelAndView("redirect:/patients", model);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                modelAndView2.addObject("message", "Нельзя удалить пациента имеющего рецепт");
                modelAndView2.addObject("error_message", e);
                return modelAndView2;
            } else {
                modelAndView2.addObject("message", "Cannot delete a patient");
                modelAndView2.addObject("error_message", e);
                return modelAndView2;
            }

        }
    }

    @RequestMapping(value = "/recipes", method = RequestMethod.GET)
    public ModelAndView recipesPage() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("jsp/recipesMain");
            modelAndView.addObject("recipes", recipeService.getAll());
        } catch (Exception e) {
            modelAndView.setViewName("jsp/errorPage2");
            // modelAndView.addObject("message",e.getStackTrace());
            modelAndView.addObject("err", e);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/addRecipe", method = RequestMethod.POST)
    public ModelAndView addRecipe(ModelMap model, @RequestParam("doctor_id") String doctor_id,
                                  @RequestParam("patient_id") String patient_id,
                                  @RequestParam("description") String description,
                                  @RequestParam("priority") String priority) {


        ModelAndView modelAndView = new ModelAndView("redirect:/recipes", model);
        if (description.length() == 0 ||priority.length() ==0){
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            modelAndView2.addObject("message", "Cannot create a recipes");
            modelAndView2.addObject("error_message", "Enter fields!");
            return modelAndView2;
        }
        try {
            recipeService.add(doctor_id, patient_id, description, priority);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            modelAndView2.addObject("message", "Cannot create a recipe. Check doctor id, patient id");
            modelAndView2.addObject("error_message", e.toString());
            return modelAndView2;
        }
    }

    @RequestMapping(value = {"/updateRecipePage"}, method = RequestMethod.POST)
    public ModelAndView updateRecipePage(ModelMap model,
                                          @RequestParam("idRecipeUpdate") long id) {

        ModelAndView modelAndView = new ModelAndView("jsp/updateRecipePage", model);
        try {
            modelAndView.addObject(recipeService.findById(id));
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            modelAndView2.addObject("message", "Cannot update a recipe");
            modelAndView2.addObject("error_message", e.toString());
            return modelAndView2;
        }
    }

    @RequestMapping(value = {"/updateRecipe"}, method = RequestMethod.POST)
    public ModelAndView updateRecipe(ModelMap model,
                                      @RequestParam("id") long id,
                                     @RequestParam("doctor_id") long doctor_id,
                                     @RequestParam("patient_id") long patient_id,
                                      @RequestParam("description") String description,
                                      @RequestParam("priority") String priority)
                                       {

        try {
            Recipe old_recipe = recipeService.findById(id);

            old_recipe.setPriority(priority);
            old_recipe.setDescription(description);
            recipeService.update(old_recipe);
            ModelAndView modelAndView = new ModelAndView("redirect:/recipes", model);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
            modelAndView2.addObject("message", "Cannot update a recipe");
            modelAndView2.addObject("error_message", e.toString());
            return modelAndView2;
        }

    }


    @RequestMapping(value = "/deleteRecipe", method = RequestMethod.POST)
    public ModelAndView deleteRecipe(ModelMap model, @RequestParam("id") long id) {

        try {
            ModelAndView modelAndView = new ModelAndView("redirect:/recipes", model);
           // recipeService.delete(recipeService.findById(id)); разные контексты и по ссылку объект лучше не передавать
            // или передовать но снова обновлять свзяь с hibernate
            recipeService.delete(id);
            return modelAndView;
        } catch (Exception e) {
            ModelAndView modelAndView2 = new ModelAndView("jsp/errorPage2", model);
                modelAndView2.addObject("message", "Cannot delete a recipe");
                modelAndView2.addObject("error_message", e);
                return modelAndView2;
            }

        }
    }

