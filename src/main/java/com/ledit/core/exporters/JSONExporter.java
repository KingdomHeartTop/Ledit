package com.ledit.core.exporters;

import com.ledit.core.Level;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class JSONExporter extends BaseExporter {
    
    public JSONExporter(Level level) {
        super(level);
    }
    
    @Override
    public void export(String filepath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filepath)) {
            gson.toJson(level.toMap(), writer);
        }
    }
    
    @Override
    public String getExtension() {
        return ".json";
    }
    
    @Override
    public String getFormatName() {
        return "JSON (Универсальный)";
    }
}
