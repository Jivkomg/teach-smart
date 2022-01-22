package bg.sofia.uni.fmi.piss.project.wevip.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "teach_smart_users")
public class TeachSmartUser extends BaseEntity{

  private String username;

  private String email;

  private String password;

  private String role;

  public TeachSmartUser() {
    this.role = "ROLE_USER";
  }

  public TeachSmartUser(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = "ROLE_USER";
  }

  @Column(name = "username", nullable = false)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name = "email", nullable = false)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "password", nullable = false)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "role", nullable = false)
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Transient
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(this.role));
    return authorities;
  }
}
