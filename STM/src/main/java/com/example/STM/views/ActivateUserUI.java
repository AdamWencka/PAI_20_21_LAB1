package com.example.STM.views;


import com.example.STM.model.User;
import com.example.STM.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("adminView/activeUserView")
@SpringComponent
@UIScope
public class ActivateUserUI extends VerticalLayout {
    private Grid<User> userGrid;
    private Button activateUserButton;
    private Label informationLabel;

    private User userToActivate;
    @Autowired
    private UserService userService;

    @PostConstruct
    private void  init(){
        userGrid=new Grid<>(User.class);
        activateUserButton=new Button("Activate User");
        informationLabel=new Label();

        userGrid.setItems(userService.selectAllUsers());
        userGrid.setColumns("userId","name","lastName","email","password","status","registrationDateTime");

        userGrid.addItemClickListener(l->{
           userToActivate=l.getItem();
        });

        activateUserButton.addClickListener(l->{
            userService.activateUser(userToActivate.getUserId());
            userGrid.deselect(userToActivate);
            userGrid.setItems(userService.selectAllUsers());
        });
        add(userGrid,activateUserButton,informationLabel);
    }
}
