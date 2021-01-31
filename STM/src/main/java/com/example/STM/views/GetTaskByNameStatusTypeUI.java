package com.example.STM.views;

import com.example.STM.model.Task;
import com.example.STM.model.enums.Status;
import com.example.STM.model.enums.Type;
import com.example.STM.service.TaskService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

@Route("userView/getTaskByNameStatusTypeView")
@SpringComponent
@UIScope
public class GetTaskByNameStatusTypeUI extends VerticalLayout {
    private TextField titleField;
    private Button searchButton;
    private ComboBox<Status> statusBox;
    private ComboBox<Type> typeBox;
    private Grid<Task> taskGrid;
    private Label informationLabel;

    @Autowired
    private TaskService taskService;


    @PostConstruct
    private void init(){
        titleField=new TextField("Task title:");
        statusBox=new ComboBox<>("Task status");
        statusBox.setAllowCustomValue(false);
        statusBox.setItems(Arrays.asList(Status.values()));
        typeBox=new ComboBox<>("Task type:");
        typeBox.setAllowCustomValue(false);
        typeBox.setItems(Arrays.asList(Type.values()));
        searchButton=new Button("Search");
        taskGrid=new Grid<>(Task.class);
        taskGrid.setColumns("taskId","title","description","dateAdded","type","status");
        informationLabel=new Label();

        titleField.addInputListener(l->{
            statusBox.setValue(null);
            typeBox.setValue(null);
        });
        statusBox.addValueChangeListener(l->{
            titleField.setValue("");
            typeBox.setValue(null);
        });
        typeBox.addValueChangeListener(l->{
            titleField.setValue("");
            statusBox.setValue(null);
        });
        searchButton.addClickListener(l->{
            if(!titleField.isEmpty() || !statusBox.isEmpty() || !typeBox.isEmpty()){
                taskGrid.setItems(taskService.getTask(titleField.getOptionalValue(),statusBox.getOptionalValue(),typeBox.getOptionalValue()));
            }else{
                informationLabel.setText("Fill at least one field!");
            }

        });
        taskGrid.addItemClickListener(l->{
            informationLabel.setText(String.format("Full description: %s",l.getItem().getDescription()));
        });
        add(taskGrid,informationLabel,titleField,statusBox,typeBox,searchButton);
    }
}
