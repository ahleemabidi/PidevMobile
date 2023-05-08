/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.user;
import com.company.services.userservice;
import java.util.ArrayList;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.Baseform;

/**
 *
 * @author asus
 */
public class delete extends Baseform {

    private Resources res;
    private ArrayList<user> users;
    Container list;

    public delete(Form previous, Resources res) {
        super("Delete a user", new BorderLayout());
        this.res = res;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        userservice service = userservice.getInstance();
        users = service.getAllCategorie();
        list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        this.add(CENTER, list);
        showUserList();
    }

    void showUserList() {
        for (user u : users) {
            list.add(CreateListElement(u));
        }
        list.repaint();
        list.revalidateWithAnimationSafety();
    }

    Container CreateListElement(user u) {
        Container listElement = new Container(new BorderLayout());

        Label labelId = new Label("id: " + u.getId());
        Label labelEmail = new Label("email: " + u.getEmail());
        Label labelNom = new Label("nom: " + u.getNom());
        Label labelPreNom = new Label("Prenom: " + u.getPrenom());
        Label labelRole = new Label("role: " + u.getRole());
        Container dataCont = BoxLayout.encloseY(labelId, labelEmail, labelNom, labelPreNom, labelRole);

        Image delIcon = res.getImage("delete.png");
        Button delIconLabel = new Button(delIcon, "ProfilePic");
        // Gestionnaire d'événements pour le clic sur le bouton de suppression
        delIconLabel.addActionListener(evt -> {
            // Supprimer la catégorie
            userservice service = userservice.getInstance();
            boolean deleted = service.deleteCategorie(u.getId());

            if (deleted) {
                // Suppression réussie
                Dialog.show("Success", "user deleted successfully", "OK", null);
                users.remove(u);
                // Mettre à jour l'affichage en supprimant le label et le bouton de suppression
                list.removeComponent(listElement);
                list.repaint();
                list.revalidateWithAnimationSafety();
            } else {
                // Échec de la suppression
                Dialog.show("Error", "Failed to delete user", "OK", null);
            }
        });
        Component separator = createLineSeparator(0x4bc2ff);

        listElement.add(BorderLayout.EAST, (delIconLabel));
        listElement.add(BorderLayout.CENTER, dataCont);
        listElement.add(BorderLayout.SOUTH, separator);

        return listElement;
    }
}
