import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Panel;
import java.awt.Point;

import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App extends JFrame {
    private JPanel contentPane;
    private static JPanel progressBar_panel;
    private static FromDB data = new FromDB();;
    private static String question;
    private static PlaySound ply = new PlaySound();

    private static int randomQuestionIndex;
    private int indexForMoneySequence = 1;
    private int indexForMoney = 16;
    private static final String[] audinceJokerOptions = {
            "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/asikki.PNG",
            "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/bsikki.PNG",
            "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/csikki.PNG",
            "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/dsikki.PNG" };
    private static int money = 0;
    private static final String[] correctAnswersVoice = {
            "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/correctAnswerA.wav",
            "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/correctAnswerB.wav",
            "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/correctAnswerC.wav",
            "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/correctAnswerD.wav"

    };


    private static final String wrongAnswer = "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/sounds/wrongAnswer.wav";
    private static final String correctAnswer = "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/sounds/correctAnswer2.wav";
    private static final String fiftyFifty = "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/sounds/50_50.wav";
    private static final String askAudince = "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/sounds/askAudince1.wav";
    private static final String callSound = "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/sounds/telefonjoker_loop.wav";
    private JTextPane[] textPanes = new JTextPane[8];
    private static String[] options = new String[] {"Continue", "Walk Away"};

    // private static JLabel decisionQuestion = new JLabel(message);
    // private static JButton continueButton = new JButton("CONTINUE");
    // private static JButton walkAwayButton = new JButton("WALK AWAY");

    // textPane = new JTextPane(); // 0
    // choiceAanswer // 1
    // choiceBanswer // 2
    // choiceCanswer // 3
    // choiceDanswer // 4

    // competitionNameRespond // 5
    // surnameRespond // 6
    // valueOfPrize // 7
    private Random randomIndex = new Random();
    private int randomInteger;
    private JLabel answerAreaLabel = new JLabel("");
    private JPanel panel = new JPanel();
    private static ProgressBar pb = new ProgressBar(0, 0, 458, 266, Color.BLUE);
    private static JButton[] buttons = new JButton[36];
    // btnSave // 32
    // fiftyFiftyButton // 33
    // audienceButton //34
    // callForHelp// 35

    private static ActionListener actionL = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            pb.ilerle();
            progressBar_panel.repaint();

        }

    };

    private Timer timerForProgressBar = new Timer(delay, actionL);

    private JLabel[] jLabel = new JLabel[5];

    // lblPrize // 0
    // competitionName// 1
    // lblSurname // 2
    // questionAreaLabel // 3
    // false correct icon

    private static Border emptyBorder = BorderFactory.createEmptyBorder();

    private static int delay = 2000;
    private JLabel audinceResult;
    private static JTextField nameSurname ;
    private final Panel panel_1 = new Panel();
    private final JLabel lblNewLabel = new JLabel("Competitor Name Surname");
    private final JLabel lblVersion = new JLabel("version1.0  ");

    public App() {


        setBounds(100, 100, 980, 722);
        setTitle("Who wants to be a milloeare ");
        contentPane = new JPanel();
        nameSurname = new JTextField();

        contentPane.setForeground(SystemColor.info);
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setResizable(false);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        for (int i = 0; i < textPanes.length; i++)
            textPanes[i] = new JTextPane();
        for (int i = 0; i < jLabel.length; i++)
            jLabel[i] = new JLabel();

        setButtons(); // Initialize buttons and texts

        progressBar_panel = new JPanel() {
            public void paint(Graphics g) {
                super.paint(g);
                pb.draw(g);
            }
        };

        progressBar_panel.setBackground(Color.BLACK);
        progressBar_panel.setBounds(171, 74, 458, 266);
        contentPane.add(progressBar_panel);
        progressBar_panel.setBorder(emptyBorder);
        audinceResult = new JLabel();
        audinceResult.setBounds(10, 74, 151, 207);
        contentPane.add(audinceResult);
        panel_1.setBackground(new Color(0, 0, 205));
        panel_1.setBounds(761, 512, 190, 137);

        contentPane.add(panel_1);
        panel_1.setLayout(null);

        nameSurname.setHorizontalAlignment(SwingConstants.CENTER);
        nameSurname.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
        nameSurname.setBounds(0, 56, 190, 26);
        panel_1.add(nameSurname);
        nameSurname.setForeground(Color.WHITE);


        nameSurname.setBackground(Color.BLACK);
        nameSurname.setColumns(10);
        nameSurname.setBorder(emptyBorder);
        nameSurname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    nameSurname.setText(nameSurname.getText());
                    nameSurname.setEditable(false);
                    nameSurname.getCaret().setVisible(false);

                }

            }
        });



        lblNewLabel.setForeground(new Color(255, 140, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 0, 190, 35);

        panel_1.add(lblNewLabel);
        lblVersion.setFont(new Font("Tahoma", Font.ITALIC, 11));
        lblVersion.setHorizontalAlignment(SwingConstants.TRAILING);
        lblVersion.setBackground(new Color(0, 0, 0));
        lblVersion.setForeground(new Color(255, 255, 255));
        lblVersion.setBounds(813, 668, 138, 28);

        contentPane.add(lblVersion);

        timerForProgressBar.start();

        // ply.playSound(clockSound);

        //////////////// DEFAULT

        textPanes[0].setText(question);
        textPanes[1].setText(data.getOptionsA(randomQuestionIndex));
        textPanes[2].setText(data.getOptionsB(randomQuestionIndex));
        textPanes[3].setText(data.getOptionsC(randomQuestionIndex));
        textPanes[4].setText(data.getOptionsD(randomQuestionIndex));

        buttons[33].addActionListener(new ActionListener() { // fifty-fifty button

            // Dealing with fifty-fifty option
            public void actionPerformed(ActionEvent e) {
                System.out.println("You pressed " + buttons[33].getName());

                ply.playSound(fiftyFifty);
                String correctOption = data.getCorrectOption(randomQuestionIndex);
                if (!data.getOptionsA(randomQuestionIndex).equals(correctOption))
                    textPanes[1].setVisible(false);
                else if (!data.getOptionsB(randomQuestionIndex).equals(correctOption))
                    textPanes[2].setVisible(false);
                if (!data.getOptionsC(randomQuestionIndex).equals(correctOption))
                    textPanes[3].setVisible(false);
                else if (!data.getOptionsD(randomQuestionIndex).equals(correctOption))
                    textPanes[4].setVisible(false);

                buttons[33].setEnabled(false);

            }
        });

        buttons[34].addActionListener(new ActionListener() { // Audince Button
            public void actionPerformed(ActionEvent e) {
                randomInteger = randomIndex.nextInt(3);
                ply.playSound(askAudince);
                buttons[34].setEnabled(false);
                try {
                    TimeUnit.SECONDS.sleep(17);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                audinceResult.setIcon(new ImageIcon(audinceJokerOptions[randomInteger]));
                audinceResult.setVisible(true);

            }
        });

        buttons[35].addActionListener(new ActionListener() { // callButton

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                buttons[35].setEnabled(false);
                randomInteger = randomIndex.nextInt(4);
                // Checks if fifty-fifty joker used or not
                if (!buttons[33].isEnabled() || (!buttons[33].isEnabled() && !buttons[34].isEnabled())) { // buttons[33]
                    // represent
                    // fifty-fifty


                    // Checks if fifty-fifty button used or not for callButton
                    if (!textPanes[1].isVisible() && !textPanes[2].isVisible()){ // if A and B choice enable

                        while (randomInteger == 0 || randomInteger == 2 || randomInteger ==1)
                            randomInteger = randomIndex.nextInt(4);
                    }
                    else if (!textPanes[1].isVisible() && !textPanes[3].isVisible())
                        while (randomInteger == 0 || randomInteger == 1 || randomInteger==3)
                            randomInteger = randomIndex.nextInt(4);

                    else if (!textPanes[2].isVisible() &&! textPanes[3].isVisible())
                        while (randomInteger == 0 || randomInteger == 2 || randomInteger==3)
                            randomInteger = randomIndex.nextInt(4);

                    else if (!textPanes[1].isVisible() && !textPanes[4].isVisible())
                        while (randomInteger == 0 || randomInteger == 1 || randomInteger==4)
                            randomInteger = randomIndex.nextInt(3);
                    else if (!textPanes[2].isVisible() && !textPanes[4].isVisible())
                        while (randomInteger == 0 || randomInteger == 2 || randomInteger==4)
                            randomInteger = randomIndex.nextInt(3);

                }ply.playSound(correctAnswersVoice[randomInteger]);
                try {
                    TimeUnit.MICROSECONDS.sleep(ply.getClip().getMicrosecondLength());
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        // SETTING QUESTION AND ANSWERS

        textPanes[1].addMouseListener(new MouseAdapter() { // option A
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.out.println(textPanes[0].getText());
                System.out.println(data.getCorrectOption(randomQuestionIndex));
                if (data.getOptionsA(randomQuestionIndex) == data.getCorrectOption(randomQuestionIndex)) {
                    ifCorrectAnswer();

                } else
                    elseSituation();

            }
        });
        textPanes[2].addMouseListener(new MouseAdapter() { // option B

            @Override
            public void mouseClicked(MouseEvent arg0) {

                if (data.getOptionsB(randomQuestionIndex) == data.getCorrectOption(randomQuestionIndex)) {

                    ifCorrectAnswer();

                } else {
                    elseSituation();
                }

            }
        });
        textPanes[3].addMouseListener(new MouseAdapter() { // option C
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(data.getOptionsC(randomQuestionIndex));
                System.out.println(data.getCorrectOption(randomQuestionIndex));
                if (data.getOptionsC(randomQuestionIndex) == data.getCorrectOption(randomQuestionIndex)) {

                    ifCorrectAnswer();

                } else {
                    elseSituation();
                }

            }
        });
        textPanes[4].addMouseListener(new MouseAdapter() { // option D

            @Override
            public void mouseClicked(MouseEvent arg0) {

                System.out.println(textPanes[4].getText());
                System.out.println(data.getCorrectOption(randomQuestionIndex));
                if (data.getOptionsD(randomQuestionIndex) == data.getCorrectOption(randomQuestionIndex)) {

                    ifCorrectAnswer();

                } else
                    elseSituation();

            }

        });

    }

    public void ifCorrectAnswer() {

        buttons[indexForMoney].setBackground(Color.GREEN);
        buttons[indexForMoneySequence].setBackground(Color.GREEN);
        ply.playSound(correctAnswer);

        pb.reset();
        ImageIcon dollarIcon = new ImageIcon("/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/dollarIcon.png");

        changeQuestion();
        int response = JOptionPane.showOptionDialog(null, "You won "+ calculateEarnedMoney()+ " $", "Do you want to continue ?",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                dollarIcon, options, options[0]);
        if(response ==1){
            pb.setVisible(false);
            if(calculateEarnedMoney()>0)
                JOptionPane.showMessageDialog(
                        null,
                        "Your total earned money is "+ calculateEarnedMoney()+ " $",
                        "Congrulations >:)",
                        JOptionPane.INFORMATION_MESSAGE,
                        dollarIcon);


            else
                JOptionPane.showMessageDialog(
                        null,
                        "Never Mind !",
                        "You could not earn money, Good Luck rest of your life", JOptionPane.INFORMATION_MESSAGE,
                        dollarIcon
                );
            System.exit(0);
        }

        indexForMoneySequence++;
        indexForMoney++;

    }

    public void elseSituation() {
        pb.setVisible(false);

        JTextField messageTo = new JTextField();
        messageTo.setEditable(false);
        messageTo.setForeground(Color.WHITE);
        messageTo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        messageTo.setBorder(emptyBorder);
        messageTo.setBackground(Color.BLACK);
        // ply.stop();

        // ----------------SETTING MONEY TREE SEQUENCE----------------------\\

        if (indexForMoneySequence < 5) // checks money tree to make green to
            // blue if answer is false
            for (int i = 1; i <= indexForMoneySequence; i++){
                buttons[i].setBackground(Color.blue);
                indexForMoneySequence--;
            }

        if (indexForMoneySequence > 5 && indexForMoneySequence < 10)
            for (int i = 6; i <= indexForMoneySequence; i++){
                buttons[i].setBackground(Color.blue);
                indexForMoneySequence--;
            }

        if (indexForMoneySequence > 10 && indexForMoneySequence < 15)
            for (int i = 11; i <= indexForMoneySequence; i++){
                buttons[i].setBackground(Color.blue);
                indexForMoneySequence--;
            }

        if (indexForMoneySequence == 15)
            for (int i = indexForMoneySequence; i > 10; i--){
                buttons[i].setBackground(Color.blue);
                indexForMoneySequence--;
            }

        // -------SETTING MONEY TREE--------\\

        if (indexForMoney < 20)
            for (int i = 16; i <= indexForMoney; i++)
                buttons[i].setBackground(Color.blue);
        if (indexForMoney > 20 && indexForMoney < 25)
            for (int i = 21; i < indexForMoney; i++)
                buttons[i].setBackground(Color.blue);

        if (indexForMoney > 25 && indexForMoney < 30)
            for (int i = 26; i < indexForMoney; i++)
                buttons[i].setBackground(Color.blue);

        if (indexForMoney == 30)
            for (int i = indexForMoney; i > 25; i--)
                buttons[i].setBackground(Color.blue);

        indexForMoneySequence--;
        indexForMoney--;

        if (calculateEarnedMoney() > 0 && indexForMoneySequence >= 5) {
            messageTo.setText("Congrulations, you won " + calculateEarnedMoney() + " $");
            progressBar_panel.setBorder(emptyBorder);
            progressBar_panel.setBounds(170, 148, 458, 96);
            progressBar_panel.add(messageTo);
            progressBar_panel.setVisible(true);
            progressBar_panel.setBackground(Color.BLACK);
        } else {

            messageTo.setText("You could not win money, Never Mind :) ");
            progressBar_panel.setBounds(170, 148, 458, 96);
            progressBar_panel.add(messageTo);
            progressBar_panel.setVisible(true);
            progressBar_panel.setBackground(Color.black);
        }

        for (int i = 0; i < 5; i++) {
            textPanes[i].setEnabled(false);
            textPanes[i].setText("");
        }

        ply.playSound(wrongAnswer);
    }

    // This method changes the questions

    private void changeQuestion() {
        audinceResult.setVisible(false);
        randomQuestionIndex = data.getNumbers();

        for (int i = 1; i < 5; i++)
            textPanes[i].setVisible(true);
        for (int i = 0; i < 5; i++) {
            if (textPanes[i].isEnabled()) {
                // textPanes[0] refers questionTextArea
                textPanes[0].setText(data.getQuestion(randomQuestionIndex));
                // textPanes[1] refers optionA
                textPanes[1].setText(data.getOptionsA(randomQuestionIndex));
                // textPanes[2] refers optionA
                textPanes[2].setText(data.getOptionsB(randomQuestionIndex));
                // textPanes[3] refers optionA
                textPanes[3].setText(data.getOptionsC(randomQuestionIndex));
                // textPanes[4] refers optionA
                textPanes[4].setText(data.getOptionsD(randomQuestionIndex));

            }
        }

    }

    private int calculateEarnedMoney() {

        // ! DO NOT FORGET TO DECRASE INDEX IF THE USER DID NOT KNOW THE
        // QUESTION

        switch (indexForMoneySequence) {
            case 1:
                money = 100;
                break;
            case 2:
                money = 200;
                break;
            case 3:
                money = 300;
                break;
            case 4:
                money = 500;
                break;
            case 5:
                money = 1000;
                break;
            case 6:
                money = 2000;
                break;
            case 7:
                money = 4000;
                break;
            case 8:
                money = 8000;
                break;
            case 9:
                money = 16000;
                break;
            case 10:
                money = 32000;
                break;
            case 11:
                money = 64000;
                break;
            case 12:
                money = 125000;
                break;
            case 13:
                money = 250000;
                break;
            case 14:
                money = 500000;
                break;
            case 15:
                money = 1000000;
                break;

        }
        return money;

    }

    public void startActivity() {

        // connection() method is providing questions
        // and answers from MySQL DB
        data.connection();
        data.randomIndex(); // This is needed to create random question
        randomQuestionIndex = data.getNumbers();
        question = data.getQuestion(randomQuestionIndex);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App frame = new App();

                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void setButtons() {

        textPanes[1].setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));

        textPanes[1].setForeground(Color.WHITE);
        textPanes[1].setBackground(SystemColor.desktop);
        textPanes[1].setBounds(160, 519, 201, 51);
        contentPane.add(textPanes[1]);

        textPanes[1].setEditable(false);

        textPanes[3].setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        textPanes[3].setForeground(Color.WHITE);
        textPanes[3].setBackground(SystemColor.desktop);
        textPanes[3].setBounds(160, 596, 201, 44);
        contentPane.add(textPanes[3]);
        textPanes[3].setEditable(false);

        textPanes[2].setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        textPanes[2].setForeground(Color.WHITE);
        textPanes[2].setBackground(SystemColor.desktop);
        textPanes[2].setBounds(439, 519, 190, 51);
        contentPane.add(textPanes[2]);
        textPanes[2].setEditable(false);

        textPanes[4].setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
        textPanes[4].setForeground(Color.WHITE);
        textPanes[4].setBackground(SystemColor.desktop);
        textPanes[4].setBounds(439, 596, 190, 44);
        contentPane.add(textPanes[4]);
        textPanes[4].setEditable(false);

        panel.setBackground(Color.BLACK);
        panel.setBounds(761, 45, 190, 473);
        contentPane.add(panel);
        panel.setLayout(null);
        panel.setBorder(emptyBorder);

        jLabel[1].setBackground(new Color(173, 255, 47));
        jLabel[1].setFont(new Font("Tahoma", Font.BOLD, 11));
        jLabel[1].setBounds(10, 23, 80, 20);
        jLabel[1].setText("Name : ");

        textPanes[5].setBounds(100, 23, 67, 20);

        jLabel[2].setFont(new Font("Tahoma", Font.BOLD, 11));
        jLabel[2].setBounds(10, 54, 80, 20);
        jLabel[2].setText("Surname : ");

        textPanes[6].setBounds(100, 54, 67, 20);

        jLabel[0].setFont(new Font("Tahoma", Font.BOLD, 11));
        jLabel[0].setBounds(10, 85, 80, 20);

        textPanes[7].setBounds(100, 85, 67, 20);

        buttons[32] = new JButton("SAVE");
        buttons[32].setBackground(new Color(135, 206, 250));
        buttons[32].setBounds(49, 116, 89, 23);

        answerAreaLabel.setIcon(new ImageIcon(
                "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/cevapalanlariolceklendirilmis.png"));
        answerAreaLabel.setBounds(82, 512, 634, 137);
        contentPane.add(answerAreaLabel);

        buttons[35] = new JButton();
        buttons[35].setBounds(359, 17, 91, 51);
        contentPane.add(buttons[35]);
        buttons[35].setBackground(Color.BLACK);
        buttons[35].setBorder(emptyBorder);
        buttons[35]
                .setIcon(new ImageIcon("/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/phoneAFriend2.png"));

        buttons[33] = new JButton();
        buttons[33].setBounds(549, 17, 91, 51);
        contentPane.add(buttons[33]);
        buttons[33].setForeground(SystemColor.desktop);

        buttons[33].setBackground(SystemColor.desktop);
        buttons[33].setFocusable(false);
        buttons[33].setIcon(new ImageIcon("/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/Classic50502.png"));

        buttons[33].setBorder(emptyBorder);

        buttons[34] = new JButton();

        buttons[34].setBounds(171, 17, 91, 51);
        contentPane.add(buttons[34]);
        buttons[34].setForeground(SystemColor.desktop);
        buttons[34].setBackground(SystemColor.desktop);
        buttons[34].setBackground(Color.black);
        buttons[34].setIcon(
                new ImageIcon("/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/seyirci.png"));

        buttons[34].setBorder(emptyBorder);

        textPanes[0].setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        textPanes[0].setForeground(Color.WHITE);
        textPanes[0].setBackground(Color.BLACK);
        textPanes[0].setBounds(144, 358, 506, 120);
        contentPane.add(textPanes[0]);
        textPanes[0].setEditable(false);
        textPanes[0].setBorder(emptyBorder);

        jLabel[3].setBackground(Color.BLACK);
        jLabel[3].setIcon(new ImageIcon(
                "/Users/ahmetturkmen/IdeaProjects/Whowantstobemillionaire/src/main/resources/images/sorualaniarkaplani.png"));
        jLabel[3].setForeground(Color.WHITE);
        jLabel[3].setBounds(82, 348, 634, 141);
        contentPane.add(jLabel[3]);

        buttons[15] = new JButton("15");

        buttons[15].setActionCommand("\r\n");
        buttons[15].setBackground(Color.BLUE);
        buttons[15].setForeground(Color.WHITE);
        buttons[15].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[15].setBounds(10, 10, 48, 23);
        panel.add(buttons[15]);
        buttons[15].setBorder(emptyBorder);

        buttons[30] = new JButton("$1 MILLION");
        buttons[30].setBackground(Color.BLUE);
        buttons[30].setForeground(Color.WHITE);
        buttons[30].setBorder(emptyBorder);
        buttons[30].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[30].setBounds(74, 10, 106, 23);
        panel.add(buttons[30]);

        buttons[29] = new JButton("$500,000");
        buttons[29].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[29].setForeground(new Color(255, 153, 0));
        buttons[29].setBackground(Color.BLUE);
        buttons[29].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[29].setBounds(74, 39, 106, 23);
        panel.add(buttons[29]);
        buttons[29].setBorder(emptyBorder);

        buttons[28] = new JButton("$250,000");
        buttons[28].setBackground(Color.BLUE);
        buttons[28].setForeground(new Color(255, 153, 0));
        buttons[28].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[28].setBounds(74, 68, 106, 23);
        panel.add(buttons[28]);
        buttons[28].setBorder(emptyBorder);

        buttons[27] = new JButton("$125,000");
        buttons[27].setBackground(Color.BLUE);
        buttons[27].setForeground(new Color(255, 153, 0));
        buttons[27].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[27].setBounds(74, 97, 106, 23);
        panel.add(buttons[27]);
        buttons[27].setBorder(emptyBorder);

        buttons[26] = new JButton("$64,000");
        buttons[26].setBackground(Color.BLUE);
        buttons[26].setForeground(new Color(255, 153, 0));
        buttons[26].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[26].setBounds(74, 126, 106, 23);
        panel.add(buttons[26]);
        buttons[26].setBorder(emptyBorder);

        buttons[25] = new JButton("$32,000");
        buttons[25].setBackground(Color.BLUE);
        buttons[25].setForeground(Color.WHITE);
        buttons[25].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[25].setBounds(74, 155, 106, 23);
        panel.add(buttons[25]);
        buttons[25].setBorder(emptyBorder);

        buttons[24] = new JButton("$16,000");
        buttons[24].setBackground(Color.BLUE);
        buttons[24].setForeground(new Color(255, 153, 0));
        buttons[24].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[24].setBounds(74, 184, 106, 23);
        panel.add(buttons[24]);
        buttons[24].setBorder(emptyBorder);

        buttons[23] = new JButton("$ 8,000");
        buttons[23].setBackground(Color.BLUE);
        buttons[23].setForeground(new Color(255, 153, 0));
        buttons[23].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[23].setBounds(74, 213, 106, 23);
        panel.add(buttons[23]);
        buttons[23].setBorder(emptyBorder);

        buttons[22] = new JButton("$ 4,000");
        buttons[22].setForeground(new Color(255, 153, 0));
        buttons[22].setBackground(Color.BLUE);
        buttons[22].setForeground(new Color(255, 153, 0));
        buttons[22].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[22].setBounds(74, 242, 106, 23);
        panel.add(buttons[22]);
        buttons[22].setBorder(emptyBorder);

        buttons[21] = new JButton("$2,000");
        buttons[21].setBackground(Color.BLUE);
        buttons[21].setForeground(new Color(255, 153, 0));
        buttons[21].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[21].setBounds(74, 271, 106, 23);
        panel.add(buttons[21]);
        buttons[21].setBorder(emptyBorder);

        buttons[20] = new JButton("$1,000");
        buttons[20].setBackground(Color.BLUE);
        buttons[20].setForeground(Color.WHITE);
        buttons[20].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[20].setBounds(74, 300, 106, 23);
        panel.add(buttons[20]);
        buttons[20].setBorder(emptyBorder);

        buttons[19] = new JButton("$500");
        buttons[19].setBackground(Color.BLUE);
        buttons[19].setForeground(new Color(255, 153, 0));
        buttons[19].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[19].setBounds(74, 329, 106, 23);
        panel.add(buttons[19]);
        buttons[19].setBorder(emptyBorder);

        buttons[18] = new JButton("$300");
        buttons[18].setBackground(Color.BLUE);
        buttons[18].setForeground(new Color(255, 153, 0));
        buttons[18].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[18].setBounds(74, 358, 106, 23);
        panel.add(buttons[18]);
        buttons[18].setBorder(emptyBorder);

        buttons[17] = new JButton("$200");
        buttons[17].setBackground(Color.BLUE);
        buttons[17].setForeground(new Color(255, 153, 0));
        buttons[17].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[17].setBounds(74, 387, 106, 23);
        panel.add(buttons[17]);
        buttons[17].setBorder(emptyBorder);

        buttons[16] = new JButton("$100");
        buttons[16].setForeground(new Color(255, 153, 0));
        buttons[16].setBackground(Color.BLUE);
        buttons[16].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[16].setBounds(74, 416, 106, 23);
        panel.add(buttons[16]);
        buttons[16].setBorder(emptyBorder);

        buttons[14] = new JButton("14");
        buttons[14].setForeground(new Color(255, 153, 0));
        buttons[14].setBackground(Color.BLUE);
        buttons[14].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[14].setBounds(10, 39, 48, 23);
        panel.add(buttons[14]);
        buttons[14].setBorder(emptyBorder);

        buttons[13] = new JButton("13");
        buttons[13].setBackground(Color.BLUE);
        buttons[13].setForeground(new Color(255, 153, 0));
        buttons[13].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[13].setBounds(10, 68, 48, 23);
        panel.add(buttons[13]);
        buttons[13].setBorder(emptyBorder);

        buttons[12] = new JButton("12");
        buttons[12].setBackground(Color.BLUE);
        buttons[12].setForeground(new Color(255, 153, 0));
        buttons[12].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[12].setBounds(10, 97, 48, 23);
        panel.add(buttons[12]);
        buttons[12].setBorder(emptyBorder);

        buttons[11] = new JButton("11");
        buttons[11].setForeground(new Color(255, 153, 0));
        buttons[11].setBackground(Color.BLUE);
        buttons[11].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[11].setBounds(10, 126, 48, 23);
        panel.add(buttons[11]);
        buttons[11].setBorder(emptyBorder);

        buttons[10] = new JButton("10");
        buttons[10].setBackground(Color.BLUE);
        buttons[10].setForeground(Color.WHITE);
        buttons[10].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[10].setBounds(10, 155, 48, 23);
        panel.add(buttons[10]);
        buttons[10].setBorder(emptyBorder);

        buttons[9] = new JButton("9");
        buttons[9].setForeground(new Color(255, 153, 0));
        buttons[9].setBackground(Color.BLUE);
        buttons[9].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[9].setBounds(10, 184, 48, 23);
        panel.add(buttons[9]);
        buttons[9].setBorder(emptyBorder);

        buttons[8] = new JButton("8");
        buttons[8].setBackground(Color.BLUE);
        buttons[8].setForeground(new Color(255, 153, 0));
        buttons[8].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[8].setBounds(10, 213, 48, 23);
        panel.add(buttons[8]);
        buttons[8].setBorder(emptyBorder);

        buttons[7] = new JButton("7");
        buttons[7].setForeground(new Color(255, 153, 0));
        buttons[7].setBackground(Color.BLUE);
        buttons[7].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[7].setBounds(10, 242, 48, 23);
        panel.add(buttons[7]);
        buttons[7].setBorder(emptyBorder);

        buttons[6] = new JButton("6");
        buttons[6].setForeground(new Color(255, 153, 0));
        buttons[6].setBackground(Color.BLUE);
        buttons[6].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[6].setBounds(10, 271, 48, 23);
        panel.add(buttons[6]);
        buttons[6].setBorder(emptyBorder);

        buttons[5] = new JButton("5");
        buttons[5].setBackground(Color.BLUE);
        buttons[5].setForeground(Color.WHITE);
        buttons[5].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[5].setBounds(10, 300, 48, 23);
        panel.add(buttons[5]);
        buttons[5].setBorder(emptyBorder);

        buttons[4] = new JButton("4");
        buttons[4].setForeground(new Color(255, 153, 0));
        buttons[4].setBackground(Color.BLUE);
        buttons[4].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[4].setBounds(10, 329, 48, 23);
        panel.add(buttons[4]);
        buttons[4].setBorder(emptyBorder);

        buttons[3] = new JButton("3");
        buttons[3].setBackground(Color.BLUE);
        buttons[3].setForeground(new Color(255, 153, 0));
        buttons[3].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[3].setBounds(10, 358, 48, 23);
        panel.add(buttons[3]);
        buttons[3].setBorder(emptyBorder);

        buttons[2] = new JButton("2");
        buttons[2].setForeground(new Color(255, 153, 0));
        buttons[2].setBackground(Color.BLUE);
        buttons[2].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[2].setBounds(10, 387, 48, 23);
        panel.add(buttons[2]);
        buttons[2].setBorder(emptyBorder);

        buttons[1] = new JButton("1");
        buttons[1].setBackground(Color.BLUE);
        buttons[1].setForeground(new Color(255, 153, 0));
        buttons[1].setFont(new Font("Dialog", Font.BOLD, 12));
        buttons[1].setBounds(10, 416, 48, 23);
        panel.add(buttons[1]);

        jLabel[4].setBounds(295, 87, 214, 214);
        contentPane.add(jLabel[4]);

        buttons[1].setBorder(emptyBorder);

    }



}
