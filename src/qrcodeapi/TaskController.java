package qrcodeapi;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("api/qrcode")
    public ResponseEntity<Task> notImplemented() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
