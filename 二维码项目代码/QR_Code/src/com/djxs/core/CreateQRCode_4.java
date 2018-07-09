
package com.djxs.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * 生成二维码(QRCode)图片带logo和渐变
 * 
 * @author Administrator
 *
 */
public class CreateQRCode_4 {
	/**
	 * @param content
	 *            二维码图片的内容
	 * @param imgPath
	 *            生成二维码图片完整的路径
	 * @param logoPath
	 *            二维码图片中间的logo路径
	 */
	public static int createQRCode(String content, String imgPath, String logoPath, int version , String startRgb, String endRgb) {
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('L');
			// N代表数字,A代表字符a-Z,B代表其他字符
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码版本，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(version);
			// 图片尺寸
			int imgSize = 67 + 12 * (version - 1);
			// logo尺寸
			int logoSize = imgSize / 4;

			byte[] contentBytes = content.getBytes("utf-8");
			BufferedImage image = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = image.createGraphics();

			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize); // 清除下画板内容

			// 设定图像颜色 > BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量 不设置可能导致解析出错
			int pixoff = 2;
			int startR = 0,startG = 0,startB = 0;
			if(null != startRgb) {
				String[] rgb = startRgb.split(",");
				startR = Integer.valueOf(rgb[0]);
				startG = Integer.valueOf(rgb[1]);
				startB = Integer.valueOf(rgb[2]);
			}
			int endR = 255,endG = 255,endB = 255;
			if(null != endRgb) {
				String[] rgb = endRgb.split(",");
				endR = Integer.valueOf(rgb[0]);
				endG = Integer.valueOf(rgb[1]);
				endB = Integer.valueOf(rgb[2]);
			}
			// 输出内容 > 二维码
			if (contentBytes.length > 0 && contentBytes.length < 1000) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {//控制行
					for (int j = 0; j < codeOut.length; j++) {//控制列
						if (codeOut[j][i]) {
							if (codeOut[j][i]) {
								int num1 = (int) (startR + (endR - startB) / logoSize * (j + 1));
								int num2 = (int) (startG + (endG - startG) / logoSize * (j + 1));
								int num3 = (int) (startB + (endB - startB) / logoSize * (j + 1));
								if (num1 > 255) {
									num1 = 255;
								}
								if (num2 > 255) {
									num2 = 255;
								}
								if (num3 > 255) {
									num3 = 255;
								}
								Color color = new Color(num1, num2, num3);
								gs.setColor(color);
								gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
							}
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = " + contentBytes.length + " not in [ 0,1000]. ");
				return -1;
			}
			Image logo = ImageIO.read(new File(logoPath));// 实例化一个Image对象。
			
			//logo放在中心
			int o = (imgSize - logoSize) / 2;
			gs.drawImage(logo, o, o, logoSize, logoSize, null);
			gs.dispose();
			image.flush();

			//生成二维码QRCode图片
			File imgFile = new File(imgPath);
			ImageIO.write(image, "png", imgFile);
			System.out.println("二维码生成完毕");

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	public static void main(String[] args) {
		String imgPath = "D:/二维码生成/logo_QRCode.png";
		String logoPath = "D:/logo.jpg";
		//通过VCard特殊格式实现面片二维码
		String content = "BEGIN:VCARD\r\n" + 
						  "N:姓;名:李大为;;;\r\n" + 
						  "FN: 名:李大为  姓\r\n" + 
						  "TITLE:java开发工程师\r\n" + 
						  "ADR;WORK:;;秦皇岛金梦海湾第嘉传媒;;;;\r\n" + 
						  "TEL;CELL,VOICE:18603369235\r\n" + 
						  "TEL;WORK,VOICE:0335-1111111\r\n" + 
						  "URL;WORK:http://www.dijiaxueshe.com\r\n" + 
						  "EMAIL;INTERNET,HOME:532231254@qq.com\r\n" + 
						  "END:VCARD";
		int createQRCode = CreateQRCode_4.createQRCode(content, imgPath, logoPath, 12, "50,155,0", "100,200,50");
		if (createQRCode == -1) {
			System.out.println("生成二维码失败");
		}
	}
}
