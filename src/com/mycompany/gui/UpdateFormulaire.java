/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Formulairer;
import com.company.services.ServiceReservation;
import java.util.ArrayList;

/**
 *
 * @author faten
 */
public class UpdateFormulaire extends Form{
                String type = null;
                String categ = null;
       public UpdateFormulaire(Formulairer f, Form previous) {
          setTitle("Update a categorie");
        setLayout(BoxLayout.y());
    ServiceReservation service = ServiceReservation.getInstance();
    ArrayList<Formulairer> formulaires = service.getAllFormulaire();


    for (Formulairer formulaire : formulaires) {
        String labelText = "id: " + formulaire.getId()+ "tlp: " + formulaire.getTlp() + ", Mail: " + formulaire.getMail() + ", Nbr: " + formulaire.getNbr()+ ", type: " + formulaire.getType()+ ", Categ: " + formulaire.getCateg()+ ", depart: " + formulaire.getDepart()+ ", destination: " + formulaire.getDestination()+ ", option: " + formulaire.getOption();
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
    

    final Formulairer currentFormulaire = formulaire; // Variable locale finale pour stocker la référence à la catégorie

    // Gestionnaire d'événements pour le clic sur le bouton "Update"
    updateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Créer le formulaire de mise à jour de la catégorie
            Form updateForm = new Form("Update Formulaire", BoxLayout.y());

            TextField tfNom = new TextField(currentFormulaire.getNom(), "Nom");
            TextField tfNum = new TextField(String.valueOf(currentFormulaire.getTlp()), "Numéro");
            TextField tfMail = new TextField(currentFormulaire.getMail(), "Mail");
            TextField tfNbr = new TextField(String.valueOf(currentFormulaire.getNbr()), "Nombre");
            Label l1 = new Label("Choissez le type du trajet");
           CheckBox cb1 = new CheckBox("Intra_Urbain");
           CheckBox cb2 = new CheckBox("Inter_Urbain");
            Label l2 = new Label("Mentionnez la catégorie souhaitée ");
           CheckBox cb3 = new CheckBox("Haute_Gamme");
           CheckBox cb4 = new CheckBox("Moyenne_Gamme");
            TextField tfDepart = new TextField(currentFormulaire.getDepart(), "depart");
            TextField tfDestination = new TextField(currentFormulaire.getDestination(), "destination");
            TextField tfOption = new TextField(currentFormulaire.getOption(), "Option");
            
         

            Button btnSave = new Button("Save");
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
         if ((tfNum.getText().length()==0)&& (tfMail.getText().length()==0)&& (tfDepart.getText().length()==0)&& (tfDestination.getText().length()==0))
                    Dialog.show("Alert", "Remplir les champs", new Command("OK"));
                else
                {
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
               updateForm. addAll(tfNom,tfNum,tfMail,tfNbr,l1,cb1,cb2,l2,cb3,cb4,tfDepart,tfDestination,tfOption,btnSave);
            updateForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            updateForm.show();
        }

            
    });
}}
 private void showUpdatedCategoriesList(Form previous) {
    Form updatedListForm = new Form("Updated Categories", BoxLayout.y());
        ServiceReservation service = ServiceReservation.getInstance();

    // Charger les catégories mises à jour
    ArrayList<Formulairer> updatedFormulaires = service.getAllFormulaire();
    
    // Afficher les catégories dans le formulaire
    for (Formulairer updatedFormulaire : updatedFormulaires) {
        // Créer le texte de l'étiquette
        String labelText = "id: " + updatedFormulaire.getId()+ "Num: " + updatedFormulaire.getTlp() + ", Mail: " + updatedFormulaire.getMail() + ", Nbr: " + updatedFormulaire.getNbr()+ ", type: " + updatedFormulaire.getType()+ ", Categ: " + updatedFormulaire.getCateg()+ ", depart: " + updatedFormulaire.getDepart()+ ", destination: " + updatedFormulaire.getDestination()+ ", option: " + updatedFormulaire.getOption();

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
