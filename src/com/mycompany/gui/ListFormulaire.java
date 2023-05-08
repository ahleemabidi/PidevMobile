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
import com.mycompany.entities.Formulairer;
import com.company.services.ServiceReservation;
import java.util.ArrayList;

/**
 *
 * @author faten
 */
public class ListFormulaire extends Form{
        public ListFormulaire(Form previous) {
        setTitle("List formulaire");
        setLayout(BoxLayout.y());
        ArrayList<Formulairer> formulaire = ServiceReservation.getInstance().getAllFormulaire();
        for (Formulairer f : formulaire) {
            addElement(f);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Formulairer formulaire) {
String labelText = "id: " + formulaire.getId()+ "Num: " + formulaire.getTlp() + ", Mail: " + formulaire.getMail() + ", Nbr: " + formulaire.getNbr()+ ", type: " + formulaire.getType()+ ", Categ: " + formulaire.getCateg()+ ", depart: " + formulaire.getDepart()+ ", destination: " + formulaire.getDestination()+ ", option: " + formulaire.getOption();
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
