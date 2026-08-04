package com.ledit.ui;

import com.ledit.core.I18n;
import com.ledit.core.Language;
import com.ledit.core.PluginManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private JLabel statusLabel;
    private JMenu languageMenu;
    
    public MainWindow() {
        // Заголовок с локализацией
        setTitle(I18n.tr("app.title"));
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        createMenuBar();
        createToolbar();
        createStatusBar();
        
        // Обновляем UI при смене языка
        // (можно добавить слушатель)
    }
    
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // Файл
        JMenu fileMenu = new JMenu(I18n.tr("menu.file"));
        // ... пункты меню с локализацией ...
        
        // Язык
        languageMenu = new LanguageMenu();
        menuBar.add(languageMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void createStatusBar() {
        statusLabel = new JLabel(I18n.tr("status.ready"));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(statusLabel, BorderLayout.SOUTH);
    }
    
    /**
     * Обновить статус с локализацией
     */
    public void setStatus(String key, Object... params) {
        String text = I18n.tr(key, params);
        statusLabel.setText(text);
    }
}
