/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memoryimprovementgame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Matt
 */
public class Setup {

    public static ArrayList<JTextField> names = new ArrayList<>();
    public static ArrayList<JComboBox> relation = new ArrayList<>();
    public static ArrayList<JButton> picture = new ArrayList<>();
    public static String[] rel = {"Choose One...", "Father", "Mother", "Brother", "Sister", "Grand-mother", "Grand-father", "Aunt", "Uncle",
        "Cousin", "Nephew", "Niece", "Step-Father", "Step-Mother", "Step-Brother", "Step-Sister", "Half-Brother", "Half-Sister", "Son", "Daughter",
        "Grand-son", "Grand-daughter", "Great Grand-son", "Great Grand-daughter"};
    public static JFileChooser fc = new JFileChooser();
    public static String filePath;
    public static ArrayList<FamilyMember> members = new ArrayList<>();
    public static int count = 0;

    public static void Main(String[] args) {

    }
    
    public static void setImageFileFilter() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image File", "jpg", "png", "jpeg");
        fc.setFileFilter(filter);
        //fc.showSaveDialog(this);
        File file = fc.getSelectedFile();
        filePath = file.getAbsolutePath();
    }

    public static void setupScreen(int nOfMembers) {
        int num = nOfMembers;
        //create text fields
        for (int i = 0; i <= num; i++) {
            names.add(new JTextField());
        }

        //create combo boxes
        JComboBox<String> test = new JComboBox<>(rel);
        for (int i = 0; i <= num; i++) {
            relation.add(new JComboBox<String>(rel));
        }
        System.out.println(relation);
        //create family member objects
        for (int i = 0; i <= num; i++) {
            members.add(new FamilyMember());
        }
        
        

        ActionListener btnListener = new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                setImageFileFilter();
                members.get(count).name = names.get(count).getText();
                members.get(count).filePath = filePath;
            }

        };

        //create picture btns
        for (int i = 0; i <= num; i++) {
            picture.add(new JButton("Picture"));
            picture.get(i).addActionListener(btnListener);
        }
        
        JPanel textFields = new JPanel();
        textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));
        JPanel comboBoxes = new JPanel();
        comboBoxes.setLayout(new BoxLayout(comboBoxes, BoxLayout.Y_AXIS));
        JPanel picBtn = new JPanel();
        picBtn.setLayout(new BoxLayout(picBtn, BoxLayout.Y_AXIS));
        JFrame main = new JFrame();
        
        for (int i = 0; i <= num; i++) {
            textFields.add(names.get(i));
            comboBoxes.add(relation.get(i));
            picBtn.add(picture.get(i));
        }
        
        GridLayout grid = new GridLayout(0, 1);
        textFields.setLayout(grid);
        comboBoxes.setLayout(grid);
        picBtn.setLayout(grid);
        main.setLocationRelativeTo(null);
        main.add(textFields);
        main.add(comboBoxes);
        main.add(picBtn);
        main.setPreferredSize(new Dimension(1200, 1000));
        main.pack();
        main.setVisible(true);

    }

}
