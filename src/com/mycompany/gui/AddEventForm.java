/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.l10n.SimpleDateFormat;
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
import com.mycompany.entities.Colaborationevent;
import com.company.services.ServiceColaborationevent;
import java.util.Date;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;


/**
 *
 * @author Sondes
 */
public class AddEventForm extends Form {
    
  
    public AddEventForm(Form previous) {
        super("newfeed",BoxLayout.y());

        
        
        
      
        
        setTitle("Ajoute Event");
        setLayout(BoxLayout.y());
        
       
        
        ///////////////// Create input fields and button//////////////////////
        TextField tfNomevent = new TextField("", "Nom Event");
        tfNomevent.setUIID("TextFieldBlack");    
        Picker pkDateevent = new Picker();
        pkDateevent.setType(Display.PICKER_TYPE_DATE);
        pkDateevent.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));
        pkDateevent.setDate(new Date(System.currentTimeMillis()));
        pkDateevent.setUIID("PickerBlack");       
        TextField tfAdresseevent = new TextField("", "Adresse Event");
        tfAdresseevent.setUIID("TextFieldBlack");
        TextField tfNbrplacevehicule = new TextField("", "N°places");
        tfNbrplacevehicule.setUIID("TextFieldBlack");
        TextField tfPrixvehiculeevent = new TextField("", "Prix");
        tfPrixvehiculeevent.setUIID("TextFieldBlack");
        Button btnAdd = new Button("Add");
        
        
        ////////////////// Add action listener to button/////////////////////////
        btnAdd.addActionListener(evt -> {
            if (tfNomevent.getText().length() == 0 || pkDateevent.getDate() == null ||
                    tfAdresseevent.getText().length() == 0 || tfNbrplacevehicule.getText().length() == 0 ||
                    tfPrixvehiculeevent.getText().length() == 0) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    // Create new colaborationevent object and add it to the server
                    Date date = pkDateevent.getDate();
                    Colaborationevent colaborationevent = new Colaborationevent(tfNomevent.getText(), date,
                            tfAdresseevent.getText(), Integer.parseInt(tfNbrplacevehicule.getText()),
                            Integer.parseInt(tfPrixvehiculeevent.getText()));

                    if (ServiceColaborationevent.getInstance().addColaborationevent(colaborationevent)) {
                        Dialog.show("Success", "Congrats", new Command("OK"));
                    } else {
                        Dialog.show("Error", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("Error", "Invalid input", new Command("OK"));
                }
            }
        });
        
        // Add input fields and button to the form
        addAll(tfNomevent, pkDateevent, tfAdresseevent, tfNbrplacevehicule, tfPrixvehiculeevent, btnAdd);
        
        
        
        ////////////////////////// Add back button to the toolbar///////////////////////////
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack());
        
          
//        Style backButtonStyle = UIManager.getInstance().getComponentStyle("TitleCommand");
//backButtonStyle.setFgColor(0xff0000); // set the color to red
//
//FontImage backIcon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, backButtonStyle);
//getToolbar().addCommandToLeftBar("", backIcon, e -> previous.showBack());

    }
}
