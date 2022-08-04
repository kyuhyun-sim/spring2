package com.sparta.springcore.security;

import com.sparta.springcore.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }//비밀번호

    @Override
    public String getUsername() {
        return user.getUsername();
    }//아이디

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }//계정 만료여부, true는 만료안됨

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }//계정 잠김 여부. true는 잠기지 않음

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }//비밀번호 만료 여부, true는 만료안됨

    @Override
    public boolean isEnabled() {
        return true;
    }//사용자 활성화 여부, true 활성화 상태

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority>authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(getUsername()));
        return authorities;
// 스프링시큐리티가 권한을 확인 가능하게 해줌
    }


}