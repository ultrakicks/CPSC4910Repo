/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.memoryimprovementgame;

/**
 *
 * @author Matt
 */
public class FamilyMember implements java.io.Serializable{
    public String name;
    public String relation;
    public String filePath;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setRelation(String relation) {
        this.relation = relation;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public String toString() {
        return new StringBuffer(" Name : ")
                .append(this.name).append(" Relation : ")
                .append(this.relation).append(" File Path : ").toString();
    }
}
