package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.xml.Schema;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleLineCommandSchemaLoader implements SchemaLoader{
    public final String[] commands;
    private final ConnectionProperties conProps;

    public SingleLineCommandSchemaLoader(XmlSchemaLoader schema, ConnectionProperties conn) {
        this.commands = schema.getSchema().getCommands();
        this.conProps = conn;
    }

    @Override
    public void load() {

        try{
            var con = DriverManager.getConnection(conProps.getUrl(),conProps.getUsername(), conProps.getPassword());
            var statement = con.createStatement();

            for(int i = 0; commands.length> i;i++ ){
                statement.executeUpdate(commands[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
