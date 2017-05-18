/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.InputStream;

/**
 *
 * @author Othman Ben Jemaa
 */
public class MyFile {
    
    public static File blob2File(InputStream binary){
                  InputStream is = binary;
                 return MyFile.stream2file(is);
              }

    private static File stream2file(InputStream is) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
