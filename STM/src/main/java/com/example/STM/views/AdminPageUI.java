package com.example.STM.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import javax.annotation.PostConstruct;


@Route("adminView")
@SpringComponent
@UIScope
public class AdminPageUI extends VerticalLayout {
    private Button createUserTaskButton;
    private Button getAllUsersTaskButton;
    private Button findUserByIdOrEmailTaskButton;
    private Button activeUserTaskButton;
    private Button deleteUserTaskButton;

    @PostConstruct
    private void init(){
        createUserTaskButton=new Button("Create new user");
        getAllUsersTaskButton=new Button("Get all users");
        findUserByIdOrEmailTaskButton=new Button("Get user by id or email");
        activeUserTaskButton=new Button("Activate user");
        deleteUserTaskButton=new Button("Delete user");

        createUserTaskButton.addClickListener(l->{
            UI.getCurrent().getPage().setLocation("/adminView/createUserView");
        });
        getAllUsersTaskButton.addClickListener(l->{
            UI.getCurrent().getPage().setLocation("/adminView/getAllUsersView");
        });
        findUserByIdOrEmailTaskButton.addClickListener(l->{
            UI.getCurrent().getPage().setLocation("/adminView/findUserByIdOrEmailView");
        });
        activeUserTaskButton.addClickListener(l->{
            UI.getCurrent().getPage().setLocation("/adminView/activeUserView");
        });
        deleteUserTaskButton.addClickListener(l->{
            UI.getCurrent().getPage().setLocation("/adminView/deleteUserView");
        });

        add(createUserTaskButton,getAllUsersTaskButton,findUserByIdOrEmailTaskButton,activeUserTaskButton,deleteUserTaskButton);


    }

}
