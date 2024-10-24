public class Main {
    public static void main(String[] args) {
        try {
            checkLoginPassword("null_________________", "null", "null");
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

        if (login == null || login.isBlank()) {
            throw new WrongLoginException("Логин пустой");
        }

        if (login.length() > 20) {
            throw new WrongLoginException("Длина логина больше 20 символов");
        }

        for (int i = 0; i < login.length(); i++) {
            if (rightSymbols.indexOf(login.substring(i, i + 1)) < 0) {
                throw new WrongLoginException("Логин содержит недопустимые символы");
            }
        }

        if (password == null || password.isBlank()) {
            throw new WrongPasswordException("Пароль пустой");
        }

        if (password.length() > 20) {
            throw new WrongPasswordException("Длина пароля больше 20 символов");
        }

        for (int i = 0; i < password.length(); i++) {
            if (rightSymbols.indexOf(password.substring(i, i + 1)) < 0) {
                throw new WrongPasswordException("Пароль содержит недопустимые символы");
            }
        }

        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароль и его подтверждение не совпадают");
        }

    }
}