/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.Format;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.entity.Offre;
import com.codename1.uikit.util.DataSource;
import com.codename1.uikit.util.Statics;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hp
 */
public class ServiceOffre {

    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     private ConnectionRequest request;
    private boolean responseResult;
    public ArrayList<Offre> comments;

    public ServiceOffre() {
        request = DataSource.getInstance().getRequest();

    }
    
    
    public boolean addOffre(Offre r) {

        String url = Statics.BASE_URL + "/api/addOffre?description=" + r.getDescription()+ "&duree=" + r.getDure();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    
    
    public ArrayList<Offre> getAllOffre() {
        String url = Statics.BASE_URL + "/api/offre/";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    comments = parseType(new String(request.getResponseData()));
                    request.removeResponseListener(this);
                } catch (ParseException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return comments;
    }
    
    
    
    
    public ArrayList<Offre> parseType(String jsonText) throws ParseException {
        try {
            comments = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                ;
                int id = (int) Float.parseFloat(obj.get("id").toString());

                String duree = obj.get("description").toString();
                String description = obj.get("description").toString();


                Offre v = new Offre();
                v.setDescription(description);
                v.setId(id);
                v.setDure(duree);

                
                comments.add(v);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return comments;
    }
    
    
    
        public boolean DeleteOffre(int id) {

        String url = Statics.BASE_URL + "/api/supprimerOffre/" + id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
        
        
       public boolean ModifierOffre(Offre r) {

        String url = Statics.BASE_URL + "/api/modifier?description=" + r.getDescription() + "&duree=" + r.getDure()+ "&id=" + r.getId();
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
       
       
       
       
       ////////////////////:::pdf////////////////////
       
      
  
            
      
    
    
    
}
