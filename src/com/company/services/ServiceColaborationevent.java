/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.codename1.ui.Form;
import java.io.IOException;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.html.DocumentInfo;
import com.mycompany.entities.Colaborationevent;
import com.mycompany.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * HP
 */
public class ServiceColaborationevent {
    public static ServiceColaborationevent instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Colaborationevent> listCategory;

    public ServiceColaborationevent() {
        req = new ConnectionRequest();
    }

    public static ServiceColaborationevent getInstance() {
        if (instance == null) {
            instance = new ServiceColaborationevent();
        }
        return instance;
    }
    
    //////////////////////////////AFICHAGE////////////////////////////////////////
    
    public ArrayList<Colaborationevent> parseTasks(String jsonText) {
    try {
        listCategory = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        // Check if the "root" key exists in the parsed JSON object
        
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            
            for (Map<String, Object> obj : list) {
                Colaborationevent c = new Colaborationevent();
                //System.out.println(obj);
                // Check if the "idevent" key exists and has a valid value
                if (obj.containsKey("idEvent") && obj.get("idEvent") instanceof Number) {
                c.setIdevent((int) Float.parseFloat(obj.get("idEvent").toString()));
                }

                // Check if the "nomevent" key exists and has a valid value
                if (obj.containsKey("nomEvent") && obj.get("nomEvent") instanceof String) {
                    c.setNomevent((String) obj.get("nomEvent"));
                }
//                c.setNomevent(obj.get("NomEvent").toString());

                // Check if the "dateevent" key exists and has a valid value
                if (obj.containsKey("dateEvent") && obj.get("dateEvent") instanceof String) {
                    String dateStr = (String) obj.get("dateEvent");
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = dateFormat.parse(dateStr);
                        c.setDateevent(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                // Check if the "adresseevent" key exists and has a valid value
                if (obj.containsKey("adresseEvent") && obj.get("adresseEvent") instanceof String) {
                    c.setAdresseevent((String) obj.get("adresseEvent"));
                }
//                c.setAdresseevent(obj.get("AdresseEvent").toString());
                
                // Check if the "nbrplacevehicule" key exists and has a valid value
                       if (obj.containsKey("nbrPlaceVehicule") && obj.get("nbrPlaceVehicule") instanceof Number) {
                        c.setNbrplacevehicule((int) Float.parseFloat(obj.get("nbrPlaceVehicule").toString()));
                           }
     if (obj.containsKey("prixVehiculeEvent") && obj.get("prixVehiculeEvent") instanceof Number) {
        c.setPrixvehiculeevent((int) Float.parseFloat(obj.get("prixVehiculeEvent").toString()));
    }

                listCategory.add(c);
            }   
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    
    
        System.out.println(listCategory.toString());
    return listCategory;
}
    
    public ArrayList<Colaborationevent> getAllCategorie() {
           
        
        String url = Statics.BASE_URL + "colaborationeventJson/aa";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listCategory = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listCategory;
    }
    
    //////////////////////////////show by id /////////////////////
     
    public ArrayList<Colaborationevent> parseTask(String jsonText) {
    ArrayList<Colaborationevent> listCategory = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        // Check if the "root" key exists in the parsed JSON object
        List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

        for (Map<String, Object> obj : list) {
            Colaborationevent c = new Colaborationevent();

            // Check if the "idevent" key exists and has a valid value
            if (obj.containsKey("idEvent") && obj.get("idEvent") instanceof Number) {
                c.setIdevent((int) Float.parseFloat(obj.get("idEvent").toString()));
            }

            // Check if the "nomEvent" key exists and has a valid value
            if (obj.containsKey("nomEvent") && obj.get("nomEvent") instanceof String) {
                c.setNomevent((String) obj.get("nomEvent"));
            }

            // Check if the "dateEvent" key exists and has a valid value
            if (obj.containsKey("dateEvent") && obj.get("dateEvent") instanceof String) {
                String dateStr = (String) obj.get("dateEvent");
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = dateFormat.parse(dateStr);
                    c.setDateevent(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            // Check if the "adresseEvent" key exists and has a valid value
            if (obj.containsKey("adresseEvent") && obj.get("adresseEvent") instanceof String) {
                c.setAdresseevent((String) obj.get("adresseEvent"));
            }

            // Check if the "nbrPlaceVehicule" key exists and has a valid value
            if (obj.containsKey("nbrPlaceVehicule") && obj.get("nbrPlaceVehicule") instanceof Number) {
                c.setNbrplacevehicule((int) Float.parseFloat(obj.get("nbrPlaceVehicule").toString()));
            }

            if (obj.containsKey("prixVehiculeEvent") && obj.get("prixVehiculeEvent") instanceof Number) {
                c.setPrixvehiculeevent((int) Float.parseFloat(obj.get("prixVehiculeEvent").toString()));
            }

            listCategory.add(c);
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }

    System.out.println(listCategory.toString());
    return listCategory;
}

    
   public ArrayList<Colaborationevent> getEvent(int idEvent) {
    String url = Statics.BASE_URL + "colaborationeventJson/" + idEvent;
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            listCategory = parseTask(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return listCategory;
}


    
    
    
///////////////////////////////////////ajout/////////////////////////////////////////////////////
    
    public boolean addColaborationevent(Colaborationevent t) {

        String nomevent = t.getNomevent();
       Date dateevent=t.getDateevent();
       String adresseevent=t.getAdresseevent();
       int nbrplacevehicule=t.getNbrplacevehicule();
       int prixvehiculeevent=t.getPrixvehiculeevent();
       
    
String url= Statics.BASE_URL + "colaborationeventJson/new?"
            + "NomEvent=" + t.getNomevent()
            + "&DateEvent=" + new SimpleDateFormat("yyyy-MM-dd").format(t.getDateevent())
            + "&AdresseEvent=" + t.getAdresseevent()
            + "&NbrPlaceVehicule=" + t.getNbrplacevehicule()
            + "&PrixVehiculeEven=" + t.getPrixvehiculeevent();
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

          
       

/*********************************************UPDATE***************************************************/    
    
    
    
   public boolean updateCategorie(String nomevent, Date dateevent, String adresse, String nbrplace, String prix, int idEvent) {
    String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateevent);
    String url = Statics.BASE_URL + "colaborationeventJson/" + idEvent + "/edit?"
            + "NomEvent="+ nomevent
            + "&DateEvent=" + formattedDate
            + "&AdresseEvent=" + adresse
            + "&NbrPlaceVehicule=" + nbrplace
            + "&PrixVehiculeEven=" + prix;
    
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


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //////////////////////////////////DELETE///////////////////////////////////////
  
    
   
   public boolean deleteEvent(int idEvent) {

    String url = Statics.BASE_URL + "colaborationeventJson/supp/" + idEvent;
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
   
   
    
    
    
     
   
}

    
   

