package qrcodeapi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

//Imports for QRCode Generation
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

//Imports for Error Correction Levels
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


public class QrCodeGenerator {
    private final int maxSize = 350;
    private final int minSize = 150;
    private int size;

    private String type;
    private char correctionLevel;


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

    public boolean setType(String type) {
        List<String> acceptableTypes = List.of("png", "jpeg", "gif");
        if(acceptableTypes.contains(type)) {
            this.type = type;
            return true;
        }
        return false;
    }

    public String getType() {
        return type;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getMinSize() {
        return minSize;
    }

    public boolean setCorrectionLevel(char correctionLevel) {
        List<Character> levels = List.of('L', 'M', 'Q', 'H');
        correctionLevel = Character.toUpperCase(correctionLevel);
        if (levels.contains(correctionLevel)) {
            this.correctionLevel = correctionLevel;
            return true;
        }
        return false;
    }

    public BufferedImage createQrCodeBlank() {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D square = image.createGraphics();
        square.setColor(Color.WHITE);
        square.fillRect(0, 0, size, size);
        square.dispose();
        return image;
    }

    public BufferedImage createQrCode(String address) {
        Map<Character, ErrorCorrectionLevel> levels = Map.of(
                'L', ErrorCorrectionLevel.L,
                'M', ErrorCorrectionLevel.M,
                'Q', ErrorCorrectionLevel.Q,
                'H', ErrorCorrectionLevel.H);
        QRCodeWriter writer = new QRCodeWriter();
        BufferedImage defaultImage = createQrCodeBlank();
        Map<EncodeHintType, ErrorCorrectionLevel> errorCorrectionLevel =
                Map.of(EncodeHintType.ERROR_CORRECTION, levels.get(correctionLevel));
        try {
            BitMatrix bitMatrix = writer.encode(address, BarcodeFormat.QR_CODE, size, size, errorCorrectionLevel);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            System.out.println(e.getMessage());
        }
        return defaultImage;

    }
}
