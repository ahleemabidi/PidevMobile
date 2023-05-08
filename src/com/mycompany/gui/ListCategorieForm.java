/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.Categorie;
import com.company.services.ServiceCategorie;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class ListCategorieForm extends Form{
        public ListCategorieForm(Form previous) {
        setTitle("List categorie");
        setLayout(BoxLayout.y());
        ArrayList<Categorie> categorie = ServiceCategorie.getInstance().getAllCategorie();
        for (Categorie c : categorie) {
            addElement(c);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Categorie categorie) {
String labelText = "idCategorie: " + categorie.getId_categorie()+ "Type: " + categorie.getType() + ", Matricule: " + categorie.getMatricule() + ", Marque: " + categorie.getMarque();
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
