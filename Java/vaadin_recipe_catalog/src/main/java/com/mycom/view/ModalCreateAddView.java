package com.mycom.view;


import com.mycom.entity.Doctor;
import com.mycom.entity.Patient;
import com.mycom.services.DoctorService;
import com.mycom.services.PatientService;
import com.mycom.services.RecipeService;
import com.vaadin.ui.*;

import java.sql.Date;

public class ModalCreateAddView  extends Window {

    private TextField nameField;
    private TextField last_nameField;
    private TextField honestlyField;
    private TextField phoneField;
    private TextField specializationField;
    private Date creationDate;
    private Date validity;
    private String descriptionField;
    private String priorityField;
    private Button ok_butt;
    private Button cancel_butt;
    private static float window_width = 400.0f;
    private FormLayout formLayout;

    private PatientsView patientsParentView;
    private RecipesView recipesParentView;
    private DoctorsView doctorsParentView;

    private DoctorService doctorService;
    private PatientService patientService;
    private RecipeService recipeService;





    //заполнение формы сущности
    public ModalCreateAddView (Composite parentView, Object entityService, Object entity){
        super();
        super.setModal(true);
        super.center();
        super.isDraggable();
        super.setWidth(window_width,Unit.PIXELS);

        cancel_butt = new Button("Отменить", e -> cancelButtonAction());

        formLayout = new FormLayout();

        if (entityService.getClass() == DoctorService.class){
            this.doctorsParentView = (DoctorsView) parentView;
            doctorService = (DoctorService) entityService;
            nameField = new TextField("Имя");
            last_nameField = new TextField("Фамилия");
            honestlyField = new TextField("Отчество");
            specializationField = new TextField("Специализация");
            formLayout.setMargin(true);
            // форма с фунцией добавления
            if (entity == null){
                super.setCaption("Добавить врача");
                ok_butt = new Button("ОК",e -> addDoctorButtonAction(e));
            }
            // форма с функцией изменения
           else {
                Doctor doctor = (Doctor) entity;
                nameField.setValue(doctor.getName());
                last_nameField.setValue(doctor.getLast_name());
                honestlyField.setValue(doctor.getHonestly());
                specializationField.setValue(doctor.getSpecialization());
                super.setCaption("Изменить врача");
                ok_butt = new Button("OK", e -> changePatientButtonAction(e));
            }
            formLayout.addComponents(nameField,last_nameField,honestlyField,specializationField,new HorizontalLayout(ok_butt,cancel_butt));
            super.setContent(formLayout);
        }
        if (entityService.getClass() == PatientService.class){
            patientsParentView = (PatientsView) parentView;
            patientService = (PatientService) entityService;
            nameField = new TextField("Имя");
            last_nameField = new TextField("Фамилия");
            honestlyField = new TextField("Отчество");
            phoneField = new TextField("Телефон");
            formLayout.setMargin(true);
            super.setContent(formLayout);
            if (entity == null){
                super.setCaption("Добавить пациента");
                ok_butt = new Button("OK",e -> addPatientButtonAction(e));
            }
            // форма с функцией изменения
            else {
                Patient patient  = (Patient) entity;
                super.setCaption("Изменить пациента");
                nameField.setValue(patient.getName());
                last_nameField.setValue(patient.getLast_name());
                honestlyField.setValue(patient.getHonestly());
                phoneField.setValue(patient.getPhone());
                ok_butt = new Button("OK",e -> changePatientButtonAction(e));
            }
            formLayout.addComponents(nameField,last_nameField,honestlyField,phoneField,new HorizontalLayout(ok_butt,cancel_butt));

        }

        if (entityService.getClass() == RecipeService.class){
            recipesParentView = (RecipesView) parentView;
            recipeService = (RecipeService) entityService;
            super.setCaption("Добавить рецепт");

        }

    }

    //заполнение форм изменения сущностей


    private void cancelButtonAction(){
        close();
    }

    private void  changePatientButtonAction (Event e){
        // TODO проверка формы.
        close();
    }
    private void addPatientButtonAction(Event e){
        // TODO проверка формы.
        try {
            patientService.add(new Patient(nameField.getValue(),last_nameField.getValue(),honestlyField.getValue(),phoneField.getValue()));
            close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Notification.show("Exception",
                    e.toString(),
                    Notification.Type.ERROR_MESSAGE);
            close();
        }
    }
    private void addDoctorButtonAction(Event e){
        //TODO проверка формы
        try {
            doctorService.add(new Doctor(nameField.getValue(),last_nameField.getValue(),honestlyField.getValue(),specializationField.getValue()));
            close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Notification.show("Exception",
                    e.toString(),
                    Notification.Type.ERROR_MESSAGE);
            close();
        }
        close();
    }

    private void changeDoctorButtonAction(Event e){
        //TODO проверка формы
        close();
    }

}


