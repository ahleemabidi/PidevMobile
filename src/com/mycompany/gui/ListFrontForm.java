/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Categorie;
import com.company.services.ServiceCategorie;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class ListFrontForm extends Form {

    private Resources theme;
    public ListFrontForm(Form previous)  {
        setTitle("List categorie");
        setLayout(BoxLayout.y());
        ArrayList<Categorie> categorie = ServiceCategorie.getInstance().getAllCategorie();
        for (Categorie c : categorie) {
            addElement(c);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }

    public void addElement(Categorie categorie) {
        
        theme = UIManager.initFirstTheme("/theme");
       String labelText = "Matricule: " + categorie.getMatricule() + ", Marque: " + categorie.getMarque();
        Label label = new Label(labelText);

 String imagePath = "\\img\\" + categorie.getMarque().toLowerCase() + ".png";
        Image urlImage = theme.getImage(categorie.getMarque().toLowerCase() + ".png");
                
 ImageViewer imageView = new ImageViewer(urlImage);
 Button button = new Button("Click me");
    button.addActionListener(e -> {
        // Gérer l'action du bouton ici
    });

    Container box = new Container();
    box.setLayout(BoxLayout.y());
    box.add(imageView);
    box.add(label);
    box.add(button);

    add(box);
    }
}
