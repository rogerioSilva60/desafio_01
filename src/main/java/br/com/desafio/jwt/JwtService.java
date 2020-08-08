package br.com.desafio.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.desafio.usuario.entity.Usuarios;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JwtService {

	static final String CLAIM_KEY_ID = "id_user";
    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_ROLES = "roles";
    static final String CLAIM_KEY_CREATED = "created";
    
    @Value("${security.jwt.expiration}")
	private Long expiration;
	@Value("${security.jwt.subscription-key}")
	private String subscriptionKey;
	
	public String getToken(Usuarios usuario) {
		JwtUsuario jwtUser = new JwtUsuario(usuario.getId(), usuario.getLogin(), null, usuario.getArrayPapeis());
		Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, jwtUser.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_ID, usuario.getId());
        List<String> roles = new ArrayList<>();
        jwtUser.getAuthorities().forEach(authority -> roles.add(authority.getAuthority()));
        claims.put(CLAIM_KEY_ROLES, roles);
        return generateToken(claims);
	}
	
	public String getToken(JwtUsuario usuario) {
		Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, usuario.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_ID, usuario.getId());
        List<String> roles = new ArrayList<>();
        usuario.getAuthorities().forEach(authority -> roles.add(authority.getAuthority()));
        claims.put(CLAIM_KEY_ROLES, roles);
        return generateToken(claims);
	}
	
	private Claims getClains(String token) throws ExpiredJwtException{
		Claims claims = Jwts
			.parser()
			.setSigningKey(subscriptionKey)
			.parseClaimsJws(token)
			.getBody();
		return claims;
			
	}
	
	public boolean tokenIsValid(String token) {
		try {
			Claims clains = getClains(token);
			Date dateExpiration = clains.getExpiration();
			LocalDateTime date = dateExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(date);
		} catch (Exception e) {
			return false;
		}
	}
	
	private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(subscriptionKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
	
	public String getLoginUsuario(String token) throws ExpiredJwtException{
		String login = getClains(token).getSubject();
		return login;
	}
	
	public JwtUsuario getJwtUsuario(HttpServletRequest request) {
		try {
			String token = request.getHeader("Authorization");
	        if (token != null && token.startsWith("Bearer ")) {
	            token = token.substring(7);
	        }
            Claims claims = getClaimsFromToken(token);
            List<String> listRoles = (List<String>) claims.get("roles");
            String[] roles = new String[listRoles.size()];
            for (int i = 0; i < roles.length; i++) {
				roles[i] = listRoles.get(i).substring(5);
			}
            JwtUsuario jwtUser = new JwtUsuario(Long.parseLong(claims.get("id_user").toString()), claims.getSubject(),
                    null, roles);
            return jwtUser;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }
	
	private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
        		.setClaims(claims)
        		.setExpiration(expirationToDateGenerate())
                .signWith(SignatureAlgorithm.HS512, subscriptionKey)
                .compact();
    }
	
	private Date expirationToDateGenerate() {
        Long expiracao = System.currentTimeMillis() + expiration;
        Date date = new Date(expiracao);
        return date;
    }
}
