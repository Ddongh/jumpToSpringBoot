package com.mysite.sbb.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String username, String email, String password) { //User 데이터를 생성
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		user.setPassword(passwordEncoder.encode(password)); //사용자의 비밀번호는 보안을 위해 반드시 암호화하여 저장
		this.userRepository.save(user);
		return user;
	}

}
