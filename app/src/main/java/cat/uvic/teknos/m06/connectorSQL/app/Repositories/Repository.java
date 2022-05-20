package cat.uvic.teknos.m06.connectorSQL.app.Repositories;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ClientExcpetion;

import java.util.List;

public interface Repository <Model>{

    void save(Model model) throws Exception;
    void delete(Model model);
    Model getById(int id);
    List<Model> getAll();
    void insert(Model model) throws Exception;
    void update(Model model) throws Exception;
}
