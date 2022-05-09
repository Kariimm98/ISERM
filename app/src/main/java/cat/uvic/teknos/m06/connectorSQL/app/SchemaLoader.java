package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ConnectionException;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ExecuteCommandException;

import java.sql.SQLException;

public interface SchemaLoader {

    void load() throws SQLException, ExecuteCommandException, ConnectionException;
}
