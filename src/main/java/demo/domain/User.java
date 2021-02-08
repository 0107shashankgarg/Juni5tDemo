package demo.domain;

public class User {
    private String name;
    private String password;
    private State state;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public enum State {
        ENABLED, DISABLED, LOCKED
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public State getState() {
        return state;
    }


    public static User extractFromConfig(String config) {
        String[] credentials = config.split(",");
        assert credentials.length == 2;

        return new User(credentials[0], credentials[1]);
    }
}
