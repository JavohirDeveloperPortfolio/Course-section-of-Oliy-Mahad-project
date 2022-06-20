package uz.oliymahad.courseservice.service.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.oliymahad.userservice.model.entity.UserEntity;
import uz.oliymahad.userservice.repository.UserRepository;
import uz.oliymahad.userservice.security.oauth2.UserPrincipal;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
//    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Optional<UserEntity> oUser = userRepository.findByEmail(email);

        UserEntity user = oUser
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
                );

        return UserPrincipal.create(user);
    }
}
