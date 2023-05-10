/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gestion.promotion.utils;
import com.codename1.io.Storage;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.IOException;
import tn.gestion.promotion.enitite.Reclamation;

/**
 *
 * @author abidi
 */



public class PdfReclamation {
     public void recupererpdf(Reclamation colab) throws DocumentException, BadElementException, IOException {
    
            
    // Ouvrir le document PDF
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, Storage.getInstance().createOutputStream("Event.pdf"));
    document.open();

    // Ajouter le titre de la facture
    //Font fontTitre = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.GREEN);
    Font fontTitre = FontFactory.getFont(FontFactory.COURIER_BOLD, 25, new BaseColor(10, 150, 100));
    Chunk chunkTitre = new Chunk("DisKover                                      ", fontTitre);
    document.add(chunkTitre);
    
    LineSeparator line = new LineSeparator();
line.setLineColor(new BaseColor(10, 150, 100));
line.setLineWidth(2);
document.add(line);
    
    
  /*  Image image1 = Image.getInstance("C:\\Users\\farah\\OneDrive\\Desktop\\log.png"); 
image1.setAbsolutePosition(520, PageSize.A4.getHeight() - -120 - image1.getHeight());
image1.scaleToFit(100, 70);
document.add(image1);
*/
 /* Image image = Image.getInstance("C://Users//farah//.cn1//My project-1.jpg"); 
image.setAbsolutePosition(PageSize.A4.getWidth() - 200, 300);
document.add(image);
  
    */// Ajouter le tableau
    PdfPTable table = new PdfPTable(4); // Nombre de colonnes
    table.setWidthPercentage(100); // Largeur de la table
    table.setSpacingBefore(10f); // Espace avant la table
    table.setSpacingAfter(10f); // Espace après la table
    // Ajouter les cellules
    PdfPCell cell;
    cell = new PdfPCell(new Phrase("Nom Evenement"));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Date Evenement"));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Localisation"));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);
    
   

    cell = new PdfPCell(new Phrase("Prix"));
    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(cell);

    // Ajouter les données du tableau
    // Récupérer la valeur du TextField
       

// Récupérer les données du tableau



    // Vérifier si le numéro de livraison correspond à celui entré dans le TextField
    
        table.addCell(colab.getCin());
        table.addCell(colab.getObjet());
        table.addCell(colab.getType());
        table.addCell(colab.getMessage());
        


    // Centrer le tableau
    table.setTotalWidth(new float[] { 100f, 130f, 130f, 70f }); // ajustez les valeurs en fonction de vos besoins

    PdfContentByte canvas = writer.getDirectContent();
    Rectangle rect = canvas.getPdfDocument().getPageSize();
    //table.setTotalWidth(table.getTotalWidth());
    table.writeSelectedRows(0, -1, (rect.getLeft() + rect.getRight() - table.getTotalWidth()) / 2, rect.getTop() - table.getTotalHeight() - 200, canvas);

    
  
    
PdfContentByte cb = writer.getDirectContent();
//PdfContentByte cb = writer.getDirectContent();
cb.beginText();
cb.setFontAndSize(FontFactory.getFont(FontFactory.COURIER_BOLD, 14, new BaseColor(0, 128, 0)).getBaseFont(), 14);
cb.showTextAligned(Element.ALIGN_RIGHT, "Vous pouvez réserver en ligne via le SiteWeB: ", PageSize.A4.getWidth() - 200, 400, 0);
cb.endText();

cb.beginText();
cb.setFontAndSize(FontFactory.getFont(FontFactory.COURIER_BOLD, 14, new BaseColor(0, 200, 0)).getBaseFont(), 18);
cb.showTextAligned(Element.ALIGN_RIGHT, "Detail Event : ", PageSize.A4.getWidth() - 260, 700, 0);
cb.endText();

cb.beginText();
cb.setFontAndSize(FontFactory.getFont(FontFactory.COURIER_BOLD, 14, new BaseColor(0, 128, 0)).getBaseFont(), 14);
cb.showTextAligned(Element.ALIGN_RIGHT, "[ www.DisKover.tn/Event ] . ", PageSize.A4.getWidth() - 70, 350, 0);
cb.endText();

/*Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLACK);
Paragraph p = new Paragraph("BUGBUSTERS", font);
p.setAlignment(Element.ALIGN_RIGHT);
document.add(p);

   */ // Fermer le document PDF
    document.close();
     }
}