/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import dao.SchoolDAO;
import java.io.File;

/**
 *
 * @author Admin
 */
public class UploadData {
    
    public void listCode(final File folder) {       
    for ( File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            System.out.println("Code "+fileEntry.getName());
                    SchoolDAO dao= new SchoolDAO();
            listFile(fileEntry, fileEntry.getName());
        } else {

        }
    }
}
    
        public void listFile(final File folder, String Code) {       
    for ( File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {

    
        } else {
                    SchoolDAO dao= new SchoolDAO();
                    dao.createImage(Code, fileEntry.getName());
            System.out.println("Name "+fileEntry.getName());
        }
    }
}
}
