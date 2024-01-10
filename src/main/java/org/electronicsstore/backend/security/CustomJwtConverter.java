package org.electronicsstore.backend.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.List;

public class CustomJwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        List<GrantedAuthority> authorities = extractGrantedAuthorities(source);
        return new CustomJwt(source, authorities);
    }

    private List<GrantedAuthority> extractGrantedAuthorities(Jwt source) {
        var authorities = new ArrayList<GrantedAuthority>();
        var realmAccess = source.getClaimAsMap("realm_access");
        if (realmAccess != null && realmAccess.get("roles") != null) {
            var roles = realmAccess.get("roles");
            if (roles instanceof List list) {
                list.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
            }
        }
        return authorities;
    }


}
