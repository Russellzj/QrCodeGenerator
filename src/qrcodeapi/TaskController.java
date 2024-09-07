package qrcodeapi;
import org.apache.tomcat.util.json.JSONParserTokenManager;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.List;

@RestController
public class TaskController {
    @GetMapping("/api/health2")
    public ResponseEntity<Task> returnSystem() {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @GetMapping("/api/health")
    public ResponseEntity<Task> returnStatus() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = "api/qrcode")
    //    public ResponseEntity<BufferedImage> qrCode(@RequestParam int size) {
    public ResponseEntity<?> qrCode(@RequestParam int size,
                                    @RequestParam String type) {
        QrCodeGenerator qrCodeSquare = new QrCodeGenerator();
        if (!qrCodeSquare.setSize(size)) {

            String errorMessage = String.format("Image size must be between %d and %d pixels",
                    qrCodeSquare.getMinSize(),
                    qrCodeSquare.getMaxSize());
            List<String> errorList = List.of(errorMessage);
            return ResponseEntity.badRequest().
                    contentType(MediaType.APPLICATION_JSON).
                    body(errorList.get(0));
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/" + type))
                    .body(qrCodeSquare.createQrCode());
        }
    }




}
