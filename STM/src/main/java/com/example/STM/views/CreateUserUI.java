package com.example.STM.views;

import com.example.STM.model.User;
import com.example.STM.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
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

@Route("adminView/createUserView")
@SpringComponent
@UIScope
public class CreateUserUI extends VerticalLayout {
    private TextField nameField;
    private TextField lastNameField;
    private EmailField emailField;
    private PasswordField passwordField;
    private PasswordField passwordFieldConf;
    private Button confirmationButton;
    private Label informationLabel;

    private User user;
    @Autowired
    private UserService userService;
    @PostConstruct
    private void init(){
        nameField= new TextField("Name: ");
        lastNameField = new TextField("Last name: ");
        emailField = new EmailField("Email: ");
        passwordField = new PasswordField("Password: ");
        passwordFieldConf = new PasswordField("Confirm password: ");
        confirmationButton = new Button("Create");
        informationLabel = new Label();

        confirmationButton.addClickListener(c->{
            if(!nameField.isEmpty() && !lastNameField.isEmpty() && !emailField.isEmpty()
            && !passwordField.isEmpty() && !passwordFieldConf.isEmpty() &&
                    (passwordField.getValue().equals(passwordFieldConf.getValue()))){
                String name=nameField.getValue();
                String lastName=lastNameField.getValue();
                String email=emailField.getValue();
                String password=passwordField.getValue();
                User userToSave = new User(name,lastName,email,password);
                user=userService.insertUser(userToSave);
                if(user!=null){
                    informationLabel.setText(String.format("User %s %s added correctly!",name,lastName));
                }else{
                    informationLabel.setText(String.format("Error. It's possible that user already exists"));
                }
            }else{
                informationLabel.setText("Provide all data and make sure that password and password confirmation is equal");
            }
        });
        add(nameField,lastNameField,emailField,passwordField,passwordFieldConf,confirmationButton,informationLabel);
    }


}
