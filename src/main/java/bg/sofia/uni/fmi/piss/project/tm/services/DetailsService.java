package bg.sofia.uni.fmi.piss.project.tm.services;

import java.util.Optional;

import bg.sofia.uni.fmi.piss.project.tm.models.TeachSmartUser;
import bg.sofia.uni.fmi.piss.project.tm.repositories.TeachSmartUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailsService implements UserDetailsService {

    private TeachSmartUserRepository userRepository;

    @Autowired
    public DetailsService(TeachSmartUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<TeachSmartUser> user = this.userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("%s was not found", username));
        }

        return new org.springframework.security.core.userdetails.User(
            user.get().getUsername(),
            user.get().getPassword(),
            AuthorityUtils.createAuthorityList(user.get().getRole())
        );
    }
}