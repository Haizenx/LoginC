package src;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {

    private JFrame frmAdaAyumi;
    private JTextField textField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login window = new Login();
                window.frmAdaAyumi.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public Login() {
        initialize();
    }

    /**
     * @wbp.parser.entryPoint
     */
    private void initialize() {
        frmAdaAyumi = new JFrame();
        frmAdaAyumi.setTitle("ADA AYUMI");
        frmAdaAyumi.getContentPane().setBackground(new Color(255, 255, 255));
        frmAdaAyumi.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(710, 442, 73, 23);
        frmAdaAyumi.getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(710, 476, 73, 23);
        frmAdaAyumi.getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(783, 476, 158, 23);
        passwordField.setBorder(new RoundedBorder(1)); // Set round border
        frmAdaAyumi.getContentPane().add(passwordField);

        textField = new JTextField();
        textField.setBounds(783, 442, 158, 23);
        textField.setBorder(new RoundedBorder(5)); // Set round border
        frmAdaAyumi.getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Log In");
        btnNewButton.addActionListener(e -> {
            String username = textField.getText();
            String password = new String(passwordField.getPassword());
            if (authenticateUser(username, password)) {
                JOptionPane.showMessageDialog(frmAdaAyumi, "Login Successful!");
                dashboard.main(null);
                frmAdaAyumi.dispose();
            } else {
                JOptionPane.showMessageDialog(frmAdaAyumi, "Invalid username or password!");
            }
        });
        btnNewButton.setBounds(817, 524, 89, 23);
        frmAdaAyumi.getContentPane().add(btnNewButton);

        JLabel lblWelcomaaeTo = new JLabel("ADA MAYUMI'S");
        lblWelcomaaeTo.setForeground(Color.WHITE);
        lblWelcomaaeTo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 42));
        lblWelcomaaeTo.setBounds(650, 182, 336, 76);
        frmAdaAyumi.getContentPane().add(lblWelcomaaeTo);

        JLabel welcomeTXT = new JLabel("Welcome To");
        welcomeTXT.setForeground(new Color(255, 255, 255));
        welcomeTXT.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
        welcomeTXT.setBounds(612, 135, 140, 36);
        frmAdaAyumi.getContentPane().add(welcomeTXT);

        JLabel car = new JLabel("");
        car.setIcon(new ImageIcon(Login.class.getResource("/image/car.png")));
        car.setBounds(-156, 284, 974, 439);
        frmAdaAyumi.getContentPane().add(car);

        JLabel circle = new JLabel("");
        circle.setIcon(new ImageIcon(Login.class.getResource("/image/c2.png")));
        circle.setBounds(-759, -94, 1617, 913);
        frmAdaAyumi.getContentPane().add(circle);

        JLabel background = new JLabel("");
        background.setIcon(new ImageIcon(Login.class.getResource("/image/bg.png")));
        background.setBounds(-273, -28, 1766, 968);
        frmAdaAyumi.getContentPane().add(background);
        frmAdaAyumi.setBounds(100, 100, 1149, 736);
        frmAdaAyumi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private boolean authenticateUser(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/admin";
        String user = "root";
        String dbPassword = "howelltoehhh123"; 

        try (Connection conn = DriverManager.getConnection(url, user, dbPassword);
             Statement stmt = conn.createStatement()) {
            String query = "SELECT * FROM admin.user WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(query);
            return rs.next(); // If the result set has any row, user is authenticated
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of any SQL exception
        }
    }

    class RoundedBorder implements Border {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}



