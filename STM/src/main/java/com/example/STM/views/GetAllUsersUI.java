package com.example.STM.views;

import com.example.STM.model.User;
import com.example.STM.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("adminView/getAllUsersView")
@SpringComponent
@UIScope
public class GetAllUsersUI extends VerticalLayout {
    private Grid<User> userGrid;
    @Autowired
    private UserService userService;

    @PostConstruct
    private void init(){
        userGrid = new Grid<>(User.class);
        userGrid.setItems(userService.selectAllUsers());
        userGrid.setColumns("userId","name","lastName","email","password","status","registrationDateTime");
        add(userGrid);
    }
}
