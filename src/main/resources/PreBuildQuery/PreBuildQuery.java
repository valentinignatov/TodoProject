import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PreBuildQuery {
    private Connection connection;

    public UserRepositoryImpl() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/todos",
                    "postgres",
                    "558226");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
