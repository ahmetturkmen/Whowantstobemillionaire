
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
// Todo:
// - put all resources to cloud then pull from there (- NOT call from local paths -)
// - put application into docker and make it available
// - present option to upload custom question by user
// - create stop button for time
// - and more ...

public class WelcomeScreen extends JFrame {
    private static final String intro = "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/sounds/intro.wav";
    private static final String letsPlay = "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/sounds/lets_play.wav";
    private JPanel contentPane;
    private JButton btnQuit;
    private static PlaySound ply= new PlaySound();
    private static Border emptyBorder = BorderFactory.createEmptyBorder();
    private JButton applyButton;
    private static App app;
    private static JButton btnStart = new JButton("START");
    private String nameAndSurname="";
    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    WelcomeScreen frame = new WelcomeScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ply.playSound(intro);

    }

    /**
     * Create the frame.
     */
    public WelcomeScreen() {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 961, 736);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        setTitle("Welcome To Who Wants To Be A Milloneare");
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


        btnStart.setBackground(new Color(0, 0, 0));
        btnStart.setForeground(new Color(255, 255, 255));
        btnStart.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnStart.setBorder(emptyBorder);
        btnStart.setIcon(
                new ImageIcon("/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/inUse_playButton.png"));

        btnStart.setBounds(32, 48, 112, 37);
        contentPane.add(btnStart);

        btnStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {

                ply.stop();
                ply.playSound(letsPlay);
                app=new App();
                setVisible(false);
                app.startActivity();

            }
        });


        btnQuit = new JButton("QUIT");


        btnQuit.setBackground(new Color(0, 0, 0));
        btnQuit.setIcon(
                new ImageIcon("/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/quit_button_scaled.png"));
        btnQuit.setForeground(new Color(255, 255, 255));
        btnQuit.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnQuit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);

            }
        });
        btnQuit.setBounds(43, 490, 112, 37);
        contentPane.add(btnQuit);
        btnQuit.setBorder(emptyBorder);

        JButton officialRules = new JButton("COMPLETE OFFICIAL RULES");
        officialRules.setBackground(Color.BLACK);
        officialRules.setForeground(Color.BLUE);


        officialRules.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // Redirects the user to written link below
                    // Other buttons constructed with same logic
                    Desktop d = Desktop.getDesktop();
                    d.browse(new URI("http://millionairetv.dadt.com/official-rules/"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });



        officialRules.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        officialRules.setBounds(118, 639, 273, 23);
        contentPane.add(officialRules);
        officialRules.setBackground(new Color(255, 255, 255));

        applyButton = new JButton("APPLY");

        applyButton.setForeground(Color.WHITE);
        applyButton.setBackground(Color.BLACK);
        applyButton.setBorder(emptyBorder);

        applyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop d = Desktop.getDesktop();
                    d.browse(new URI("http://www.atv.com.tr/milyoner/form"));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        applyButton
                .setIcon(new ImageIcon("/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/apply_icon30x30.png"));
        applyButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        applyButton.setBounds(138, 264, 112, 37);
        contentPane.add(applyButton);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/logo.jpg"));
        lblNewLabel.setBounds(188, 0, 767, 720);
        contentPane.add(lblNewLabel);

    }





}
