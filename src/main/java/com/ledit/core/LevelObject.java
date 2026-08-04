package com.ledit.core;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LevelObject {
    private String id;
    private String type;
    private float x, y, z;
    private float[] rotation;
    private float[] scale;
    private float[] color;
    private Map<String, Object> properties;
    
    public LevelObject(String type, float x, float y, float z) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.rotation = new float[]{0, 0, 0};
        this.scale = new float[]{1, 1, 1};
        this.color = new float[]{1.0f, 1.0f, 1.0f};
        this.properties = new HashMap<>();
    }
    
    // Геттеры и сеттеры
    public String getId() { return id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public float getX() { return x; }
    public void setX(float x) { this.x = x; }
    public float getY() { return y; }
    public void setY(float y) { this.y = y; }
    public float getZ() { return z; }
    public void setZ(float z) { this.z = z; }
    
    public float[] getPosition() { return new float[]{x, y, z}; }
    public void setPosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public float[] getRotation() { return rotation; }
    public void setRotation(float[] rotation) { this.rotation = rotation; }
    
    public float[] getScale() { return scale; }
    public void setScale(float[] scale) { this.scale = scale; }
    
    public float[] getColor() { return color; }
    public void setColor(float[] color) { this.color = color; }
    
    public Map<String, Object> getProperties() { return properties; }
    public void setProperty(String key, Object value) { properties.put(key, value); }
    public Object getProperty(String key) { return properties.get(key); }
    
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);
        map.put("position", new float[]{x, y, z});
        map.put("rotation", rotation);
        map.put("scale", scale);
        map.put("color", color);
        map.put("properties", properties);
        return map;
    }
    
    @SuppressWarnings("unchecked")
    public static LevelObject fromMap(Map<String, Object> map) {
        float[] pos = (float[]) map.get("position");
        LevelObject obj = new LevelObject((String) map.get("type"), pos[0], pos[1], pos[2]);
        obj.id = (String) map.getOrDefault("id", obj.id);
        obj.rotation = (float[]) map.getOrDefault("rotation", new float[]{0, 0, 0});
        obj.scale = (float[]) map.getOrDefault("scale", new float[]{1, 1, 1});
        obj.color = (float[]) map.getOrDefault("color", new float[]{1.0f, 1.0f, 1.0f});
        obj.properties = (Map<String, Object>) map.getOrDefault("properties", new HashMap<>());
        return obj;
    }
}
