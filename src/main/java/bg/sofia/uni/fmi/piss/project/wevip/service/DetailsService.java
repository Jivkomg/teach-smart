package bg.sofia.uni.fmi.piss.project.wevip.service;

import bg.sofia.uni.fmi.piss.project.wevip.model.WevipUser;
import bg.sofia.uni.fmi.piss.project.wevip.repository.WevipUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailsService implements UserDetailsService {

  @Autowired
  WevipUserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    WevipUser user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(String.format("%s was not found", username));
    }

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        AuthorityUtils.createAuthorityList(user.getRole())
    );
  }
}