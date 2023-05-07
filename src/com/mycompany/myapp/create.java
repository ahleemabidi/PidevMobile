package com.mycompany.myapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.RequestBuilder;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

//import com.codename1.io.NetworkRequest;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.plaf.Style;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class create extends Form {
    private TextField emailField;
    private TextField nomField;
    private TextField prenomField;
    private TextField passwordField;

   public void showRegistrationForm() {
       
    Form registrationForm = new Form("Inscription");

TextField nomField = new TextField("", "NOM", 20, TextField.ANY);
TextField prenomField = new TextField("", "Prenom", 20, TextField.ANY);
TextField emailField = new TextField("", "prenom", 50, TextField.EMAILADDR);
TextField passwordField = new TextField("", "password", 20, TextField.PASSWORD);
TextField confirmPasswordField = new TextField("", "Confirmer le mot de passe", 20, TextField.PASSWORD);

        

    Button inscriptionButton = new Button("S'inscrire");
    inscriptionButton.addActionListener(evt -> {
        if (passwordField.getText().equals(confirmPasswordField.getText())) {
            // Appeler le web service pour ajouter l'utilisateur
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl("http://127.0.0.1:8000/registermobile");
            request.setPost(true);
            request.addArgument("nom", nomField.getText());
            request.addArgument("prenom", prenomField.getText());
            request.addArgument("email", emailField.getText());
            request.addArgument("password", passwordField.getText());
            request.addResponseListener(evt2 -> {
                // Afficher la réponse du serveur
                String response = new String(request.getResponseData());
                Dialog.show("Confirmation", response, "OK", null);
            });
            NetworkManager.getInstance().addToQueue(request);
        } else {
            Dialog.show("Erreur", "Les mots de passe ne correspondent pas", "OK", null);
        }
    });

    registrationForm.addAll(nomField, prenomField, emailField, passwordField, confirmPasswordField, inscriptionButton);
    //return registrationForm ; 
    registrationForm.show() ; 
    
}

}
