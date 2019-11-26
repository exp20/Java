package com.mycom.view;

import com.mycom.entity.Recipe;
import com.mycom.services.RecipeService;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class RecipesView extends Composite implements View {

    // TODO ФИЛЬТР
    private VerticalLayout mainLayout;

    private RecipeService recipeService;
    private Grid<Recipe> grid;
    private Label title;
    private Button add_butt;
    private Button del_butt;
    private Button change_butt;
    private TextField del_or_change_id_field;
    private HorizontalLayout del_change_level;



    public RecipesView(){
        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        title = new Label("Рецепты");
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

        grid = new Grid<>();
        grid.setVisible(false);
        grid.addColumn(Recipe::getId).setCaption("id");
        grid.addColumn(Recipe::getDescription).setCaption("Описание");
        grid.addColumn(Recipe -> Recipe.getPatient().getId()).setCaption("Пациент id");
        grid.addColumn(Recipe -> Recipe.getDoctor().getId()).setCaption("Доктор id");
        grid.addColumn(Recipe::getCreation_date).setCaption("Дата создания");
        grid.addColumn(Recipe::getValidity).setCaption("Срок действия");
        grid.addColumn(Recipe::getPriority).setCaption("Приоритет");

        mainLayout.addComponent(grid);
        mainLayout.setComponentAlignment(grid,Alignment.MIDDLE_LEFT);

        setCompositionRoot(mainLayout); // ОБЯЗАТЕЛНО добавить компоненты
        init();
    }
    private void init(){

        try{
            this.recipeService = new RecipeService();
            grid.setItems(recipeService.getAll());
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

        ModalCreateAddView modal_window = new ModalCreateAddView(this,recipeService,null);
        modal_window.addCloseListener(e -> updateTable());
        getUI().addWindow(modal_window);
    }


    private void updateTable(){
        try {
            List<Recipe> recipes = recipeService.getAll();
            if (recipes.size()==0){
                this.title.setCaption("Список рецептов пуст");
                return;
            }
            else {
                grid.setItems(recipeService.getAll());
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
            recipeService.delete(recipeService.findById(id));
            updateTable();
        }
        catch (Exception e) {
                Notification.show("Exception",
                        e.toString(),
                        Notification.Type.ERROR_MESSAGE);
            }
    }

    private void changeButtonAction(){
        try{
            int id =  Integer.parseInt(del_or_change_id_field.getValue());
            ModalCreateAddView modal_window = new ModalCreateAddView(this,recipeService,recipeService.findById(id));
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
