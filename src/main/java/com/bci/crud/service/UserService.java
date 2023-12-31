package com.bci.crud.service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.bci.crud.dao.UserRepository;
import com.bci.crud.entity.User;
import com.bci.crud.responses.userResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Value("${regexpwd}")
	private String tempRegex;
	public ResponseEntity<Object> createUser(User user) {
		
		// Validando correo con expresion regular.
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        Matcher matcher = pattern.matcher(user.getEmail());
        if (matcher.find() == false) {
        	//Muestra mensaje de error al ingresar correo mal formado.
        	Map<String, String> map = new HashMap<>();
        	  map.put("Error", "El correo ingresado no es valido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
        
        Optional<User> optionaluser=userRepository.findByEmail(user.getEmail());
		if(optionaluser.isPresent()) {
			//Muestra mensaje de error al ingresar correo que existe.
        	Map<String, String> map = new HashMap<>();
        	  map.put("Error", "El correo ya fue registrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
		}
		
		
		System.out.println(tempRegex);
		// Validando expresion regular de contrasena.
        Pattern patternpwd = Pattern
                .compile(tempRegex);
        
        Matcher matcherpwd = patternpwd.matcher(user.getPassword());
        if (matcherpwd.find() == false) {
        	//Muestra mensaje de error al ingresar password mal formado.
        	Map<String, String> map = new HashMap<>();
        	  map.put("Error", "El password a crear, no es valido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
		
		//si se cumple todo lo anterior se crea el usuario.
		User newUser = userRepository.save(user);
		//se retorna clase personalizada de salida.
		userResponse customResponse = new userResponse();
		customResponse.setCreated(newUser.getCreated());
		customResponse.setEmail(newUser.getEmail());
		customResponse.setId(newUser.getId());
		customResponse.setIsactive(newUser.isIsactive());
		customResponse.setLast_login(newUser.getLast_login());
		customResponse.setModified(newUser.getModified());
		customResponse.setName(newUser.getName());
		customResponse.setPassword(newUser.getPassword());
		customResponse.setPhones(newUser.getPhones());
		customResponse.setToken(getJWTToken(newUser.getEmail()));
		return ResponseEntity.status(HttpStatus.CREATED).body(customResponse);
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("SimpleJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}
	


}
