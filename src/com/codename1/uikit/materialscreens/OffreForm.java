/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;


import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.entity.Offre;
import com.codename1.uikit.service.ServiceOffre;


import java.io.IOException;
import java.util.Date;


/**
 *
 * @author eya dhaouadi
 */
public class OffreForm extends Form{
          Resources theme;
          ServiceOffre ess = new ServiceOffre();
    
     public OffreForm()  {
                super("Offre", BoxLayout.y());
            theme = UIManager.initFirstTheme("/theme");
        this.getToolbar();
        Label logi = new Label("LES Offre");

    
         this.getToolbar();
        this.getToolbar().addCommandToOverflowMenu("Add Offre", null, ev->{
        Form addEvent = new Form("Add Offre",BoxLayout.y());
            Label AJOUT = new Label("ADD Offre");
            addEvent.add(AJOUT);
            TextField commentaire = new TextField("", "Descriptino", 20, TextArea.TEXT_CURSOR);
            TextField duree = new TextField("", "Duree", 20, TextArea.TEXT_CURSOR);


        Button save = new Button("Ajouter");
        addEvent.add("Descitpion : ").add(commentaire);
        addEvent.add("Duree").add(duree);
        addEvent.add(save);
        
    
        save.addActionListener(l
                                -> {

                            if (duree.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de duree ", "OK", null);

                            }  else if (commentaire.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de Description ", "OK", null);

                            }
                            else {
                           
                                Offre e = new Offre();
                                e.setDescription(commentaire.getText());
                                e.setDure(duree.getText());
                                System.out.println("forms.offre.addItem()"+e);
                                if (ess.addOffre(e) == true) {
                                    Dialog.show("Ajouter offre", "Ajouter offre aves success ", "OK", null);
                                    new OffreForm().show();
                                    
                                    
                                } else {
                                    Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
                                }

                            }

                        }
                        );
 addEvent.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
        
        addEvent.show();
 });
              for(Offre c: ess.getAllOffre()){
           
 
                    try {    
                        this.add(addItem(c));
                    } catch (IOException ex) {
                    }
     
 
        }
     
     
        
           
                            

      
     
     }
        public Container addItem(Offre e) throws IOException{
            
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
        Label Sujet=new Label(e.getDescription());
          Label libelle_sujet = new Label("Duree");
    ;
        Label contenue=new Label(e.getDescription());
        Label libelle_contenue = new Label("Description");
        
     

        Button btn=new Button("Details");
        

        
        
        cn2.add(libelle_sujet).add(Sujet);
        
        cn2.add(libelle_contenue).add(contenue);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
        Label sujetm=new Label(e.getDescription());
       
        Label contenuenm=new Label(e.getDescription());
        Label dfm=new Label(e.getDure());
        
 

     Button Modifier = new Button("Modifier");
     Button Supprimer = new Button("Supprimer");
     
     
     

     Modifier.addActionListener(mod -> 
     
     {
         
         Form fmodifier = new Form("Modifier Consultation", BoxLayout.y());
         
        
         fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
            fmodifier.getToolbar().setUIID("tb");
         Button submit = new Button("Submit");
         AutoCompleteTextField titre2 =  new AutoCompleteTextField(e.getDescription());
          
         titre2.setMinimumElementsShownInPopup(1);
         AutoCompleteTextField lieu2=  new AutoCompleteTextField(e.getDure());
         lieu2.setMinimumElementsShownInPopup(1);
         


      
          Label lib_titre = new Label("Description");
         fmodifier.add(lib_titre).add(titre2);
              Label lib_Lieu = new Label("Vetirinaire");
           fmodifier.add(lib_Lieu).add(lieu2);


         fmodifier.add(submit);
         
          fmodifier.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
         submit.addActionListener(sub ->
                 
         {
             Offre r = new Offre();
             r.setDescription(titre2.getText());
             r.setId(e.getId());
             r.setDure(e.getDure());
             ;
             if ( ess.ModifierOffre(r) == true) {
                 Dialog.show("Modifier Offre", "Offre Modifier aves success ", "OK", null);
                 
                 
             } else {
                 Dialog.show("Erreur", " Erreur d'ajout ", "OK", null);
             }
             new OffreForm().show();
             
         }
                 
         );
         fmodifier.show();
     } 
     );
     
       Supprimer.addActionListener(sup ->  
       
       {
           
           
            if (ess.DeleteOffre(e.getId())) {
                                        Dialog.show("Supprimer Offre", "Offre Supprimer aves success ", "OK", null);
                                        
                                        new OffreForm().show();
                                    } else {
                                        Dialog.show("Erreur", " Erreur de suppression ", "OK", null);
                                    }
           
           
           
         
       
       
       }
       
       );
         
             f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
               Label lib_titre = new Label("Discription");
      
              Label lib_Lieu = new Label("Duree");

      
            f2.add(lib_titre).add(sujetm).add(lib_Lieu).add(contenuenm).add(dfm).add(Modifier).add(Supprimer);
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
   
     
     
}
