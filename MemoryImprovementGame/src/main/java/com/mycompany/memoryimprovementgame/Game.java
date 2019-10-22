/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memoryimprovementgame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author matt
 */
public class Game {
    
    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<String> relations = new ArrayList<>();
    public static ArrayList<String> filePaths = new ArrayList<>();
    public static ArrayList<JButton> images = new ArrayList<>();
    
    public static void Main(String[] args) {
        
        
    }
    
    public static void startGame() {
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
        
        GridLayout layout = new GridLayout(2, 3);
        JPanel p = new JPanel();
        for (int i = 0; i < images.size(); i++) {
            p.add(images.get(i));
        }
        
        /*
        JPanel question = new JPanel();
        JLabel testQ = new JLabel("CLICK ON YOUR MOTHER MY DUDE");
        question.add(testQ);
        */
        
        
        /*
        File imageTest = new File("D:\\wallpapers\\242_-_3sq9tqr.jpg");
        ImageIcon testImg = new ImageIcon(imageTest);
        JButton img = new JButton();
        img.setIcon(testImg);
        p.add(img);
        */
        
        // Get a random relation from the array list of relations
        Random rand = new Random();
        int randomInt = rand.nextInt(relations.size());
        String random_relation = relations.get(randomInt);
        System.out.println("Random Relation: " + random_relation);
        
        JFrame game = new JFrame();
        game.setPreferredSize(new Dimension(1200, 1000));
        game.pack();
        game.setLocationRelativeTo(null);
        //game.add(question);
        game.add(p);
        game.setVisible(true);
        System.out.println(filePaths);
        
    }
    
    
}


