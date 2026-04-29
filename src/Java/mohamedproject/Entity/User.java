package LoginService;

public class User {

    private String userName;
    private String hashPassword;
    private String role;
    private boolean isActive;

    public User() {
        this.userName =" ";
        this.hashPassword = " ";
        this.isActive = false;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getRole() {
        return role;
    }
}
