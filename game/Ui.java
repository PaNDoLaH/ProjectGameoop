package game;

import music.Music;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Ui extends JFrame {

    private JLabel titleLabel;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JButton startButton;
    private static JTextField P1_POKE_NAME;
    private static JTextField P2_POKE_NAME;
    private static Settings settings = new Settings();

    static Music backgroundMusic = new Music(settings.getBackgroundMusic());
    private final Music buttonSound = new Music(settings.getButtonSound());

    private final Font titleFont = new Font("Times New Roman", Font.PLAIN, 65);
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 35);

    public void start() {
        createWindow();
    }

    private void createWindow() {
        setSize(settings.getGAME_WIDTH(), settings.getGAME_HEIGHT());
        setResizable(false);
        setTitle("Pokemon Fight");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        backgroundMusic.play();
        backgroundMusic.setLoop(10);
        createUI();
    }

    private void createUI() {

        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 120);
        titlePanel.setBackground(Color.decode(settings.getUI_BACKGROUND_COLOR()));

        titleLabel = new JLabel("POKEMON FIGHT");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(titleFont);

        buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 300, 200, 60);
        buttonPanel.setBackground(Color.decode(settings.getUI_BACKGROUND_COLOR()));

        startButton = new JButton("START");
        startButton.setBackground(Color.decode(settings.getUI_BACKGROUND_COLOR()));
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);
        startButton.addActionListener(startGame);

        GridBagConstraints gbcStart = new GridBagConstraints();
        gbcStart.ipadx = 60;
        gbcStart.ipady = 10;
        gbcStart.insets = new Insets(250, 300, 0, 0);
        gbcStart.weightx = 1;
        gbcStart.weighty = 1;
        gbcStart.anchor = GridBagConstraints.NORTHWEST;

        P1_POKE_NAME = new JTextField("Enter Pokemon Name - P1");
        P1_POKE_NAME.setBounds(180, 330, 200, 30);

        P2_POKE_NAME = new JTextField("Enter Pokemon Name - P2");
        P2_POKE_NAME.setBounds(450, 330, 200, 30);

        titlePanel.add(titleLabel);
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.add(startButton, gbcStart);

        add(P1_POKE_NAME);
        add(P2_POKE_NAME);
        add(titlePanel);
        add(buttonPanel);

        setVisible(true);
    }

    public static String getP1_POKE_NAME() {
        return P1_POKE_NAME.getText();
    }

    public static String getP2_POKE_NAME() {
        return P2_POKE_NAME.getText();
    }

    ActionListener startGame = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            setVisibleFalse();
            buttonSound.play();

            try {
                add(new Board("newGame"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private void setVisibleFalse() {
        titlePanel.setVisible(false);
        buttonPanel.setVisible(false);
        P1_POKE_NAME.setVisible(false);
        P2_POKE_NAME.setVisible(false);
    }
}