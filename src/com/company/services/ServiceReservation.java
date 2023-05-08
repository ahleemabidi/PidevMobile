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
import com.mycompany.entities.Formulairer;
import com.mycompany.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author faten
 */
public class ServiceReservation {
      public ArrayList<Formulairer> formulaire;

    public static ServiceReservation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReservation() {
        req = new ConnectionRequest();
    }

    public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }

    public boolean addFormulaire(Formulairer f) {

        String nom = f.getNom();
        int tlp = f.getTlp();
        String mail = f.getMail();
        int nbr = f.getNbr();
        String type = f.getType();
        String categ = f.getCateg();
        String depart = f.getDepart();
        String destination = f.getDestination();
        String option = f.getOption();
        
       
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "ajoutForm/?nom=" + nom + "&tlp=" + tlp + "&mail=" +mail+ "&nbr=" +nbr+ "&type=" +type+ "&categ=" +categ+ "&depart=" +depart+ "&destination=" +destination+ "&option=" +option ;

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

       public ArrayList<Formulairer> parseTasks(String jsonText) {
        try {
            formulaire = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list= (List<Map<String, Object>>) tasksListJson.get("root");
            for(Map<String,Object> obj :list){
            
                Formulairer f = new Formulairer();
    if (obj.containsKey("id") && obj.get("id") instanceof Number) {
        f.setId((int) Float.parseFloat(obj.get("id").toString()));
    }
                f.setNom(obj.get("nom").toString());
                f.setTlp((int)Float.parseFloat(obj.get("tlp").toString()));
                f.setMail(obj.get("mail").toString());
                f.setNbr((int)Float.parseFloat(obj.get("nbr").toString()));
                f.setType(obj.get("type").toString());
                f.setCateg(obj.get("categ").toString());
                f.setDepart(obj.get("depart").toString());
                f.setDestination(obj.get("destination").toString());
                f.setOption(obj.get("opt").toString());
               formulaire.add(f);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return formulaire;
    }
       public ArrayList<Formulairer> getAllFormulaire() {
        String url = Statics.BASE_URL + "afficherForm";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                formulaire = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return formulaire;
    }
       public boolean deleteFormulaire(int id) {

    String url = Statics.BASE_URL + "deleteForm/" + id;
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
       
          public boolean updateFormulaire(String nom, int tlp, String mail,int nbr, String type, String categ, String depart, String destination, String option, int id) {

        String url = Statics.BASE_URL + "updateForm/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("nom", nom);
        req.addArgument("tlp", String.valueOf(tlp));
        req.addArgument("mail", mail);
        req.addArgument("nbr", String.valueOf(nbr));
        req.addArgument("type", type);
        req.addArgument("categ", categ);
        req.addArgument("depart", depart);
        req.addArgument("destination", destination);
        req.addArgument("option", option);
         
        
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
        
    

