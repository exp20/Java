package com.mycomp.controller;

import com.mycomp.model.entity.*;
import com.mycomp.services.DoctorService;
import com.mycomp.services.PatientService;
import com.mycomp.services.RecipeService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // эт аннотация объединяет @Controller и @ResponseBody
public class RestAppController {
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
/*
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView watchIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }*/

    @RequestMapping(value = {"/rest/", "/rest/index"}, method = RequestMethod.GET)
    public String watchIndex() {
        return "index";
    }

    // URL:
    // http://localhost:8080/lab3_war/doctors
    // http://localhost:8080/lab3_war/doctors.xml
    // http://localhost:8080/lab3_war/doctors.json
    @RequestMapping(value = "/rest/doctors", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DoctorList> doctorsPage() {
        try {
            return new ResponseEntity(new DoctorList(doctorService.getAll()), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @RequestMapping(value = "/rest/doctors/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity doctorsPage(@PathVariable("id") String id) {
        try {

            Doctor doc = doctorService.findById(Long.parseLong(id));
            if (doc == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(doctorService.findById(Long.parseLong(id)), HttpStatus.OK);
            }
        } catch (NumberFormatException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    // consumes определяет тип содержимого тела запроса отправленного клиентом
    @RequestMapping(value = "/rest/doctors", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity addDoctor(@RequestBody Doctor new_doctor) {

        if (new_doctor.getName() == null || new_doctor.getLast_name() == null || new_doctor.getPatronymic() == null || new_doctor.getSpecialization() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (new_doctor.getName().length() == 0 || new_doctor.getLast_name().length() == 0 || new_doctor.getPatronymic().length() == 0 || new_doctor.getSpecialization().length() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            doctorService.add(new_doctor);
            return new ResponseEntity<>(new_doctor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new_doctor, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/rest/doctors/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity updateDoctor(@PathVariable("id") String id, @RequestBody Doctor update_doctor) {
        try {
            long l_id = Long.parseLong(id);
            if (update_doctor.getName().length() == 0 || update_doctor.getLast_name().length() == 0 || update_doctor.getPatronymic().length() == 0 || update_doctor.getSpecialization().length() == 0) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            Doctor doc = doctorService.findById(l_id);
            if (doc == null) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {

                doc.setSpecialization(update_doctor.getSpecialization());
                doc.setPatronymic(update_doctor.getPatronymic());
                doc.setName(update_doctor.getName());
                doc.setLast_name(update_doctor.getLast_name());
                doctorService.update(doc);
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @RequestMapping(value = "/rest/doctors/{id}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteDoctor(@PathVariable("id") String id) {
        try {
            Doctor doc = doctorService.findById(Long.parseLong(id));
            if (doc == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                doctorService.delete(doc);
                return new ResponseEntity(HttpStatus.OK);
            }

        } catch (NumberFormatException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/rest/patients", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PatientList> patientsPage() {
        try {
            return new ResponseEntity(new PatientList(patientService.getAll()), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/rest/patients/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity patientsPage(@PathVariable("id") String id) {
        try {
            Patient patient = patientService.findById(Long.parseLong(id));
            if (patient == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(patientService.findById(Long.parseLong(id)), HttpStatus.OK);
            }
        } catch (NumberFormatException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @RequestMapping(value = "/rest/patients", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity addPatient(@RequestBody Patient new_patient) {

        if (new_patient.getName() == null || new_patient.getLast_name() == null || new_patient.getPatronymic() == null || new_patient.getPhone() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (new_patient.getName().length() == 0 || new_patient.getLast_name().length() == 0 || new_patient.getPatronymic().length() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            new_patient.getPhone(); // проверка на null поле phone
            patientService.add(new_patient);
            return new ResponseEntity<>(new_patient, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new_patient, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/rest/patients/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity updatePatient(@PathVariable("id") String id, @RequestBody Patient update_patient) {
        try {
            long l_id = Long.parseLong(id);
            if (update_patient.getName().length() == 0 || update_patient.getLast_name().length() == 0 || update_patient.getPatronymic().length() == 0 || update_patient.getPhone().length() == 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Patient patient = patientService.findById(l_id);
            if (patient == null) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                patient.setLast_name(update_patient.getLast_name());
                patient.setName(update_patient.getName());
                patient.setPatronymic(update_patient.getPatronymic());
                patient.setPhone(update_patient.getPhone());
                patientService.update(patient);
                return new ResponseEntity(HttpStatus.OK);
            }

        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/rest/patients/{id}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deletePatient(@PathVariable("id") String id) {
        try {
            Patient patient = patientService.findById(Long.parseLong(id));
            if (patient == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                patientService.delete(patient);
                return new ResponseEntity(HttpStatus.OK);
            }

        } catch (NumberFormatException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/rest/recipes", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RecipeList> recipesPage() {
        try {
            return new ResponseEntity(new RecipeList(recipeService.getAll()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/rest/recipes/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity recipesPage(@PathVariable("id") String id) {
        try {

            Recipe recipe = recipeService.findById(Long.parseLong(id));
            if (recipe == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(recipeService.findById(Long.parseLong(id)), HttpStatus.OK);
            }
        } catch (NumberFormatException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {

            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    /// Примитивные поля после парсинга с json или xml антоинициализирются !! если явно не указан id то он будет = 0
    // consumes определяет тип содержимого тела запроса отправленного клиентом
    @RequestMapping(value = "/rest/recipes", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity addRecipe(@RequestBody Recipe new_recipe) {

        if (new_recipe.getDescription() == null || new_recipe.getPriority() == null || new_recipe.getDoctor()==null || new_recipe.getDoctor() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (new_recipe.getDescription().length() == 0 || new_recipe.getPriority().length() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Doctor doc = new_recipe.getDoctor();
            Patient pat = new_recipe.getPatient();
            doc = doctorService.findById(doc.getId());
            pat = patientService.findById(pat.getId());
            if (doc == null || pat == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                recipeService.add(Long.toString(doc.getId()), Long.toString(pat.getId()), new_recipe.getDescription(), new_recipe.getPriority());
            }
            return new ResponseEntity<>(new_recipe, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/rest/recipes/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity updateRecipe(@PathVariable("id") String id, @RequestBody Recipe update_recipe) {
        try {
            long l_id = Long.parseLong(id);
            if (update_recipe.getDescription() == null || update_recipe.getPriority() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (update_recipe.getDescription().length() == 0 || update_recipe.getPriority().length() == 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Recipe recipe = recipeService.findById(l_id);
            if (recipe == null) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                recipe.setPriority(update_recipe.getPriority());
                recipe.setDescription(update_recipe.getDescription());
                recipeService.update(recipe);
                return new ResponseEntity(HttpStatus.OK);
            }

        } catch (NumberFormatException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/rest/recipes/{id}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity deleteRecipe(@PathVariable("id") String id) {
        try {
            long l_id = Long.parseLong(id);
            Recipe del_recipe = recipeService.findById(l_id);
            if (del_recipe == null) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                recipeService.delete(l_id);
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

