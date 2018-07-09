package com.djxs.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 缓冲流 java.io.BufferedInputStream java.io.BufferedOutputStream
 * 缓冲流是一对高级流,使用它们可以提高读写 效率
 * 
 * @author adminitartor
 *
 */
public class CopyDemo2 {
	public static void main(String[] args) throws IOException {
		FileInputStream src = new FileInputStream("music.mp3");
		BufferedInputStream bis = new BufferedInputStream(src);

		FileOutputStream desc = new FileOutputStream("music_cp2.mp3");
		BufferedOutputStream bos = new BufferedOutputStream(desc);

		int d = -1;
		/*
		 * 缓冲流内部维护一个字节数组,实际上 也是通过提高每次实际读写的字节量 减少读写次数来提高的读写效率. 例如: 当调用bis.read()方法读取一个字节时
		 * 实际上bis会通过文件流一次性读取若干 字节并存入内部的字节数组,然后只将 第一个字节返回.当后面再次调用read
		 * 方法读取一个字节时,bis则直接将数组中 下一个字节返回,直到所有字节都已经 返回后才会再次读取一组字节回来.
		 * 
		 * 
		 */
		while ((d = bis.read()) != -1) {
			bos.write(d);
		}

		System.out.println("复制完毕!");
		bis.close();
		bos.close();
	}
}
