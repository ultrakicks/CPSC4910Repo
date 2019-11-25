/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memoryimprovementgame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    public static ArrayList<String> random_name = new ArrayList<>();
    public static int relationCount = 0;
    public static int nameCount = 0;
    public static String[] objs = {"Book", "Pen", "Key", "Umbrella", "Button", "Toothbrush", "Light bulb", "Newspaper", "Alarm Clock", "Comb", "Watch", "Apple", "Telephone", "Soap", "TV", "Bed", "Fork", "Spoon", "Flower", "Shoe", "Paper clip", "Car", "Milk", "Water"};
    public static String[] objImgs = {"resources/objs/book.png", "resources/objs/pen.png", "resources/objs/key.png", "resources/objs/umbrella.png", "resources/objs/button.png", "resources/objs/toothbrush.png", "resources/objs/lightbulb.png", "resources/objs/newspaper.png",
        "resources/objs/alarmclock.png", "resources/objs/comb.png", "resources/objs/watch.png", "resources/objs/apple.png", "resources/objs/telephone.png", "resources/objs/soap.png", "resources/objs/tv.png", "resources/objs/bed.png", "resources/objs/fork.png",
        "resources/objs/spoon.png", "resources/objs/flower.png", "resources/objs/shoe.png", "resources/objs/paperclip.png", "resources/objs/car.png", "resources/objs/milk.png", "resources/objs/water.png"};
    public static JButton[] objBtn = new JButton[24];
    public static String hold = null;
    public static JButton holdBtn;

    public static void Main(String[] args) {
    }

    public void startGame() {
        File statsFile = new File("stats.txt");
        boolean statsFileExists = statsFile.exists();
        System.out.println("Stats file exists : " + statsFileExists);

        if (!statsFileExists) {
            try {
                String gameStats = "Game Name , Incorrect Count , Correct Count , Total Count , Compentancy\n";
                BufferedWriter writer = new BufferedWriter(new FileWriter("stats.txt"));
                writer.append(gameStats);
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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

            for (int i = relations.size() - 1; i >= 0; i--) {
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
            ImageIcon icon = new ImageIcon(filePaths.get(i));
            Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            images.get(i).setIcon(icon);
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
        System.out.println(filePaths);

        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            int counter_in_secs = 0;

            public void actionPerformed(ActionEvent evt) {
                counter_in_secs += 1;
                System.out.println("Seconds Played: " + counter_in_secs);
            }
        };

        new Timer(delay, taskPerformer).start();

        //start of gamelogic
        ActionListener listener = new ActionListener() {
            //@Override
            // variables to store statistics for a user
            double incorrectSelectionCount = 0.00;
            double correctSelectionCount = 0.00;
            double totalSelectionCount = 0.00;

            public void actionPerformed(ActionEvent e) {
                totalSelectionCount++;
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
                            correctSelectionCount += 1;
                        } else if (text.equals("quit")) {

                            MainMenuFrame menu = new MainMenuFrame();
                            menu.setVisible(true);
                            relationCount = 0;
                            relations.clear();
                            filePaths.clear();
                            images.clear();
                            random_relation.clear();
                            game.dispose();

                        }
                    } else {
                        new Timer(delay, taskPerformer).stop();
                        //write statistics to the statistics file
                        correctSelectionCount += 1;
                        incorrectSelectionCount = totalSelectionCount - correctSelectionCount;
                        System.out.println("Incorrect Count (TEST): " + incorrectSelectionCount);
                        double competancy = Math.round((correctSelectionCount / totalSelectionCount) * 100);
                        System.out.println("Correct Count: " + correctSelectionCount);
                        //System.out.println("Incorrect Count: " + incorrectSelectionCount);
                        System.out.println("Total Selection Count: " + totalSelectionCount);
                        System.out.println("Competancy: " + competancy);
                        //System.out.println("Time to complete (in seconds): " + counter_in_secs);

                        try {
                            String gameStats = "Relation Game / " + incorrectSelectionCount + " / "
                                    + correctSelectionCount + " / " + totalSelectionCount + " / " + competancy + "%";
                            FileWriter fileWriter = new FileWriter("stats.txt", true);
                            PrintWriter printWriter = new PrintWriter(fileWriter);
                            printWriter.println(gameStats);
                            printWriter.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        WinFrame winRealationsGame = new WinFrame();
                        winRealationsGame.setVisible(true);
                        relationCount = 0;
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

        game.setPreferredSize(new Dimension(1200, 1000));
        game.pack();
        game.setLocationRelativeTo(null);

        //game.add(new TimerPanel());
        JPanel exitPanel = new JPanel();
        JButton exitBtn = new JButton();
        exitBtn.setText("Quit");
        exitBtn.setFont(new Font("Comic Sans", Font.PLAIN, 35));
        exitBtn.setActionCommand("quit");
        exitBtn.addActionListener(listener);
        exitPanel.add(exitBtn);
        mainPanel.add(questionPanel);
        mainPanel.add(picturePanel);
        mainPanel.add(exitPanel);
        JScrollPane jScrollPane = new JScrollPane(mainPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        game.add(jScrollPane);

        game.setExtendedState(JFrame.MAXIMIZED_BOTH);
        game.getContentPane().add(jScrollPane);
        game.setVisible(true);

        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void startNameGame() {
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
        //reads in photo file paths to an array
        try (InputStream input = new FileInputStream("filepathconfig.properties")) {
            Properties propFilePaths = new Properties();

            //load the config file
            propFilePaths.load(input);

            for (int i = names.size() - 1; i >= 0; i--) {
                filePaths.add(propFilePaths.getProperty("photo_directory" + i));
            }

            System.out.print("File Paths: " + filePaths);
            System.out.println("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < filePaths.size(); i++) {
            images.add(new JButton());
            images.get(i).setActionCommand(names.get(i));
        }
        System.out.println(names);
        System.out.println(images);

        for (int i = 0; i < filePaths.size(); i++) {
            ImageIcon icon = new ImageIcon(filePaths.get(i));
            Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            images.get(i).setIcon(icon);
        }

        //create main panel to hold other panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Get a random relation from the array list of relations        
        for (int i = 0; i < names.size(); i++) {
            random_name.add(names.get(i));
        }
        Collections.shuffle(random_name);
        for (String f : random_name) {
            System.out.println("Random Name: " + f);
        }

        GridLayout layout = new GridLayout(2, 3);
        JPanel picturePanel = new JPanel();

        //add picture(buttons) to JPanel
        for (int i = 0; i < images.size(); i++) {
            picturePanel.add(images.get(i));
        }

        // Add question prompt to a JPanel
        JPanel questionPanel = new JPanel();
        JLabel question = new JLabel("Click on " + random_name.get(0));
        question.setFont(new Font("Comic Sans", Font.PLAIN, 50));
        questionPanel.add(question);

        JFrame game = new JFrame();
        System.out.println(filePaths);

        //start of gamelogic
        ActionListener listener = new ActionListener() {
            //@Override
            // variables to store statistics for a user
            double incorrectSelectionCount = 0.00;
            double correctSelectionCount = 0.00;
            double totalSelectionCount = 0.00;
            double competancy = 0.00;

            public void actionPerformed(ActionEvent e) {
                totalSelectionCount += 1;
                if (e.getSource() instanceof JButton) {
                    String text = ((JButton) e.getSource()).getActionCommand();
                    System.out.println(text);
                    System.out.println(nameCount);
                    if (nameCount != random_name.size() - 1) {
                        if (text.equals(random_name.get(nameCount))) {
                            if (nameCount < random_name.size() && nameCount != random_name.size()) {
                                if (text.equals(random_name.get(nameCount))) {
                                    nameCount++;
                                    question.setText("Click on " + random_name.get(nameCount));
                                    ((JButton) e.getSource()).setVisible(false);
                                    System.out.println("Correct Count: " + correctSelectionCount);
                                    //System.out.println("Incorrect Count: " + incorrectSelectionCount);
                                    System.out.println("Total Selection Count: " + totalSelectionCount);
                                    playSound();
                                }
                            }
                            correctSelectionCount += 1;
                        } else if (text.equals("quit")) {

                            MainMenuFrame menu = new MainMenuFrame();
                            menu.setVisible(true);
                            nameCount = 0;
                            names.clear();
                            filePaths.clear();
                            images.clear();
                            random_name.clear();
                            game.dispose();

                        }
                    } else {
                        //write statistics to the statistics file
                        correctSelectionCount += 1;
                        incorrectSelectionCount = totalSelectionCount - correctSelectionCount;
                        double competancy = Math.round((correctSelectionCount / totalSelectionCount) * 100);
                        System.out.println("Correct Count: " + correctSelectionCount);
                        System.out.println("Incorrect Count: " + incorrectSelectionCount);
                        System.out.println("Total Selection Count: " + totalSelectionCount);
                        System.out.println("Competancy: " + competancy);
                        try {
                            String gameStats = "Name Game / " + incorrectSelectionCount + " / "
                                    + correctSelectionCount + " / " + totalSelectionCount + " / " + competancy + "%";
                            FileWriter fileWriter = new FileWriter("stats.txt", true);
                            PrintWriter printWriter = new PrintWriter(fileWriter);
                            printWriter.println(gameStats);
                            printWriter.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        WinFrame winNameGame = new WinFrame();
                        System.out.println("Displaying WinNameGame frame...");
                        winNameGame.setVisible(true);
                        nameCount = 0;
                        names.clear();
                        filePaths.clear();
                        images.clear();
                        random_name.clear();
                        game.dispose();
                        playWinSound();
                    }
                }
            }
        };

        for (int i = 0;
                i < images.size();
                i++) {
            images.get(i).addActionListener(listener);
        }

        game.setPreferredSize(new Dimension(1200, 1000));
        game.pack();
        game.setLocationRelativeTo(null);

        //game.add(new TimerPanel());
        JPanel exitPanel = new JPanel();
        JButton exitBtn = new JButton();
        exitBtn.setText("Quit");
        exitBtn.setFont(new Font("Comic Sans", Font.PLAIN, 35));
        exitBtn.setActionCommand("quit");
        exitBtn.addActionListener(listener);
        exitPanel.add(exitBtn);
        mainPanel.add(questionPanel);
        mainPanel.add(picturePanel);
        mainPanel.add(exitPanel);
        JScrollPane jScrollPane = new JScrollPane(mainPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        game.add(jScrollPane);

        game.setExtendedState(JFrame.MAXIMIZED_BOTH);
        game.getContentPane().add(jScrollPane);
        game.setVisible(true);

        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void startNameAndRelationGame() {
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

            for (int i = names.size() - 1; i >= 0; i--) {
                relations.add(propRelations.getProperty("relation" + i));
            }

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

            for (int i = names.size() - 1; i >= 0; i--) {
                filePaths.add(propFilePaths.getProperty("photo_directory" + i));
            }

            System.out.print("File Paths: " + filePaths);
            System.out.println("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < filePaths.size(); i++) {
            images.add(new JButton());
            images.get(i).setActionCommand(names.get(i) + relations.get(i));
        }
        System.out.println(names);
        System.out.println(images);

        for (int i = 0; i < filePaths.size(); i++) {
            ImageIcon icon = new ImageIcon(filePaths.get(i));
            Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            images.get(i).setIcon(icon);
        }

        //create main panel to hold other panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Get a random relation from the array list of relations        
        for (int i = 0; i < names.size(); i++) {
            random_name.add(names.get(i));
        }
        Collections.shuffle(random_name);
        for (String f : random_name) {
            System.out.println("Random Name: " + f);
        }

        GridLayout layout = new GridLayout(2, 3);
        JPanel picturePanel = new JPanel();

        //add picture(buttons) to JPanel
        for (int i = 0; i < images.size(); i++) {
            picturePanel.add(images.get(i));
        }

        // Add question prompt to a JPanel
        JPanel questionPanel = new JPanel();
        JLabel question = new JLabel("Click on " + random_name.get(0) + " and please select their relation to you.");
        JComboBox<String> relationBox = new JComboBox<String>();
        List<String> listWithoutDuplicates = relations.stream().distinct().collect(Collectors.toList());
        relationBox.setPreferredSize(new Dimension(300, 40));
        relationBox.setFont(new Font("Comic Sans", Font.PLAIN, 35));
        //add available relations to combobox
        for (int i = 0; i < listWithoutDuplicates.size(); i++) {
            relationBox.addItem(listWithoutDuplicates.get(i));
        }
        question.setFont(new Font("Comic Sans", Font.PLAIN, 35));
        questionPanel.add(question);
        questionPanel.add(relationBox);

        JFrame game = new JFrame();

        //start of gamelogic
        ActionListener listener = new ActionListener() {
            //@Override
            // variables to store statistics for a user
            double incorrectSelectionCount = 0.00;
            double correctSelectionCount = 0.00;
            double totalSelectionCount = 0.00;
            double competancy = 0.00;

            public void actionPerformed(ActionEvent e) {
                totalSelectionCount += 1;
                if (e.getSource() instanceof JButton) {
                    String text = ((JButton) e.getSource()).getActionCommand();
                    System.out.println(random_name.get(nameCount) + relationBox.getSelectedItem().toString());
                    System.out.println(text);
                    System.out.println(nameCount);
                    if (nameCount != random_name.size() - 1) {
                        if (nameCount < random_name.size() && nameCount != random_name.size()) {
                            if (text.equals(random_name.get(nameCount) + relationBox.getSelectedItem().toString())) {
                                nameCount++;
                                question.setText("Click on " + random_name.get(nameCount) + " and their relation to you.");
                                ((JButton) e.getSource()).setVisible(false);
                                playSound();
                                correctSelectionCount += 1;
                            } else if (text.equals("quit")) {

                                MainMenuFrame menu = new MainMenuFrame();
                                menu.setVisible(true);
                                nameCount = 0;
                                names.clear();
                                relations.clear();
                                filePaths.clear();
                                images.clear();
                                random_name.clear();
                                game.dispose();

                            }
                        }
                    } else {
                        //write statistics to the statistics file
                        correctSelectionCount += 1;
                        incorrectSelectionCount = totalSelectionCount - correctSelectionCount;
                        double competancy = Math.round((correctSelectionCount / totalSelectionCount) * 100);
                        System.out.println("Correct Count: " + correctSelectionCount);
                        System.out.println("Incorrect Count: " + incorrectSelectionCount);
                        System.out.println("Total Selection Count: " + totalSelectionCount);
                        System.out.println("Competancy: " + competancy);

                        try {
                            String gameStats = "Name and Realtion Game / " + incorrectSelectionCount + " / "
                                    + correctSelectionCount + " / " + totalSelectionCount + " / " + competancy + "%";
                            FileWriter fileWriter = new FileWriter("stats.txt", true);
                            PrintWriter printWriter = new PrintWriter(fileWriter);
                            printWriter.println(gameStats);
                            printWriter.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        WinFrame winNameAndRelationsGame = new WinFrame();
                        winNameAndRelationsGame.setVisible(true);
                        nameCount = 0;
                        names.clear();
                        relations.clear();
                        filePaths.clear();
                        images.clear();
                        random_name.clear();
                        game.dispose();
                        playWinSound();
                    }
                }
            }
        };

        for (int i = 0; i < images.size(); i++) {
            images.get(i).addActionListener(listener);
        }

        game.setPreferredSize(new Dimension(1200, 1000));
        game.pack();
        game.setLocationRelativeTo(null);

        //game.add(new TimerPanel());
        JPanel exitPanel = new JPanel();
        JButton exitBtn = new JButton();
        exitBtn.setText("Quit");
        exitBtn.setFont(new Font("Comic Sans", Font.PLAIN, 35));
        exitBtn.setActionCommand("quit");
        exitBtn.addActionListener(listener);
        exitPanel.add(exitBtn);
        mainPanel.add(questionPanel);
        mainPanel.add(picturePanel);
        mainPanel.add(exitPanel);
        JScrollPane jScrollPane = new JScrollPane(mainPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        game.add(jScrollPane);

        game.setExtendedState(JFrame.MAXIMIZED_BOTH);
        game.getContentPane().add(jScrollPane);
        game.setVisible(true);

        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void objectsGame() {
        JFrame game = new JFrame();

        //create buttons to match with images
        for (int i = 0; i < objBtn.length; i++) {
            objBtn[i] = new JButton();
            objBtn[i].setText(objs[i]);
            objBtn[i].setFont(new Font("Comic Sans", Font.PLAIN, 35));
            objBtn[i].setActionCommand(objs[i]);
        }

        //create buttons to hold images
        for (int i = 0; i < objs.length; i++) {
            images.add(new JButton());
            images.get(i).setActionCommand(objs[i]);
        }
        //add images to buttons
        for (int i = 0; i < objs.length; i++) {
            ImageIcon icon = new ImageIcon(objImgs[i]);
            Image img = icon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            images.get(i).setIcon(icon);
        }

        //create main panel to hold other panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        GridLayout grid = new GridLayout(0, 6);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(grid);
        //shuffle buttons
        List<JButton> tmp = Arrays.asList(objBtn);
        Collections.shuffle(tmp);
        //add matching buttons to JPanel
        for (int i = 0; i < objs.length; i++) {
            btnPanel.add(tmp.get(i));
        }

        JPanel picturePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        picturePanel.setLayout(grid);
        //shuffle images
        Collections.shuffle(images);
        //add picture(buttons) to JPanel
        for (int i = 0; i < images.size(); i++) {
            picturePanel.add(images.get(i));
        }

        //start of gamelogic
        ActionListener listener = new ActionListener() {
            //@Override
            // variables to store statistics for a user
            double incorrectSelectionCount = 0.00;
            double correctSelectionCount = 0.00;
            double totalSelectionCount = 0.00;
            double competancy = 0.00;

            public void actionPerformed(ActionEvent e) {
                totalSelectionCount += 1;
                if (e.getSource() instanceof JButton) {
                    String text = ((JButton) e.getSource()).getActionCommand();
                    if (nameCount != images.size() - 1) {
                        if (text.equals(hold)) {
                            nameCount++;
                            ((JButton) e.getSource()).setVisible(false);
                            holdBtn.setVisible(false);
                            playSound();
                            correctSelectionCount += 1;
                        } else if (text.equals("quit")) {

                            MainMenuFrame menu = new MainMenuFrame();
                            images.clear();
                            objBtn = new JButton[24];
                            menu.setVisible(true);
                            game.dispose();

                        } else {
                            hold = text;
                            holdBtn = (JButton) e.getSource();
                        }
                    } else {
                        //write statistics to the statistics file
                        //correctSelectionCount += 1;
                        //incorrectSelectionCount = totalSelectionCount - correctSelectionCount;
                        double competancy = Math.round((correctSelectionCount - incorrectSelectionCount / correctSelectionCount) * 100);
                        System.out.println("Correct Count: " + correctSelectionCount);
                        System.out.println("Total Selection Count: " + totalSelectionCount);
                        System.out.println("Competancy: " + competancy);

                        try {
                            String gameStats = "Everyday Objects Game / " + incorrectSelectionCount + " / "
                                    + correctSelectionCount + " / " + totalSelectionCount + " / " + competancy + "%";
                            FileWriter fileWriter = new FileWriter("stats.txt", true);
                            PrintWriter printWriter = new PrintWriter(fileWriter);
                            printWriter.println(gameStats);
                            printWriter.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        WinFrame winNameAndRelationsGame = new WinFrame();
                        winNameAndRelationsGame.setVisible(true);
                        nameCount = 0;
                        objBtn = new JButton[24];
                        images.clear();
                        game.dispose();
                        playWinSound();
                    }

                }
            }
        };

        for (int i = 0; i < objs.length; i++) {
            objBtn[i].addActionListener(listener);
            images.get(i).addActionListener(listener);
        }

        game.setPreferredSize(new Dimension(1200, 1000));
        game.pack();
        game.setLocationRelativeTo(null);

        //game.add(new TimerPanel());
        JPanel exitPanel = new JPanel();
        JButton exitBtn = new JButton();
        exitBtn.setText("Quit");
        exitBtn.setFont(new Font("Comic Sans", Font.PLAIN, 35));
        exitBtn.setActionCommand("quit");
        exitBtn.addActionListener(listener);
        exitPanel.add(exitBtn);
        mainPanel.add(btnPanel);
        mainPanel.add(picturePanel);
        mainPanel.add(exitPanel);
        JScrollPane jScrollPane = new JScrollPane(mainPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        game.add(jScrollPane);

        game.setExtendedState(JFrame.MAXIMIZED_BOTH);
        game.getContentPane().add(jScrollPane);
        game.setVisible(true);

        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    

    public static void playSound() {
        System.out.println("Being playing sound...");
        try {
            String audioFilePath = "resources/correct.wav";
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
            String audioFilePath = "resources/win.wav";
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
