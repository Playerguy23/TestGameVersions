package main.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Fontloader {
	public static Font fontLoader(String path, float size) {
		try{
			return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
		}catch(FontFormatException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
