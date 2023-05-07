package com.company.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.user;
import com.mycompany.myapp.WalkthruForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;



public class userservice {
    public ArrayList<user> users;
    
    private static userservice instance = null;
     boolean resultOK ; 
    
    private ConnectionRequest req;

    
    private userservice() {
        req = new ConnectionRequest();
    }
    
    public static userservice getInstance() {
        if(instance == null) {
            instance = new userservice();
        }
        return instance;
    }
    
    public void signIn(TextField login, TextField password) {
        
        String url = "http://127.0.0.1:8000/loginmobile?email=" + login.getText().toString() + "&password=" + password.getText().toString();
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
                 request.setPost(false);

        
        request.addResponseListener((evt) -> {
            
            JSONParser jsonParser = new JSONParser();
            
            try {
            
                String json = new String(request.getResponseData()) + "";
            
                if(json.equals("Invalid Credentials")) {
                    Dialog.show("Echec d'authentification", "Nom d'utilisateur ou mot de passe incorrect", "OK", null);
                }
                else {
                    
                    System.out.println("data =="+json);
                
                    Map<String,Object> user = jsonParser.parseJSON(new CharArrayReader(json.toCharArray()));
                
                    //float id = Float.parseFloat(user.get("id").toString());
              //  SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                //SessionManager.setPassowrd(user.get("password").toString());
               // SessionManager.setEmail(login.getText().toString());
                //SessionManager.setEmail(user.get("email").toString());
                    
                    
                }
                
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });
    
        NetworkManager.getInstance().addToQueueAndWait(request);
    }   
  public ArrayList<user> parseTasks(String jsonText) {

   users = new ArrayList<>(); // Define the "users" ArrayList here

    try {
        JSONParser j = new JSONParser();
        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
          for (Map<String, Object> obj : list) {
            user u = new user();

            if (obj.containsKey("id") && obj.get("id") instanceof Number) {
                u.setId((int) Float.parseFloat(obj.get("id").toString()));
            }
            u.setNom(obj.get("nom").toString());
            u.setPrenom(obj.get("prenom").toString());
            u.setEmail(obj.get("email").toString());
            u.setPassowrd(obj.get("password").toString());
            u.setRole(obj.get("roles").toString());
            

            users.add(u);
        }

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }

    return users;
}

       public ArrayList<user> getAllCategorie() {
         String url = "http://127.0.0.1:8000/mobile" ;
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
         req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
       public boolean deleteCategorie(int idCategorie) {

String url = "http://127.0.0.1:8000/" + idCategorie + "/deletemobile";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);

       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
}
       
          public boolean updateCategorie(String email, String nom, String prenom,int id) {

       String url = "http://127.0.0.1:8000/editmobile" ;
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        req.addArgument("email", email);
        req.addArgument("nom", nom);
        req.addArgument("prenom", prenom);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
