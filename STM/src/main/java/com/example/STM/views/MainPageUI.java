package com.example.STM.views;

import com.example.STM.model.User;
import com.example.STM.repository.UserRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


@Route("")
@SpringComponent
@UIScope
public class MainPageUI extends VerticalLayout {
    private Button goToAdminViewButton;
    private Button goToUserViewButton;
    private EmailField emailField;
    private Label informationLabel;

    private HorizontalLayout horizontalLayout1;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init(){
        goToAdminViewButton = new Button("Admin View");
        goToUserViewButton = new Button("User View");
        emailField=new EmailField();
        emailField.setPlaceholder("email");
        informationLabel= new Label();
        horizontalLayout1 = new HorizontalLayout();

        goToAdminViewButton.addClickListener(c->{
            UI.getCurrent().getPage().setLocation("/adminView");
        });
        goToUserViewButton.addClickListener(c->{
            String email = emailField.getValue();
            User user = userRepository.findByEmail(email).orElse(null);
            if(user!=null){
                UI.getCurrent().getPage().setLocation("/userView");
            }else{
                informationLabel.setText("No user has that email");
            }
        });
        horizontalLayout1.add(emailField,goToUserViewButton,informationLabel);
        add(goToAdminViewButton,horizontalLayout1);



    }
}
