/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memoryimprovementgame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 *
 * @author Matt and Brandon
 */
public class Game {

    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<String> relations = new ArrayList<>();
    public static ArrayList<String> filePaths = new ArrayList<>();
    public static ArrayList<JButton> images = new ArrayList<>();

    public static void Main(String[] args) {

    }

    public void startGame() {
        //reads in names to an array
        try (InputStream input = new FileInputStream("namesconfig.properties")) {
            Properties propNames = new Properties();

            //load the config file
            propNames.load(input);

            propNames.values().forEach(x -> names.add(x.toString()));

            System.out.print("Names: " + names);
            System.out.println("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //reads in relations to an array
        try (InputStream input = new FileInputStream("relationsconfig.properties")) {
            Properties propRelations = new Properties();

            //load the config file
            propRelations.load(input);

            propRelations.values().forEach(x -> relations.add(x.toString()));

            System.out.print("Relations: " + relations);
            System.out.println("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //reads in photo file paths to an array
        try (InputStream input = new FileInputStream("filepathconfig.properties")) {
            Properties propFilePaths = new Properties();

            //load the config file
            propFilePaths.load(input);

            propFilePaths.values().forEach(x -> filePaths.add(x.toString()));

            System.out.print("File Paths: " + filePaths);
            System.out.println("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < filePaths.size(); i++) {
            images.add(new JButton());
        }

        for (int i = 0; i < filePaths.size(); i++) {
            //ImageIcon icon = new ImageIcon(getClass().getResource(filePaths.get(i).toString()));
            images.get(i).setIcon(new ImageIcon(filePaths.get(i).toString()));
        }
        
        //create main panel to hold other panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // Get a random relation from the array list of relations
        Random rand = new Random();
        int randomInt = rand.nextInt(relations.size());
        String random_relation = relations.get(randomInt);
        System.out.println("Random Relation: " + random_relation);
        
        GridLayout layout = new GridLayout(2, 3);
        JPanel picturePanel = new JPanel();
        
        //add picture(buttons) to JPanel
        for (int i = 0; i < images.size(); i++) {
            picturePanel.add(images.get(i));
        }
        
        // Add question prompt to a JPanel
        JPanel questionPanel = new JPanel();
        JLabel question = new JLabel("Click on your " + random_relation);
        question.setFont(new Font("Comic Sans", Font.PLAIN, 50));
        questionPanel.add(question);

        JFrame game = new JFrame();
        game.setPreferredSize(new Dimension(1200, 1000));
        game.pack();
        game.setLocationRelativeTo(null);

        //game.add(new TimerPanel());
        mainPanel.add(questionPanel);
        mainPanel.add(picturePanel);
        game.add(mainPanel);

        game.setVisible(true);
        System.out.println(filePaths);
        
        //start of gamelogic
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    String text = ((JButton) e.getSource()).getText();
                    JOptionPane.showMessageDialog(null, text);
                }
            }
        };
        images.addActionListener(listener);
        
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public class TimerPanel extends JPanel {
        Timer timer;
        int count;
        
        public TimerPanel() {
            //timer set up
            JLabel time = null;
            timer = new javax.swing.Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               count++;
               if (count < 100000) {
                   time.setText(Integer.toString(count));
               } else {
                   ((javax.swing.Timer) (e.getSource())).stop();
               }
            }
            });
            timer.setInitialDelay(0);
            timer.start();
            JPanel timePanel = new JPanel();
            timePanel.add(time);
        }
         
    }
}
