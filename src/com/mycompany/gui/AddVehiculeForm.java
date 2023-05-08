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
import com.mycompany.entities.Vehicule;
import com.company.services.ServiceVehicule;
import com.codename1.ui.spinner.Picker;
import java.util.Date;

/**
 *
 * @author asus
 */
public class AddVehiculeForm extends Form {

    public AddVehiculeForm(Form previous) {
        setTitle("Add a new vehicule");
        setLayout(BoxLayout.y());

        TextField tfDisp = new TextField("", "disponibilite");
        TextField tfNum = new TextField("", "numEntretien");
        Picker dateEntretienPicker = new Picker();
        dateEntretienPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        dateEntretienPicker.setDate(new Date());
        TextField tfRes = new TextField("", "resEntretien");

        Button btnValider = new Button("Add Vehicule");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfDisp.getText().isEmpty() || tfNum.getText().isEmpty()|| tfRes.getText().isEmpty()) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else if (!tfDisp.getText().equals("0") && !tfDisp.getText().equals("1")) {
                    Dialog.show("Error", "Invalid Type value. Valid values are 0 or 1", new Command("OK"));
                } else {
                    try {
                        Date dateEntretien = dateEntretienPicker.getDate();
                        Vehicule v = new Vehicule(Integer.parseInt(tfDisp.getText()), Integer.parseInt(tfNum.getText()), dateEntretien, tfRes.getText());
                        if (ServiceVehicule.getInstance().addVehicule(v,dateEntretienPicker)) {
                            Dialog.show("Success", "Vehicule created", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfDisp, tfNum, dateEntretienPicker, tfRes, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
