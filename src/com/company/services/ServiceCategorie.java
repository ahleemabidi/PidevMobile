/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Categorie;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServiceCategorie {
        public ArrayList<Categorie> categorie;

    public static ServiceCategorie instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCategorie() {
        req = new ConnectionRequest();
    }

    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }

    public boolean addCategorie(Categorie c) {

        String type = c.getType();
        String matricule = c.getMatricule();
        String marque = c.getMarque();

      
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "ajout/?type=" + type + "&matricule=" + matricule + "&marque=" +marque ;
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
//     public ArrayList<Categorie> parseTasks(String jsonText) {
//        try {
//            categorie = new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String, Object> tasksListJson
//                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//
//            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
//            for (Map<String, Object> obj : list) {
//                Categorie c = new Categorie();
//                float id_categorie = Float.parseFloat(obj.get("id_categorie").toString());
//                c.setId_categorie((int) id_categorie);
//                c.setType(((int) Float.parseFloat(obj.get("type").toString())));
//                if (obj.get("matricule") == null) {
//                   c.setMatricule("null");
//                } else {
//                    c.setMatricule(obj.get("matricule").toString());
//                }
//                   if (obj.get("marque") == null) {
//                   c.setMarque("null");
//                } else {
//                    c.setMarque(obj.get("marque").toString());
//                }
//
//                categorie.add(c);
//            }
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return categorie;
//    }
//
//    public ArrayList<Categorie> getAllCategorie() {
//        String url = Statics.BASE_URL + "afficher";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                categorie = parseTasks(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return categorie;
//    }
       public ArrayList<Categorie> parseTasks(String jsonText) {
        try {
            categorie = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Categorie c = new Categorie();

//                float id_categorie = Float.parseFloat(obj.get("id_categorie").toString());
//                c.setId_categorie((int) id_categorie);
    if (obj.containsKey("idCategorie") && obj.get("idCategorie") instanceof Number) {
        c.setId_categorie((int) Float.parseFloat(obj.get("idCategorie").toString()));
    }
                c.setType(obj.get("type").toString());
                c.setMatricule(obj.get("matricule").toString());
                c.setMarque(obj.get("marque").toString());
               categorie.add(c);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return categorie;
    }
       public ArrayList<Categorie> getAllCategorie() {
        String url = Statics.BASE_URL + "afficher";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categorie = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categorie;
    }
       public boolean deleteCategorie(int idCategorie) {

    String url = Statics.BASE_URL + "delete/" + idCategorie;
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
       
          public boolean updateCategorie(String type, String matricule, String marque,int idCategorie) {

        String url = Statics.BASE_URL + "update/" + idCategorie;
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("type", type);
        req.addArgument("matricule", matricule);
        req.addArgument("marque", marque);
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
