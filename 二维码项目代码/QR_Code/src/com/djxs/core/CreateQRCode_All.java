package com.djxs.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
/**
 * 完整版
 * @author LDW
 *
 */
public class CreateQRCode_All {
	public static void main(String[] args) {
		int version = 12;
		//二维码的内容
		String content = "http://dijiaxueshe.com";
		String content1 = "dijiaxueshe";
		String content2 = "123456";
		//通过VCard特殊格式实现面片二维码
		String content3 = "BEGIN:VCARD\r\n" + 
						  "PHOTO;VALUE=uri:http://img4.imgtn.bdimg.com/it/u=3630352509,3120025421&fm=27&gp=0.jpg\r\n" + 
						  "N:姓;名:李大为;;;\r\n" + 
						  "FN: 名:李大为  姓\r\n" + 
						  "TITLE:java开发工程师\r\n" + 
						  "ADR;WORK:;;秦皇岛金梦海湾第嘉传媒;;;;\r\n" + 
						  "TEL;CELL,VOICE:18603369235\r\n" + 
						  "TEL;WORK,VOICE:0335-1111111\r\n" + 
						  "URL;WORK:http://www.dijiaxueshe.com\r\n" + 
						  "EMAIL;INTERNET,HOME:532231254@qq.com\r\n" + 
						  "END:VCARD";
		String imgPath = "D:/qrcode.png";
		String logoPath = "D:/logo.jpg";
		CreateQRCode_All.generateQRCode(version, content3, imgPath, logoPath , "255,0,0", "0,255,255");
	}
	/**
	 * 
	 * @param version	二维码版本
	 * @param content	二维码内容
	 * @param imgPath	二维码保存路径
	 * @param logoPath	logo路径
	 * @param startRGB	渐变起始色
	 * @param endRGB	渐变结束色
	 * @return
	 */
	public static int generateQRCode(int version, String content, String imgPath, String logoPath, String startRGB, String endRGB) {
		try {
			//二维码大小
			int imgSize = 67+(version-1)*12;
			//1.创建Qrcode对象
			Qrcode qrcode = new Qrcode();
			//1.1设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcode.setQrcodeErrorCorrect('L');
			//1.2设置二维码可存储的信息类型可以是N,A,B
			//N:是数字类型
			//A:a-z,A-Z
			//B:任意字符
			qrcode.setQrcodeEncodeMode('B');
			//1.3设置二维码的版本号,可以是1-40,版本号越大二维码可以包含的信息就越大,二维码也就越复杂
			qrcode.setQrcodeVersion(version);
			//2.获取图片缓存区
			BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_BGR);
			//3.获取绘图对象
			Graphics2D gs = bufferedImage.createGraphics();
			//3.1设置背景颜色
			gs.setBackground(Color.WHITE);
			//设置二维码要填充的设置
			//颜色可以自定义
			//3.2设置前景颜色
			gs.setColor(Color.BLACK);
			//3.3清除画板内容
			gs.clearRect(0, 0, imgSize, imgSize);
			//获取二维码对应位置是否要填充颜色
			boolean[][] calQrcode = qrcode.calQrcode(content.getBytes("utf-8"));
			//设置偏移量
			int pixoff = 2;
			
			int startR=0,startG=0,startB=0;
			if (null != startRGB) {
				String [] rgb = startRGB.split(",");
				startR = Integer.valueOf(rgb[0]);
				startG = Integer.valueOf(rgb[1]);
				startB = Integer.valueOf(rgb[2]);
			}
			int endR=0,endG=0,endB=0;
			if(null != startRGB) {
				String [] rgb = endRGB.split(",");
				endR = Integer.valueOf(rgb[0]);
				endG = Integer.valueOf(rgb[1]);
				endB = Integer.valueOf(rgb[2]);
			}
			
			for (int i = 0; i < calQrcode.length; i++) {
				for (int j = 0; j < calQrcode.length; j++) {
					if (calQrcode[i][j]) {
						int num1 = startR+(endR-startR)*(i+1)/calQrcode.length;
						int num2 = startG+(endG-startG)*(i+1)/calQrcode.length;
						int num3 = startB+(endB-startB)*(i+1)/calQrcode.length;
						if (num1 > 255) {
							num1 = 255;
						}
						if (num2 > 255) {
							num2 = 255;
						}
						if (num3 > 255) {
							num3 = 255;
						}
						/*if(20<=i && 22>=i) {
							continue;
						}
						if(20<=j && 22>=j) {
							continue;
						}*/
						Color color = new Color(num1, num2, num3);
						gs.setColor(color);
						gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
					}
				}
			}
			//读取logo头像
			BufferedImage logo = ImageIO.read(new File(logoPath));
			int logoSize = imgSize/4;
			int o = (imgSize - logoSize)/2;
			//给图片缓存对象添加logo图像
			gs.drawImage(logo, o, o, logoSize, logoSize, null);
			//关闭绘图
			gs.dispose();
			//输出缓冲中的资源
			bufferedImage.flush();
			//设置图片格式，与输出的路径
			ImageIO.write(bufferedImage, "png", new File(imgPath));
			System.out.println("输出完毕");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("二维码生成失败");
			return -1;
		}
		return 1;
	}
}
