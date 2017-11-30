package kochbuch.project.kochbuch.Benutzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserAdapterService implements UserDetailsService
{
    private final UserRepository userRepository;


    @Autowired
    public UserAdapterService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username)
    {
        User user = userRepository.findByName(username);

        return new UserAdapter(user);
    }
}
