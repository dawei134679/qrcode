package com.djxs.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode_1 {
	public static void main(String[] args) throws IOException {
		int v =6;
		int width = 127;
		int height = 127;
		Qrcode x = new Qrcode();
		  	x.setQrcodeErrorCorrect('L');
	        x.setQrcodeEncodeMode('B');//注意版本信息 N代表数字 、A代表 a-z,A-Z、B代表 其他)
	        x.setQrcodeVersion(v);//版本号  1-40
	        String qrData = "http://www.dijiaxueshe.com";//内容信息

	        byte[] contentBytes = qrData.getBytes("utf-8");//汉字转格式需要抛出异常

	        //缓冲区
	        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

	        //绘图
	        Graphics2D gs = bufferedImage.createGraphics();

	        gs.setBackground(Color.WHITE);
	        gs.setColor(Color.BLACK);
	        gs.clearRect(0, 0, width, height);

	        //偏移量
	        int pixoff = 2;


	        /**
	         * 容易踩坑的地方
	         * 1.注意for循环里面的i，j的顺序，
	         *   s[j][i]二维数组的j，i的顺序要与这个方法中的 gs.fillRect(j*3+pixoff,i*3+pixoff, 3, 3);
	         *   顺序匹配，否则会出现解析图片是一串数字
	         * 2.注意此判断if (d.length > 0 && d.length < 120)
	         *   是否会引起字符串长度大于120导致生成代码不执行，二维码空白
	         *   根据自己的字符串大小来设置此配置
	         */
	        if (contentBytes.length > 0 && contentBytes.length < 125) {
	            boolean[][] s = x.calQrcode(contentBytes);
	            for (int i = 0; i < s.length; i++) {
	                for (int j = 0; j < s.length; j++) {
	                    if (s[j][i]) {
	                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
	                    }
	                }
	            }
	        }else {
	        	System.err.println("QRCode content bytes length = " + contentBytes.length + " not in [ 0,125]. ");
	        }
	        gs.dispose();//关闭绘图
	        bufferedImage.flush(); //输出缓冲中的资源缓,冲流的flush方法的作用是强制 将缓冲流内部缓冲区已经缓存的数据一次性写出.
	        //设置图片格式，与输出的路径
	        ImageIO.write(bufferedImage, "png", new File("D:/qrcode.png"));
	        System.out.println("二维码生成完毕");
		 
	}
}
