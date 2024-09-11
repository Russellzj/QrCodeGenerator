package qrcodeapi;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
import qrcodeapi.Responses.Message;

import java.util.List;

@RestController
public class TaskController {
    public final List<Message> MESSAGES = List.of(
            new Message(String.format("Image size must be between %d and %d pixels",
                    QrCodeGenerator.getMIN_SIZE(), QrCodeGenerator.getMAX_SIZE())),
            new Message("Only png, jpeg and gif image types are supported"),
            new Message("Contents cannot be null or blank"),
            new Message("Permitted error correction levels are L, M, Q, H"));


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
    public ResponseEntity<?> qrCode(@RequestParam (defaultValue = "250") int size,
                                    @RequestParam (defaultValue = "png") String type,
                                    @RequestParam String contents,
                                    @RequestParam (defaultValue = "L") String correction) {
        QrCodeGenerator qrCodeSquare = new QrCodeGenerator();
       if (contents.trim().isBlank()) {
            return ResponseEntity.badRequest().
                    contentType(MediaType.APPLICATION_JSON).
                    body(MESSAGES.get(2));
       } else if (!qrCodeSquare.setSize(size)) {
           return ResponseEntity.badRequest().
                   contentType(MediaType.APPLICATION_JSON).
                   body(MESSAGES.get(0));
       } else if (!qrCodeSquare.setCorrectionLevel(correction)) {
           return ResponseEntity.badRequest().
                   contentType(MediaType.APPLICATION_JSON).
                   body(MESSAGES.get(3));
        } else if(!qrCodeSquare.setType(type)) {
           return ResponseEntity.badRequest().
                   contentType(MediaType.APPLICATION_JSON).
                   body(MESSAGES.get(1));
       } else {
           return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/" + qrCodeSquare.getType()))
                    .body(qrCodeSquare.createQrCode(contents));
        }
    }




}
