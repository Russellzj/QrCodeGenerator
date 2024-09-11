package qrcodeapi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    private static final int MAX_SIZE = 350;
    private static final int MIN_SIZE = 150;
    private final List<String> AVAILABLE_FILE_TYPES = List.of("png", "jpeg", "gif");
    private int size;

    private String type;
    private String correctionLevel;


    public boolean setSize(int size) {
        if (size > MAX_SIZE) {
            return false;
        }
        if (size < MIN_SIZE) {
            return false;
        }
        this.size = size;
        return true;
    }

    public boolean setType(String type) {
        if(AVAILABLE_FILE_TYPES.contains(type)) {
            this.type = type;
            return true;
        }
        return false;
    }

    public String getType() {
        return type;
    }

    public static int getMAX_SIZE() { return MAX_SIZE; }

    public static int getMIN_SIZE() {
        return MIN_SIZE;
    }

    public boolean setCorrectionLevel(String correctionLevel) {
        List<String> levels = List.of("L", "M", "Q", "H");
        correctionLevel = correctionLevel.toUpperCase();
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

    public boolean isAllRequiredVariablesSet(){
        if (size == 0 || type == null || correctionLevel == null)
            return false;
        return true;
    }

    public BufferedImage createQrCode(String address) {
        Map<String, ErrorCorrectionLevel> levels = Map.of(
                "L", ErrorCorrectionLevel.L,
                "M", ErrorCorrectionLevel.M,
                "Q", ErrorCorrectionLevel.Q,
                "H", ErrorCorrectionLevel.H);
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
