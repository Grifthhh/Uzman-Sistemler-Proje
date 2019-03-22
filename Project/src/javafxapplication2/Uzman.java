/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
/**
 *
 * @author umiitkose
 */
public class Uzman {

    private ArrayList<Telefon> telefonList;

    public ArrayList<Telefon> getTelefonList() {
        return telefonList;
    }
    
    
    public Uzman() {
        telefonList = new ArrayList<>();
        try {
            mainFunction();
        } catch (IOException ex) {
            Logger.getLogger(Uzman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Siteden alınan telefonların alt adresine inmek için gerekli adresi döndürür
    private static String url_yap(String alturl){  
     
      int bas=alturl.indexOf("https");  
      int son=alturl.indexOf("html"); 
      son+=4;
      return alturl.substring(bas, son);
    }
 
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    private void mainFunction() throws IOException {
      String tmp="https://www.epey.com/akilli-telefonlar/e/YToxOntpOjE4NzA7YToxOntpOjA7czo2OiIzODE0NDkiO319X3M6OToicHVhbjpERVNDIjs=/";  //Kaynak site
      int sayac=1;  //Toplam telefon sayisi
      String alturl;    //Telefonların Alt adresi
    for(int i=1;i<=3;i++){
        try {
             Document telefonOku = Jsoup.connect(tmp+String.valueOf(i)).get();
         //  System.out.println("html Data : "+telefonOku.html().toString());
             Elements telefonlar1 = telefonOku.select("#listele > div.listele.table");
             Elements telefonlar=telefonlar1.select("ul[class= metin row]");
             
             System.out.println(i+". Sayfa -Telefonlar  : " + telefonlar.size()); //Her sayfadaki telefon miktarı
             for (Element element : telefonlar) {
              Telefon telefon=new Telefon();        //Siteden gelen bilgiler class a aktarılır
              telefon.setModel(element.getElementsByClass("urunadi").text());
              telefon.setBatarya(element.getElementsByClass("ozellik ozellik7 cell").text());
              telefon.setEkran_boyut(element.getElementsByClass("ozellik ozellik1 cell").text());            
              telefon.setFiyat(element.getElementsByClass("fiyat cell").text());      
              telefon.setRam(element.getElementsByClass("ozellik ozellik14 cell").text());
              telefon.setHafiza(element.getElementsByClass("ozellik ozellik21 cell").text());             
          
              alturl=element.select("li.adi.cell > div.detay.cell > a[href]").toString();   
              System.out.println(url_yap(alturl));
              Document Ayrinti = Jsoup.connect(url_yap(alturl)).get();  //Telefonların alt sitelerine geçiş
              telefon.setKamera(Ayrinti.select("li#id19.cls0 > span.cell.cs1").text());  
              telefon.setCozunurluk(Ayrinti.select("li#id3.cls0 > span.cell.cs1").text());   
              telefonList.add(telefon);
             }
        }catch (HttpStatusException ex){}
    }
    for(Telefon telefon : telefonList){
              
        System.out.println("------Telefon " + sayac+ " --------------" );
        System.out.println("Model : " +telefon.getModel());
        System.out.println("Fiyat : " +telefon.getFiyat());
        System.out.println("Ekran : " +telefon.getEkran_boyut());
        System.out.println("RAM : " +telefon.getRam());
        System.out.println("Hafiza : " +telefon.getHafiza());
        System.out.println("Batarya : " +telefon.getBatarya());
        System.out.println("Kamera : " +telefon.getKamera());
        System.out.println("Cozunurluk : " +telefon.getCozunurluk());
        sayac++;
        System.out.println("-------------------------------------");
    }
    }
    
}