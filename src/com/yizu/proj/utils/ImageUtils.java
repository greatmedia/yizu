package com.yizu.proj.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {
	/**
     * 对图片进行放大
     * @param originalImage 原始图片
     * @param times 放大倍数
     * @return
     */
    public static BufferedImage  zoomInImage(BufferedImage  originalImage, Integer times){
        int width = originalImage.getWidth()*times;
        int height = originalImage.getHeight()*times;
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,width,height,null);
        g.dispose();
        return newImage;
    }
    /**
     * 对图片进行放大
     * @param srcPath 原始图片路径(绝对路径)
     * @param newPath 放大后图片路径（绝对路径）
     * @param times 放大倍数
     * @return 是否放大成功
     */
    public static boolean zoomInImage(String srcPath,String newPath,Integer times){
        BufferedImage bufferedImage = null;
        try {
            File of = new File(srcPath);
            if(of.canRead()){
                bufferedImage =  ImageIO.read(of);
            }
        } catch (IOException e) {
            //TODO: 打印日志
            return false;
        }
        if(bufferedImage != null){
            bufferedImage = zoomInImage(bufferedImage,times);
            try {
                //TODO: 这个保存路径需要配置下子好一点
                ImageIO.write(bufferedImage, "JPG", new File(newPath)); //保存修改后的图像,全部保存为JPG格式
            } catch (IOException e) {
                // TODO 打印错误信息
                return false;
            }
        }
        return true;
    }
  //缩放图片比例
    public static void getFixedBoundIcon(String filePath,String fileTarget, int height, int width) throws Exception{
	  double Ratio=0.0;
	  File F = new File(filePath);
	  System.out.println(fileTarget);
	  if (!F.isFile()) throw new Exception(F+" is not image file error in getFixedBoundIcon!");
	  BufferedImage Bi = ImageIO.read(F);
//	  int type = Bi.getType(); 
	  System.out.println(Bi.getWidth()+"    ----     "+Bi.getHeight());
	  if(Bi.getWidth() > Bi.getHeight()){
		  if (Bi.getWidth() > width){
			  Ratio = (new Integer(width)).doubleValue()/Bi.getWidth();
			  width = (int) (Bi.getWidth()*Ratio);
			  height = (int) (Bi.getHeight()*Ratio);
		  }
	  }else{
		  if(Bi.getHeight() > height){
			  Ratio = (new Integer(height)).doubleValue() /Bi.getHeight();
			  height = (int) (Bi.getHeight()*Ratio);
			  width = (int) (Bi.getWidth()*Ratio);
		  }
	  }
	  
      File ThF = new File(fileTarget);//+"_"+height +"_"+width);
      Image image = Bi.getScaledInstance(width, height,Image.SCALE_SMOOTH);
      BufferedImage tag = new BufferedImage(width, height,Bi.TYPE_INT_RGB);
      Graphics g = tag.getGraphics();
      g.drawImage(image, 0, 0, null); // 绘制缩小后的图
      g.dispose();
      String s =(String) fileTarget.substring(fileTarget.lastIndexOf(".")+1, fileTarget.length());
      System.out.println(s);
      ImageIO.write(tag, s, ThF);// 输出到文件流
     }
    /**
     * 对图片进行缩小
     * @param originalImage 原始图片
     * @param times 缩小倍数
     * @return 缩小后的Image
     */
    public static BufferedImage  zoomOutImage(BufferedImage  originalImage, Integer times){
        int width = originalImage.getWidth()/times;
        int height = originalImage.getHeight()/times;
        BufferedImage newImage = new BufferedImage(width,height,originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0,0,width,height,null);
        g.dispose();
        return newImage;
    }
    /**
     * 对图片进行缩小
     * @param srcPath 源图片路径（绝对路径）
     * @param newPath 新图片路径（绝对路径）
     * @param times 缩小倍数
     * @return 保存是否成功
     */
    public static boolean zoomOutImage(String srcPath,String newPath,Integer times){
        BufferedImage bufferedImage = null;
        try {
            File of = new File(srcPath);
            if(of.canRead()){
                bufferedImage =  ImageIO.read(of);
            }
        } catch (IOException e) {
            //TODO: 打印日志
            return false;
        }
        if(bufferedImage != null){
            bufferedImage = zoomOutImage(bufferedImage,times);
            try {
                //TODO: 这个保存路径需要配置下子好一点
                ImageIO.write(bufferedImage, "JPG", new File(newPath)); //保存修改后的图像,全部保存为JPG格式
            } catch (IOException e) {
                // TODO 打印错误信息
                return false;
            }
        }
        return true;
    }
    /** 
     * 图像缩放 jpg格式 
     * 
     * @param imgsrc 
     *             :原图片文件路径 
     * @param imgdist 
     *             :生成的缩略图片文件路径 
     * @param widthdist 
     *             :生成图片的比例宽度 
     * @param heightdist 
     *             :生成图片的高度 
     */   
   public  void reduceImg(String imgsrc, String imgdist, int widthdist,    
           int heightdist) {
       try {    
            File srcfile = new File(imgsrc);    
           if (!srcfile.exists()) {    
               return;
            }    
            Image src = ImageIO.read(srcfile);  
            int imageWidth = src.getWidth(null);   
            int imageHeight = src.getHeight(null);
            widthdist = widthdist * imageWidth / 100;
            heightdist = imageHeight * heightdist /100;
            BufferedImage tag = new BufferedImage((int) widthdist,    
                    (int) heightdist, BufferedImage.TYPE_INT_RGB);  
            
           /* 
             * Image.SCALE_SMOOTH 的缩略算法   生成缩略图片的平滑度的 
             * 优先级比速度高 生成的图片质量比较好 但速度慢 
             */   
            tag.getGraphics().drawImage(    
                    src.getScaledInstance(widthdist, heightdist,    
                            Image.SCALE_SMOOTH), 0, 0, null);    
               
            FileOutputStream out = new FileOutputStream(imgdist);    
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);    
            encoder.encode(tag);    
            out.close();    
  
        } catch (IOException ex) {    
            ex.printStackTrace();    
        }    
    }  
   /**
    * 缩放图像（按比例缩放）
    * @param srcImageFile 源图像文件地址
    * @param result 缩放后的图像地址
    * @param scale 缩放比例
    * @param flag 缩放选择:true 放大; false 缩小;
    */
   public final static void scale(String srcImageFile, String result,
           int scale, boolean flag) {
       try {
           BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
           int width = src.getWidth(); // 得到源图宽
           int height = src.getHeight(); // 得到源图长
           if (flag) {// 放大
               width = width * scale;
               height = height * scale;
           } else {// 缩小
               width = width / scale;
               height = height / scale;
           }
           Image image = src.getScaledInstance(width, height,
                   Image.SCALE_DEFAULT);
           BufferedImage tag = new BufferedImage(width, height,
                   BufferedImage.TYPE_INT_RGB);
           Graphics g = tag.getGraphics();
           g.drawImage(image, 0, 0, null); // 绘制缩小后的图
           g.dispose();
           ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
    public static void main(String[] args) {
//        boolean testIn = zoomInImage("E:/uploadPath/user/imagePath/20130606/20130606210554-484641173.jpg","E:\\in.jpg",4);
//        if(testIn){
//            System.out.println("in ok");
//        }
//        boolean testOut = zoomOutImage("E:/11.jpg","E:\\out.jpg",4);
//        if(testOut){
//            System.out.println("out ok");
//        }
    	try {
			getFixedBoundIcon("E:/uploadPath/user/imagePath/20130606/20130606210554-484641173.jpg","E:/uploadPath/user/imagePath/20130606/20130606210554-4846411731.jpg", 220, 220);
//    		 BufferedImage src = ImageIO.read(new File("E:/uploadPath/user/imagePath/20130606/20130606210554-484641173.jpg")); // 读入文件
//             int width = src.getWidth(); // 得到源图宽
//             int height = src.getHeight(); // 得到源图长
//             System.out.println("width:"+width+"  height:"+height);
//             System.out.println("width:"+width+"  height:"+height);
//             int scale = 0;
//             if(width>height)
//             {
//            	 scale = width/230;
//             }
//             else if(width<height)
//             {
//            	 scale = height/230;
//             }else{
//            	 scale = height/230;
//             }
//    		scale("E:/uploadPath/user/imagePath/20130606/20130606210554-484641173.jpg","E:/uploadPath/user/imagePath/20130606/20130606210554-4846411731.jpg", scale, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
}