/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.gui.AddEventForm;
import com.mycompany.gui.ListColaborationevent;
import com.mycompany.gui.ListeEvent;
import com.mycompany.gui.modifier;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.entities.Colaborationevent;
import java.util.List;


/**
 *
 * @author Sondes
 */
public class HomeForm_event extends Form {

    private static final char ADD_ICON_NAME = FontImage.MATERIAL_ADD_CIRCLE_OUTLINE;
    private static final char LIST_ICON_NAME = FontImage.MATERIAL_LIST_ALT;
    private static final char UPDATE_ICON_NAME = FontImage.MATERIAL_CREATE;
    private static final char DELETE_ICON_NAME = FontImage.MATERIAL_DELETE;
    private static final String ADD_BUTTON_TEXT = "Ajouter Event ADMIN";
    private static final String UP_BUTTON_TEXT = "Modifier Event ADLIN";
     private static final String Delete_BUTTON_TEXT = "Supprimer Event";
    
     Resources theme = UIManager.initFirstTheme("/theme");


    public HomeForm_event() {
        setTitle("Event option");
        setLayout(BoxLayout.y());

        add(new Label("Choose an Event option "));

        Button addButton = new Button("");
        addButton.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        addButton.setText(ADD_BUTTON_TEXT);
        addButton.addActionListener(e -> new AddEventForm(this).show());

        

        Button listButton = new Button("Afficher Liste Eventt ADMIN");
        listButton.setIcon(FontImage.createMaterial(LIST_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
      listButton.addActionListener(e -> {ListColaborationevent colab = new ListColaborationevent(theme);
        colab.show();
});
        
       Button list2Button = new Button("Afficher Liste Eventt");
       list2Button.setIcon(FontImage.createMaterial(LIST_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
      list2Button.addActionListener(e -> {ListeEvent colab = new ListeEvent(theme);
       colab.show();
});
      // e -> new ListeEvent(theme).show();
     
        
        
        
        Button upButton = new Button("");
        upButton.setIcon(FontImage.createMaterial(UPDATE_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        upButton.setText(UP_BUTTON_TEXT);
        Colaborationevent c = new Colaborationevent();
        upButton.addActionListener(e -> new modifier(c,this).show());
      addAll(addButton, listButton, upButton);
        
                ///////////////////////////button DELETE///////////////////////////

//        Button delButton = new Button("");
//        delButton.setIcon(FontImage.createMaterial(DELETE_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
//        delButton.setText(Delete_BUTTON_TEXT);
//        delButton.addActionListener(e -> new DeleteEvent(this).show());
        
//////////////////////2eme Classe////////////////////////
//        add(new Label("Choose an Reservation option "));
//        Button listrButton = new Button("");
//        listrButton.setIcon(FontImage.createMaterial(LIST_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
//        listrButton.setText(LISTR_BUTTON_TEXT);
//        listrButton.addActionListener(e -> new ListeReservationevent(this).show());
        
        //addAll(addButton, listButton, list2Button, upButton, delButton);
    }
   
}





     


