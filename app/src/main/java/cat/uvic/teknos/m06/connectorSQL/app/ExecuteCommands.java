package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ExecuteCommandException;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ExecuteCommands implements SchemaLoader{
    public final String[] commands;
    private final ConnectionProperties conProps;

    public ExecuteCommands(XmlSchemaLoader schema, ConnectionProperties conn) {
        this.commands = schema.getSchema().getCommands();
        this.conProps = conn;
    }

    @Override
    public void load() throws ExecuteCommandException{
        var error = "";
        try{
            var con = DriverManager.getConnection(conProps.getUrl(),conProps.getUsername(), conProps.getPassword());
            var statement = con.createStatement();

            for(int i = 0; commands.length> i;i++ ){
                try{
                    statement.executeUpdate(commands[i]);
                }catch (SQLException e) {
                    error += "Error en ejecutar Comando nº " + i+1 + e.getMessage();
                }
            }
            if(error.length() != 0){
                throw new ExecuteCommandException(error);
            }
        } catch (SQLException e) {

        }

    }
}
