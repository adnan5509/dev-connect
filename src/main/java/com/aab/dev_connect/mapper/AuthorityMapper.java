package com.aab.dev_connect.mapper;


import com.aab.dev_connect.dto.AuthorityRequest;
import com.aab.dev_connect.dto.AuthorityResponse;
import com.aab.dev_connect.model.Authority;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    Authority AuthorityRequestToAuthority(AuthorityRequest authorityRequest);
    List<Authority> AuthorityRequestToAuthority(List<AuthorityRequest> authorityRequest);

    AuthorityResponse AuthorityToAuthorityResponse(Authority authority);




}
