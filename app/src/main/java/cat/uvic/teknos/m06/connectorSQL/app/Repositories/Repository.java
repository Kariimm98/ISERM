package cat.uvic.teknos.m06.connectorSQL.app.Repositories;

import java.util.List;

public interface Repository <Model>{

    void save(Model model);
    void delete(Model model);
    Model getById(int id);
    List<Model> getAll();
}
