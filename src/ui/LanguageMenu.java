package com.ledit.ui;

import com.ledit.core.I18n;
import com.ledit.core.Language;
import javax.swing.*;

/**
 * Меню для переключения языка
 */
public class LanguageMenu extends JMenu {
    
    public LanguageMenu() {
        super("🌍 " + I18n.tr("menu.language"));
        
        // Добавляем пункты для каждого языка
        for (Language lang : Language.values()) {
            JMenuItem item = new JMenuItem(lang.toString());
            item.addActionListener(e -> {
                I18n.getInstance().setLanguage(lang);
                JOptionPane.showMessageDialog(
                    null,
                    "✅ Язык изменён на: " + lang.getDisplayName() + "\n" +
                    "Language changed to: " + lang.getDisplayName(),
                    "Language / Язык",
                    JOptionPane.INFORMATION_MESSAGE
                );
            });
            add(item);
        }
    }
}
