package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ConnectionException;
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
    private static String pathLog = "logs/errorSchemaLoaderLog";
    public Connection connection;


    public ExecuteCommands(XmlSchemaLoader schema, ConnectionProperties conn) throws ConnectionException{
        this.commands = schema.getSchema().getCommands();
        this.conProps = conn;
        this.connection = getConnection();
    }

    @Override
    public void load() throws ExecuteCommandException, ConnectionException, SQLException {
        clearLog();
        var error = "";

            var statement = this.connection.createStatement();

        for(int i = 0; commands.length> i;i++ ){
            error = executeCommand(error, statement, i);
        }

        if(!error.isEmpty()){
            addToLog(error);
            throw new ExecuteCommandException("has been errors on Execute, view log on: "+ pathLog);
        }
    }

    private String executeCommand(String error, Statement statement, int i) {
        try{
            statement.executeUpdate(commands[i]);
        }catch (SQLException e) {
            var num = i +1;
            var mes = "\n Error executing command nº " + num +" "+ e.getMessage();
            error += mes;
        }
        return error;
    }

    private Connection getConnection() throws ConnectionException {
        try {
            return  DriverManager.getConnection(conProps.getUrl(), conProps.getUsername(), conProps.getPassword());
        } catch (SQLException e) {
            var mess = "\n error connecting to DB: ";
            addToLog(mess);
            addToLog("\n " +e.getMessage());
            e.printStackTrace();
            throw new ConnectionException(e.getMessage());
        }
    }

    private void addToLog(String mess){
        File log = new File(pathLog);
        try{
            if (!log.exists()) {
                log.createNewFile();
            }
            FileWriter fw = new FileWriter(log);
            BufferedWriter writter = new BufferedWriter(fw);
            writter.newLine();
            writter.write(mess);


            writter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void clearLog(){
        File log = new File(pathLog);
        try {
            if (log.exists()) {
                log.delete();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private boolean logExist(){
        File log = new File(pathLog);

        return log.exists();
    }
}
