package com.djxs.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
/**
 * java.io.File File类的每一个实例是用于表示操作系统中 文件系统里的一个文件或目录 使用File可以: 1:访问文件或目录的属性信息
 * 2:操作文件或目录(创建,删除) 3:访问一个目录中的子项 但是不能访问文件数据.
 * 
 * @author adminitartor
 *
 */
public class File_info {
	public static void main(String[] args) {
		try {
			File file = new File("D:/test.txt");
			// 获取文件名
			String name = file.getName();
			System.out.println("文件名:" + name);
			// 返回文件的大小(字节量)
			long length = file.length();
			System.out.println("大小:" + length + "字节");
			
			boolean canRead = file.canRead();
			boolean canWrite = file.canWrite();
			System.out.println("可读:" + canRead);
			System.out.println("可写:" + canWrite);
			boolean isHidden = file.isHidden();
			System.out.println("是否隐藏:" + isHidden);
			
			// 最后修改日期 2017年6月28日,9:29:42
			long time = file.lastModified();
			System.out.println(time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日,H:m:s");
			String str = sdf.format(time);
			System.out.println(str);
			
			/*
			 * boolean exists()
			 * 判断当前File表示的文件或目录是否
			 * 已经存在
			 */
			if(!file.exists()){
				file.createNewFile();
				System.out.println("创建完毕!");
			}else{
				System.out.println("该文件已存在!");
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
