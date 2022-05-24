package com.mycompany.training;

import com.mongodb.*;
import com.mongodb.client.model.DBCollectionUpdateOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mycompany.training.thrift.UserInfo;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

public class MongoConnector {
    DB database;
    DBCollection collection;
    DBCollection session;

    MongoConnector mongoConnector;

    public MongoConnector() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDB("mongodb");
        collection = database.getCollection("customers");
        session = database.getCollection("sessions");
    }

    public String login(String username, String password) {
        JsonArray arrUserInfo = new JsonArray();
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("username", username);
        searchQuery.put("password", password);
        DBCursor cursor = collection.find(searchQuery);

        while (cursor.hasNext()) {
            arrUserInfo.add(cursor.next());
        }
        System.out.println("login result query :" + (arrUserInfo.size() != 0));
        if (arrUserInfo.isEmpty()) {
            return null;
        }
        return createSessionUser(username);
    }

    protected String createSessionUser(String username) {
        String sessionId = String.valueOf(UUID.randomUUID());

        BasicDBObject query = new BasicDBObject();
        query.put("username", username);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("sessionId", sessionId);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);
        DBCollectionUpdateOptions options = new DBCollectionUpdateOptions().upsert(true);

        try {
            WriteResult writeResult = session.update(query, updateObject, options);
            System.out.println("createSessionUser result :" + newDocument.toJson() + ":" + writeResult.wasAcknowledged());
            if (writeResult.wasAcknowledged()) {
                return sessionId;
            }
            return null;
        } catch (Exception e) {
            System.out.println("createSessionUser result :" + newDocument.toJson() + ":" + false);
            return null;
        }
    }

    protected String checkSessionId(String sessionId) {
        JsonArray arrUserInfo = new JsonArray();
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("sessionId", sessionId);
        DBCursor cursor = session.find(searchQuery);

        while (cursor.hasNext()) {
            arrUserInfo.add(cursor.next());
        }
        System.out.println("checkSessionId result query :" + arrUserInfo.size());
        if (arrUserInfo.isEmpty()) {
            return null;
        }
        return arrUserInfo.getJsonObject(0).getString("username");
    }

    public JsonObject getUserBySession(String sessionId) {
        String username = checkSessionId(sessionId);
        if (username == null || username.isEmpty()) {
            return null;
        }
        return readData(username, "");
    }

    public JsonObject readData(String username, String password) {
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
        if (arrUserInfo.isEmpty()) {
            return null;
        }
        return arrUserInfo.getJsonObject(0);
    }

    public boolean insertData(String username, String password, UserInfo userInfo) {
        BasicDBObject document = new BasicDBObject();
        document.put("username", username);
        document.put("password", password);
        document.put("name", userInfo.getName());
        document.put("address", userInfo.getAddress());
        document.put("age", userInfo.getAge());
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
        System.out.println("updateData result :" + newDocument.toJson() + ":" + writeResult.wasAcknowledged());
        return writeResult.wasAcknowledged();
    }

    public boolean logout(String username){
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("username", username);
        WriteResult writeResult = session.remove(searchQuery);
        return writeResult.wasAcknowledged();
    }
}
