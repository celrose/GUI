import java.awt.*;
import java.awt.Dialog;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/*
-oct 26, 2016
cadavos, rocelle anne
also thanks to luke for the tips on this mp
*/

public class RPS extends Frame{
    
    private int pChoice;
    private int choice;
    private int playerScore = 0;
    private int computerScore = 0;

    private String computerChoice;
    private String choiceHolder;
    private String winner;

    private Label playerChoiceLabel;
    private Label playerScoreLabel;
    private Label compScoreLabel;
    private Label dialogLabel;

    private TextField playerScoreText;
    private TextField computerScoreText;
    private TextArea result;

    private Button playButton;
    private Button okplayButton;
    
    private CheckboxGroup choicesCheckbox;
    private Dialog dialog;
    private static Random rand = new Random();

    public RPS(){

        setTitle("LUCK IS NO EXCUSE");
        setLayout(new FlowLayout());
        setLocation(600, 300);
        setResizable(false);
        setSize(300, 400);
        //randomizer for computer choice
        this.rand = new Random();
        
        dialog = new Dialog(this, "GAME OVER", true);
        result = new TextArea("Result:", 10, 30);

        playerChoiceLabel = new Label("Your Choice");
        playerScoreLabel = new Label("Player's Score");
        compScoreLabel = new Label("Computer's Score");
        playerScoreText = new TextField(" ", 2);
        computerScoreText = new TextField(" ",2);

        playerScoreText.setEditable(false);
        computerScoreText.setEditable(false);
        
        dialogLabel = new Label("game over");
        playButton = new Button("RockPaperScissorsLizardSpock!");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Checkbox tmp = choicesCheckbox.getSelectedCheckbox();
                choiceHolder = tmp.getLabel();
                if(choiceHolder.equals("ROCK")){
                    pChoice = 1;
                }
                else if(choiceHolder.equals("PAPER")){
                    pChoice = 2;
                }
                else if(choiceHolder.equals("SCISSORS")){
                    pChoice = 3;
                }
                else if(choiceHolder.equals("LIZARD")){
                    pChoice = 4;
                }
                else if(choiceHolder.equals("SPOCK")){
                    pChoice = 5;
                }

                choice = randInt(1,5);
                if(choice == 1){
                    computerChoice = "ROCK";
                }
                else if(choice == 2){
                    computerChoice = "PAPER";
                }
                else if(choice == 3){
                    computerChoice = "SCISSORS";
                }
                else if(choice == 4){
                    computerChoice = "LIZARD";
                }
                else if(choice == 5){
                    computerChoice = "SPOCK";
                }

                result.append("\nYou chose " + choiceHolder);
                result.append("\nComputer chose " + computerChoice + "\n");

                if(pChoice == 1){
                    if(choice == 3 || choice == 4){
                        playerScore++;
                    }
                    else if(choice != pChoice){
                        computerScore++;
                    }
                }
                else if(pChoice == 2){
                    if(choice == 1 || choice == 5){
                        playerScore++;
                    }
                    else if(choice != pChoice){
                        computerScore++;
                    }
                }
                else if(pChoice == 3){
                    if(choice == 2 || choice == 4){
                        playerScore++;
                    }
                    else if(choice != pChoice){
                        computerScore++;
                    }
                }
                else if(pChoice == 4){
                    if(choice == 2 || choice == 5){
                        playerScore++;
                    }
                    else if(choice != pChoice){
                        computerScore++;
                    }
                }
                else if(pChoice == 5){
                    if(choice == 1 || choice == 3){
                        playerScore++;
                    }
                    else if(choice != pChoice){
                        computerScore++;
                    }
                }

                playerScoreText.setText(choiceHolder.valueOf(playerScore));
                computerScoreText.setText(choiceHolder.valueOf(computerScore));

                if(playerScore == 5){
                    result.append("\n YOU WON!");
                    winner = "You";
                    winner(winner);

                }
                else if(computerScore == 5){
                    result.append("\nYOU LOSE!");
                    winner = "Computer";
                    winner(winner);
                }
            }
        });

        okplayButton = new Button("OKAY!");
        okplayButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                playerScoreText.setText(null);
                computerScoreText.setText(null);
                result.setText(null);
                playerScore = 0;
                computerScore = 0;
                dialog.setVisible(false);
            }
        });

        dialog.add(dialogLabel);
        dialog.add(okplayButton);
        dialog.setSize(90, 90);
        dialog.setLocation(650, 400);
        dialog.setResizable(false);
        dialog.setLayout(new FlowLayout());

        choicesCheckbox = new CheckboxGroup();
        Checkbox chkRock = new Checkbox("ROCK",choicesCheckbox,true);
        Checkbox chkPaper = new Checkbox("PAPER",choicesCheckbox,false);
        Checkbox chkScissors = new Checkbox("SCISSORS",choicesCheckbox,false);
        Checkbox chkLizard = new Checkbox("LIZARD",choicesCheckbox,false);
        Checkbox chkSpock = new Checkbox("SPOCK",choicesCheckbox,false);

        add(playerChoiceLabel);
        add(chkRock);
        add(chkPaper);
        add(chkScissors);
        add(chkLizard);
        add(chkSpock);
        add(result);
        add(playButton);
        add(playerScoreLabel);
        add(playerScoreText);
        add(compScoreLabel);
        add(computerScoreText);

        /*
        opted for JPanel for better layout but too confusing for me

        JPanel outerPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 250, 200));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 230, 250, 10));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 100, 10));

        outerPanel.add(leftPanel, BorderLayout.WEST);
        outerPanel.add(rightPanel, BorderLayout.EAST);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        bottomPanel.add(play, BorderLayout.NORTH);
        frame.setContentPane(outerPanel);
        frame.setVisible(true);
        */

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void winner(String winner){
        dialogLabel.setText(choiceHolder.format("Game Over! %s won!",winner));
        dialog.setVisible(true);
        
        Button exitButton = new Button();
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }    
        });
        dialog.add(exitButton);
    }

    public static int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args){
        RPS rps = new RPS();
    }
}