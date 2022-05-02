package cat.uvic.teknos.m06.connectorSQL.app;

import com.ctc.wstx.shaded.msv.org_isorelax.verifier.Schema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConnectionProperties {

    private String url;
    private String username;
    private String password;

    public ConnectionProperties(String url, String username, String pass){

        this.url = url;
        this.username = username;
        this.password = pass;

    }
    public ConnectionProperties(String path){
        var file = new File(path);
        XmlMapper xmlMapper = new XmlMapper();
        try{
            var xml = Files.readString(Path.of(path));
            var schema = xmlMapper.readValue(xml, Schema.class);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
