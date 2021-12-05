package com.applab.gli.service;

import com.applab.gli.domain.User;
import com.applab.gli.enumeration.Status;
import com.applab.gli.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.applab.gli.enumeration.Status.ACTIVE;

@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser =  userRepository.findByUserNameIgnoreCaseAndStatus(username, ACTIVE);
        if (optionalUser.isEmpty()) {
            final String userNotFoundMsg = String.format("User with username %s not found!!!", username);
            log.info(userNotFoundMsg);
            throw new UsernameNotFoundException(userNotFoundMsg);
        }

        List<GrantedAuthority> authorities = optionalUser.get().getRoles()
            .stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getDescription()))
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(optionalUser.get().getUserName(), optionalUser.get().getPassword(), optionalUser.get().getStatus() == ACTIVE, true, true, true, authorities);
    }

    @Override
    public User saveUser(User newUser) {
        Optional<User> optionalUser = userRepository.findByUserNameIgnoreCaseAndStatus(newUser.getUserName(), ACTIVE);
        if (optionalUser.isPresent()) { return null; }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(newUser);
    }
}
