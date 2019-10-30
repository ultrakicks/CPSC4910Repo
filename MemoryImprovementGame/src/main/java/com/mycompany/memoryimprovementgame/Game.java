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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
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
    public static ArrayList<String> random_relation = new ArrayList<>();
    public static int relationCount = 0;

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

            for (int i = names.size()-1; i >= 0; i--) {
                filePaths.add(propFilePaths.getProperty("photo_directory" + i));
            }
            
            System.out.print("File Paths: " + filePaths);
            System.out.println("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < filePaths.size(); i++) {
            images.add(new JButton());
            images.get(i).setActionCommand(relations.get(i));
        }
        System.out.println(relations);
        System.out.println(images);

        for (int i = 0; i < filePaths.size(); i++) {
            images.get(i).setIcon(new ImageIcon(filePaths.get(i).toString()));
        }
        
        //create main panel to hold other panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // Get a random relation from the array list of relations        
        for (int i = 0; i < relations.size(); i++) {
            random_relation.add(relations.get(i));
        }
        Collections.shuffle(random_relation);
        for (String f : random_relation) {
            System.out.println("Random Relation: " + f);
        }
                
        GridLayout layout = new GridLayout(2, 3);
        JPanel picturePanel = new JPanel();
        
        //add picture(buttons) to JPanel
        for (int i = 0; i < images.size(); i++) {
            picturePanel.add(images.get(i));
        }
        
        // Add question prompt to a JPanel
        JPanel questionPanel = new JPanel();
        JLabel question = new JLabel("Click on your " + random_relation.get(0));
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
            //@Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    String text = ((JButton) e.getSource()).getActionCommand();
                    System.out.println(text);
                    System.out.println(relationCount);
                    if (relationCount != random_relation.size() - 1) {
                        if (text.equals(random_relation.get(relationCount))) {
                        if (relationCount < random_relation.size() && relationCount != random_relation.size()) {
                            relationCount++;
                            switch (text) {
                            case "Father":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Mother":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Brother":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Sister":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Grand-mother":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Grand-father":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Aunt":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Uncle":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Cousin":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Nephew":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Niece":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Step-Father":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Step-Mother":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Step-Brother":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Step-Sister":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Half-Brother":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Half-Sister":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Son":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Daughter":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Grand-son":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Grand-daughter":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Great Grand-son":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            case "Great Grand-daughter":
                                question.setText("Click on your " + random_relation.get(relationCount));
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                            }
                        }
                        
                    } 
                    } else {
                        WinFrame win = new WinFrame();
                        win.setVisible(true);
                        relationCount = 0;
                        names.clear();
                        relations.clear();
                        filePaths.clear();
                        images.clear();
                        random_relation.clear();
                        game.dispose();
                        playWinSound();
                    }   
                }
            }
        };
        
        for (int i = 0; i < images.size(); i++) {
            images.get(i).addActionListener(listener);
        }
        
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public static void playSound() {
        System.out.println("Being playing sound...");
        try {
            String audioFilePath = "src/main/resources/correct.wav";
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playWinSound() {
        System.out.println("Being playing sound...");
        try {
            String audioFilePath = "src/main/resources/win.wav";
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
