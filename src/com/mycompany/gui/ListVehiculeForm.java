/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Categorie;
import com.mycompany.entities.Vehicule;
import com.company.services.ServiceCategorie;
import com.company.services.ServiceVehicule;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class ListVehiculeForm extends Form {
      public ListVehiculeForm(Form previous) {
        setTitle("List vehicule");
        setLayout(BoxLayout.y());
        ArrayList<Vehicule> vehicule = ServiceVehicule.getInstance().getAllVehicule();
        for (Vehicule v : vehicule) {
            addElement(v);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Vehicule vehicule) {
String labelText = "id: " + vehicule.getId()+ "Disponibilite: " + vehicule.getDisponibilite() + ", NumEntretien: " + vehicule.getNum_entretien() + ", dateEntretien: " + vehicule.getDate_entretien()+ ", resEntretien: " + vehicule.getRes_entretien();
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
