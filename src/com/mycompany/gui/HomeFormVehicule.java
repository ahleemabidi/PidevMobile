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
import com.mycompany.entities.Vehicule;

/**
 *
 * @author asus
 */
public class HomeFormVehicule extends Form{
     public HomeFormVehicule() {
        
        setTitle("HomeVehicule");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Vehicule");
       Button btnListTasks = new Button("List Vehicule");
               Button btnDeleteTasks = new Button("Delete Vehicule");
                              Button btnUpdateTasks = new Button("Update Vehicule");


     //   btnAddTask.addActionListener(e-> new AddVehiculeForm(this).show());
       btnListTasks.addActionListener(e-> new ListVehiculeForm(this).show());
       //  btnDeleteTasks.addActionListener(e-> new DeleteVehiculeForm(this).show());
//          Vehicule vehicule = new Vehicule();
//    vehicule.setDisponibilite("disponibilite");
//    vehicule.setNum_entretien("numEntretien");
//    vehicule.setDate_entretien("dateEntretien");
//        vehicule.setRes_entretien("resEntretien");
//
//        btnUpdateTasks.addActionListener(e-> new UpdateCategorieForm(vehicule,this).show());


        addAll(btnAddTask,btnListTasks,btnDeleteTasks,btnUpdateTasks);
        
        
    }
}
