package cat.uvic.teknos.m06.iserm.domain;

import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.Repository.MongoClientRepository;
import cat.uvic.teknos.m06.iserm.domain.model.Client;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MongoClientRepositoryTest {

    private static MongoClientRepository repos;
    private static MongoClient mongoClient;
    @BeforeAll
    static void setup(){

        var uri = "mongodb+srv://root:toor@iserm.cycxeao.mongodb.net/?retryWrites=true&w=majority";

        var connectionString = new ConnectionString(uri);
        var pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        var codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                pojoCodecRegistry);

        var settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .codecRegistry(codecRegistry)
                .build();

        mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("ISERM");

        repos =  new MongoClientRepository(database);
        assertNotNull(repos);
    }

    @AfterAll
    static void close(){
        mongoClient.close();
    }
    @Test
    void save() throws Exception {
        Client client = new Client();
        client.setName("Clint");
        client.setAddress("77685 Abe Terrace Suite 138 - Pawtucket, KY / 47613");
        client.setSurname("Bryant");

        Assertions.assertDoesNotThrow(()->repos.save(client));
    }

    @Test
    void delete() throws ClientExcpetion, ProductException {
        var client= new Client();

        assertDoesNotThrow(()->repos.getAll());
        var list =repos.getAll();
        client = list.get(0);

        assertTrue(client.getId()>0);

        Client finalClient = client;
        assertDoesNotThrow(()->repos.delete(finalClient));

    }

    @Test
    void getById() throws ClientExcpetion, ProductException {
        assertNotNull(repos.getById(0));
    }

    @Test
    void getAll() {
        assertDoesNotThrow(()->repos.getAll());

    }

    @Test
    void insert() {
        Client client = new Client();
        client.setName("Taylor ");
        client.setAddress("196 Weimann Wells Suite 117 - Roswell, TX / 96471");
        client.setSurname("Larson");

        Assertions.assertDoesNotThrow(()->repos.save(client));
    }

    @Test
    void update() throws ClientExcpetion, ProductException {

        var client= new Client();

        assertDoesNotThrow(()->repos.getAll());
        var list =repos.getAll();
        client = list.get(0);

        assertTrue(client.getId()>0);

        client.setSurname("Briggs");

        Client finalClient = client;
        assertDoesNotThrow(()->repos.save(finalClient));

    }
}