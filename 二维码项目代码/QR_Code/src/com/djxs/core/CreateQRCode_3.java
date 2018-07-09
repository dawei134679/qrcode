package com.djxs.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
/**
 * 生成二维码(QRCode)图片带logo
 * @author Administrator
 *
 */
public class CreateQRCode_3 {
	/**  
     * @param content 二维码图片的内容 
     * @param imgPath 生成二维码图片完整的路径 
     * @param logoPath  二维码图片中间的logo路径 
     */    
    public static int createQRCode(String content, String imgPath,String logoPath,int version) {    
        try {    
            Qrcode qrcodeHandler = new Qrcode();    
            //设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小    
            qrcodeHandler.setQrcodeErrorCorrect('M');    
            //N代表数字,A代表字符a-Z,B代表其他字符  
            qrcodeHandler.setQrcodeEncodeMode('B');   
            // 设置设置二维码版本，取值范围1-40，值越大尺寸越大，可存储的信息越大    
            qrcodeHandler.setQrcodeVersion(version);   
            // 图片尺寸    
            int imgSize =67 + 12 * (version - 1) ;  
    
            byte[] contentBytes = content.getBytes("utf-8");    
            BufferedImage image = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);    
            Graphics2D gs = image.createGraphics();    
    
            gs.setBackground(Color.WHITE);    
            gs.clearRect(0, 0, imgSize, imgSize);  //清除下画板内容  
    
            // 设定图像颜色 > BLACK    
            gs.setColor(Color.BLACK);    
    
            // 设置偏移量 不设置可能导致解析出错    
            int pixoff = 2;    
            // 输出内容 > 二维码    
            if (contentBytes.length > 0 && contentBytes.length < 130) {  
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }  
            } else {    
                System.err.println("QRCode content bytes length = "    
                        + contentBytes.length + " not in [ 0,125]. ");    
                return -1;  
            }    
            Image logo = ImageIO.read(new File(logoPath));//实例化一个Image对象。  
            int logoSize = imgSize/4; 
             /** 
               * logo放在中心 
              */  
            int o = (imgSize - logoSize) / 2;  
            gs.drawImage(logo, o, o, logoSize, logoSize, null);  
            gs.dispose();    
            image.flush();    
    
            // 生成二维码QRCode图片    
            File imgFile = new File(imgPath);    
            ImageIO.write(image, "png", imgFile);
            System.out.println("二维码生成完毕");
    
        } catch (Exception e)   
        {    
            e.printStackTrace();    
            return -1;  
        }    
          
        return 0;  
    }    
  
  
    public static void main(String[] args) {  
	    String imgPath = "D:/二维码生成/logo_QRCode.png";   
	    String logoPath = "D:/logo.jpg";  
	    String encoderContent = "http://www.dijiaxueshe.com";  
	    int createQRCode = CreateQRCode_3.createQRCode(encoderContent, imgPath, logoPath,12);  
	    if(createQRCode == -1) {
	    	System.out.println("生成二维码失败");
	    }
    }
}  
