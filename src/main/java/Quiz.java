import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Quiz extends JFrame implements ActionListener {

    List<Question> questions;
    List<String> userAnswers;

    JLabel qno, question;
    JRadioButton opt1, opt2, opt3;
    ButtonGroup groupoptions;
    JButton next, submit;

    int count = 0;
    int score = 0;

    String name;

    Quiz(String name) {
        this.name = name;
        setBounds(300, 100, 900, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Load questions from DB
        questions = QuestionLoader.loadQuestions();
        userAnswers = new ArrayList<>();

        qno = new JLabel();
        qno.setBounds(50, 50, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);

        question = new JLabel();
        question.setBounds(100, 50, 800, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);

        opt1 = new JRadioButton();
        opt1.setBounds(100, 120, 600, 30);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        opt1.setBackground(Color.WHITE);
        add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(100, 160, 600, 30);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        opt2.setBackground(Color.WHITE);
        add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(100, 200, 600, 30);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        opt3.setBackground(Color.WHITE);
        add(opt3);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);

        next = new JButton("Next");
        next.setBounds(600, 300, 100, 30);
        next.addActionListener(this);
        add(next);

        submit = new JButton("Submit");
        submit.setBounds(720, 300, 100, 30);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);

        start(count);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (groupoptions.getSelection() == null) {
            userAnswers.add("");
        } else {
            userAnswers.add(groupoptions.getSelection().getActionCommand());
        }

        if (ae.getSource() == next) {
            count++;
            if (count == questions.size() - 1) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            start(count);
        } else if (ae.getSource() == submit) {
            // Calculate score
            for (int i = 0; i < questions.size(); i++) {
                if (userAnswers.get(i).equals(questions.get(i).answer)) {
                    score += 10;
                }
            }

            setVisible(false);
            ScoreSaver.saveScore(name, score);
            new Score(name, score);
        }
    }

    public void start(int count) {
        if (count < questions.size()) {
            Question q = questions.get(count);
            qno.setText((count + 1) + ".");
            question.setText(q.question);
            opt1.setText(q.option1);
            opt1.setActionCommand(q.option1);
            opt2.setText(q.option2);
            opt2.setActionCommand(q.option2);
            opt3.setText(q.option3);
            opt3.setActionCommand(q.option3);
            groupoptions.clearSelection();
        }
    }

    public static void main(String[] args) {
        new Quiz("User");
    }
}
