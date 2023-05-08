/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Colaborationevent;
import com.mycompany.gui.HomeForm_event;
import com.company.services.ServiceColaborationevent;
import java.util.ArrayList;

/**
 *
 * @author ramzi
 */

public class ListeEvent extends Form{
    public ListeEvent(Resources res){
        setTitle("LISTE EVENT");
        setLayout(BoxLayout.y());
       
         ServiceColaborationevent sp = new ServiceColaborationevent();
      add(new InfiniteProgress());
                Display.getInstance().scheduleBackgroundTask(()-> {
                    
                    Display.getInstance().callSerially(() -> {
                           
                        removeAll();
                      setLayout(BoxLayout.y());
                     Button searchButton = new Button();
            FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, "Search Icon", 4);
            searchButton.setIcon(searchIcon);

        Button backBtn = new Button("back");

            backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //new HomeForm().show();
            }
        });
            add(backBtn);
         
        // back = Image.createImage("/logo.png");
         //back.scaled(1000, 1000);
             add(searchButton);
             Style s = UIManager.getInstance().getComponentStyle("Contenu");
 
        Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        searchButton.addActionListener(e-> { hi.show();});
        Button gui_Button_12 = new Button();
        
        Button backBtn2 = new Button("back");
            backBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();
            }
        });
            hi.add(backBtn2);
        gui_Button_12.setText("search");
        gui_Button_12.setUIID("Label");
        gui_Button_12.setName("Button_12");
        FontImage.setMaterialIcon(gui_Button_12, FontImage.MATERIAL_CIRCLE);
        TextField searchField = new TextField("", "Toolbar Search"); // <1>
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);
        hi.getToolbar().setTitleComponent(searchField);
       // FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        ArrayList<Colaborationevent> list1;
        list1 = ServiceColaborationevent.getInstance().getAllCategorie();
        //hi.add(gui_Button_12);
        searchField.addDataChangeListener((i1, i2) -> { // <2>
            String t = searchField.getText();

            if(t.length() < 1) {
                for(Component cmp : hi.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
            } else {
                t = t.toLowerCase();
                for(Component cmp : hi.getContentPane()) {
                    String val = null;
                    //hi.add(gui_Button_12);
                    if(cmp instanceof Label) {
                        val = ((Label)cmp).getText();
                    } else {
                        if(cmp instanceof TextArea) {
                            val = ((TextArea)cmp).getText();
                        } else {
                            val = (String)cmp.getPropertyValue("text");
                        }
                    }
                    boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
                    cmp.setHidden(!show); // <3>
                    cmp.setVisible(show);
                    //hi.add(gui_Button_12);
                }
            }
            hi.getContentPane().animateLayout(250);
          //  hi.add(gui_Button_12);
        });
        hi.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
            searchField.startEditingAsync(); // <4>
        //    hi.add(gui_Button_12);
        });
       

for(Colaborationevent rec : list1){
      
    
   Label b = new Label();
    b.setText("Nom:" + rec.getNomevent() + " | Adresse:" + rec.getAdresseevent() + " | Prix:" + rec.getPrixvehiculeevent()+"DT");
    hi.add(b);
    
////////////////////////    b.addPointerPressedListener(e -> {
////////////////////////
////////////////////////        if (rec.getMotifDeReclamation()== b.getText())
////////////////////////        {
////////////////////////            new  recSearch(res).show();
////////////////////////        }
////////////////////////      });
}


        Button refreshButton = new Button();
FontImage icon1 = FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("Button"));
refreshButton.setIcon(icon1);

       
       refreshButton.addActionListener(e-> new ListeEvent(res).show());
        add(refreshButton);

                        ArrayList<Colaborationevent> listerec = sp.getAllCategorie();
                        for(Colaborationevent p : listerec)
                        {
                            
                            
                            MultiButton m = new MultiButton();
                            
                            m.setTextLine1("NomEvent:"+p.getNomevent());
                           m.setTextLine2("Adresse:"+p.getAdresseevent());
                           m.setTextLine3("Prix:"+p.getPrixvehiculeevent());
            
                        Container containerImg = new Container();
            
                             int height = Display.getInstance().convertToPixels(10f);
                               int width = Display.getInstance().convertToPixels(10f);

                            
                            add(m);

 






////////////////////////////////////////SHOW .//////////////////////////////////////////////
Button btnShow = new Button();
Image icon = FontImage.createMaterial(FontImage.MATERIAL_EVENT, "ButtonIcon", 5);
btnShow.setIcon(icon);
m.addComponent(BorderLayout.WEST, btnShow);
btnShow.addActionListener(e -> new Showevent(res, list1, CENTER));

                        }
                       
                        
                             revalidate() ;   
                      });
                });
               
 
                        
                    }
}