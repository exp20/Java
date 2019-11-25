package com.mycom.view;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.servlet.annotation.WebServlet;


/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@PushStateNavigation //способ для навигатора управлять URL-адресами
public class MainView extends UI{
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        //меню
        Label title = new Label("МЕНЮ");
        title.addStyleName(ValoTheme.MENU_TITLE);
        CssLayout menu = new CssLayout();
        menu.addStyleName(ValoTheme.MENU_ROOT);
        Button doctors = new Button("Врачи", e -> getNavigator().navigateTo("doctors"));
        doctors.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        Button patients = new Button("Пациенты", e -> getNavigator().navigateTo("patients"));
        patients.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        Button recipes = new Button("Рецепты", e -> getNavigator().navigateTo("recipes"));
        recipes.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);

        menu.addComponents(title, doctors, patients, recipes);

        // остальная часть страницы
        CssLayout viewContainer = new CssLayout();
        //viewContainer.setWidth(null);

        CssLayout mainLayout = new CssLayout();
        mainLayout.setSizeFull();

        mainLayout.addComponents(menu,viewContainer);

        setContent(mainLayout);


        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView("",DefaultView.class);
        navigator.addView("doctors", DoctorsView.class);
        navigator.addView("patients", PatientsView.class);
        navigator.addView("recipes", RecipesView.class);

    }


    @WebServlet(urlPatterns = "/*",asyncSupported = true)
    @VaadinServletConfiguration(ui = MainView.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

