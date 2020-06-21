/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageProcesser;

/**
 *
 * @author AM Developers
 */
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class LaplacianOperator {

    public LaplacianOperator(String fileName) {
        try {

            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

            Mat source = org.opencv.imgcodecs.Imgcodecs.imread(fileName, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
            Mat destination = new Mat(source.rows(), source.cols(), source.type());

            ///operator with boundry consider
            float[] laplacian = new float[]{
                -1.0f, -1.0f, -1.0f,
                -1.0f, 8.0f, -1.0f,
                -1.0f, -1.0f, -1.0f
            };

            ///laplacian operator without boundry
            float[] laplacian2 = new float[]{
                0f, 1.0f, 0f,
                1.0f, -4.0f, 1.0f,
                0f, 1.0f, 0f
            };

            Mat kernel = new MatOfFloat(laplacian2);

            Imgproc.filter2D(source, destination, -1, kernel);
            org.opencv.imgcodecs.Imgcodecs.imwrite("laplacian.jpg", destination);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        LaplacianOperator convolution = new LaplacianOperator("C:\\Users\\AM Developers\\Desktop\\images\\grayscale.jpg");
    }

}
