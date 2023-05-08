/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;

import com.codename1.ui.Form;
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
import com.company.services.ServiceColaborationevent;
import java.util.ArrayList;




/**
 *
 * @author ramzi
 */
public class Showevent extends Form {
    private Resources theme;
    private int IdEvent; // Ajouter un attribut pour stocker l'ID de l'événement

    public Showevent(Resources res, ArrayList<Colaborationevent> eventsList, int eventId) {
        this.IdEvent = eventId; // Initialiser l'attribut avec l'ID de l'événement
        setTitle("ADMINISTRATION EVENT");
        setLayout(BoxLayout.y());

        // Récupérer l'événement spécifique à partir de l'ID et l'afficher
        Colaborationevent event = null;
        for (Colaborationevent e : eventsList) {
            if (e.getIdevent() == IdEvent) {
                event = e;
                break;
            }
        }
        if (event == null) {
            // Handle the case where the event with the given ID is not found
            return;
        }

        Label nameLabel = new Label("Nom: " + event.getNomevent());
        Label addressLabel = new Label("Adresse: " + event.getAdresseevent());
        Label priceLabel = new Label("Prix: " + event.getPrixvehiculeevent() + " DT");
        addAll(nameLabel, addressLabel, priceLabel);

        // Ajouter un bouton pour revenir à la liste des événements
        Button backButton = new Button("Retour");
        backButton.addActionListener(e -> new ListColaborationevent(res).show());
        add(backButton);
    }
}

