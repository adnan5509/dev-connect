package com.aab.dev_connect.service;

import com.aab.dev_connect.dto.UserRegistrationRequest;
import com.aab.dev_connect.dto.UserRegistrationResponse;
import com.aab.dev_connect.mapper.UserMapper;
import com.aab.dev_connect.model.Authority;
import com.aab.dev_connect.model.User;
import com.aab.dev_connect.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(final UserRepository userRepository, final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        // Debugging roles
        System.out.println("User: " + user.getUsername());
        user.getAuthorities().forEach(role -> System.out.println("ROLE: " + role.getAuthority()));

        List<Authority> authorities = user.getAuthorities().stream()
                .map(role -> new Authority(role.getAuthority())) // Ensure roles are correctly mapped
                .collect(Collectors.toList());

        return new User(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public UserRegistrationResponse registerNewUser(final UserRegistrationRequest userRegistrationRequest) {
        User user = userMapper.UserRegistrationRequestToUser(userRegistrationRequest);
        user.setAuthorities(userMapper.mapAuthorities(userRegistrationRequest.getAuthorities(), user));
        return userMapper.UserToUserRegistrationResponse(userRepository.save(user));
    }
}
