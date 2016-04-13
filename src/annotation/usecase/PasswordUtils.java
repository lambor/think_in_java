package annotation.usecase;

import java.util.List;

/**
 * Created by Administrator on 2016/4/12.
 */
public class PasswordUtils {
    @UseCase(id=47,description = "Passwirds must contain at least one password")
    public boolean validatePassword(String password) {
        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id=48)
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    public boolean checkForNewPassword(List<String> prevPasswords,String password) {
        return !prevPasswords.contains(password);
    }
}
