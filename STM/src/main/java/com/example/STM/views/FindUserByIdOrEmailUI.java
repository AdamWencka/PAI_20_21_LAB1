package com.example.STM.views;

import com.example.STM.model.User;
import com.example.STM.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route("adminView/findUserByIdOrEmailView")
@SpringComponent
@UIScope
public class FindUserByIdOrEmailUI extends VerticalLayout {
    private TextField idOrEmailField;
    private Button confirmationButton;
    private Grid<User> userGrid;
    private Label informationLabel;

    @Autowired
    private UserService userService;

    @PostConstruct
    private void init(){
        idOrEmailField=new TextField("Id or email:");
        confirmationButton=new Button("Search");
        userGrid=new Grid<>(User.class);
        userGrid.setColumns("userId","name","lastName","email","password","status","registrationDateTime");
        informationLabel=new Label();

        confirmationButton.addClickListener(l->{
            if(idOrEmailField.getValue().matches("\\d+")){
                if(userService.findUserByIdOrEmail(Integer.parseInt(idOrEmailField.getValue()))!=null){
                    userGrid.setItems(userService.findUserByIdOrEmail(Integer.parseInt(idOrEmailField.getValue())));
                    informationLabel.setText("Found by id");
                }else{
                    userGrid.setItems();
                    informationLabel.setText("No user with this id or email");
                }
            }else {
                if (userService.findUserByIdOrEmail(idOrEmailField.getValue()) != null) {
                    userGrid.setItems(userService.findUserByIdOrEmail(idOrEmailField.getValue()));
                    informationLabel.setText("Found by email");
                }else{
                    informationLabel.setText("No user with this id or email");
                }
            }
        });
        add(userGrid,informationLabel,idOrEmailField,confirmationButton);
    }

}
