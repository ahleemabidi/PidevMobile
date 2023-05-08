/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Mission;
import com.mycompany.entities.Vehicule;
import com.company.services.ServiceMission;
import com.company.services.ServiceVehicule;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author asus
 */
public class UpdateMissionForm extends Form {

    public UpdateMissionForm(Mission m, Form previous) {
        setTitle("Update a mission");
        setLayout(BoxLayout.y());
        ServiceMission service = ServiceMission.getInstance();
        ArrayList<Mission> missions = service.getAllMission();

        for (Mission mission : missions) {
            String labelText = "idMission: " + mission.getId_mission() + "matricule: " + mission.getMatricule() + ", Description: " + mission.getDescription() + ", heureDebut: " + mission.getHeure_debut() + ", heureFin: " + mission.getHeure_fin();
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

            // Bouton de mise à jour pour chaque catégorie
            Button updateButton = new Button("Update");
            add(updateButton);

            final Mission currentMission = mission; // Variable locale finale pour stocker la référence à la catégorie

            // Gestionnaire d'événements pour le clic sur le bouton "Update"
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    // Créer le formulaire de mise à jour de la catégorie
                    Form updateForm = new Form("Update Mission", BoxLayout.y());

                    TextField matricule = new TextField(currentMission.getMatricule(), "matricule");
                    TextField description = new TextField(currentMission.getDescription(), "description");

// Utilisez le Picker pour sélectionner la date d'entretien
                    Picker heureDebutPicker = new Picker();
                    heureDebutPicker.setType(Display.PICKER_TYPE_DATE);
                    heureDebutPicker.setDate(currentMission.getHeure_debut());
// Utilisez le Picker pour sélectionner la date d'entretien
                    Picker heureFinPicker = new Picker();
                    heureFinPicker.setType(Display.PICKER_TYPE_DATE);
                    heureFinPicker.setDate(currentMission.getHeure_fin());

                    Button btnSave = new Button("Save");
                    btnSave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (matricule.getText().isEmpty() || description.getText().isEmpty()) {
                                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                            } else if (!description.getText().equals("Intra-Urbain") && !description.getText().equals("Inter-Urbain")) {
                                Dialog.show("Error", "Invalid Type value. Valid values are Intra-Urbain or Inter-Urbain", new Command("OK"));
                            } else if (matricule.getText().length() != 10) {
                                Dialog.show("Error", "Invalid Matricule value. Valid format is '123Tun4567'", new Command("OK"));
                            } else {
                                String pays = matricule.getText().substring(3, 6);
                                if (!pays.equals("Tun")) {
                                    Dialog.show("Error", "Invalid Matricule value. Valid format is '123Tun4567'", new Command("OK"));
                                } else {
                                    boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to save the changes?", "Yes", "No");

                                    if (confirmed) {

                                        Date heureDebut = heureDebutPicker.getDate();
                                        Date heureFin = heureFinPicker.getDate();

                                        if (service.updateMission(matricule.getText(), description.getText(), heureDebut, heureFin, currentMission.getId_mission())) {
                                            Dialog.show("Success", "Mission updated", new Command("OK"));
                                            showUpdatedMissionList(previous);
                                        } else {
                                            Dialog.show("ERROR", "Server error", new Command("OK"));
                                        }
                                    }
                                }
                            }
                        }
                    });
                    updateForm.addAll(matricule, description, heureDebutPicker, heureFinPicker, btnSave);
                    updateForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
                    updateForm.show();
                }
            });
        }
    }

    private void showUpdatedMissionList(Form previous) {
        Form updatedListForm = new Form("Updated Mission", BoxLayout.y());
        ServiceMission service = ServiceMission.getInstance();

        // Charger les catégories mises à jour
        ArrayList<Mission> updatedMissions = service.getAllMission();

        // Afficher les catégories dans le formulaire
        for (Mission updatedMission : updatedMissions) {
            // Créer le texte de l'étiquette
            String labelText = "idMission: " + updatedMission.getId_mission() + "matricule: " + updatedMission.getMatricule() + ", Description: " + updatedMission.getDescription() + ", heureDebut: " + updatedMission.getHeure_debut() + ", heureFin: " + updatedMission.getHeure_fin();
            Label label = new Label(labelText);
            // Créer un conteneur avec un BoxLayout horizontal
            Container container = BoxLayout.encloseX(label);

            // Créer un conteneur scrollable avec défilement horizontal
            Container scrollContainer = new Container();
            scrollContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
            scrollContainer.setScrollableX(true);
            scrollContainer.addComponent(container);

            // Ajouter le conteneur scrollable dans le formulaire
            updatedListForm.add(scrollContainer);
        }

        // Bouton de retour vers le formulaire précédent
        Button backButton = new Button("Back");
        backButton.addActionListener(e -> previous.showBack());
        updatedListForm.add(backButton);

        updatedListForm.show();

    }
}
