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
public class TestFunction {
    public static void main(String[] args) {
        final File folder = new File("D:/Wisky/Unistart0.2/UnistartFE/src/assets/School/img");
        UploadData data = new UploadData();
         SchoolDAO dao= new SchoolDAO();
                    dao.clearImage();
            data.listCode(folder);
    
    }
}
