package Java.mohamedproject.Services.Interfaces;

import Java.mohamedproject.Entity.User;

public interface AuthService {



    User login(String username, String password) throws Exception;


}
