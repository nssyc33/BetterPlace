package spring.io.BetterPlace.Controller.Practice;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.io.BetterPlace.Service.JWTService;

@RestController
@RequestMapping("/token")
public class JwtTestController {

	@Autowired
	JWTService jwtService;
	
//	@RequestMapping("/회원가입")
//	public Map fn_회원가입() {
//		return new HashMap();
//	}
//	
//	@RequestMapping("/tokenApiKey")
//	public Map fn_jwtApiKey() {
//		return new HashMap();
//	}
//
//	@RequestMapping("/refreashKey")
//	public Map fn_refreashKey() {
//		return new HashMap();
//	}
	
	@RequestMapping("/key")
	public Map fn_jwtTest() {
		Map returnMap = new HashMap<>();
		returnMap.put("tokenKey", jwtService.fn_createToken("syc"));
		return returnMap;
	}
	
	@RequestMapping("/verify")
	public Map fn_verify(@RequestParam String key) {
		Map returnMap = new HashMap<>();
		returnMap.put("result", jwtService.fn_verifyJWT(key));
		return returnMap;
	}
	
}
