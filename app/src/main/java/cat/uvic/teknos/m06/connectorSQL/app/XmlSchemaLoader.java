package cat.uvic.teknos.m06.connectorSQL.app;


import cat.uvic.teknos.m06.connectorSQL.app.xml.Schema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlSchemaLoader implements SchemaLoader {
    private ConnectionProperties connection;
    public Schema schema;

    public XmlSchemaLoader (String path){
        var file = new File(path);

        XmlMapper xmlMapper = new XmlMapper();
        try{
            var xml = Files.readString(Path.of(path));
            schema = xmlMapper.readValue(xml,Schema.class);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Schema getSchema(){
        return this.schema;
    }

    @Override
    public void load() {

    }

}
