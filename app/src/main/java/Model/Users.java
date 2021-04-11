package Model;

public class Users {
    private String Username;
    private String Email;
    private String Password;

    public Users() {
    }

    public Users(String username, String email, String passowrd) {
        this.Username = username;
        this.Email = email;
        this.Password = passowrd;
    }

    public String getUsername() {
        return Username;
    }

    public Users setUsername(String username) {
        Username = username;
        return this;
    }

    public String getEmail() {
        return Email;
    }

    public Users setEmail(String email) {
        Email = email;
        return this;
    }

    public String getPassword() {
        return Password;
    }

    public Users setPassword(String password) {
        Password = password;
        return this;
    }
}


