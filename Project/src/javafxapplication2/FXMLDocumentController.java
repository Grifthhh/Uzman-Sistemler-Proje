/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author EKREMBÜLBÜL
 * Arayüz ayarlari
 */
public class FXMLDocumentController implements Initializable {
    Uzman uzman;
    ObservableList choiceBoxList = FXCollections.observableArrayList();
    
    @FXML
    private ChoiceBox<?> choiceBox2;

    @FXML
    private ChoiceBox<?> choiceBox1;

    @FXML
    private Button button;

    @FXML
    private RadioButton radio2dusuk;

    @FXML
    private ToggleGroup group2;

    @FXML
    private RadioButton radio2Yuksek;

    @FXML
    private RadioButton radio1Yuksek;

    @FXML
    private ToggleGroup group1;

    @FXML
    private RadioButton radio1Dusuk;
    
    @FXML
    private TextArea cikarim;
    
    @FXML
    private Label errorLabel;

    @FXML
    void handleButton(ActionEvent event) {
        int index1 = choiceBox1.getSelectionModel().getSelectedIndex(),
            index2 = choiceBox2.getSelectionModel().getSelectedIndex();
        if (index1 == index2)
            if (index1 == -1 && index2 == -1)
                errorLabel.setText("En az 1 tane seçenek seçmek zorundasınız!");
            else
                errorLabel.setText("Aynı seçenekleri seçemezsiniz!");
        else {
            errorLabel.setText("");
            if (index1 == -1)
                tekCikarim(index2, group2.getSelectedToggle().equals(radio2Yuksek));
            else if (index2 == -1)
                tekCikarim(index1, group1.getSelectedToggle().equals(radio1Yuksek));
            else
                ciftCikarim(index1, group1.getSelectedToggle().equals(radio1Yuksek), index2, group2.getSelectedToggle().equals(radio2Yuksek));
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uzman = new Uzman();
        loadData();
    }
    
    private void loadData() {
        choiceBoxList.addAll(
                "Oyun Performansı", //index 0
                "Fotoğraf Kalitesi",    //index 1
                "Hafıza Kapasitesi",    //index 2
                "Bütçe",                //index 3
                "Günlük Kullanım Süresi",   //index 4
                "Uygulama Çeşitliliği",     //index 5
                "Görüntülü/Sesli Konuşma",  //index 6
                "Video Kayıt",              //index 7
                "Hobiler (Kitap, Film, Resim, Müzik)"   //index 8
        );
        choiceBox1.getItems().addAll(choiceBoxList);
        choiceBox2.getItems().addAll(choiceBoxList);
    }

    //Seçilenlere göre ilk çıkarım kuralları
    private ArrayList<Telefon> tekCikarim(int index, boolean radio) { //index kategori-radio yüksek/düşük ayari
        ArrayList<Telefon> telList = new ArrayList<>();
        String message;
        message = "";
        
        for (Telefon telefon: uzman.getTelefonList()) {
            switch (index) {
                case 0:
                    if (radio == true) {
                        if (getInt(telefon.getBatarya()) > 3000 && getInt(telefon.getRam()) >= 4) {
                            message = yazdir(telefon, message, telList);
                        }
                    }
                    else
                        if (getInt(telefon.getBatarya()) <= 3000 && getInt(telefon.getRam()) < 4) {
                            message = yazdir(telefon, message, telList);
                        }
                    break;
                case 1:
                    if (radio == true) {
                        if (getInt(telefon.getKamera()) > 12) {
                            message = yazdir(telefon, message, telList);
                        }
                    }
                    else
                        if (getInt(telefon.getKamera()) <= 12) {
                            message = yazdir(telefon, message, telList);
                        }
                    break;
                case 2:
                    if (radio == true) {
                        if (getInt(telefon.getHafiza()) >= 64) {
                            message = yazdir(telefon, message, telList);
                        }
                    }
                    else
                        if (getInt(telefon.getHafiza()) < 64) {
                            message = yazdir(telefon, message, telList);
                        }
                    break;
                case 3:
                    if (radio == true) {
                        if (getFiyat(telefon.getFiyat()) >= 2500) {
                            message = yazdir(telefon, message, telList);
                        }
                    }
                    else
                        if (getFiyat(telefon.getFiyat()) < 2500) {
                            message = yazdir(telefon, message, telList);
                        }
                    break;
                case 4:
                    if (radio == true) {
                        if (getInt(telefon.getBatarya()) >= 3000) {
                            message = yazdir(telefon, message, telList);
                        }
                    }
                    else
                        if (getInt(telefon.getBatarya()) < 3000) {
                            message = yazdir(telefon, message, telList);
                        }
                    break;
                case 5:
                    if (radio == true) {
                        if (getInt(telefon.getRam()) >= 4 && getInt(telefon.getHafiza()) >= 64) {
                            message = yazdir(telefon, message, telList);
                        }
                    }
                    else
                        if (getInt(telefon.getRam()) < 4 && getInt(telefon.getHafiza()) < 64) {
                            message = yazdir(telefon, message, telList);
                        }
                    break;
                case 6:
                    if (radio == true) {
                        if (getCoz(telefon.getCozunurluk()) >= 1080 && getFloat(telefon.getEkran_boyut()) >= 6) {
                            message = yazdir(telefon, message, telList);
                        }
                    }
                    else
                        if (getCoz(telefon.getCozunurluk()) < 1080 && getFloat(telefon.getEkran_boyut()) < 6) {
                            message = yazdir(telefon, message, telList);
                        }
                    break;
                case 7:
                    if (radio == true) {
                        if (getInt(telefon.getHafiza()) >= 64 && getCoz(telefon.getCozunurluk()) >= 1080) {
                            message = yazdir(telefon, message, telList);
                        }
                    }
                    else
                        if (getInt(telefon.getHafiza()) < 64 && getCoz(telefon.getCozunurluk()) < 1080) {
                            message = yazdir(telefon, message, telList);
                        }
                    break;
                case 8:
                    if (radio == true) {
                        if (getFloat(telefon.getEkran_boyut()) >= 6 && getInt(telefon.getRam()) >= 3 && getInt(telefon.getBatarya()) >= 4000) {
                            message = yazdir(telefon, message, telList);
                        }
                    }
                    else
                        if (getFloat(telefon.getEkran_boyut()) < 6 && getInt(telefon.getRam()) < 3 && getInt(telefon.getBatarya()) < 4000) {
                            message = yazdir(telefon, message, telList);
                        }
                    break;
                default:
                    break;
            }
        }
        
        cikarim.setText(message);
        return telList;
    }
    
    // 2.aşama çıkarım kurallari
    private void ciftCikarim(int index1, boolean ratio1, int index2, boolean ratio2) {
        String message = "";
        ArrayList<Telefon> arrList1 = tekCikarim(index1, ratio1);   //ilk çıkarım sonucu geken telefonlar 
        ArrayList<Telefon> arrList2 = tekCikarim(index2, ratio2);   //ikinci çıkarım sonucu gelen telefonlar
        cikarim.setText("");
        for (Telefon telefon: arrList1) {
            for (Telefon telefon2: arrList2) {               // 2. aşama çıkarım kuralı ikisini de içeren telefonları kapsar
                if (telefon == telefon2) {
                    message += "Model : " + telefon.getModel() + "\n";
                    message += "Fiyat : " + telefon.getFiyat() + "\n";
                    message += "Ekran : " + telefon.getEkran_boyut() + "\n";
                    message += "RAM : " + telefon.getRam() + "\n";
                    message += "Hafiza : " + telefon.getHafiza() + "\n";
                    message += "Batarya : " + telefon.getBatarya() + "\n";
                    message += "Kamera : " + telefon.getKamera() + "\n";
                    message += "Cozunurluk : " + telefon.getCozunurluk() + "\n";
                    message += "-------------------------------------\n";  
                }
            }
        }
        cikarim.setText(message);
    }
    
    private int getInt(String s) {      // Bilgi tabanından telefon classına veri geçisi
        String[] ss = s.split(" ");
        return Integer.parseInt(ss[0]);
    }
    
    private float getFloat(String s) {  // Bilgi tabanından telefon classına veri geçisi
        String[] ss = s.split(" ");
        return Float.parseFloat(ss[0]);
    }
    
    private int getFiyat(String s) {    // Bilgi tabanından telefon classına veri geçisi
        String tmp, tmp1, yeniFiyat;    //Fiyata özel kural
        if (s.length() <= 1)
            return 0;
        int i = s.indexOf(".");
        tmp = s.substring(0, i);
        int j = s.indexOf(",");
        tmp1 = s.substring(i + 1, j);
        yeniFiyat = tmp + tmp1;
        return Integer.parseInt(yeniFiyat);
    }
    
    private int getCoz(String s) {      // Bilgi tabanından telefon classına veri geçisi
        int i = s.indexOf("x");         //Cözünürlüğe özel kural
        String tmp = s.substring(0, i);
        return Integer.parseInt(tmp);
    }
    //Arayüze mesaj yazdırır ve ilk çıkarım sonucu geçici telefonlar dizisini oluşturur
    private String yazdir(Telefon telefon, String message, ArrayList<Telefon> telArr1) {
        telArr1.add(telefon);
        message += "Model : " + telefon.getModel() + "\n";      
        message += "Fiyat : " + telefon.getFiyat() + "\n";
        message += "Ekran : " + telefon.getEkran_boyut() + "\n";
        message += "RAM : " + telefon.getRam() + "\n";
        message += "Hafiza : " + telefon.getHafiza() + "\n";
        message += "Batarya : " + telefon.getBatarya() + "\n";
        message += "Kamera : " + telefon.getKamera() + "\n";
        message += "Cozunurluk : " + telefon.getCozunurluk() + "\n";
        message += "-------------------------------------\n";
        return message;
    }
}
