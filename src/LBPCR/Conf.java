package LBPCR;

public class Conf implements java.io.Serializable{
    private String url="";
    private String port="";
    private String timeOut="";

    public Conf(){
    }

    public Conf(String url, String port, String timeOut){
        this.url = url;
        this.port = port;
        this.timeOut = timeOut;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String toString() {
        return url;
    }
}