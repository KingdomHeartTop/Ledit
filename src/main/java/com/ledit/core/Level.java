package com.ledit.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.*;

public class Level {
    private String name;
    private String version;
    private List<LevelObject> objects;
    private Map<String, Object> metadata;
    
    public Level() {
        this.name = "Untitled";
        this.version = "1.0";
        this.objects = new ArrayList<>();
        this.metadata = new HashMap<>();
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    
    public List<LevelObject> getObjects() { return objects; }
    
    public void addObject(LevelObject obj) {
        objects.add(obj);
    }
    
    public void removeObject(String id) {
        objects.removeIf(obj -> obj.getId().equals(id));
    }
    
    public LevelObject getObject(String id) {
        for (LevelObject obj : objects) {
            if (obj.getId().equals(id)) {
                return obj;
            }
        }
        return null;
    }
    
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("version", version);
        map.put("metadata", metadata);
        
        List<Map<String, Object>> objList = new ArrayList<>();
        for (LevelObject obj : objects) {
            objList.add(obj.toMap());
        }
        map.put("objects", objList);
        return map;
    }
    
    @SuppressWarnings("unchecked")
    public static Level fromMap(Map<String, Object> map) {
        Level level = new Level();
        level.name = (String) map.getOrDefault("name", "Untitled");
        level.version = (String) map.getOrDefault("version", "1.0");
        level.metadata = (Map<String, Object>) map.getOrDefault("metadata", new HashMap<>());
        
        List<Map<String, Object>> objList = (List<Map<String, Object>>) map.getOrDefault("objects", new ArrayList<>());
        for (Map<String, Object> objMap : objList) {
            level.objects.add(LevelObject.fromMap(objMap));
        }
        return level;
    }
    
    public void save(String filepath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filepath)) {
            gson.toJson(toMap(), writer);
        }
    }
    
    public void load(String filepath) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filepath)) {
            Map<String, Object> map = gson.fromJson(reader, Map.class);
            Level loaded = Level.fromMap(map);
            this.name = loaded.name;
            this.version = loaded.version;
            this.objects = loaded.objects;
            this.metadata = loaded.metadata;
        }
    }
}
