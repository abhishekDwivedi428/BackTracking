package microservice;

import com.backtracking.BackTracking.entity.Application;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Service
public class ApplicationUtil {

    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity<String>> saveApplication()
    {
        RestTemplate restTemplate = new RestTemplate();
        String applicationUrl = "http://localhost:9091/api/v1/applications";

        try{
           URI uri = new URI(applicationUrl);
            Application application = new Application();
            application.setName(" Microservice ");
            application.setOwner("Abhishek");
            application.setDescription("Microservice Data");
            HttpHeaders httpHeaders = new HttpHeaders();
            HttpEntity<Application> httpEntity = new HttpEntity<Application>(application, httpHeaders);
            ResponseEntity<String> response = restTemplate.postForEntity(uri, httpEntity, String.class);
            System.out.println(response);
            return CompletableFuture.completedFuture(response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

       return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity<String>> getApplication()
    {
        RestTemplate restTemplate = new RestTemplate();
        String applicationUrl = "http://localhost:9091/api/v1/applications/2";

        try{
            URI uri = new URI(applicationUrl);
            HttpHeaders httpHeaders = new HttpHeaders();
            HttpEntity<Application> httpEntity = new HttpEntity<Application>(httpHeaders);
            ResponseEntity<String> response = restTemplate.postForEntity(uri, httpEntity, String.class);
            System.out.println(response);
            return CompletableFuture.completedFuture(response);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
