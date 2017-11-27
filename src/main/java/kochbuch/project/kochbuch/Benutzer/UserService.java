package kochbuch.project.kochbuch.Benutzer;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService
{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        if (userRepository.count() == 0) {
            createUser("Kim", "Griffith", "test", true);
            createUser("Nick", "Droegemueller", "test", true);
            createUser("Kai", "Okamoto", "test", true);

        }
    }

    private void createUser(String fname, String lname, String pswd, Boolean admin) {
        userRepository.save(new User(admin, fname,lname, pswd));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
/*
    public List<User> findUsers() {
        return userRepository.findUsers();
    }
*/
}
