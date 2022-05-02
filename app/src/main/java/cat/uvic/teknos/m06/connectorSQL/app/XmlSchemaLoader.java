package cat.uvic.teknos.m06.connectorSQL.app;

import com.ctc.wstx.shaded.msv.org_isorelax.verifier.Schema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlSchemaLoader implements SchemaLoader {
    private Schema schema;

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

    @Override
    public void load() {

    }

}
