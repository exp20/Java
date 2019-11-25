package com.mycom.view;


import com.vaadin.navigator.View;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DefaultView extends Composite implements View {
    public DefaultView(){
        VerticalLayout defaultLayout = new VerticalLayout();
        defaultLayout.addComponent(new Label("Добро пожаловать"));
        defaultLayout.setSizeFull();
        setCompositionRoot(defaultLayout);

    }
}

