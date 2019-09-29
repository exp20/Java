package EE.RMI.server;


import EE.RMI.ImageProcessing;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class ImgHandler implements ImageProcessing {
    public byte[] ImgProcess(byte[] byteImg) {


        try {

            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteImg);
            BufferedImage image = ImageIO.read(inputStream);

            int width = image.getWidth();
            int height = image.getHeight();
            Raster raster = image.getRaster();

            BufferedImage new_image = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            WritableRaster wraster = new_image.getRaster();

            int kernel[][] = new int[][]{{-1, -1, -1}, {-1, 9, -1,}, {-1, -1, -1}};
            int x, y, buffx, buffy;
            int sR, sG, sB;
            int buffPix[];

            x = width - 3;
            y = height - 3;

            for (int i = 0; i < x; i++) {
                buffy = i;
                for (int g = 0; g < y; g++) {
                    buffx = g;
                    sR = 0;
                    sG = 0;
                    sB = 0;

                    for (int k = 0; k < 3; k++) {

                        for (int a = 0; a < 3; a++) {


                            buffPix = raster.getPixel(i + k, g + a, new int[3]);
                            sR += buffPix[0] * kernel[k][a];
                            sG += buffPix[1] * kernel[k][a];
                            sB += buffPix[2] * kernel[k][a];

                        }
                    }
                    if (sR < 0) sR = 0;
                    if (sR > 255) sR = 255;

                    if (sB < 0) sB = 0;
                    if (sB > 255) sB = 255;

                    if (sG < 0) sG = 0;
                    if (sG > 255) sG = 255;
                    //  System.out.println(sR+" "+sB+" "+sG);
                    wraster.setPixel(i + 1, g + 1, new int[]{sR, sG, sB});

                }

            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(new_image, "jpeg", baos);
            baos.flush();
            byte []  new_img_bytes = baos.toByteArray();
            baos.close();
            return new_img_bytes;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
