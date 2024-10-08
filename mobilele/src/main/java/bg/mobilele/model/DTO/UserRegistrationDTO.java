package bg.mobilele.model.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
    @NotBlank
    @Column(name="username", unique = true)
    @Size(min = 3, max = 10)
    private String username;
    @NotBlank
    @Size(min = 4, max = 10)
    private String password;
    @NotBlank
    @Size(min = 4, max = 10)
    private String confirmPassword;
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(min = 3, max = 10)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 10)
    private String lastName;

    private boolean userIsExist;

    private boolean emailIsExist;

    private boolean unConfPass;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isUserIsExist() {
        return userIsExist;
    }

    public void setUserIsExist(boolean userIsExist) {
        this.userIsExist = userIsExist;
    }

    public boolean isEmailIsExist() {
        return emailIsExist;
    }

    public void setEmailIsExist(boolean emailIsExist) {
        this.emailIsExist = emailIsExist;
    }

    public boolean isUnConfPass() {
        return unConfPass;
    }

    public void setUnConfPass(boolean unConfPass) {
        this.unConfPass = unConfPass;
    }
}
