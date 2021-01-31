package com.example.STM.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import javax.annotation.PostConstruct;

@Route("userView")
@SpringComponent
@UIScope
public class UserPageUI extends VerticalLayout {
    private Button createNewTaskButton;
    private Button getAllTasksButton;
    private Button getTaskByNameStatusTypeButton;
    private Button changeTaskStatusButton;
    private Button deleteTaskButton;


    @PostConstruct
    private void init() {
        createNewTaskButton = new Button("Create new task");
        getAllTasksButton = new Button("Get all tasks orderd by Desc");
        getTaskByNameStatusTypeButton = new Button("Find task by name/status/type");
        changeTaskStatusButton = new Button("Change task's status");
        deleteTaskButton = new Button("DeleteTask");

        createNewTaskButton.addClickListener(l -> {
            UI.getCurrent().getPage().setLocation("/userView/createTaskView");

        });
        getAllTasksButton.addClickListener(l -> {
            UI.getCurrent().getPage().setLocation("/userView/getAllTasksView");
        });
        getTaskByNameStatusTypeButton.addClickListener(l -> {
            UI.getCurrent().getPage().setLocation("/userView/getTaskByNameStatusTypeView");
        });
        changeTaskStatusButton.addClickListener(l -> {
            UI.getCurrent().getPage().setLocation("/userView/changeTaskStatusView");
        });
        deleteTaskButton.addClickListener(l -> {
            UI.getCurrent().getPage().setLocation("/userView/deleteTaskView");
        });

        add(createNewTaskButton, getAllTasksButton, getTaskByNameStatusTypeButton, changeTaskStatusButton, deleteTaskButton);
    }
}
