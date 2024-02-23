package com.tutorhub.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tutorhub.model.user.Role;
import com.tutorhub.model.user.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class SecurityUser implements UserDetails {

  private final Long id;
  private final String username;
  private final String password;
  private final Collection<SimpleGrantedAuthority> authorities;

  public SecurityUser(final User user) {
    this(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
  }

  public SecurityUser(
      final Long id, final String username, final String password, final Set<Role> roles) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities = mapToGrantedAuthorities(new ArrayList<>(roles));
  }

  private static List<SimpleGrantedAuthority> mapToGrantedAuthorities(final List<Role> roles) {
    return roles.stream()
        .map(Enum::name)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  private SecurityUser(final Long id, final String username, final String password) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities = new ArrayList<>();
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
