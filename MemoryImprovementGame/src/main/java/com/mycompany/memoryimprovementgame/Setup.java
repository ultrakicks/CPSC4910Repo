/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memoryimprovementgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
        for (int i = 0; i <= num; i++) {
            relation.add(new JComboBox<String>());
            for (int j = 0; j < rel.length; i++) {
                relation.get(i).addItem(rel[j]);
            }
        }
        
        //create family member objects
        for (int i = 0; i <= num; i++) {
            members.add(new FamilyMember());
        }
        
        

        ActionListener listener = new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                setImageFileFilter();
                fm1.name = nameField1.getText();
                fm1.filePath = filePath;
            }

        };

        //create picture btns
        for (int i = 0; i <= num; i++) {
            picture.add(new JButton());
            picture.get(i).addActionListener(listener);
        }

    }

}
