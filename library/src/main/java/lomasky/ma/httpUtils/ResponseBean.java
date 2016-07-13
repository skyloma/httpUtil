package lomasky.ma.httpUtils;


import java.io.Serializable;

public class ResponseBean implements Serializable {
    public String api;
    public int code;
    public String content;




    public ResponseBean(String api, int code, String content) {
        this.api = api;
        this.code = code;
        this.content = content;
    }

}
