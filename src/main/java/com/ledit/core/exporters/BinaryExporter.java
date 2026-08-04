package com.ledit.core.exporters;

import com.ledit.core.Level;
import com.ledit.core.LevelObject;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryExporter extends BaseExporter {
    
    public BinaryExporter(Level level) {
        super(level);
    }
    
    @Override
    public void export(String filepath) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filepath))) {
            // Магическое число
            out.writeBytes("LVL2");
            
            // Количество объектов
            out.writeInt(level.getObjects().size());
            
            for (LevelObject obj : level.getObjects()) {
                // Тип (32 байта)
                byte[] typeBytes = new byte[32];
                byte[] typeStr = obj.getType().getBytes();
                System.arraycopy(typeStr, 0, typeBytes, 0, Math.min(typeStr.length, 32));
                out.write(typeBytes);
                
                // Позиция (3 float)
                out.writeFloat(obj.getX());
                out.writeFloat(obj.getY());
                out.writeFloat(obj.getZ());
            }
        }
    }
    
    @Override
    public String getExtension() {
        return ".bin";
    }
    
    @Override
    public String getFormatName() {
        return "Binary (Быстрый)";
    }
}
