package com.djxs.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * PrintWriter在流连接中的应用
 * 
 * @author adminitartor
 *
 */
public class PrintWriter_println2 {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		/*
		 * PrintWriter(OutputStream out) PrintWriter(Writer writer)
		 * 
		 */
		FileOutputStream fos = new FileOutputStream("pw2.txt");
		/*
		 * 在流连接中若希望按照指定字符集写出,需要 自行连接转换流OSW并指定字符集.
		 */
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		PrintWriter pw = new PrintWriter(osw);

		pw.println("摩擦摩擦,似魔鬼的步伐.");
		pw.println("在光滑的马路牙子上打出溜滑!");

		System.out.println("写出完毕!");
		pw.close();
	}
}
