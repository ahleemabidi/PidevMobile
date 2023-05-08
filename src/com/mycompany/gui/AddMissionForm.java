/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Mission;
import com.mycompany.entities.Vehicule;
import com.company.services.ServiceMission;
import com.company.services.ServiceVehicule;
import java.util.Date;

/**
 *
 * @author asus
 */
public class AddMissionForm extends Form{
     public AddMissionForm(Form previous) {
        setTitle("Add a new mission");
        setLayout(BoxLayout.y());

        TextField tfMat = new TextField("", "matricule");
        TextField tfDesc = new TextField("", "description");
        Picker heureDebutPicker = new Picker();
        heureDebutPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        heureDebutPicker.setDate(new Date());
        Picker heureFinPicker = new Picker();
        heureFinPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        heureFinPicker.setDate(new Date());

        Button btnValider = new Button("Add Mission");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfMat.getText().isEmpty() || tfDesc.getText().isEmpty()) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else if (!tfDesc.getText().equals("Intra-Urbain") && !tfDesc.getText().equals("Inter-Urbain")) {
                    Dialog.show("Error", "Invalid Type value. Valid values are Intra-Urbain or Inter-Urbain", new Command("OK"));
                } else if (tfMat.getText().length() != 10) {
                        Dialog.show("Error", "Invalid Matricule value. Valid format is '123Tun4567'", new Command("OK"));
                    } else {
                        String pays = tfMat.getText().substring(3, 6);
                        if (!pays.equals("Tun")) {
                            Dialog.show("Error", "Invalid Matricule value. Valid format is '123Tun4567'", new Command("OK"));
                        } else  {
                    try {
                        Date heureDebut = heureDebutPicker.getDate();
                        Date heureFin = heureFinPicker.getDate();

                        Mission m = new Mission(tfMat.getText(),tfDesc.getText(),heureDebut,heureFin );
                        if (ServiceMission.getInstance().addMission(m,heureDebutPicker,heureFinPicker)) {
                            Dialog.show("Success", "Mission created", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }}

            }
        });

        addAll(tfMat, tfDesc, heureDebutPicker, heureFinPicker, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
}
