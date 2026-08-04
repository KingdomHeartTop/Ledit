package com.ledit.core.exporters;

import com.ledit.core.Level;
import com.ledit.core.LevelObject;
import java.io.*;

public class LuaExporter extends BaseExporter {
    
    public LuaExporter(Level level) {
        super(level);
    }
    
    @Override
    public void export(String filepath) throws IOException {
        StringBuilder code = new StringBuilder();
        code.append("-- Level: ").append(level.getName()).append("\n");
        code.append("-- Version: ").append(level.getVersion()).append("\n\n");
        code.append("local level = {\n");
        code.append("    name = \"").append(level.getName()).append("\",\n");
        code.append("    version = \"").append(level.getVersion()).append("\",\n");
        code.append("    objects = {\n");
        
        for (LevelObject obj : level.getObjects()) {
            code.append("        {\n");
            code.append("            type = \"").append(obj.getType()).append("\",\n");
            code.append("            position = {").append(obj.getX()).append(", ").append(obj.getY()).append(", ").append(obj.getZ()).append("},\n");
            code.append("        },\n");
        }
        
        code.append("    }\n");
        code.append("}\n\n");
        code.append("return level\n");
        
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write(code.toString());
        }
    }
    
    @Override
    public String getExtension() {
        return ".lua";
    }
    
    @Override
    public String getFormatName() {
        return "Lua (LÖVE/Defold)";
    }
}
