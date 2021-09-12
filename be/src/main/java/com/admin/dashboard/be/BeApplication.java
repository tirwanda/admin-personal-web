package com.admin.dashboard.be;

import com.admin.dashboard.be.entity.Authority;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BeApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailRepository userDetailRepository;

	public static void main(String[] args) {
		SpringApplication.run(BeApplication.class, args);
	}

	@PostConstruct
	protected void init() {
		List<Authority> authorityList = new ArrayList<>();
		authorityList.add(createAuthority("USER", "User role"));
		authorityList.add(createAuthority("ADMIN", "Admin role"));

		User user = new User();
		user.setUserName("tirwanda");
		user.setFirstName("Edho");
		user.setLastName("Dwi");
		user.setUserPassword(passwordEncoder.encode("rahasia"));
		user.setEnabled(true);
		user.setAuthorities(authorityList);

		userDetailRepository.save(user);
	}

	private Authority createAuthority(String roleCode, String roleDescription) {
		Authority authority = new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
	}
}
