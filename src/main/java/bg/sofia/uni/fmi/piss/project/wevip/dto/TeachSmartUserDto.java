package bg.sofia.uni.fmi.piss.project.wevip.dto;

import javax.validation.constraints.NotNull;

public class TeachSmartUserDto {
    private String userId;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String role;

    public TeachSmartUserDto() {
    }

    public TeachSmartUserDto(String userId, @NotNull String username, @NotNull String email, @NotNull String password, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
