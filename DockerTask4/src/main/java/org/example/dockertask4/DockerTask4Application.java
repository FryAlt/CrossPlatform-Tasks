package org.example.dockertask4;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class DockerTask4Application {

    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASS = System.getenv("DB_PASSWORD");

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("✅ Успешное подключение к PostgreSQL");

            createTable(conn);
            insertRecord(conn);
            readRecords(conn);
            updateRecord(conn);
            deleteRecord(conn);

            System.out.println("🚀 Все CRUD-операции выполнены успешно!");
        } catch (SQLException e) {
            System.err.println("❌ Ошибка работы с БД: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                email VARCHAR(50) UNIQUE NOT NULL
            )
        """;
        conn.createStatement().execute(sql);
        System.out.println("📝 Таблица 'users' создана/проверена.");
    }

    private static void insertRecord(Connection conn) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "Akiro");
            ps.setString(2, "akiro@example.com");
            ps.executeUpdate();
            System.out.println("➕ Запись добавлена.");
        }
    }

    private static void readRecords(Connection conn) throws SQLException {
        String sql = "SELECT id, name, email FROM users";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("👀 Чтение записей:");
            while (rs.next()) {
                System.out.printf("  ID: %d | Name: %s | Email: %s%n",
                        rs.getInt("id"), rs.getString("name"), rs.getString("email"));
            }
        }
    }

    private static void updateRecord(Connection conn) throws SQLException {
        String sql = "UPDATE users SET email = ? WHERE name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "new_email@domain.com");
            ps.setString(2, "Akiro");
            ps.executeUpdate();
            System.out.println("✏️ Запись обновлена.");
        }
    }

    private static void deleteRecord(Connection conn) throws SQLException {
        String sql = "DELETE FROM users WHERE name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "Akiro");
            ps.executeUpdate();
            System.out.println("🗑️ Запись удалена.");
        }
    }

}
