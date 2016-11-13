package LBPCR;

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

    @FXML
    public void initialize(){
        fieldURL.setText("192.168.1.14");
        fieldPORT.setText("8080");
        fieldTimeOut.setText("1000");

        setUrl(fieldURL.getText());
        setPort(fieldPORT.getText());
        setTimeOut(fieldTimeOut.getText());
    }

    private String port;
    private String url;
    private String timeOut;

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
            String url = "http://"+getUrl()+":"+getPort()+"/remoteControl/cmd?operation=01&key="+pushed+"&mode=0";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(Integer.parseInt(getTimeOut()));
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
            System.out.println("Erreur: Impossible de se connecter à "+getUrl()+":"+getPort()+" Vérifiez votre configuration.");
        }catch (Exception e){
            System.out.println("push error : "+e);
        }
    }

    /**
     * Save the current config
     */
    public void saveSettings(){ //TODO: Ajouter sauvegarde de la conf sous forme de fichier
        loadSettings();
        System.out.println("Saved");
    }

    /**
     * Load the current config
     */
    private void loadSettings(){
        setUrl(fieldURL.getText());
        setPort(fieldPORT.getText());
        setTimeOut(fieldTimeOut.getText());
    }


}
