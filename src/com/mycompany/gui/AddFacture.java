/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
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
//import com.mycompany.entities.Facturer;
import com.company.services.ServiceReservation;
import java.util.Date;

/**
 *
 * @author faten
 */
public class AddFacture extends Form{
//    String statut = null;
//
//       public AddFacture(Form previous) {
//        setTitle("Add a new categorie");
//        setLayout(BoxLayout.y());
//        
//        Picker dateFacturePicker = new Picker();
//        dateFacturePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
//        dateFacturePicker.setDate(new Date());
//        Label l1 = new Label("Votre statut");
//           CheckBox cb1 = new CheckBox("Payer en ligne");
//           CheckBox cb2 = new CheckBox("Payer en espèce");
//        TextField tfNote = new TextField("","Notes");
//        
//
//        Button btnValider = new Button("Valider");
//        
//
//
//        
//        btnValider.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                
//                    try {
//                        Date dateFacture = dateFacturePicker.getDate();
//                        Facturer f = new Facturer(Integer.parseInt(tfNote.getText()), Integer.parseInt(tfNum.getText()), dateFacture);
//                        if (ServiceReservation.getInstance().addFacture(f,dateFacturePicker)) {
//                            Dialog.show("Success", "Vehicule created", new Command("OK"));
//                        } else {
//                            Dialog.show("ERROR", "Server error", new Command("OK"));
//                        }
//                    } catch (NumberFormatException e) {
//                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
//                    }
//
//                }
//
//            }
//        });
//
//        addAll(tfDisp, tfNum, dateEntretienPicker, tfRes, btnValider);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
//
//    }

    
    
}
