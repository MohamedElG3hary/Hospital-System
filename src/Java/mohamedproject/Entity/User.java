package Java.mohamedproject.Entity;

public class User {

    private int employeePersonId;
    private String userName;
    private String hashPassword;
    private String role;
    private boolean isActive;

    public User() {
        this.employeePersonId = 0 ;
        this.userName =" ";
        this.hashPassword = " ";
        this.isActive = false;
    }


    public User(int employeePersonId, String userName, String hashPassword, String role, boolean isActive) {
        this.employeePersonId = employeePersonId;
        this.userName = userName;
        this.hashPassword = hashPassword;
        this.role = role;
        this.isActive = isActive;
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

    public void setEmployeePersonId(int employeePersonId) {
        this.employeePersonId = employeePersonId;
    }

    public int getEmployeePersonId() {
        return employeePersonId;
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


    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(role);
    }


    public boolean isReception() {
        return "RECEPTION".equalsIgnoreCase(role);
    }



















}
