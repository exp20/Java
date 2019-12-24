package com.mycom.view;



import com.mycom.entity.Doctor;
import com.mycom.entity.Patient;
import com.mycom.entity.Recipe;
import com.mycom.services.DoctorService;
import com.mycom.services.PatientService;
import com.mycom.services.RecipePriorities;
import com.mycom.services.RecipeService;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.LocalDateTimeToDateConverter;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.ui.*;



import java.sql.Date;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ModalCreateAddView  extends Window {

    private TextField nameField;
    private TextField last_nameField;
    private TextField honestlyField;
    private TextField phoneField;
    private TextField specializationField;
    private TextField recipe_patient_id;
    private TextField recipe_doctor_id;
    private DateTimeField creationDate;
    private DateTimeField validity;
    private TextArea descriptionField;
    private ComboBox recipe_priority_combobox;
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

    private Binder<Doctor> doctor_binder;
    private Binder<Patient> patient_binder;
    private Binder<Recipe> recipe_binder;
    private LocalDateTimeToDateConverter localDateTimeToDateConverter;





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

            doctor_binder= new Binder<>(Doctor.class);
            doctor_binder.forField(nameField).withValidator( str -> str.length()!=0,"null Field").bind(Doctor::getName,Doctor::setName);
            doctor_binder.forField(last_nameField).withValidator( str -> str.length()!=0,"null Field").bind(Doctor::getLast_name, Doctor::setLast_name);
            doctor_binder.forField(honestlyField).withValidator( str -> str.length()!=0,"null Field").bind(Doctor::getHonestly, Doctor::setHonestly);
            doctor_binder.forField(specializationField).withValidator( str -> str.length()!=0,"null Field").bind(Doctor::getSpecialization, Doctor::setSpecialization);

            // форма с фунцией добавления
            if (entity == null){
                super.setCaption("Добавить врача");
                ok_butt = new Button("ОК",e -> addDoctorButtonAction(e));
            }
            // форма с функцией изменения
           else {
                Doctor doctor = (Doctor) entity;
                doctor_binder.readBean(doctor);
                super.setCaption("Изменить врача");
                ok_butt = new Button("OK", e -> changeDoctorButtonAction(doctor.getId()));
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

            patient_binder= new Binder<>(Patient.class);
            patient_binder.forField(nameField).withValidator( str -> str.length()!=0,"null Field").bind(Patient::getName,Patient::setName);
            patient_binder.forField(last_nameField).withValidator( str -> str.length()!=0,"null Field").bind(Patient::getLast_name, Patient::setLast_name);
            patient_binder.forField(honestlyField).withValidator( str -> str.length()!=0,"null Field").bind(Patient::getHonestly,Patient::setHonestly);
            patient_binder.forField(phoneField).withValidator( str -> check_phone(str),"wrong data Field").bind(Patient::getPhone, Patient::setPhone);


            if (entity == null){
                super.setCaption("Добавить пациента");
                ok_butt = new Button("OK",e -> addPatientButtonAction(e));
            }
            // форма с функцией изменения
            else {
                Patient patient  = (Patient) entity;
                patient_binder.readBean(patient);
                super.setCaption("Изменить пациента");

                ok_butt = new Button("OK",e -> changePatientButtonAction(patient.getId()));
            }
            formLayout.addComponents(nameField,last_nameField,honestlyField,phoneField,new HorizontalLayout(ok_butt,cancel_butt));
            super.setContent(formLayout);
        }

        if (entityService.getClass() == RecipeService.class){
            recipesParentView = (RecipesView) parentView;
            recipeService = (RecipeService) entityService;
            descriptionField = new TextArea("Описание рецепта");
            recipe_patient_id = new TextField("Id пациента");
            recipe_doctor_id = new TextField("Id врача");

            creationDate = new DateTimeField();
            creationDate.setResolution(DateTimeResolution.DAY);
            creationDate.setTextFieldEnabled(false);

            validity = new DateTimeField();
            validity.setResolution(DateTimeResolution.DAY);
            validity.setTextFieldEnabled(false);

            recipe_priority_combobox = new ComboBox("Приоритет");
            recipe_priority_combobox.setItems(RecipePriorities.NORMAL,RecipePriorities.CITO,RecipePriorities.STATIM);
            recipe_priority_combobox.setSelectedItem(RecipePriorities.NORMAL);
            recipe_priority_combobox.setTextInputAllowed(false);

            recipe_binder= new Binder();
            recipe_binder.forField(descriptionField).withValidator( str -> str.length()!=0,"null Field").bind(Recipe::getDescription,Recipe::setDescription);

            if(entity == null){ //создание рецепта
                super.setCaption("Добавить рецепт");
                recipe_priority_combobox.setSelectedItem(RecipePriorities.NORMAL);
                ok_butt = new Button("OK", e ->addRecipeButtonAction());
            }
            else{ //изменение

                Recipe recipe = (Recipe) entity;
                recipe_binder.readBean(recipe);
                recipe_patient_id.setValue(Long.toString(recipe.getPatient().getId()));
                recipe_doctor_id.setValue(Long.toString(recipe.getDoctor().getId()));
                recipe_patient_id.setEnabled(false);
                recipe_doctor_id.setEnabled(false);
                creationDate.setValue(convertDateToLocalDateTime(recipe.getCreation_date()));
                validity.setValue(convertDateToLocalDateTime(recipe.getValidity()));
                recipe_priority_combobox.setSelectedItem(RecipePriorities.getValueOf(recipe.getPriority()));
                super.setCaption("Изменить");
                ok_butt = new Button("OK",e-> changeRecipeButtonAction(recipe));
            }
            formLayout.setMargin(true);
            formLayout.addComponents(descriptionField,recipe_patient_id,recipe_doctor_id,creationDate,validity,recipe_priority_combobox,new HorizontalLayout(ok_butt,cancel_butt));
            super.setContent(formLayout);
        }

    }

    private LocalDateTime convertDateToLocalDateTime(Date date){

        LocalDateTime ldt =  LocalDateTime.of(date.toLocalDate(),LocalTime.of(0,0));

        return ldt;
    }
    private Date convertLocalDateTimeToDate(LocalDateTime localDateTime){
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        Date output = Date.valueOf(localDateTime.toLocalDate());
        return output;
    }

    private boolean check_phone(String str) {
        try {
            Integer res = Integer.parseInt(str);
           return  (res == null) ? false :true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void changeRecipeButtonAction(Recipe update_recipe) {
        boolean close = false; //закрыть окно если все норм
        try {
            Recipe buff = new Recipe();
            recipe_binder.writeBean(buff);
            update_recipe.setDescription(buff.getDescription());

            if (recipe_priority_combobox.getSelectedItem() == null || !recipe_priority_combobox.getSelectedItem().isPresent()){
                Notification.show("Exception",
                        "Выберите приоритет",
                        Notification.Type.ERROR_MESSAGE);
                return;
            }
            RecipePriorities priority = (RecipePriorities)recipe_priority_combobox.getSelectedItem().get();
            update_recipe.setPriority(priority.toString());
            LocalDateTime creatDate = creationDate.getValue();
            LocalDateTime val = validity.getValue();
            if (val == null || creatDate == null){
                Notification.show("Exception",
                        "Введите дату создания/срок",
                        Notification.Type.ERROR_MESSAGE);
                return;
            }
            update_recipe.setCreation_date(convertLocalDateTimeToDate(creatDate));
            update_recipe.setValidity(convertLocalDateTimeToDate(val));
            recipeService.update(update_recipe);
            close = true;
        }

        catch (ValidationException ex) {
            Notification.show("Exception",
                    "Проверьте правильность заполнения формы",
                    Notification.Type.ERROR_MESSAGE);
            close=false;
        } catch (Exception ex) {
            ex.printStackTrace();
            Notification.show("Exception",
                    ex.toString(),
                    Notification.Type.ERROR_MESSAGE);
            close = true;
        }
        if (close){
            close();
        }

    }

    private void addRecipeButtonAction() {
        boolean close = false; //закрыть окно если все норм
        try {
            Recipe new_recipe = new Recipe();
            LocalDateTime creatDate = creationDate.getValue();
            LocalDateTime val = validity.getValue();
            if (val == null || creatDate == null){
                Notification.show("Exception",
                        "Введите дату создания/срок",
                        Notification.Type.ERROR_MESSAGE);
                return;
            }
            new_recipe.setCreation_date(convertLocalDateTimeToDate(creatDate));
            new_recipe.setValidity(convertLocalDateTimeToDate(val));
            if(recipe_priority_combobox.getSelectedItem() == null || !recipe_priority_combobox.getSelectedItem().isPresent()){
                Notification.show("Exception",
                        "Выберите приоритет",
                        Notification.Type.ERROR_MESSAGE);
                return;
            }
            RecipePriorities ppp = (RecipePriorities) recipe_priority_combobox.getSelectedItem().get();

            new_recipe.setPriority(ppp.toString());
            recipe_binder.writeBean(new_recipe);
            if (recipe_doctor_id.getValue() == null || recipe_doctor_id.getValue().length()==0 ||recipe_patient_id.getValue() == null || recipe_patient_id.getValue().length()==0) {
                Notification.show("Exception",
                        "Введите id пациента/врача",
                        Notification.Type.ERROR_MESSAGE);

                return;
            }
            long doc_id = Long.parseLong(recipe_doctor_id.getValue());
            long pat_id = Long.parseLong(recipe_patient_id.getValue());
            doctorService = new DoctorService();
            patientService = new PatientService();
            Doctor doctor = doctorService.findById(doc_id);
            Patient patient = patientService.findById(pat_id);
            if (doctor == null || patient == null)
            {
                Notification.show("Exception",
                        "Не существует такого id пациента/врача",
                        Notification.Type.ERROR_MESSAGE);
                return;
            }

            doctorService.update(doctor);
            patientService.update(patient);
            new_recipe.setDoctor(doctor);
            new_recipe.setPatient(patient);
            recipeService.add(new_recipe);
            close = true;
        }

        catch (ValidationException ex) {
            Notification.show("Exception",
                    "Проверьте правильность заполнения формы",
                    Notification.Type.ERROR_MESSAGE);
            close=false;
        } catch (Exception ex) {
            ex.printStackTrace();
            Notification.show("Exception",
                    ex.toString(),
                    Notification.Type.ERROR_MESSAGE);
            close = true;
        }
        if (close){
            close();
        }
    }

    //заполнение форм изменения сущностей


    private void cancelButtonAction(){
        close();
    }

    private void  changePatientButtonAction (Long change_patient_id){
        boolean close = false; //закрыть окно если все норм
        try {
            Patient update_patient = patientService.findById(change_patient_id);
            patient_binder.writeBean(update_patient);
            patientService.update(update_patient);
            close = true;
        } catch (ValidationException ex) {
            Notification.show("Exception",
                    "Проверьте правильность заполнения формы",
                    Notification.Type.ERROR_MESSAGE);
            close = false;
        } catch (Exception ex) {
            ex.printStackTrace();
            Notification.show("Exception",
                    ex.toString(),
                    Notification.Type.ERROR_MESSAGE);
            close = true;
        }
        if (close){
            close();
        }
    }

    private void addPatientButtonAction(Event e){

        boolean close = false; //закрыть окно если все норм
        try {
            Patient new_patient = new Patient();
            patient_binder.writeBean(new_patient);
            patientService.add(new_patient);
            close = true;
        } catch (ValidationException ex) {
            Notification.show("Exception",
                    "Проверьте правильность заполнения формы",
                    Notification.Type.ERROR_MESSAGE);
            close=false;
        } catch (Exception ex) {
            Notification.show("Exception",
                    ex.toString(),
                    Notification.Type.ERROR_MESSAGE);
            close = true;
        }
        if (close){
            close();
        }
    }
    private void addDoctorButtonAction(Event e){
        boolean close = false; //закрыть окно если все норм
        try {
            Doctor new_doctor = new Doctor();
            doctor_binder.writeBean(new_doctor);
            doctorService.add(new_doctor);
            close = true;
        } catch (ValidationException ex) {
            Notification.show("Exception",
                    "Проверьте правильность заполнения формы",
                    Notification.Type.ERROR_MESSAGE);
            close=false;
        } catch (Exception ex) {
            Notification.show("Exception",
                    ex.toString(),
                    Notification.Type.ERROR_MESSAGE);
            close = true;
        }
        if (close){
            close();
        }

    }

    private void changeDoctorButtonAction(Long change_doctor_id){
        boolean close = false; //закрыть окно если все норм

        try {
            Doctor update_doctor = doctorService.findById(change_doctor_id);
            doctor_binder.writeBean(update_doctor);
            doctorService.update(update_doctor);
            close = true;
        } catch (ValidationException ex) {
            Notification.show("Exception",
                    "Проверьте правильность заполнения формы",
                    Notification.Type.ERROR_MESSAGE);
            close = false;
        } catch (Exception ex) {
            Notification.show("Exception",
                    ex.toString(),
                    Notification.Type.ERROR_MESSAGE);
            close = true;
        }
        if (close){
            close();
        }
    }

}


