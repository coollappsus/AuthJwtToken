package main.dto;

public class JwtRequestDTO {

    private String login;
    private String password;

    public JwtRequestDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public JwtRequestDTO() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
