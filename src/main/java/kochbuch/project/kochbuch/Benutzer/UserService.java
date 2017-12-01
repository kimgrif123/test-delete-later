package kochbuch.project.kochbuch.Benutzer;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void init() {
        if (userRepository.count() == 0) {
            createUser("Kim", "test", "ADMIN");
            createUser("Nick",  "test", "ADMIN");
            createUser("Kai", "test", "ADMIN");

            System.out.println(userRepository.count());
        }
    }

    public void createUser(String uname, String pswd, String role) {
        userRepository.save(new User(role, uname, passwordEncoder.encode(pswd)));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAdmins() {
        return userRepository.findAdmins();
    }

    public User findUserByName(String username){return userRepository.findByName(username);}

}
