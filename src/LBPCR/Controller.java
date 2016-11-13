package LBPCR;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Controller {

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
     116 : RIGHT
     352 : OK
     158 : BACK
     139 : MENU
     164 : PLAY/PAUSE
     168 : FBWD
     159 : FFWD
     167 : REC
     393 : VOD
     */
    @FXML
    private Button onOff;
    @FXML
    private Button t0;
    @FXML
    private Button t1;
    @FXML
    private Button t2;
    @FXML
    private Button t3;
    @FXML
    private Button t4;
    @FXML
    private Button t5;
    @FXML
    private Button t6;
    @FXML
    private Button t7;
    @FXML
    private Button t8;
    @FXML
    private Button t9;
    @FXML
    private Button chP;
    @FXML
    private Button chM;
    @FXML
    private Button volP;
    @FXML
    private Button volM;
    @FXML
    private Button mute;
    @FXML
    private Button up;
    @FXML
    private Button down;
    @FXML
    private Button left;
    @FXML
    private Button right;
    @FXML
    private Button ok;
    @FXML
    private Button back;
    @FXML
    private Button menu;
    @FXML
    private Button playPause;
    @FXML
    private Button backward;
    @FXML
    private Button forward;
    @FXML
    private Button rec;
    @FXML
    private Button vod;

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
        push("116");
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
            String url = "http://192.168.1.10:8080/remoteControl/cmd?operation=01&key="+pushed+"&mode=0";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            //con.setRequestProperty("User-Agent", USER_A);
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

        }catch (Exception e){
            System.out.println("push error : "+e);
        }
    }
}
