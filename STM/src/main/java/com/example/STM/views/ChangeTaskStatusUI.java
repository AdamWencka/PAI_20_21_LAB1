package com.example.STM.views;
import com.example.STM.model.Task;
import com.example.STM.model.enums.Status;
import com.example.STM.service.TaskService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Route("userView/changeTaskStatusView")
@SpringComponent
@UIScope
public class ChangeTaskStatusUI extends VerticalLayout {
    private Grid<Task> tasksGrid;
    private ComboBox<Status> statusBox;
    private Button confirmButton;
    private Label informationLabel1;
    private Label informationLabel2;

    private Task selectedTask;

    @Autowired
    private TaskService taskService;


    @PostConstruct
    private void init(){
        tasksGrid=new Grid<>(Task.class);
        tasksGrid.setColumns("taskId","title","description","dateAdded","type","status");
        tasksGrid.setItems(taskService.selectAllTasks());
        statusBox=new ComboBox<>("Update status:");
        statusBox.setAllowCustomValue(false);
        statusBox.setItems(Arrays.asList(Status.values()));
        confirmButton=new Button("Update");
        informationLabel1=new Label();
        informationLabel2=new Label();

        tasksGrid.addItemClickListener(l->{
            selectedTask=l.getItem();
            informationLabel1.setText(String.format("Full description: %s",selectedTask.getDescription()));
        });
        confirmButton.addClickListener(l->{
            if(selectedTask!=null){
                Status prevStatus=taskService.selectTaskById(selectedTask.getTaskId()).getStatus();
                taskService.changeTaskStatus(selectedTask.getTaskId(),statusBox.getValue());
                Status newStatus=taskService.selectTaskById(selectedTask.getTaskId()).getStatus();
                informationLabel2.setText(String.format("Task with id %d changed status from %s to %s.",
                        selectedTask.getTaskId(),prevStatus,newStatus));
                tasksGrid.setItems(taskService.selectAllTasks());
            }else{
                informationLabel2.setText("Select task");
            }
        });
        add(tasksGrid,informationLabel1,statusBox,confirmButton,informationLabel2);
    }
}
