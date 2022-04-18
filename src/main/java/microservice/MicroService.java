package microservice;

import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public class MicroService {

    public static void main(String[] args) {
        ApplicationUtil applicationUtil = new ApplicationUtil();

        CompletableFuture<ResponseEntity<String>> employeeAddress = applicationUtil.saveApplication();
        CompletableFuture<ResponseEntity<String>> employeeName = applicationUtil.getApplication();

        // Wait until they are all done
        CompletableFuture.allOf(employeeAddress, employeeName).join();

    }
}
