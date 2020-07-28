package Server;

import java.sql.*;

public class DBAuthService implements AuthService{
    Connection connection ;

    @Override
    public void start() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:chat.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getNickByLoginAndPass(String login, String password) {
        String query = String.format("select * from users where Login = '%s' and  Password = '%s'", login, password);
        try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query)) {
            if (rs.next()) {
                return rs.getString("Nick");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean changeNick(String oldnick, String newnick) {
        String query = String.format("update users set Nick = '%s' where Nick = '%s'", newnick, oldnick);
        try(Statement statement = connection.createStatement()) {
            statement.execute(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
