package com.mycompany.myapp;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.user;
import com.company.services.userservice;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class listall extends Form{
        public listall(Form previous) {
        setTitle("List categorie");
        setLayout(BoxLayout.y());
        ArrayList<user> users = userservice.getInstance().getAllCategorie();
        for (user c : users) {
            addElement(c);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(user categorie) {
String labelText = "id: " + categorie.getId()+ "email: " + categorie.getEmail() + ", nom: " + categorie.getNom() + ", prenom: " + categorie.getPrenom()+", password: " + categorie.getPassowrd()+", role: " + categorie.getRole();
Label label = new Label(labelText);

// Créer un conteneur avec un BoxLayout horizontal
Container container = BoxLayout.encloseX(label);

// Créer un conteneur scrollable avec défilement horizontal
Container scrollContainer = new Container();
scrollContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
scrollContainer.setScrollableX(true);
scrollContainer.addComponent(container);

// Ajouter le conteneur scrollable dans la Form
add(scrollContainer);

    }

}