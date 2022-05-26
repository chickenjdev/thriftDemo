package com.mycompany.training.db;

import com.mongodb.*;
import com.mongodb.client.model.DBCollectionUpdateOptions;
import com.mycompany.training.thrift.SessionInfo;
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

    public JsonObject login(String username, String password) {
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
            return new JsonObject();
        }
        SessionInfo sessionInfo = createSessionUser(username);
        return new JsonObject().put("userInfo", JsonObject.mapFrom(getUserBySession(sessionInfo.getSessionId()))).put("sessionInfo", JsonObject.mapFrom(sessionInfo));
    }

    protected SessionInfo createSessionUser(String username) {
        SessionInfo sessionInfo = new SessionInfo();
        String sessionId = String.valueOf(UUID.randomUUID());

        BasicDBObject query = new BasicDBObject();
        query.put("username", username);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("sessionId", sessionId);
        newDocument.put("expireTime", System.currentTimeMillis() + 60 * 60 * 60);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);
        DBCollectionUpdateOptions options = new DBCollectionUpdateOptions().upsert(true);

        try {
            WriteResult writeResult = session.update(query, updateObject, options);
            System.out.println("createSessionUser result :" + newDocument.toJson() + ":" + writeResult.wasAcknowledged());
            if (writeResult.wasAcknowledged()) {
                return JsonObject.mapFrom(newDocument).mapTo(SessionInfo.class);
            }
            return null;
        } catch (Exception e) {
            System.out.println("createSessionUser result :" + newDocument.toJson() + ":" + false);
            return null;
        }
    }

    public SessionInfo checkSessionId(String sessionId) {
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
        return arrUserInfo.getJsonObject(0).mapTo(SessionInfo.class);
    }

    public UserInfo getUserBySession(String sessionId) {
        System.out.println("sessionId " + sessionId);
//        try {
//            Thread.sleep(5000);
//        } catch (Exception e) {
//        }
        SessionInfo sessionInfo = checkSessionId(sessionId);
        if (sessionInfo == null) {
            return null;
        }
        return readData(sessionInfo.getUsername(), "");
    }

    public UserInfo readData(String username, String password) {
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
        return arrUserInfo.getJsonObject(0).mapTo(UserInfo.class);
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

    public boolean logout(String username) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("username", username);
        WriteResult writeResult = session.remove(searchQuery);
        return writeResult.wasAcknowledged();
    }
}
