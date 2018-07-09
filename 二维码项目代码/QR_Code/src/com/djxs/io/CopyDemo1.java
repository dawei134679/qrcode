package com.djxs.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用文件流复制文件
 * 
 * @author adminitartor
 *
 */
public class CopyDemo1 {
	public static void main(String[] args) throws IOException {
		FileInputStream src = new FileInputStream("music.mp3");
		FileOutputStream desc = new FileOutputStream("music_cp.mp3");
		byte[] data = new byte[1024 * 10];
		int len = -1;
		while ((len = src.read(data)) != -1) {
			desc.write(data, 0, len);
		}
		src.close();
		desc.close();
	}
}
