package com.core.was.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


@Getter
@Setter
@ToString
public class Member implements UserDetails {

    private int id;
    private String loginId;
    private String loginPassword;
    private String email;
    private String mobileNo;
    private String name;
    private String userAuth;
    private LocalDateTime createTime;
    private String deleteYn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(userAuth));

        return auth;
    }

    @Override
    public String getPassword() {
        return this.loginPassword;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.loginId;
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
