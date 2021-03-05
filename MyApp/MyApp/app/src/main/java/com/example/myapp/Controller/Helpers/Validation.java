package com.example.myapp.Controller.Helpers;

import android.content.Context;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation{

    private Context context;
    private String exception;
    private Pattern pw_pattern;
    private Pattern log_pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,40})";
    private static final String LOGIN_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,40})";

    public Validation(Context _context) {
        context = _context;
        pw_pattern = Pattern.compile(PASSWORD_PATTERN);
        log_pattern = Pattern.compile(LOGIN_PATTERN);
    }

    public boolean is_validate_user(final String name, final String subject, final String login, final String password) {
        if (is_empty(name, "Имя не должен быть пустым"))
            if (is_empty(subject, "Предмет не должен быть пустым"))
                if (validate_login(login))
                    if (validate_password(password))
                        return true;
                    else return false;
                else return false;
            else return false;
        else return false;
    }

    public boolean is_empty(final String object, final String string) {
        if (!object.isEmpty()) return true;
        else {
            message(string);
            return false;
        }
    }

    public void message(final String string){
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    private boolean validate_password(final String password) {
        if (is_empty(password, "Пароль не должен быть пустым")) {
            matcher = pw_pattern.matcher(password);
            if (matcher.matches()) return true;
            else {
                exception = "Требования к паролю:" +
                        "Имеет длину от 8 до 40 символов\n" +
                        "Содержит хотя бы одну цифру.\n" +
                        "Содержит хотя бы один символ нижнего регистра.\n" +
                        "Содержит хотя бы один символ верхнего регистра.";
                message(exception);
                return false;
            }
        }
        else return false;
    }

    private boolean validate_login(final String login){
        if (is_empty(login, "Логин не должен быть пустым")){
            matcher = pw_pattern.matcher(login);
            if (matcher.matches()) return true;
            else {
                exception = "Требования к логину:" +
                        "Имеет длину от 8 до 40 символов\n" +
                        "Содержит хотя бы одну цифру.\n" +
                        "Содержит хотя бы один символ нижнего регистра.\n" +
                        "Содержит хотя бы один символ верхнего регистра.";
                message(exception);
                return false;
            }
        }
        else return false;
    }
}
