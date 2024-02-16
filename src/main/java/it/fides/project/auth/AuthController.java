package it.fides.project.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.fides.project.models.dtos.SigninDto;
import it.fides.project.models.dtos.SignupDto;
import it.fides.project.models.dtos.UserDto;
import it.fides.project.models.entities.UserEntity;
import it.fides.project.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder bCrypt;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/signup")
	public UserEntity signup(@RequestBody SignupDto signupDto) {

		UserDto user = new UserDto();
        
		user.setFirstName(signupDto.getFirstName());
		user.setLastName(signupDto.getLastName());
		user.setEmail(signupDto.getEmail());
		user.setPassword(bCrypt.encode(signupDto.getPassword()));
		user.setBirthDate(signupDto.getBirthDate());

		return userService.insertUser(user);
    }	
	
	@PostMapping("/signin")
	public String signin(@RequestBody SigninDto signinDto, HttpServletResponse response) {
		
		UserEntity user = userService.getUserByEmail(signinDto.getEmail());
		String token = null;
		
		if (bCrypt.matches(signinDto.getPassword(), user.getPassword())) {				
			token = jwtService.createToken(user);
			
	        Cookie cookie = new Cookie("jwtToken", token);
	        cookie.setPath("/");
	        cookie.setHttpOnly(true);
	        cookie.setMaxAge(3600);
	        response.addCookie(cookie);
		}
		return token;
	}
}
