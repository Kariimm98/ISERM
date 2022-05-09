package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ExecuteCommandException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteCommands implements SchemaLoader{
    public final String[] commands;
    private final ConnectionProperties conProps;
    private static String pathLog = "C:\\tmp\\executeLog.txt";
    public ExecuteCommands(XmlSchemaLoader schema, ConnectionProperties conn) {
        this.commands = schema.getSchema().getCommands();
        this.conProps = conn;
    }

    @Override
    public void load() throws ExecuteCommandException,SQLException {
        var error = "";

        var con = getConnection();
        var statement = con.createStatement();

        for(int i = 0; commands.length> i;i++ ){
            error = executeCommand(error, statement, i);
        }

        if(logExist()){
            throw new ExecuteCommandException("has been errors on Execute, view log on: "+ pathLog);
        }
    }

    private String executeCommand(String error, Statement statement, int i) {
        try{
            statement.executeUpdate(commands[i]);
        }catch (SQLException e) {
            var num = i +1;
            var mes = "\n Error en ejecutar Comando nº " + num + e.getMessage();
            error += mes;
            addToLog(mes);
        }
        return error;
    }

    private Connection getConnection()  {
        try {
            return DriverManager.getConnection(conProps.getUrl(), conProps.getUsername(), conProps.getPassword());
        } catch (SQLException e) {
            addToLog("\n " +e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private void addToLog(String mess){
        File log = new File(pathLog);
        try{
            if (!log.exists()) {
                log.createNewFile();
            }
            FileWriter fw = new FileWriter(log);
            BufferedWriter writter = new BufferedWriter(fw);
            writter.write(mess);

            writter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean logExist(){
        File log = new File(pathLog);

        return log.exists();
    }
}
