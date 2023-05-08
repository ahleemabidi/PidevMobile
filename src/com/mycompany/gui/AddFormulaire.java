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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Formulairer;
//import com.mycompany.gui.Paiement;
import com.company.services.ServiceReservation;


/**
 *
 * @author faten
 */
public class AddFormulaire extends Form{
                String type = null;
                String categ = null;
                
       public AddFormulaire(Form previous) {
        setTitle("Add a new categorie");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Votre Nom");
        TextField tfNum = new TextField("","Votre numéro");
        TextField tfMail = new TextField("","Votre adresse mail");
        TextField tfNbr = new TextField("","Le nombre de personne");
        Label l1 = new Label("Choissez le type du trajet");
           CheckBox cb1 = new CheckBox("Intra_Urbain");
           CheckBox cb2 = new CheckBox("Inter_Urbain");
        Label l2 = new Label("Mentionnez la catégorie souhaitée ");
           CheckBox cb3 = new CheckBox("Haute_Gamme");
           CheckBox cb4 = new CheckBox("Moyenne_Gamme");
        TextField tfDepart = new TextField("","Lieu de départ");
        TextField tfDestination = new TextField("","Votre destination");
        TextField tfOption = new TextField("","Choisissez une option");

        Button btnValider = new Button("Valider");
        
//
//
//
//btnValider.addActionListener((e) -> {
//    String email = tfMail.getText();
//    if (email.isEmpty()) {
//        Dialog.show("Error", "Please enter an email address", "OK", null);
//    } else {
//        // Validate email address using regular expression
//        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
//        if (!email.matches(emailRegex)) {
//            Dialog.show("Error", "Invalid email address", "OK", null);
//        } else {
//            // Email is valid, proceed with submission
//        }
//    }
//});

        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if ((tfNum.getText().length()==0)&& (tfMail.getText().length()==0)&& (tfDepart.getText().length()==0)&& (tfDestination.getText().length()==0))
                    Dialog.show("Alert", "Remplir les champs", new Command("OK"));
                else
                {
                    
                    
                    String email = tfMail.getText();
                    
                    
                    
                    try {
                        
                        if(cb1.isSelected()){
                            type="Intra_Urbain";}
                        else type="Inter_Urbain";
                        
                        if(cb3.isSelected()){
                            categ="Haute_Gamme";}
                        else type="Moyenne_Gamme";
                       
                        Formulairer f = new Formulairer(tfNom.getText().toString(),Integer.parseInt(tfNbr.getText()), tfMail.getText().toString(),Integer.parseInt(tfNbr.getText()), type ,categ ,tfDepart.getText().toString(),tfDestination.getText().toString(),tfOption.getText().toString() );
                        if( ServiceReservation.getInstance().addFormulaire(f))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                         
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }

        });
        
         Button btnConfirmer = new Button("Payer");
       // btnConfirmer.addActionListener(e -> new Paiement(previous)) ;
        
      
        
        
   
        
        addAll(tfNom,tfNum,tfMail,tfNbr,l1,cb1,cb2,l2,cb3,cb4,tfDepart,tfDestination,tfOption,btnValider,btnConfirmer);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
