package com.example.STM.views;

import com.example.STM.model.Task;
import com.example.STM.service.TaskService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("userView/deleteTaskView")
@SpringComponent
@UIScope
public class DeleteTaskUI extends VerticalLayout {
    private Grid<Task> tasksGrid;
    private Label informationLabel1;
    private Button deleteTaskButton;
    private Label informationLabel2;

    private Task selectedTask;

    @Autowired
    private TaskService taskService;

    @PostConstruct
    private void init(){
        tasksGrid=new Grid<>(Task.class);
        tasksGrid.setColumns("taskId","title","description","dateAdded","type","status");
        tasksGrid.setItems(taskService.selectAllTasks());
        informationLabel1=new Label();
        deleteTaskButton=new Button("Delete task");
        informationLabel2=new Label();

        tasksGrid.addItemClickListener(l->{
            selectedTask=l.getItem();
            informationLabel1.setText(String.format("Full description: %s",selectedTask.getDescription()));
        });
        deleteTaskButton.addClickListener(l->{
            if(selectedTask!=null){
                int id=selectedTask.getTaskId();
                String title=selectedTask.getTitle();
                taskService.deleteTask(id);
                informationLabel2.setText(String.format("Task with id: %d and title: \"%s\" has been deleted ",id,title));
                tasksGrid.setItems(taskService.selectAllTasks());
            }else{
                informationLabel2.setText("Select task");
            }
        });

        add(tasksGrid,informationLabel1,deleteTaskButton,informationLabel2);
    }
}
