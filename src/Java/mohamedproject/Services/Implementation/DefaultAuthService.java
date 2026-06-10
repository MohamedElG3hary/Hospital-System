package Java.mohamedproject.Services.Implementation;

import Java.mohamedproject.Entity.User;
import Java.mohamedproject.Repository.Interfaces.AuthRepository;
import Java.mohamedproject.Services.Interfaces.AuthService;
import Java.mohamedproject.util.DatabaseConnection;

import java.sql.Connection;

public class DefaultAuthService implements AuthService {



    private final AuthRepository authRepository;

    public DefaultAuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public User login(String username, String password) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        User user = authRepository.login(connection, username, password);

        if (user == null) {
            throw new Exception("Invalid username or password.");
        }

        return user;
    }

}
