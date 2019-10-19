/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memoryimprovementgame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author matt
 */
public class Game {
    
    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<String> relations = new ArrayList<>();
    public static ArrayList<String> filePaths = new ArrayList<>();
    public static ArrayList<JLabel> images = new ArrayList<>();
    
    public static void Main(String[] args) {
        
        
        
    }
    
    public static void startGame() {
        //reads in names to an array
        try (InputStream input = new FileInputStream("namesconfig.properties")) {
            Properties propNames = new Properties();
            
            //load the config file
            propNames.load(input);
                       
            propNames.values().forEach(x -> names.add(x.toString()));
            
            System.out.print(names);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //reads in relations to an array
        try (InputStream input = new FileInputStream("relationsconfig.properties")) {
            Properties propRelations = new Properties();
            
            //load the config file
            propRelations.load(input);
            
            propRelations.values().forEach(x -> relations.add(x.toString()));
            
            System.out.print(relations);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //reads in photo file paths to an array
        try (InputStream input = new FileInputStream("filepathconfig.properties")) {
            Properties propFilePaths = new Properties();
            
            //load the config file
            propFilePaths.load(input);
            
            propFilePaths.values().forEach(x -> filePaths.add(x.toString()));
            
            System.out.print(filePaths);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        for (int i = 0; i < filePaths.size(); i++) {
            images.add(new JLabel());
        }
        
        for (int i = 0; i < filePaths.size(); i++) {
            //ImageIcon icon = new ImageIcon(getClass().getResource(filePaths.get(i).toString()));
            images.get(i).setIcon(new ImageIcon(filePaths.get(i).toString()));
        }
        
        JPanel p = new JPanel();
        for (int i = 0; i < images.size(); i++) {
            p.add(images.get(i));
        }
        
        JFrame game = new JFrame();
        game.add(p);
        game.setVisible(true);
    }
    
    
}


