package spring.io.BetterPlace.Service;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import spring.io.BetterPlace.Enums.JwtStatus;

@Service
public class JWTService {

	private final static SignatureAlgorithm sigAlgorithm = SignatureAlgorithm.HS256;
	private final static String apikeys = "777778888877777888887777788888777778888877777888887777788888";
	private final static String apikeyss = "7777788888777778888877777888887777788888777778888877777888881";	
	
	public static void main(String[] args) {
		System.out.println(JwtStatus.DONE.getMsg());
	}
	
	public String fn_createToken(String loginId) {

        //Header
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", sigAlgorithm);

        //PayLoad
        Map<String, Object> payloadMap = new HashMap<>();
//        payloadMap.put("data1", "data1 inputs");
//        payloadMap.put("data2", "data2 inputs");
//        payloadMap.put("data3", "data3 inputs");
//        Long endTime = 1000 * 60L * 60L * 24L; // 유효 시간(2시간)
        Long endTime = 1000 * 20L;
        Date endTerm = new Date(); // 만료 시간
        endTerm.setTime(endTerm.getTime() + endTime);
     
        Key signKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(apikeys), sigAlgorithm.getJcaName());
        
        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headerMap) // Headers 설정
                .setClaims(payloadMap) // Claims 설정
                .setSubject(loginId) // 토큰 용도  
                .setExpiration(endTerm) // 토큰 만료 시간 설정
                .signWith(signKey, sigAlgorithm) // HS256과 Key로 Sign
                .compact(); // 토큰 생성
        return jwt;
        
    }
    
    //토큰 검증
    public String fn_verifyJWT(String tokens){
        String result = "";
    	try {
            Claims claims = Jwts.parserBuilder()
            					.setSigningKey(DatatypeConverter.parseBase64Binary(apikeyss))
            					.build()
            					.parseClaimsJws(tokens)
            					.getBody();
            claims = null; 
            result = JwtStatus.DONE.toString();
        }catch(MalformedJwtException | SignatureException | UnsupportedJwtException e) {
        	result = JwtStatus.ERROR.toString();
        }
    	catch (ExpiredJwtException e) { // 토큰이 만료되었을 경우
        	e.printStackTrace();
        	result = JwtStatus.EXPIRE.toString();
        } catch (Exception e) { // 그외 에러났을 경우
        	e.printStackTrace();
        	result = JwtStatus.FAILE.toString();
        } finally {
			return result;
		}
    }
   
}
