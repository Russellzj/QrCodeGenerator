package qrcodeapi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class QrCodeGenerator {
    public static BufferedImage createQrCode() {
        BufferedImage image = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
        Graphics2D square = image.createGraphics();
        square.setColor(Color.WHITE);
        square.fillRect(0, 0, 250, 250);
        square.dispose();
        return image;
    }
}
