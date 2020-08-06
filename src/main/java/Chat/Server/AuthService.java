package Chat.Server;

public interface AuthService {
    void start();
    void stop();
    String getNickByLoginAndPass(String login, String password);
    boolean changeNick(String oldnick, String newnick);
}
