/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Categorie;
import com.company.services.ServiceCategorie;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author asus
 */
public class AddCategorieForm extends Form {

    public AddCategorieForm(Form previous) {
        setTitle("Add a new categorie");
        setLayout(BoxLayout.y());

        TextField tfType = new TextField("", "Type");
        TextField tfMatricule = new TextField("", "Matricule");
        TextField tfMarque = new TextField("", "Marque");

        Button btnValider = new Button("Add Categorie");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (tfType.getText().isEmpty() || tfMatricule.getText().isEmpty() || tfMarque.getText().isEmpty()) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else if (!tfType.getText().equals("moyenne") && !tfType.getText().equals("haute")) {
                    Dialog.show("Error", "Invalid Type value. Valid values are 'moyenne' or 'haute'", new Command("OK"));
                } else {
                    String matricule = tfMatricule.getText();

                    if (matricule.length() != 10) {
                        Dialog.show("Error", "Invalid Matricule value. Valid format is '123Tun4567'", new Command("OK"));
                    } else {
                        String pays = matricule.substring(3, 6);
                        if (!pays.equals("Tun")) {
                            Dialog.show("Error", "Invalid Matricule value. Valid format is '123Tun4567'", new Command("OK"));
                        } else {
                            try {

                                Categorie c = new Categorie(tfType.getText().toString(), tfMatricule.getText().toString(), tfMarque.getText().toString());
                                if (ServiceCategorie.getInstance().addCategorie(c)) {
                                    Dialog.show("Success", "Categorie created", new Command("OK"));
                                } else {
                                    Dialog.show("ERROR", "Server error", new Command("OK"));
                                }
                            } catch (NumberFormatException e) {
                                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                            }

                        }

                    }
                }}    });

                addAll(tfType, tfMatricule, tfMarque, btnValider);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

            }
        }
