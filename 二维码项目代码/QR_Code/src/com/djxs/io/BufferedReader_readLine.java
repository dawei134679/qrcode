package com.djxs.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * java.io.BufferedLine() 缓冲字符输入流,可以按行读取字符串
 * 
 * @author adminitartor
 *
 */
public class BufferedReader_readLine {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream(
				"src" + File.separator + "day08" + File.separator + "BufferedReader_readLine.java");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		/*
		 * String readLine() 连续读取若干字符,直到读取到换行 符为止,然后将所有字符以一个字符串 的形式返回.注意:返回的这个字符串
		 * 中是不包含最后的换行符的. 若该方法返回值为null,则表示读取到了 文件末尾.将来用BR读取其他设备时若 返回值为null意味着通过该留不可能再
		 * 读取到任何数据.
		 */
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}

		br.close();
	}
}
