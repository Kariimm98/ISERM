package cat.uvic.teknos.m06.connectorSQL.app;

import java.sql.SQLException;

public interface SchemaLoader {

    void load() throws SQLException;
}
