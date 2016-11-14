package LBPCR;

import XMLTools.XMLTools;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class Controller {

    @FXML
    private TextField fieldURL;
    @FXML
    private TextField fieldPORT;
    @FXML
    private TextField fieldTimeOut;

    Conf conf = new Conf("192.168.1.10", "8080", "1000");
    private String fileName = "config.xml";

    @FXML
    public void initialize(){
        loadSavedSettings();
    }

    /**
     116 : ON/OFF
     512 : 0
     513 : 1
     514 : 2
     515 : 3
     516 : 4
     517 : 5
     518 : 6
     519 : 7
     520 : 8
     521 : 9
     402 : CH+
     403 : CH-
     115 : VOL+
     114 : VOL-
     113 : MUTE
     103 : UP
     108 : DOWN
     105 : LEFT
     106 : RIGHT
     352 : OK
     158 : BACK
     139 : MENU
     164 : PLAY/PAUSE
     168 : FBWD
     159 : FFWD
     167 : REC
     393 : VOD
     */

    public void pushOnOff(){
        push("116");
    }
    public void pushT0(){
        push("512");
    }
    public void pushT1(){
        push("513");
    }
    public void pushT2(){
        push("514");
    }
    public void pushT3(){
        push("515");
    }
    public void pushT4(){
        push("516");
    }
    public void pushT5(){
        push("517");
    }
    public void pushT6(){
        push("518");
    }
    public void pushT7(){
        push("519");
    }
    public void pushT8(){
        push("520");
    }
    public void pushT9(){
        push("521");
    }
    public void pushChP(){
        push("402");
    }
    public void pushChM(){
        push("403");
    }
    public void pushVolP(){
        push("115");
    }
    public void pushVolM(){
        push("114");
    }
    public void pushMute(){
        push("113");
    }
    public void pushUp(){
        push("103");
    }
    public void pushDown(){
        push("108");
    }
    public void pushLeft(){
        push("105");
    }
    public void pushRight(){
        push("106");
    }
    public void pushOk(){
        push("352");
    }
    public void pushBack(){
        push("158");
    }
    public void pushMenu(){
        push("139");
    }
    public void pushPlayPause(){
        push("164");
    }
    public void pushBackward(){
        push("168");
    }
    public void pushForward(){
        push("159");
    }
    public void pushRec(){
        push("167");
    }
    public void pushVod(){
        push("393");
    }

    /**
     * Send a command to the TV
     *
     * @param pushed
     */
    private void push(String pushed){
        try {
            String url = "http://"+conf.getUrl()+":"+conf.getPort()+"/remoteControl/cmd?operation=01&key="+pushed+"&mode=0";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(Integer.parseInt(conf.getTimeOut()));
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            //Get back informations
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //Print result
            System.out.println(response.toString());

        }catch (SocketTimeoutException ste){
            System.out.println("Erreur: Impossible de se connecter à "+conf.getUrl()+":"+conf.getPort()+" Vérifiez votre configuration.");
        }catch (Exception e){
            System.out.println("push error : "+e);
        }
    }

    /**
     * Save the current config
     */
    public void saveSettings(){
        loadSettings();
        try {
            XMLTools.encodeToFile(conf, fileName);
            System.out.println("Saved");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Cannot write the conf.xml file");
        }
    }

    /**
     *  Load saved settings or default
     */
    public void loadSavedSettings(){
        try {
            conf = (Conf) XMLTools.decodeFromFile(fileName);
        } catch(Exception e) {
            System.out.println("Cannot open the conf.xml file");
        }
        fieldURL.setText(conf.getUrl());
        fieldPORT.setText(conf.getPort());
        fieldTimeOut.setText(conf.getTimeOut());
    }

    /**
     * Load the current config
     */
    private void loadSettings(){
        conf.setUrl(fieldURL.getText());
        conf.setPort(fieldPORT.getText());
        conf.setTimeOut(fieldTimeOut.getText());
    }

}
