package com.example.STM.views;
import com.example.STM.model.Task;
import com.example.STM.service.TaskService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
@Route("userView/getAllTasksView")
@SpringComponent
@UIScope
public class GetAllTasksUI extends VerticalLayout {
    private Grid<Task> tasksGrid;
    private Label informationLabel;

    private Task selectedTask;

    @Autowired
    private TaskService taskService;


    @PostConstruct
    private void init(){
        tasksGrid=new Grid<>(Task.class);
        tasksGrid.setItems(taskService.getAllTasks());
        tasksGrid.setColumns("taskId","title","description","dateAdded","type","status");
        informationLabel=new Label();

        tasksGrid.addItemClickListener(l->{
            selectedTask=l.getItem();
            informationLabel.setText(String.format("Full description: %s",selectedTask.getDescription()));
        });
        add(tasksGrid,informationLabel);
    }
}
