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
import com.mycompany.entities.Categorie;
import com.mycompany.entities.Mission;
import com.mycompany.entities.Vehicule;
/**
 *
 * @author asus
 */
public class HomeForm extends Form{
      public HomeForm() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Categorie");
       Button btnListTasks = new Button("List Categorie");
               Button btnDeleteTasks = new Button("Delete Categorie");
                              Button btnUpdateTasks = new Button("Update Categorie");


        btnAddTask.addActionListener(e-> new AddCategorieForm(this).show());
       btnListTasks.addActionListener(e-> new ListCategorieForm(this).show());
         btnDeleteTasks.addActionListener(e-> new DeleteCategorieForm(this).show());
          Categorie categorie = new Categorie();
    categorie.setType("type");
    categorie.setMatricule("matricule");
    categorie.setMarque("marque");
        btnUpdateTasks.addActionListener(e-> new UpdateCategorieForm(categorie,this).show());


        addAll(btnAddTask,btnListTasks,btnDeleteTasks,btnUpdateTasks);
           
        setTitle("HomeVehicule");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose another option"));
        Button btnAddV = new Button("Add Vehicule");
       Button btnListV = new Button("List Vehicule");
               Button btnDeleteV = new Button("Delete Vehicule");
                              Button btnUpdateV = new Button("Update Vehicule");


        btnAddV.addActionListener(e-> new AddVehiculeForm(this).show());
       btnListV.addActionListener(e-> new ListVehiculeForm(this).show());
         btnDeleteV.addActionListener(e-> new DeleteVehiculeForm(this).show());
          Vehicule vehicule = new Vehicule();
    

        btnUpdateV.addActionListener(e-> new UpdateVehiculeForm(vehicule,this).show());


        addAll(btnAddV,btnListV,btnDeleteV,btnUpdateV);
        
          setTitle("HomeMission");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose another option"));
        Button btnAddM= new Button("Add Mission");
       Button btnListM = new Button("List Mission");
               Button btnDeleteM = new Button("Delete Mission");
                 Button btnUpdateM = new Button("Update Mission");


        btnAddM.addActionListener(e-> new AddMissionForm(this).show());
       btnListM.addActionListener(e-> new ListMissionForm(this).show());
         btnDeleteM.addActionListener(e-> new DeleteMissionForm(this).show());
    
          Mission mission = new Mission();

        btnUpdateM.addActionListener(e-> new UpdateMissionForm(mission,this).show());


        addAll(btnAddM,btnListM,btnDeleteM,btnUpdateM);
        
           

          setTitle("HomeUser");
        setLayout(BoxLayout.y());
        
//        add(new Label("Choose another option"));
//            Button btnListFront = new Button("List Front");
//                       btnListFront.addActionListener(e-> new ListFrontForm(this).show());
//        addAll(btnListFront);

    }
    
}
