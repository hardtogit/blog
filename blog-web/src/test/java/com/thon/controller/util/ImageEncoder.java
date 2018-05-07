package com.thon.controller.util;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageEncoder {
	public static void main(String[] args) throws IOException
    {
        ImagePress();
    }
	
    public static String GetImageStr()
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = "/Users/thon/Pictures/images.jpeg";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try 
        {
            in = new FileInputStream(imgFile);        
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
       
        return Base64.encodeBase64String(data);
    }
    
    public static void ImagePress() throws IOException {
    	File file = new File("/Users/thon/.sand/not_exist.jpg");
    	
    	//ImageUtil.pressText("不存在", "/Users/thon/Pictures/test1.jpg", "Serif", Font.BOLD, Color.red, 20, 10, 10, 0.8F);
    	Font font = new Font("Serif", Font.PLAIN, 400);
    	generateImage("?", file, font, 800, 800);
    	//ImageUtil.pressText("不/n存在", "/Users/thon/Pictures/test2.jpg", "Serif", Font.BOLD, Color.red, 60, 10, 10, 0.8F);
    }
    
    public static void generateImage(String str, File file, Font font, int height, int width) throws IOException {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = bi.createGraphics();
        g.setColor(new Color(245, 245, 245));
        g.fillRect(0, 0, width, height);
        g.setFont(font);
        g.setColor(Color.WHITE);

        FontMetrics fm = g.getFontMetrics(font);
        java.awt.geom.Rectangle2D rect = fm.getStringBounds(str, g);
        int textWidth = (int) (rect.getWidth());
        int textHeight = (int) (rect.getHeight());

        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2 + fm.getAscent();

        g.drawString(str, x, y);
        g.dispose();
        ImageIO.write(bi, "png", file);
    }
    
}
