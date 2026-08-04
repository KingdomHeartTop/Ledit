package com.ledit.core.exporters;

import com.ledit.core.Level;
import java.io.IOException;

public abstract class BaseExporter {
    protected Level level;
    
    public BaseExporter(Level level) {
        this.level = level;
    }
    
    public abstract void export(String filepath) throws IOException;
    public abstract String getExtension();
    public abstract String getFormatName();
}
