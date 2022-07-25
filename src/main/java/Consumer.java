import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Consumer {
    public static void main(String[] args) {
        final String sensorName = "S333e";
        registerService(sensorName);
        for(int i=0; i <15;i++){
            addMeasurement(sensorName);
        }
    }
    public static void sendRequest(String url, Map<String, Object> mapToSend){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> response = new HttpEntity<>(mapToSend,headers);
        try{
            restTemplate.postForObject(url, response, SensorDTO.class);
        }catch (RestClientException exception){
            System.out.println("ERROR: "+exception.getMessage());
        }
    }
    public static void registerService(String sensorName){
        String url = "http://localhost:8080/sensors/registration";
        Map<String, Object> map = new HashMap<>();
        map.put("name", sensorName);
        sendRequest(url,map);
    }
    public static void addMeasurement(String scannerName){
        String url = "http://localhost:8080/measurements/add";
        Random random = new Random();
        Map<String, Object> map = new HashMap<>();
        map.put("value", generateRandom(-101, 101));
        map.put("raining", random.nextBoolean());
        map.put("sensor", Map.of("name", scannerName));
        sendRequest(url,map);
    }
    public static double generateRandom(double min, double max){
        double diff = max - min;
        Random random = new Random();
        double i =random.nextDouble(diff+1);
        i += min;
        return i;
    }
}
