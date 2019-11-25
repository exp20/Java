package com.mycom.view;


import com.mycom.entity.Patient;
import com.mycom.services.PatientService;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import org.hibernate.exception.ConstraintViolationException;
import java.util.List;


public class PatientsView extends Composite implements View {
    private VerticalLayout mainLayout;

    private PatientService patientService;
    private Grid<Patient> grid;
    private Label title;
    private Button add_butt;
    private Button del_butt;
    private Button change_butt;
    private TextField del_or_change_id_field;
    private HorizontalLayout del_change_level;




    public PatientsView(){
        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        title = new Label("Пациенты");
        mainLayout.addComponent(title);
        mainLayout.setComponentAlignment(title,Alignment.TOP_LEFT);

        add_butt = new Button("Добавить", e-> addButtonAction());
        mainLayout.addComponent(add_butt);
        mainLayout.setComponentAlignment(add_butt,Alignment.TOP_LEFT);

        del_change_level = new HorizontalLayout();
        mainLayout.addComponent(del_change_level);

        del_butt = new Button("Удалить",e -> delButtonAction());
        del_change_level.addComponent(del_butt);

        change_butt = new Button("Изменить",e -> changeButtonAction());
        del_change_level.addComponent(change_butt);

        del_or_change_id_field = new TextField();
        del_or_change_id_field.setPlaceholder("id");
        del_change_level.addComponent(del_or_change_id_field);

        grid = new Grid<>(Patient.class);
        grid.setVisible(false);


        mainLayout.addComponent(grid);
        mainLayout.setComponentAlignment(grid,Alignment.MIDDLE_LEFT);
        // TODO
        //grid.setColumns("id","Имя","Фамилия","Отчество","Телефон");

        setCompositionRoot(mainLayout); // ОБЯЗАТЕЛНО добавить компоненты
        init();
    }
    private void init(){

        try{
            this.patientService = new PatientService();
        }
        catch (Exception e){

            Notification.show("Exception",
                    e.toString(),
                    Notification.Type.ERROR_MESSAGE);
            return;
        }

        updateTable();
    }

    private void addButtonAction(){

        ModalCreateAddView modal_window = new ModalCreateAddView(this,patientService,null);
        modal_window.addCloseListener(e -> updateTable());
        getUI().addWindow(modal_window);
    }


    private void updateTable(){
        try {
            List<Patient> patients = patientService.getAll();
            if (patients.size()==0){
                this.title.setCaption("Список пациентов пуст");
                return;
            }
            else {
                grid.setItems(patients);
                grid.setVisible(true);
            }
        } catch (Exception e) {
            Notification.show("Exception",
                    e.toString(),
                    Notification.Type.ERROR_MESSAGE);
            return;
        }
    }

    private void delButtonAction()  {
        try{
           int id =  Integer.parseInt(del_or_change_id_field.getValue());
           patientService.delete(patientService.findById(id));
           updateTable();
        }
        catch (Exception e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                Notification.show("Exception",
                        "Нельзя удалить пациента имеющего рецепт",
                        Notification.Type.ERROR_MESSAGE);
            }
            else
            {
                Notification.show("Exception",
                        e.toString(),
                        Notification.Type.ERROR_MESSAGE);

            }
        }
    }

    private void changeButtonAction(){
        try{
            int id =  Integer.parseInt(del_or_change_id_field.getValue());
            ModalCreateAddView modal_window = new ModalCreateAddView(this,patientService,patientService.findById(id));
            modal_window.addCloseListener(e -> updateTable());
            getUI().addWindow(modal_window);

        }
        catch (NumberFormatException ex){
            Notification.show("Exception",
                    "Введите целое число",
                    Notification.Type.ERROR_MESSAGE);

        }
        catch (Exception e){
            Notification.show("Exception",
                    "Введен не верный id",
                    Notification.Type.ERROR_MESSAGE);

        }

    }

}
