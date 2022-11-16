package com.woniu.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class ImageUtils {
	
	
	/**
	 * @param bgUrl : 背景图片在线地址
	 * @param codeUrl：需要叠加的图片地址
	 * @param codeWidth 叠加图片的宽
	 * @param codeHeight 叠加图片高
	 * @param padding  填充矩形底边框与背景底边框的间距
	 * @param margin  填充矩形与叠加图片的间距
	 * @return
	 * @throws Exception
	 */
	public static InputStream overlayImage(String bgUrl,String codeUrl,int codeWidth,int codeHeight,int padding ,int margin)throws Exception {
		//设置背景图片大小
		BufferedImage oriImage=ImageIO.read(new URL(bgUrl));
		BufferedImage bg=resizeImage(oriImage.getWidth(),oriImage.getHeight(),oriImage);
		//new URL(qrCodePath)
		BufferedImage qrCode=resizeImage(codeWidth,codeHeight,ImageIO.read(new URL(codeUrl)));
		Graphics2D g=bg.createGraphics();
		//(从底部往上计算矢量)y的全长为背景图的height, 矩形的y起点：背景.height-边框与背景底边的间距-边框与内图底边的间距-内图.height-上边框与内图上边的间距
		//填充一个白色背景的边框 ：内图.height+20+20 *内图.width+20+20
		//padding是矩形的底边框与背景图底边框的间距，是内图与矩形的间距
//		g.fillRect(padding,  oriImage.getHeight()-padding-margin-codeWidth-margin,  qrCode.getWidth()+margin+margin,  qrCode.getHeight()+margin+margin) ;
		g.drawImage(qrCode, padding+margin,oriImage.getHeight()-codeHeight-padding-margin, qrCode.getWidth(),qrCode.getHeight(), null);
		g.dispose();
		return bufferedImageToInputStream(bg);
	}
	
	/**
	 * 将BufferedImage转换为InputStream
	 * @param image
	 * @return
	 */
	public static InputStream bufferedImageToInputStream(BufferedImage image)throws Exception{
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    ImageIO.write(image, "png", os);
	    return new ByteArrayInputStream(os.toByteArray());
	    
	}
    
	
	public static BufferedImage resizeImage(int x,int y,BufferedImage bfi) {
		BufferedImage bufferedImage=new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);
		bufferedImage.getGraphics().drawImage(bfi.getScaledInstance(x,y,Image.SCALE_SMOOTH), 0,0,null);
		return bufferedImage;
	}	
	
	
	public static ImageInputStream getImageInput(URL input) throws IOException {
        if (input == null) {
            throw new IllegalArgumentException("input == null!");
        }
        InputStream istream = null;
        try {
            istream = input.openStream();
        } catch (IOException e) {
            throw new IIOException("Can't get input stream from URL!", e);
        }        
        return ImageIO.createImageInputStream(istream);      
    }

	
	
}
