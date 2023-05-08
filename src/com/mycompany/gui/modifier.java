/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import com.mycompany.entities.Colaborationevent;
import com.company.services.ServiceColaborationevent;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
//public class modifier extends Form {
//    public modifier(Form previous) {
//        setTitle("Modifier Event");
//        setLayout(BoxLayout.y());
//        
//     TextField ID = new TextField("","IdEvent");
//        TextField tnom = new TextField("","NomEvent");
//        TextField tdate = new TextField("","DateEvent");
//        TextField tadresse = new TextField("","AdresseEvent");
//        TextField tnbr = new TextField("","NbrPlaceVehicule");
//        TextField tprix = new TextField("","PrixVehiculeEven");
//        
//        Button btnValider = new Button("Update ");
//          
//         
//        btnValider.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                if ((tnom.getText().length()==0))
//                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
//                else
//                {
//                   try {
//    int id = Integer.parseInt(ID.getText().toString());
//    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//    java.util.Date date = format.parse(tdate.getText().toString());
//    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//    Colaborationevent t = new Colaborationevent(id, tnom.getText().toString(), sqlDate, tadresse.getText().toString(), Integer.parseInt(tnbr.getText().toString()), Integer.parseInt(tprix.getText().toString()));
//    if (ServiceColaborationevent.getInstance().modifierColaborationevent(t)) {
//        Dialog.show("Success", "Congrats!!", new Command("OK"));
//    } else {
//        Dialog.show("ERROR", "Server error", new Command("OK"));
//    }
//} catch (NumberFormatException e) {
//    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
//} catch (ParseException e) {
//    Dialog.show("ERROR", "Invalid date format", new Command("OK"));
//}     
//                }
//                
//                
//           }
//        });
//        
//        addAll(ID,tnom,tdate,tadresse,tnbr,tprix,btnValider);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
//             
//    }
//    
//}

public class modifier extends Form{
       public modifier(Colaborationevent c, Form previous) {
          setTitle("Update a Event");
        setLayout(BoxLayout.y());
    ServiceColaborationevent service = ServiceColaborationevent.getInstance();
    ArrayList<Colaborationevent> colabs = service.getAllCategorie();


    for (Colaborationevent colab : colabs) {
     String labelText = "ID:" + colab.getIdevent()+"|"+"Nom: " + colab.getNomevent() +"|"+"Adresseevent: " + colab.getAdresseevent() +"|"+ "N°Place: " + colab.getNbrplacevehicule()+"|"+"Prix:" + colab.getPrixvehiculeevent();
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
    

    final Colaborationevent currentColaborationevent = colab; // Variable locale finale pour stocker la référence à la catégorie

    // Gestionnaire d'événements pour le clic sur le bouton "Update"
    updateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Créer le formulaire de mise à jour de la catégorie
            Form updateForm = new Form("Update Event", BoxLayout.y());

                        
            TextField nomevent = new TextField(currentColaborationevent.getNomevent(), "nomevent");
             nomevent.setUIID("TextFieldBlack");
              Picker pkDateevent = new Picker();
                pkDateevent.setType(Display.PICKER_TYPE_DATE);
                pkDateevent.setFormatter(new com.codename1.l10n.SimpleDateFormat("yyyy-MM-dd"));
                pkDateevent.setDate(new Date(System.currentTimeMillis()));
                          pkDateevent.setUIID("TextFieldBlack");
            TextField adresse = new TextField(currentColaborationevent.getAdresseevent(), "adresse");
                         adresse.setUIID("TextFieldBlack");
            TextField nbrplace = new TextField(String.valueOf(currentColaborationevent.getNbrplacevehicule()), "nbrplace");
                         nbrplace.setUIID("TextFieldBlack");
            TextField prix = new TextField(String.valueOf(currentColaborationevent.getPrixvehiculeevent()), "prix");
                         prix.setUIID("TextFieldBlack");


    Button btnSave = new Button("Save");
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (nomevent.getText().isEmpty() || adresse.getText().isEmpty() || nbrplace.getText().isEmpty() || prix.getText().isEmpty()) {
                        Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    } else {
                        Date date = pkDateevent.getDate();
                        if (service.updateCategorie(nomevent.getText(),date ,adresse.getText(), nbrplace.getText(),prix.getText(), currentColaborationevent.getIdevent())) {
                            Dialog.show("Success", "Event updated", new Command("OK"));
                                            showUpdatedCategoriesList(previous);
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    }
                }
            });
               updateForm.addAll(nomevent,pkDateevent ,adresse, nbrplace,prix,btnSave);
            updateForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            updateForm.show();
        }
    });
}}
 private void showUpdatedCategoriesList(Form previous) {
    Form updatedListForm = new Form("Updated Categories", BoxLayout.y());
        ServiceColaborationevent service = ServiceColaborationevent.getInstance();

    // Charger les catégories mises à jour
    ArrayList<Colaborationevent> updatedCategories = service.getAllCategorie();
    
    // Afficher les catégories dans le formulaire
    for (Colaborationevent updatedCategorie : updatedCategories) {
        // Créer le texte de l'étiquette
        String labelText = "idEvent: " + updatedCategorie.getIdevent() + "Nomevent: " + updatedCategorie.getNomevent() + ", Date: " + updatedCategorie.getDateevent()+ ", Adresse: " + updatedCategorie.getAdresseevent()+ ", NbrPlaces: " + updatedCategorie.getNbrplacevehicule()+ ", Prix: " + updatedCategorie.getPrixvehiculeevent();
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
    
    
    
}}



