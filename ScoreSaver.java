import java.sql.Connection;
import java.sql.PreparedStatement;

public class ScoreSaver {

    public static void saveScore(String name, int score) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO scores (name, score) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, score);
            ps.executeUpdate();
            System.out.println("Score saved for: " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
