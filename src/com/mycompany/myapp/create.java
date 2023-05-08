package com.mycompany.myapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;

//enum PasswordStrength {
//    INVALID,
//    WEAK,
//    MEDIUM,
//    STRONG
//}

public class create extends Form {

   

    private TextField emailField;
    private TextField nomField;
    private TextField prenomField;
    private TextField passwordField;
    
//    //Minimum 4 characters, at least one letter and one number
//    private final String WEAK_REGEX = "([:alnum:]){4,}";//"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$";
//    //Minimum eight characters, at least one letter, one number and one special character
//    private final String MIDIUM_REGEX = "^([A-Za-z]) ^([0-9])(([A-Za-z])|([0-9])){4,}";//"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
//    //Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character
//    private final String STRONG_REGEX = "([:digit:]+)([:alpha:]+)[:alnum:]{4,}";//"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
//
//    private Boolean validatePassword(String password, String regex) {
//        RE r = new RE(regex);
//        return r.match(password);
//    }
//
//    public PasswordStrength CheckPasswordStrength(String password) {
//        PasswordStrength result = PasswordStrength.INVALID;
//        if (password != null && password != "") {
//            Boolean isWeak = validatePassword(password, WEAK_REGEX);
//            Boolean isMid = validatePassword(password, MIDIUM_REGEX);
//            Boolean isStrong = validatePassword(password, STRONG_REGEX);
//
//            Log.p(" " + password + ": " + isWeak + " " + isMid + " " + isStrong, Log.DEBUG);
//            if (isWeak) {
//                result = PasswordStrength.WEAK;
//            }
//
//            if (isMid) {
//                result = PasswordStrength.MEDIUM;
//            }
//
//            if (isStrong) {
//                result = PasswordStrength.STRONG;
//            }
//        }
//        return result;
//    }

    public void showRegistrationForm() {

        Form registrationForm = new Form("Inscription");

        TextField nomField = new TextField("", "NOM", 20, TextField.ANY);
        TextField prenomField = new TextField("", "Prenom", 20, TextField.ANY);
        TextField emailField = new TextField("", "prenom", 50, TextField.EMAILADDR);
        TextField passwordField = new TextField("", "password", 20, TextField.PASSWORD);
        TextField confirmPasswordField = new TextField("", "Confirmer le mot de passe", 20, TextField.PASSWORD);

        passwordField.addDataChangedListener((type, index) -> {
//            CheckPasswordStrength(passwordField.getText());
//            {
//
//            }
        });

        Button inscriptionButton = new Button("S'inscrire");
        inscriptionButton.addActionListener(evt -> {
            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                //if (CheckPasswordStrength(confirmPasswordField.getText()) != PasswordStrength.INVALID) {
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
//                } else {
//                    Dialog.show("Erreur", "Le mot de passe est invalid", "OK", null);
//                }
            } else {
                Dialog.show("Erreur", "Les mots de passe ne correspondent pas", "OK", null);
            }
        });

        registrationForm.addAll(nomField, prenomField, emailField, passwordField, confirmPasswordField, inscriptionButton);
        //return registrationForm ; 
        registrationForm.show();
    }
}
