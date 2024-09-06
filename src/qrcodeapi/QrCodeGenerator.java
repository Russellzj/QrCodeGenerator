package qrcodeapi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class QrCodeGenerator {
    private final int maxSize = 350;
    private final int minSize = 150;
    private int size;


    public boolean setSize(int size) {
        if (size > maxSize) {
            return false;
        }
        if (size < minSize) {
            return false;
        }
        this.size = size;
        return true;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getMinSize() {
        return minSize;
    }

    public BufferedImage createQrCode() {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D square = image.createGraphics();
        square.setColor(Color.WHITE);
        square.fillRect(0, 0, size, size);
        square.dispose();
        return image;
    }
}
