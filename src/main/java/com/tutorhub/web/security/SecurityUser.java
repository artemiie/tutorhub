package com.tutorhub.web.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tutorhub.model.User;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class SecurityUser implements UserDetails {

  private final ObjectId id;
  private final String username;
  private final String password;
  private final Collection<SimpleGrantedAuthority> authorities;

  public SecurityUser(final User user) {
    this(user.getId(), user.getUsername(), user.getPassword());
    this.authorities.add(mapToGrantedAuthorities(user.getRole()));
  }

  private static SimpleGrantedAuthority mapToGrantedAuthorities(final String role) {
    return new SimpleGrantedAuthority(role);
  }

  private SecurityUser(final ObjectId id, final String username, final String password) {
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
