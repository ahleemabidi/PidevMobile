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
import com.mycompany.entities.Mission;
import com.mycompany.entities.Vehicule;
import com.company.services.ServiceMission;
import com.company.services.ServiceVehicule;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class ListMissionForm extends Form{
       public ListMissionForm(Form previous) {
        setTitle("List MISSION");
        setLayout(BoxLayout.y());
        ArrayList<Mission> mission = ServiceMission.getInstance().getAllMission();
        for (Mission m : mission) {
            addElement(m);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Mission mission) {
String labelText = "id: " + mission.getId_mission()+ "Matricule: " + mission.getMatricule() + ", Description: " + mission.getDescription() + ", heureDebut: " + mission.getHeure_debut()+ ", heureFin: " + mission.getHeure_fin();
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
