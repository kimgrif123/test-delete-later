package kochbuch.project.kochbuch.Benutzer;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService
{
/*
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            createUser(true, "Kim", "Griffith", "test");
            createUser(true, "Nick", "Droegemueller", "test");
            createUser(true, "Kai", "Okamoto", "test");

        }
    }

    private void createUser(String fname, String lname, String pswd, Boolean admin) {
        userRepository.save(new User(admin, fname,lname, pswd.encode(password)));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsers() {
        return userRepository.findUsers();
    }
 */
    private void createUser(String fname, String lname, String pswd, Boolean admin)
    {
       new User(admin, fname,lname, pswd);
    }
}
