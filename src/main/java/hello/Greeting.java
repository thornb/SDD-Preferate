package hello;

public class Greeting {

    private final long id;
    //private final String content;

    public Greeting() {

        String url = "jdbc:mysql://localhost:3306/preferate";
        String username = "root";
        String password = "CrackerWindow654";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");

            Statement stmt = connection.createStatement();
            ResultSet stmt.executeQuery("SELECT * FROM `user`");

            while(rs.next()){
                System.out.println(rs.getInt("id"));
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
            throw new IllegalStateException("Cannot connect the database!", e);
        }


        //this.id = id;
        // this.content = content;
    }

    public long getId() {
        return id;
    }

    // public String getContent() {
    //     return content;
    // }
}