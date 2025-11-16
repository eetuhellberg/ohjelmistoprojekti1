package syksy25.card.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import syksy25.card.domain.AppUser;
import syksy25.card.domain.AppUserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public UserDetailsServiceImplementation(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = appUserRepository.findByUsername(username);

        AppUser appUser = optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .authorities("ROLE_" + appUser.getRole().replace("ROLE_", ""))
                .build();
    }
}