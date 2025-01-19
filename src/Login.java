import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa Login creeaza o fereastra grafica pentru autentificare.
 * Contine elemente precum campuri de introducere pentru username si parola,
 * butoane pentru autentificare si recuperarea parolei, si un panou cu gradient.
 */
public class Login extends JFrame {

    /**
     * Initializeaza si afiseaza fereastra de autentificare.
     * Configureaza dimensiunile ferestrei, titlul si comportamentul la inchidere.
     * Adauga componentele grafice, inclusiv campurile de username si parola,
     * precum si butoanele pentru autentificare si recuperarea parolei.
     */
    public void showloginWindow() {
        setSize(700, 400);
        setResizable(false);
        setVisible(true);
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainpanel = new GradientPanel();
        mainpanel.setLayout(null);
        add(mainpanel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Serif", Font.BOLD, 18));
        userLabel.setBounds(190, 100, 100, 30);
        mainpanel.add(userLabel);

        JTextField userInput = new JTextField();
        userInput.setHorizontalAlignment(JTextField.CENTER);
        userInput.setBounds(300, 100, 200, 30);
        mainpanel.add(userInput);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Serif", Font.BOLD, 18));
        passLabel.setBounds(195, 160, 100, 30);
        mainpanel.add(passLabel);

        JPasswordField passInput = new JPasswordField();
        passInput.setHorizontalAlignment(JTextField.CENTER);
        passInput.setBounds(300, 160, 200, 30);
        mainpanel.add(passInput);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(350, 220, 100, 30);
        getRootPane().setDefaultButton(loginButton);
        mainpanel.add(loginButton);

        JButton forgetButton = new JButton("Forget Password");
        forgetButton.setBounds(330, 270, 140, 30);
        mainpanel.add(forgetButton);

        revalidate();
        repaint();

        String User = "admin";
        String Pass = "admin";

        loginButton.addActionListener(new ActionListener() {
            /**
             * Verifica daca datele introduse in campurile de autentificare corespund cu cele predefinite.
             * Afiseaza mesaje corespunzatoare pentru succes sau eroare si initiaza o noua interfata in caz de succes.
             * @param e evenimentul declansat de apasarea butonului
             */
            public void actionPerformed(ActionEvent e) {
                String username = userInput.getText();
                char[] password = passInput.getPassword();

                if (username.equals(User) && new String(password).equals(Pass)) {
                    JOptionPane.showMessageDialog(null, "Autentificare cu succes!", "Login succes", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    Biblioteca biblioteca = new Biblioteca();
                    biblioteca.createAndShowGUI();
                } else if (username.equals("") && new String(password).equals("")) {
                    JOptionPane.showMessageDialog(null, "Introduceti date de autentificare!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Datele introduse sunt incorecte, incercati din nou.", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        forgetButton.addActionListener(new ActionListener() {
            /**
             * Afiseaza datele predefinite pentru autentificare atunci cand este apasat butonul de recuperare parola.
             * @param actionEvent evenimentul declansat de apasarea butonului
             */
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Username: " + User + "\nPassword: " + Pass, "Login data", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}

/**
 * Clasa GradientPanel extinde JPanel pentru a desena un fundal cu gradient.
 */
class GradientPanel extends JPanel {

    /**
     * Deseneaza un gradient vertical pe suprafata panoului.
     * Creeaza un efect vizual de trecere intre doua culori.
     * @param g obiectul Graphics folosit pentru desenare
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = new Color(192, 192, 192);
        Color color2 = new Color(41, 50, 60);
        int width = getWidth();
        int height = getHeight();

        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }
}
