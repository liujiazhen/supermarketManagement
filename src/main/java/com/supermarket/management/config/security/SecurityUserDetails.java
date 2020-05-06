package com.supermarket.management.config.security;

import com.supermarket.management.entity.Role;
import com.supermarket.management.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUserDetails(User user) {

        if (user != null) {
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setRoles(user.getRoles());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList = new ArrayList<>();
        // 添加角色
        List<Role> roles = this.getRoles();
        if (roles != null && roles.size() > 0) {
            roles.forEach(item -> {
                if (!StringUtils.isEmpty(item.getRolename())) {
                    authorityList.add(new SimpleGrantedAuthority(item.getRolename()));
                }
            });
        }
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
