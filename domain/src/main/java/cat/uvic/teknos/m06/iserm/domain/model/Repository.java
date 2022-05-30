package cat.uvic.teknos.m06.iserm.domain.model;


import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;

import java.util.List;

public interface Repository<Model>{

    void save(Model model) throws Exception;
    void delete(Model model) throws ProductException, ClientExcpetion;
    Model getById(int id) throws ProductException, ClientExcpetion;
    List<Model> getAll() throws ClientExcpetion, ProductException;
    void insert(Model model) throws Exception;
    void update(Model model) throws Exception;
}
