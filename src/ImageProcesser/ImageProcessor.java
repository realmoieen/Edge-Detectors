/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageProcesser;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author AM Developers
 */
public class ImageProcessor {
    static BufferedImage inputImg,outputImg,returnImage;
static int[][] pixelMatrix=new int[3][3];

    
 public static BufferedImage convertToSobel(File file){
   try {
            
        
        inputImg=ImageIO.read(file);
        outputImg=new BufferedImage(inputImg.getWidth(),inputImg.getHeight(),BufferedImage.TYPE_INT_RGB);

        for(int i=1;i<inputImg.getWidth()-1;i++){
            for(int j=1;j<inputImg.getHeight()-1;j++){
                pixelMatrix[0][0]=new Color(inputImg.getRGB(i-1,j-1)).getRed();
                pixelMatrix[0][1]=new Color(inputImg.getRGB(i-1,j)).getRed();
                pixelMatrix[0][2]=new Color(inputImg.getRGB(i-1,j+1)).getRed();
                pixelMatrix[1][0]=new Color(inputImg.getRGB(i,j-1)).getRed();
                pixelMatrix[1][2]=new Color(inputImg.getRGB(i,j+1)).getRed();
                pixelMatrix[2][0]=new Color(inputImg.getRGB(i+1,j-1)).getRed();
                pixelMatrix[2][1]=new Color(inputImg.getRGB(i+1,j)).getRed();
                pixelMatrix[2][2]=new Color(inputImg.getRGB(i+1,j+1)).getRed();

                int edge=(int) convolution(pixelMatrix);
                outputImg.setRGB(i,j,(edge<<16 | edge<<8 | edge));
            }
        }

        

    } catch (IOException | NullPointerException ex) {
        JOptionPane.showMessageDialog(null,ex);
    }
 return outputImg;
}
 public static double convolution(int[][] pixelMatrix){

    int gy=(pixelMatrix[0][0]*-1)+(pixelMatrix[0][1]*-2)+(pixelMatrix[0][2]*-1)+(pixelMatrix[2][0])+(pixelMatrix[2][1]*2)+(pixelMatrix[2][2]*1);
    int gx=(pixelMatrix[0][0])+(pixelMatrix[0][2]*-1)+(pixelMatrix[1][0]*2)+(pixelMatrix[1][2]*-2)+(pixelMatrix[2][0])+(pixelMatrix[2][2]*-1);
    return Math.sqrt(Math.pow(gy,2)+Math.pow(gx,2));

}
 
 
 ////
 
 public static BufferedImage convertToLaplacian(File file){
     try {
            
        
        inputImg=ImageIO.read(file);
        outputImg=new BufferedImage(inputImg.getWidth(),inputImg.getHeight(),BufferedImage.TYPE_INT_RGB);

        for(int i=1;i<inputImg.getWidth()-1;i++){
            for(int j=1;j<inputImg.getHeight()-1;j++){
                pixelMatrix[0][0]=new Color(inputImg.getRGB(i-1,j-1)).getRed();
                pixelMatrix[0][1]=new Color(inputImg.getRGB(i-1,j)).getRed();
                pixelMatrix[0][2]=new Color(inputImg.getRGB(i-1,j+1)).getRed();
                pixelMatrix[1][0]=new Color(inputImg.getRGB(i,j-1)).getRed();
                pixelMatrix[1][2]=new Color(inputImg.getRGB(i,j+1)).getRed();
                pixelMatrix[2][0]=new Color(inputImg.getRGB(i+1,j-1)).getRed();
                pixelMatrix[2][1]=new Color(inputImg.getRGB(i+1,j)).getRed();
                pixelMatrix[2][2]=new Color(inputImg.getRGB(i+1,j+1)).getRed();

                int edge=(int) laplacianConvolution(pixelMatrix);
                outputImg.setRGB(i,j,edge);
            }
        }

        

    } catch (IOException | NullPointerException ex) {
        JOptionPane.showMessageDialog(null,ex);
    }
 return outputImg;
 }
 


public static int laplacianConvolution(int[][] pixelMatrix){
int lap=(pixelMatrix[1][1]*-4+pixelMatrix[1][0]*1+pixelMatrix[1][2]*1+pixelMatrix[0][1]*1+pixelMatrix[2][1]*1+pixelMatrix[0][0]*0
        +pixelMatrix[0][2]*0+pixelMatrix[2][2]*2+pixelMatrix[2][0]*0);    
        int result=0;
                if(lap<=0){
                    result=0;
                }if(lap>=255){
                    result=255;
                }
                
return result;
}
}
