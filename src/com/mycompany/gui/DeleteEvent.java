package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Colaborationevent;
import com.company.services.ServiceColaborationevent;
import java.util.ArrayList;

public class DeleteEvent extends Form {

    public DeleteEvent(Form previous) {
        setTitle("Delete an Event");
        setLayout(BoxLayout.y());
        ServiceColaborationevent service = ServiceColaborationevent.getInstance();
        ArrayList<Colaborationevent> colabs = service.getAllCategorie();

        for (Colaborationevent colab : colabs) {
            String labelText = "idEvent: " + colab.getIdevent() + ", Nom: " + colab.getNomevent() + ", Date: " + colab.getDateevent() + ", Adresse: " + colab.getAdresseevent() + ", Places: " + colab.getNbrplacevehicule() + ", Prix: " + colab.getPrixvehiculeevent();
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

            // Bouton de suppression pour chaque catégorie
            Button deleteButton = new Button("Delete");
            add(deleteButton);

            // Gestionnaire d'événements pour le clic sur le bouton de suppression
            deleteButton.addActionListener(evt -> {
                // Supprimer la catégorie
                boolean deleted = service.deleteEvent(colab.getIdevent());

                if (deleted) {
                    // Suppression réussie
                    Dialog.show("Success", "Event deleted successfully", "OK", null);

                    // Mettre à jour l'affichage en supprimant le label et le bouton de suppression
                    removeComponent(container);
                    removeComponent(deleteButton);
                } else {
                    // Échec de la suppression
                    Dialog.show("Error", "Failed to delete Event", "OK", null);
                }
            });
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
