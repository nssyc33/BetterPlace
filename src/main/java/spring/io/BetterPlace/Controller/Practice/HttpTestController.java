package spring.io.BetterPlace.Controller.Practice;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HttpTestController {

	@RequestMapping("/test")
	public Map test(@RequestParam Map inutMap) {
		Map resultMap = new HashMap();
		String targetURL = "http://localhost:8900/"+"test2";
		try {
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(); 
			factory.setConnectTimeout(1000*20);
			factory.setReadTimeout(1000*20);

			CloseableHttpClient httpClient = HttpClientBuilder.create() 
					.setMaxConnTotal(100) 
					.setMaxConnPerRoute(100)
					.build();
			factory.setHttpClient(httpClient);
			
			RestTemplate restTemplate = new RestTemplate(factory); 
			/*******************요청 본문 세팅********************/
			HashMap itMap = new HashMap();
			itMap.put("key1", "value 1");
			itMap.put("key2", "value 2");
			/*******************요청 본문 세팅********************/
			resultMap = restTemplate.postForObject(targetURL, itMap, HashMap.class);
			
		}catch(Exception e) {
			//NA
		}
		return resultMap;
	}
	
	@RequestMapping("/testtest")
	public Map testtest(@RequestParam Map inutMap) {
		Map resultMap = new HashMap();
		try {
			RestTemplate restTemplate = new RestTemplate();
			String targetURL = "http://localhost:8900/"+"test2";
			String requestJson = "{\"queriedQuestion\":\"Is there pain in your hand?\"}";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
			String answer = restTemplate.postForObject(targetURL, entity, String.class);
			System.out.println("결과 값입니다 : " + answer);
			
		}catch(Exception e) {
			resultMap.put("error", e.getMessage());
		}
		return resultMap;
	}
}
