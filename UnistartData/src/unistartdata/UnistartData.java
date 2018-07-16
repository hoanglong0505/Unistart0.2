/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unistartdata;

import function.mbti;
import function.school_Content;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class UnistartData {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        school_Content content= new school_Content();
//        String link="https://thongtintuyensinh.vn/Thong-tin-tuyen-sinh_C50_D671.htm";
//        content.getContent(link,"QHX");   
//        String link="https://thongtintuyensinh.vn/Thong-tin-tuyen-sinh_C52_D1703.htm#.U5p8SXKSxCg";
//        content.getlink2(link);
mbti m= new mbti();
m.getquestion();
    }
    
}
