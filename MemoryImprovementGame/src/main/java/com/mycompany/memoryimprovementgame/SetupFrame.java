/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memoryimprovementgame;

import static com.mycompany.memoryimprovementgame.musicSelector.audioClip;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.Properties;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @authors: Matt Kelley & Brandon Hough
 */
public class SetupFrame extends javax.swing.JFrame {

    JFileChooser fc = new JFileChooser();
    ArrayList<FamilyMember> fmArry = new ArrayList<>();
    Properties properties = new Properties();
    String filePath;
    String configFile = "config.ser";
    int memberCount = 0;
    int motherCount = 0;
    int fatherCount = 0;
    FamilyMember fm1 = new FamilyMember();
    FamilyMember fm2 = new FamilyMember();
    FamilyMember fm3 = new FamilyMember();
    FamilyMember fm4 = new FamilyMember();
    FamilyMember fm5 = new FamilyMember();
    public static Clip audioClip;

    /**
     * Creates new form SetupFrame
     */
    public SetupFrame() {
        System.out.println("Initializing Componenets...");
        initComponents();
        System.out.println("Done Initializing Componenets!");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomeMsg = new javax.swing.JLabel();
        nameLbl = new javax.swing.JLabel();
        relationLbl = new javax.swing.JLabel();
        pictureLbl = new javax.swing.JLabel();
        nameField1 = new javax.swing.JTextField();
        nameField2 = new javax.swing.JTextField();
        nameField3 = new javax.swing.JTextField();
        nameField4 = new javax.swing.JTextField();
        nameField5 = new javax.swing.JTextField();
        relationBox1 = new javax.swing.JComboBox<>();
        relationBox2 = new javax.swing.JComboBox<>();
        relationBox3 = new javax.swing.JComboBox<>();
        relationBox4 = new javax.swing.JComboBox<>();
        relationBox5 = new javax.swing.JComboBox<>();
        pictureBtn1 = new javax.swing.JButton();
        pictureBtn2 = new javax.swing.JButton();
        pictureBtn3 = new javax.swing.JButton();
        pictureBtn4 = new javax.swing.JButton();
        pictureBtn5 = new javax.swing.JButton();
        nextFrame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Memory Improvement Game");

        welcomeMsg.setText("Welcome! Please input family names, relations, and a picture for each member to begin.");

        nameLbl.setText("Name");

        relationLbl.setText("Relation");

        pictureLbl.setText("Picture");

        nameField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameField1ActionPerformed(evt);
            }
        });

        nameField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameField2ActionPerformed(evt);
            }
        });

        nameField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameField3ActionPerformed(evt);
            }
        });

        nameField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameField4ActionPerformed(evt);
            }
        });

        nameField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameField5ActionPerformed(evt);
            }
        });

        relationBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose One...", "Father", "Mother", "Brother", "Sister", "Grand-mother", "Grand-father", "Aunt", "Uncle", "Cousin", "Nephew", "Niece", "Step-Father", "Step-Mother", "Step-Brother", "Step-Sister", "Half-Brother", "Half-Sister", "Son", "Daughter", "Grand-son", "Grand-daughter", "Great Grand-son", "Great Grand-daughter" }));
        relationBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relationBox1ActionPerformed(evt);
            }
        });

        relationBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose One...", "Father", "Mother", "Brother", "Sister", "Grand-mother", "Grand-father", "Aunt", "Uncle", "Cousin", "Nephew", "Niece", "Step-Father", "Step-Mother", "Step-Brother", "Step-Sister", "Half-Brother", "Half-Sister", "Son", "Daughter", "Grand-son", "Grand-daughter", "Great Grand-son", "Great Grand-daughter" }));
        relationBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relationBox2ActionPerformed(evt);
            }
        });

        relationBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose One...", "Father", "Mother", "Brother", "Sister", "Grand-mother", "Grand-father", "Aunt", "Uncle", "Cousin", "Nephew", "Niece", "Step-Father", "Step-Mother", "Step-Brother", "Step-Sister", "Half-Brother", "Half-Sister", "Son", "Daughter", "Grand-son", "Grand-daughter", "Great Grand-son", "Great Grand-daughter" }));
        relationBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relationBox3ActionPerformed(evt);
            }
        });

        relationBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose One...", "Father", "Mother", "Brother", "Sister", "Grand-mother", "Grand-father", "Aunt", "Uncle", "Cousin", "Nephew", "Niece", "Step-Father", "Step-Mother", "Step-Brother", "Step-Sister", "Half-Brother", "Half-Sister", "Son", "Daughter", "Grand-son", "Grand-daughter", "Great Grand-son", "Great Grand-daughter" }));
        relationBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relationBox4ActionPerformed(evt);
            }
        });

        relationBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose One...", "Father", "Mother", "Brother", "Sister", "Grand-mother", "Grand-father", "Aunt", "Uncle", "Cousin", "Nephew", "Niece", "Step-Father", "Step-Mother", "Step-Brother", "Step-Sister", "Half-Brother", "Half-Sister", "Son", "Daughter", "Grand-son", "Grand-daughter", "Great Grand-son", "Great Grand-daughter" }));
        relationBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relationBox5ActionPerformed(evt);
            }
        });

        pictureBtn1.setText("Add picture");
        pictureBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pictureBtn1ActionPerformed(evt);
            }
        });

        pictureBtn2.setText("Add picture");
        pictureBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pictureBtn2ActionPerformed(evt);
            }
        });

        pictureBtn3.setText("Add picture");
        pictureBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pictureBtn3ActionPerformed(evt);
            }
        });

        pictureBtn4.setText("Add picture");
        pictureBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pictureBtn4ActionPerformed(evt);
            }
        });

        pictureBtn5.setText("Add picture");
        pictureBtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pictureBtn5ActionPerformed(evt);
            }
        });

        nextFrame.setText("Next");
        nextFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextFrameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nextFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pictureBtn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pictureBtn3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pictureBtn4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pictureBtn5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(welcomeMsg))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(nameLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(relationBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(relationLbl)
                                .addGap(221, 221, 221)
                                .addComponent(pictureLbl))
                            .addComponent(relationBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(relationBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(relationBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(relationBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(welcomeMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLbl)
                    .addComponent(relationLbl)
                    .addComponent(pictureLbl))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relationBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pictureBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relationBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pictureBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relationBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pictureBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relationBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pictureBtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relationBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pictureBtn5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(258, 258, 258)
                .addComponent(nextFrame)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nameField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameField1ActionPerformed

    }//GEN-LAST:event_nameField1ActionPerformed

    private void nameField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameField2ActionPerformed

    private void nameField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameField3ActionPerformed

    private void nameField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameField4ActionPerformed

    private void nameField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameField5ActionPerformed

    private void nextFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextFrameActionPerformed
        boolean run = true;
        while (run){
            run = false;
            Properties propNames = new Properties();
            Properties propRelations = new Properties();
            Properties propFilePath = new Properties();

            // Create a list for each family member attribute
            String[] names_list = {fm1.name, fm2.name, fm3.name, fm4.name, fm5.name};
            String[] relations_list = {fm1.relation, fm2.relation, fm3.relation, fm4.relation, fm5.relation};
            String[] file_path_list = {fm1.filePath, fm2.filePath, fm3.filePath, fm4.filePath, fm5.filePath};

            int mother = 0;
            int father = 0;
            int grandmother = 0;
            int grandfather = 0;

            try {
                // Add each family member to the config file here
                for (int i = 0; i < names_list.length; i++) {
                    propNames.setProperty("name" + i, names_list[i]);
                    System.out.println("Adding to config file... " + "Name: " + names_list[i]);

                    //save properties to configuration file
                    propNames.store(new FileOutputStream("namesconfig.properties"), null);
                }
                for (int i = 0; i < relations_list.length; i++) {
                    propRelations.setProperty("relation" + i, relations_list[i]);
                    System.out.println("Adding to config file... " + " Relation: " + relations_list[i]);

                    //save properties to configuration file
                    propRelations.store(new FileOutputStream("relationsconfig.properties"), null);

                    if (relations_list[i].equals("Father")){
                        father++;
                    }
                    if (relations_list[i].equals("Mother")){
                        mother++;
                    }
                    if (relations_list[i].equals("Grand-father")){
                        grandfather++;
                    }
                    if (relations_list[i].equals("Grand-mother")){
                        grandmother++;
                    }
                }
                for (int i = 0; i < file_path_list.length; i++) {
                    propFilePath.setProperty("photo_directory" + i, file_path_list[i]);
                    System.out.println("Adding to config file... " + " File Path:" + file_path_list[i]);

                    //save properties to configuration file
                    propFilePath.store(new FileOutputStream("filepathconfig.properties"), null);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println(fatherCount);
            System.out.println(motherCount);

            System.out.println("the father amount is: " + father);
            System.out.println("the mother amount is: " + mother);
            System.out.println("the grandfather amount is: " + grandfather);
            System.out.println("the grandmother amount is: " + grandmother);

            if (father > 1 || mother > 1){
                run = true;
                JOptionPane.showMessageDialog(null, "You cannot have more than one mother or father. Please check the relations and try again.");
                throw new DuplicateFormatFlagsException("There can only be one father/mother");                
            }
            if (grandfather > 2 || grandmother > 2){
                run = true;
                JOptionPane.showMessageDialog(null, "You cannot have more than two grandmother or grandfather. Please check the relations and try again.");
                throw new DuplicateFormatFlagsException("There can only be two grandather/grandmother");
            }
        }
        MainMenuFrame mainFrame = new MainMenuFrame();
            mainFrame.setVisible(true);
            this.dispose();
            

            //commented out for now
            /**
             * ***if (fatherCount != 1 || motherCount != 1) {
             * JOptionPane.showMessageDialog(null, "You cannot have more than one
             * mother or father. Please check the relations and try again.",
             * "Warning", JOptionPane.INFORMATION_MESSAGE); } else { MainMenuFrame
             * mainFrame = new MainMenuFrame(); mainFrame.setVisible(true);
             * this.dispose(); } **
             */

    }//GEN-LAST:event_nextFrameActionPerformed

    public void setImageFileFilter() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image File", "jpg", "png", "jpeg");
        fc.setFileFilter(filter);
        fc.showSaveDialog(this);
        File file = fc.getSelectedFile();
        filePath = file.getAbsolutePath();
    }

    private void pictureBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pictureBtn1ActionPerformed
        setImageFileFilter();
        fm1.name = nameField1.getText();
        fm1.filePath = filePath;
    }//GEN-LAST:event_pictureBtn1ActionPerformed

    private void pictureBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pictureBtn2ActionPerformed
        setImageFileFilter();
        fm2.name = nameField2.getText();
        fm2.filePath = filePath;
    }//GEN-LAST:event_pictureBtn2ActionPerformed

    private void pictureBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pictureBtn3ActionPerformed
        setImageFileFilter();
        fm3.name = nameField3.getText();
        fm3.filePath = filePath;
    }//GEN-LAST:event_pictureBtn3ActionPerformed

    private void pictureBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pictureBtn4ActionPerformed
        setImageFileFilter();
        fm4.name = nameField4.getText();
        fm4.filePath = filePath;
    }//GEN-LAST:event_pictureBtn4ActionPerformed

    private void pictureBtn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pictureBtn5ActionPerformed
        setImageFileFilter();
        fm5.name = nameField5.getText();
        fm5.filePath = filePath;
    }//GEN-LAST:event_pictureBtn5ActionPerformed

    private void relationBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relationBox1ActionPerformed
        fm1.relation = relationBox1.getSelectedItem().toString();
        System.out.println("fm1.relation" + fm1.relation);
    }//GEN-LAST:event_relationBox1ActionPerformed

    private void relationBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relationBox2ActionPerformed
        fm2.relation = relationBox2.getSelectedItem().toString();
        System.out.println("fm2.relation" + fm2.relation);         
    }//GEN-LAST:event_relationBox2ActionPerformed

    private void relationBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relationBox3ActionPerformed
        fm3.relation = relationBox3.getSelectedItem().toString();
        System.out.println("fm3.relation" + fm3.relation);
    }//GEN-LAST:event_relationBox3ActionPerformed

    private void relationBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relationBox4ActionPerformed
        fm4.relation = relationBox4.getSelectedItem().toString();
        System.out.println("fm4.relation" + fm4.relation);
    }//GEN-LAST:event_relationBox4ActionPerformed

    private void relationBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relationBox5ActionPerformed
        fm5.relation = relationBox5.getSelectedItem().toString();
        System.out.println("fm5.relation" + fm5.relation);
    }//GEN-LAST:event_relationBox5ActionPerformed

    public static void playSound() {
        System.out.println("Being playing sound...");
        try {          
            String audioFilePath = "resources/background_music.wav";
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stopSound() {
        audioClip.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SetupFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetupFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetupFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetupFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        playSound();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SetupFrame().setVisible(true);
            }
        });
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField nameField1;
    private javax.swing.JTextField nameField2;
    private javax.swing.JTextField nameField3;
    private javax.swing.JTextField nameField4;
    private javax.swing.JTextField nameField5;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JButton nextFrame;
    private javax.swing.JButton pictureBtn1;
    private javax.swing.JButton pictureBtn2;
    private javax.swing.JButton pictureBtn3;
    private javax.swing.JButton pictureBtn4;
    private javax.swing.JButton pictureBtn5;
    private javax.swing.JLabel pictureLbl;
    private javax.swing.JComboBox<String> relationBox1;
    private javax.swing.JComboBox<String> relationBox2;
    private javax.swing.JComboBox<String> relationBox3;
    private javax.swing.JComboBox<String> relationBox4;
    private javax.swing.JComboBox<String> relationBox5;
    private javax.swing.JLabel relationLbl;
    private javax.swing.JLabel welcomeMsg;
    // End of variables declaration//GEN-END:variables
}
