package com.example.STM.views;
import com.example.STM.model.Task;
import com.example.STM.model.User;
import com.example.STM.model.enums.Status;
import com.example.STM.model.enums.Type;
import com.example.STM.service.TaskService;
import com.example.STM.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;
@Route("userView/createTaskView")
@SpringComponent
@UIScope
public class CreateTaskUI extends VerticalLayout {
    private EmailField emailField;
    private PasswordField passwordField;
    private PasswordField passwordFieldConf;
    private Button confirmLogButton;
    private Label informationLabel1;

    private TextField titleField;
    private TextField descriptionField;
    private ComboBox<Type> typeBox;
    private ComboBox<Status> statusBox;
    private Button createTaskButton;
    private Label informationLabel2;

    private User currentUser;
    private Task createdTask;

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;


    @PostConstruct
    private void init(){
        emailField=new EmailField("Email:");
        passwordField=new PasswordField("Password:");
        passwordFieldConf=new PasswordField("Confirm password:");
        confirmLogButton=new Button("Confirm");
        informationLabel1=new Label();

        titleField=new TextField("Task title:");
        descriptionField=new TextField("Task description:");
        typeBox=new ComboBox<>("Task type");
        statusBox=new ComboBox<>("Task status");
        createTaskButton=new Button("Create");
        informationLabel2=new Label();

        titleField.setEnabled(false);
        descriptionField.setEnabled(false);
        typeBox.setEnabled(false);
        typeBox.setAllowCustomValue(false);
        typeBox.setItems(Arrays.asList(Type.values()));
        statusBox.setEnabled(false);
        statusBox.setAllowCustomValue(false);
        statusBox.setItems(Arrays.asList(Status.values()));
        createTaskButton.setEnabled(false);



        confirmLogButton.addClickListener(l->{
            if(passwordField.getValue().equals(passwordFieldConf.getValue())){
                String email=emailField.getValue();
                String password=passwordField.getValue();
                currentUser=userService.findUserByEmailAndPassword(email,password);
                if(currentUser!=null){
                    if(currentUser.isStatus()){
                        titleField.setEnabled(true);
                        descriptionField.setEnabled(true);
                        typeBox.setEnabled(true);
                        statusBox.setEnabled(true);
                        createTaskButton.setEnabled(true);
                        informationLabel1.setText(String.format("Logged as %s %s",currentUser.getName(),currentUser.getLastName()));
                    }else {
                        informationLabel1.setText("User is inactive. Contact admin");
                    }
                }else{
                    informationLabel1.setText("No such user");
                }
            }else{
                informationLabel1.setText("Passwords aren't equal!");
            }

        });

        createTaskButton.addClickListener(l->{
            if(!titleField.isEmpty() && !descriptionField.isEmpty() && !typeBox.isEmpty() && !statusBox.isEmpty()){
                String title=titleField.getValue();
                String description=descriptionField.getValue();
                Type type=typeBox.getValue();
                Status status=statusBox.getValue();
                createdTask=taskService.createTask(title,description,type,status,currentUser.getUserId());
                informationLabel2.setText(String.format("Task with id: %d and title: \"%s\" added successfully.",
                        createdTask.getTaskId(),createdTask.getTitle()));
            }else{
                informationLabel2.setText("Provide all data");
            }
        });
        add(emailField,passwordField,passwordFieldConf,confirmLogButton,informationLabel1,
                titleField,descriptionField,typeBox,statusBox,createTaskButton,informationLabel2);
    }
}
