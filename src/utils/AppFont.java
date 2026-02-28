package utils;

import java.awt.Font;

public class AppFont {

	private static Font base;
	
	static {
		try {
			base = Font.createFont(
					Font.TRUETYPE_FONT, 
					AppFont.class.getResourceAsStream("/fonts/Roboto.ttf")
			);
			
		} catch(Exception e) {
			base = new Font("Times New Roman", Font.PLAIN, 14);
		}
	}
	
	public static Font normal() {
		return base.deriveFont(16f);
	}
	
	public static Font bold() {
		return base.deriveFont(Font.BOLD, 16f);
	}
	
	public static Font small() {
		return base.deriveFont(14f);
	}
	
	public static Font title() {
		return base.deriveFont(Font.BOLD, 20f);
	}
	
}
