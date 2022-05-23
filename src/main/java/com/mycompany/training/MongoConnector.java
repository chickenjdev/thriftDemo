package com.mycompany.training;

import com.mongodb.*;
import io.vertx.core.json.JsonArray;

public class MongoConnector {
    DB database;
    DBCollection collection;

    MongoConnector mongoConnector;

    public MongoConnector() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDB("mongodb");
        collection = database.getCollection("customers");
    }

    public JsonArray readData(String username, String password) {
        JsonArray arrUserInfo = new JsonArray();
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("username", username);
        if (!password.isEmpty())
            searchQuery.put("password", password);
        DBCursor cursor = collection.find(searchQuery);

        while (cursor.hasNext()) {
            arrUserInfo.add(cursor.next());
        }
        System.out.println("readData result query :" + arrUserInfo.size());
        return arrUserInfo;
    }

    public boolean insertData(String username, String password, String name, String address, int age) {
        BasicDBObject document = new BasicDBObject();
        document.put("username", username);
        document.put("password", password);
        document.put("name", name);
        document.put("address", address);
        document.put("age", age);
        try {
            WriteResult writeResult = collection.insert(document);
            System.out.println("insertData result :" + document.toJson() + ":" + writeResult.wasAcknowledged());
            return writeResult.wasAcknowledged();
        } catch (Exception e) {
            System.out.println("insertData result :" + document.toJson() + ":" + false);
            return false;
        }
    }

    public boolean updateData(String username, String name, String address, int age) {
        BasicDBObject query = new BasicDBObject();
        query.put("username", username);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", name);
        newDocument.put("address", address);
        newDocument.put("age", age);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        WriteResult writeResult = collection.update(query, updateObject);
        return writeResult.wasAcknowledged();
    }
}
