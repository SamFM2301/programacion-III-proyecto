package utils;

import java.awt.Font;
import java.io.InputStream;

public class AppFonts {
	private static Font baseFont;

    static {
        try {
            InputStream is = AppFonts.class.getResourceAsStream("/assets/font/Roboto.ttf");
            baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
            System.out.println("Se cargo la fuente");
        } catch (Exception e) {
            System.out.println(e);
            baseFont = new Font("Arial", Font.PLAIN, 14);
            System.out.println("NO se cargo la fuente");
        }
    }

    // Texto normal
    public static Font regular(float size) {
        return baseFont.deriveFont(Font.PLAIN, size);
    }

    // Negrita
    public static Font bold(float size) {
        return baseFont.deriveFont(Font.BOLD, size);
    }

    // Títulos grandes
    public static Font title() {
        return baseFont.deriveFont(Font.BOLD, 20f);
    }

    // Subtítulos
    public static Font subtitle() {
        return baseFont.deriveFont(Font.PLAIN, 18f);
    }

    // Botones
    public static Font button() {
        return baseFont.deriveFont(Font.BOLD, 15f);
    }

    // Texto pequeño
    public static Font small() {
        return baseFont.deriveFont(Font.PLAIN, 12f);
    }
}
