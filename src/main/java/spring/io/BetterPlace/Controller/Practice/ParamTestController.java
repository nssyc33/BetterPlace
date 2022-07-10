package spring.io.BetterPlace.Controller.Practice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParamTestController {

	@RequestMapping("/test1")
	public Map fn_test1(@RequestParam Map inputMap) {
		Map returnMap = new HashMap();
		System.out.println("중간 값 확인 test1 : " +  inputMap.toString());
		returnMap = inputMap;
		return returnMap;
	}
	
	@PostMapping(path = "/test2", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Map fn_test2(@RequestBody Map inputMap) {
		Map returnMap = new HashMap();
		System.out.println("중간 값 확인 test2-1 : " +  inputMap.toString());
		returnMap = inputMap;
		return returnMap;
	}
	
	@PostMapping(path = "/test2")
	public Map fn_test2_another(@RequestBody Map inputMap) {
		Map returnMap = new HashMap();
		System.out.println("중간 값 확인 test2-2 : " +  inputMap.toString());
		returnMap = inputMap;
		return returnMap;
	}
	
}
