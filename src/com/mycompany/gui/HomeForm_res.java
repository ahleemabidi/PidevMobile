/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Formulairer;

/**
 *
 * @author faten
 */
public class HomeForm_res extends Form{
      public HomeForm_res() {
            setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
       //Button btnAddTask = new Button("Add Formulaire");
       Button btnListTasks = new Button("List Formulaire");
       Button btnDeleteTasks = new Button("Delete Formulaire");
       Button btnUpdateTasks = new Button("Update Formulaire");


        //btnAddTask.addActionListener(e-> new AddFormulaire(this).show());
        btnListTasks.addActionListener(e-> new ListFormulaire(this).show());
        btnDeleteTasks.addActionListener(e-> new DeleteFormulaire(this).show());
         Formulairer formulaire = new Formulairer();
//    formulaire.setMatricule("matricule");
//    formulaire.setMarque("marque");
        btnUpdateTasks.addActionListener(e-> new UpdateFormulaire(formulaire,this).show());


        addAll(btnListTasks,btnDeleteTasks,btnUpdateTasks);
        
        
    }
    
    
}
