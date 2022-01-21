package bg.sofia.uni.fmi.piss.project.wevip.dto;

import javax.validation.constraints.NotNull;

public class WevipUserDto {
  private Long userId;
  @NotNull
  private String username;
  @NotNull
  private String email;
  @NotNull
  private String password;
  private String role;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setName(String name) {
    this.username = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
