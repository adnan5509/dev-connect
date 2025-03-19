package com.aab.dev_connect.mapper;


import com.aab.dev_connect.dto.AuthorityRequest;
import com.aab.dev_connect.dto.UserRegistrationRequest;
import com.aab.dev_connect.dto.UserRegistrationResponse;
import com.aab.dev_connect.model.Authority;
import com.aab.dev_connect.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = AuthorityMapper.class)
public abstract class UserMapper {

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userRegistrationRequest.getPassword()))")
    public abstract User UserRegistrationRequestToUser(UserRegistrationRequest userRegistrationRequest);

    public abstract UserRegistrationResponse UserToUserRegistrationResponse(User user);

    public List<Authority> mapAuthorities(List<AuthorityRequest> authorities, User user) {
        return authorities.stream()
                .map(authorityName -> {
                    Authority authority = new Authority(authorityName.getAuthority());
                    authority.setUser(user); // Set the user reference in each authority
                    return authority;
                })
                .collect(Collectors.toList());
    }



}
