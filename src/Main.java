import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(String[] args) {
        try {
            checkLoginPassword("null", "null", "null");
        } catch (WrongLoginException e) {
            System.out.println("Логин указан с ошибкой: " + e.getMessage());
        } catch (WrongPasswordException e) {
            System.out.println("Пароль указан с ошибкой: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkLoginPassword(String login, String password, String confirmPassword) throws WrongLoginException, WrongPasswordException {
        StringBuilder rightSymbols = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        rightSymbols.append(rightSymbols.toString().toLowerCase());
        rightSymbols.append("0123456789_");

        if (isValEmpty(login)) {
            throw new WrongLoginException("Логин пустой");
        }

        if (login.length() > 20) {
            throw new WrongLoginException("Длина логина больше 20 символов");
        }

        if (hasWrongSymbols(login, rightSymbols)) {
            throw new WrongLoginException("Логин содержит недопустимые символы");
        }

        if (isValEmpty(password)) {
            throw new WrongPasswordException("Пароль пустой");
        }

        if (password.length() > 20) {
            throw new WrongPasswordException("Длина пароля больше 20 символов");
        }

        if (hasWrongSymbols(password, rightSymbols)) {
            throw new WrongPasswordException("Пароль содержит недопустимые символы");
        }


        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароль и его подтверждение не совпадают");
        }
    }

    private static boolean hasWrongSymbols(@NotNull String value, @NotNull StringBuilder rightSymbols) {
        for (int i = 0; i < value.length(); i++) {
            if (rightSymbols.indexOf(value.substring(i, i + 1)) < 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValEmpty(String value) {
        return value == null || value.isBlank();
    }
}