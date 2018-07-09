package com.djxs.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


import com.swetake.util.Qrcode;
/**
 * 提取方法
 * @author Administrator
 *
 */
public class CreateQRcode_2 {
	public static void main(String[] args) {
		String content = "http://www.dijiaxueshe.com";
		int version = 10;
		String imgPath = "D:/qrCode12.png";
		int createQRCode = createQRCode(content, imgPath, version);
		if(createQRCode>0) {
			System.out.println("生成成功");
		}else {
			System.out.println("生成失败");
		}
		
	}
	/**  
     * 生成二维码(QRCode)图片  (logo)
     * @param content 二维码图片的内容 
     * @param imgPath 生成二维码图片完整的路径 
     * @param version  二维码图片版本
     */   
	public static int createQRCode(String content, String imgPath, int version) {
		try {
			int width = 67+(version-1)*12;
			int height = 67+(version-1)*12;
			//1.创建Qrcode对象
			//1.1 设置二维码排错率
			//1.2设置二维码可存储的信息类型
			//1.3设置二维码的版本号
			Qrcode qrcode = new Qrcode();
			qrcode.setQrcodeErrorCorrect('L');
			qrcode.setQrcodeEncodeMode('B');
			qrcode.setQrcodeVersion(version);
			
			byte[] bytes = content.getBytes("utf-8");
			//2.获取图片缓存区
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			//3.获取绘图对象
			Graphics2D gs = bufferedImage.createGraphics();
			//3.1设置背景颜色
			gs.setBackground(Color.WHITE);
			//3.2设置前景颜色
			gs.setColor(Color.BLACK);
			//3.3清除画板内容
			gs.clearRect(0, 0, width, height);
			
			//设置偏移量
			int pixoff = 2;
			//4.填充二维码
			
			//4.1判断二维码对应的坐标是否填充颜色
			boolean[][] codeOut = qrcode.calQrcode(bytes);
			for (int i = 0; i < codeOut.length; i++) {
				for (int j = 0; j < codeOut.length; j++) {
					if (codeOut[j][i]) {
						//4.2给二维码对应的位置填充颜色
						gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
					}
				}
			}
			//关闭绘图(释放资源)
			gs.dispose();
			
			//输出缓冲中的资源
			bufferedImage.flush();
			
			File imgfile = new File(imgPath);
			//5.设置图片格式,与输出的路径
			ImageIO.write(bufferedImage, "png", imgfile);
			System.out.println("二维码生成完毕");
		} catch (Exception e) {
			e.getStackTrace();
			return -100;
		}
		return 1;		
	}
}
