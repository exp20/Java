package com.mycom.view;

import com.mycom.entity.Doctor;
import com.mycom.services.DoctorService;
import com.mycom.services.PatientService;
import com.mycom.services.RecipeService;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import org.hibernate.exception.ConstraintViolationException;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


public class DoctorsView extends Composite implements View {
    private VerticalLayout mainLayout;
    private DoctorService doctorService;
    private Grid<Doctor> grid;
    private Label title;
    private Label statistic_lable;
    private Button add_butt;
    private Button del_butt;
    private Button change_butt;
    private TextField del_or_change_id_field;
    private HorizontalLayout del_change_level;

    private Map<BigInteger,BigInteger> recipes_statistic;
    public DoctorsView(){
        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        title = new Label("Врачи");
        mainLayout.addComponent(title);
        mainLayout.setComponentAlignment(title,Alignment.TOP_LEFT);

        statistic_lable = new Label("Всего рецептов:");
        mainLayout.addComponent(statistic_lable);
        mainLayout.setComponentAlignment(statistic_lable,Alignment.TOP_LEFT);

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

        grid = new Grid<>();
        grid.setVisible(false);


        mainLayout.addComponent(grid);
        mainLayout.setComponentAlignment(grid,Alignment.MIDDLE_LEFT);

        grid.setVisible(false);
        grid.addColumn(Doctor::getId).setCaption("id");
        grid.addColumn(Doctor::getName).setCaption("Имя");
        grid.addColumn(Doctor::getLast_name).setCaption("Фамилия");
        grid.addColumn(Doctor::getHonestly).setCaption("Отчество");
        grid.addColumn(Doctor::getSpecialization).setCaption("Специализация");
        //в поле recipes_statistic лежит мапа <доктор_id,кол-во рецептов>
        grid.addColumn(Doctor -> recipes_statistic.get(BigInteger.valueOf(Doctor.getId()))).setCaption("Кол-во рецептов");
        setCompositionRoot(mainLayout); // ОБЯЗАТЕЛНО добавить компоненты
        init();
        }
        private void init(){

            try{
                this.doctorService = new DoctorService();
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
        ModalCreateAddView modal_window = new ModalCreateAddView(this,doctorService,null);
        modal_window.addCloseListener(e -> updateTable()); //чтоб обновилась таблица при закрытии модального окна
        getUI().addWindow(modal_window);
    }
    private void updateTable(){
        try {
            List<Doctor> doctors = doctorService.getAll();
            if (doctors.size()==0){
                this.title.setCaption("Список врачей пуст");
                return;
            }
            else {
                Object[] statistic = doctorService.getDoctorRecipesStatistic();
                recipes_statistic = (Map<BigInteger, BigInteger>) statistic[0];
                BigInteger recipes_count = (BigInteger)statistic[1];
                this.statistic_lable.setValue("Всего рецептов: "+recipes_count.toString());
                System.out.println("FFF"+statistic[1]);
                grid.setItems(doctors);
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
            doctorService.delete(doctorService.findById(id));
            updateTable();
        }
        catch (Exception e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                Notification.show("Exception",
                        "Нельзя удалить врача имеющего рецепт",
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
            ModalCreateAddView modal_window = new ModalCreateAddView(this,doctorService,doctorService.findById(id));
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
