package com.album.service.api;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by akarpinska on 5/20/14.
 */
public class ImageProcessor {

    private static final int previewWidth = 140;

    public static byte[] createImagePreview(byte[] source) {
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(source));

            double width = image.getWidth();
            double height = image.getHeight();
            double previewHeight = (double) previewWidth *(height/width);

            BufferedImage preview = new BufferedImage(previewWidth, (int) previewHeight, BufferedImage.TYPE_INT_RGB);
            Graphics g = preview.createGraphics();
            g.drawImage(image, 0, 0, previewWidth, (int) previewHeight, null);
            g.dispose();

            return imageToByteArray(preview);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] imageToByteArray(BufferedImage image) {
        try {
            ByteArrayOutputStream imageWriter = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", imageWriter);
            return imageWriter.toByteArray();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return null;
    }
}
