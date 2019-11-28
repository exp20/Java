package com.mycom.view;

import com.mycom.entity.Recipe;
import com.mycom.services.RecipeService;
import com.mycom.services.RecipePriorities;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;

import java.util.List;


public class RecipesView extends Composite implements View {

    private VerticalLayout mainLayout;

    private RecipeService recipeService;
    private Grid<Recipe> grid;
    private Label title;
    private Button add_butt;
    private Button del_butt;
    private Button change_butt;
    private TextField del_or_change_id_field;
    private HorizontalLayout del_change_level;

    //фильтр
    private HorizontalLayout filter_layout;
    private TextField description_filter_field;
    private TextField patient_id_filter_field;
    private ComboBox recipe_priority_combobox;
    private Button start_filter;



    public RecipesView(){
        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        title = new Label("Рецепты");
        mainLayout.addComponent(title);
        mainLayout.setComponentAlignment(title,Alignment.TOP_LEFT);

        filter_layout = new HorizontalLayout();
        mainLayout.addComponent(filter_layout);
        mainLayout.setComponentAlignment(filter_layout,Alignment.TOP_LEFT);

        start_filter = new Button("Фильтровать:", e-> filterActionButton());
        filter_layout.addComponent(start_filter);

        description_filter_field = new TextField();
        description_filter_field.setPlaceholder("описание рецепта");
        filter_layout.addComponent(description_filter_field);

        patient_id_filter_field = new TextField();
        patient_id_filter_field.setPlaceholder("id пациента");
        filter_layout.addComponent(patient_id_filter_field);

        recipe_priority_combobox = new ComboBox();
        recipe_priority_combobox.setItems(RecipePriorities.NORMAL, RecipePriorities.CITO, RecipePriorities.STATIM);
        recipe_priority_combobox.setPlaceholder("Приоритет");
        recipe_priority_combobox.setTextInputAllowed(false);
        filter_layout.addComponent(recipe_priority_combobox);

        mainLayout.addComponent(filter_layout);


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

    private void filterActionButton() {
        try {
            String filter_description = this.description_filter_field.getValue();
            String filter_id_string = this.patient_id_filter_field.getValue();
            RecipePriorities filter_priority = (RecipePriorities) this.recipe_priority_combobox.getValue();
            Long id_long = (filter_id_string==null || filter_id_string.length()==0 ?null :Long.parseLong(filter_id_string));
            List<Recipe> recipes = recipeService.getAll();

            this.grid.setItems(recipeService.filtering(recipes, filter_description,id_long,filter_priority));

        }
        catch (NumberFormatException e){
            Notification.show("Exception",
                    "Укажите число в поле "+this.patient_id_filter_field.getCaption()+" фильтра",
                    Notification.Type.ERROR_MESSAGE);
        }
        catch (Exception e) {
            Notification.show("Exception",
                    e.toString(),
                    Notification.Type.ERROR_MESSAGE);
        }
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
            if (del_or_change_id_field.getValue() == null || del_or_change_id_field.getValue().length()==0) {
                Notification.show("Exception",
                        "Введите id для удаления",
                        Notification.Type.ERROR_MESSAGE);
                return;
            }
            Long id =  Long.parseLong(del_or_change_id_field.getValue());
            recipeService.delete(recipeService.findById(id));
            updateTable();
        }
        catch (NumberFormatException ex) {
            Notification.show("Exception",
                    "Введите целое число",
                    Notification.Type.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
            Notification.show("Exception",
                    "не существует рецепта с таким id",
                    Notification.Type.ERROR_MESSAGE);
        }
        catch (Exception e) {
                Notification.show("Exception",
                        e.toString(),
                        Notification.Type.ERROR_MESSAGE);
            }
    }

    private void changeButtonAction(){
        try{
            if (del_or_change_id_field.getValue() == null || del_or_change_id_field.getValue().length()==0){
                Notification.show("Exception",
                        "Введите id для изменения",
                        Notification.Type.ERROR_MESSAGE);
                return;
            }
            long id =  Long.parseLong(del_or_change_id_field.getValue());
            Recipe find_recipe = recipeService.findById(id);
            if (find_recipe == null)
            {
                Notification.show("Exception",
                        "не существует рецепта с таким id",
                        Notification.Type.ERROR_MESSAGE);
                return;
            }
            ModalCreateAddView modal_window = new ModalCreateAddView(this,recipeService,find_recipe);
            modal_window.addCloseListener(e -> updateTable());
            getUI().addWindow(modal_window);

        }
        catch (NumberFormatException ex){
            Notification.show("Exception",
                    "Введите целое число",
                    Notification.Type.ERROR_MESSAGE);

        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
            Notification.show("Exception",
                    "не существует рецепта с таким id",
                    Notification.Type.ERROR_MESSAGE);
        }
        catch (Exception e){
            e.printStackTrace();
            Notification.show("Exception",
                    e.toString(),
                    Notification.Type.ERROR_MESSAGE);

        }

    }


}
