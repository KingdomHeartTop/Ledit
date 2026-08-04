package com.ledit.core.exporters;

import com.ledit.core.Level;
import com.ledit.core.LevelObject;
import java.io.*;

public class JavaExporter extends BaseExporter {
    
    public JavaExporter(Level level) {
        super(level);
    }
    
    @Override
    public void export(String filepath) throws IOException {
        String className = new File(filepath).getName().replace(".java", "");
        StringBuilder code = new StringBuilder();
        
        code.append("public class ").append(className).append(" {\n");
        code.append("    public static final String VERSION = \"").append(level.getVersion()).append("\";\n");
        code.append("    \n");
        code.append("    public static LevelObject[] OBJECTS = {\n");
        
        for (int i = 0; i < level.getObjects().size(); i++) {
            LevelObject obj = level.getObjects().get(i);
            code.append("        new LevelObject(\n");
            code.append("            \"").append(obj.getType()).append("\",\n");
            code.append("            new float[]{").append(obj.getX()).append("f, ").append(obj.getY()).append("f, ").append(obj.getZ()).append("f}\n");
            code.append("        )");
            if (i < level.getObjects().size() - 1) {
                code.append(",");
            }
            code.append("\n");
        }
        
        code.append("    };\n");
        code.append("    \n");
        code.append("    static class LevelObject {\n");
        code.append("        public String type;\n");
        code.append("        public float[] position;\n");
        code.append("        \n");
        code.append("        public LevelObject(String type, float[] position) {\n");
        code.append("            this.type = type;\n");
        code.append("            this.position = position;\n");
        code.append("        }\n");
        code.append("    }\n");
        code.append("}\n");
        
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write(code.toString());
        }
    }
    
    @Override
    public String getExtension() {
        return ".java";
    }
    
    @Override
    public String getFormatName() {
        return "Java (Android)";
    }
}
