/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static jdk.nashorn.internal.objects.ArrayBufferView.length;

/**
 *
 * @author Othman Ben Jemaa
 */
public class TextFieldValidation {
    public static boolean textValidation(TextField tf){
    
    boolean b=false;
    if (tf.getText().length()!=0 || !tf.getText().isEmpty()){
        b=true;

    }
    return b;
    
    }
    public static boolean textValidation(TextField tf ,Label lb,String errorMessage){
    
    boolean b=true;
    String mag=null;
    if (!textValidation(tf)){
    b=false;
    mag=errorMessage;
    }
   lb.setText(mag);
    return b;
    
    }
    
    public static boolean AreaValidation(TextArea tf){
    
    boolean b=false;
    if (tf.getText().length()!=0 || !tf.getText().isEmpty()){
        b=true;

    }
    return b;
    
    }
     public static boolean AreaValidation(TextArea tf ,Label lb,String errorMessage){
    
    boolean b=true;
    String mag=null;
    if (!AreaValidation(tf)){
    b=false;
    mag=errorMessage;
    }
   lb.setText(mag);
    return b;
    
    }

    
    public static boolean textalphabet(TextField tf ,Label lb,String errorMessage)
    {
    boolean isAlphabet =true;
    
    String validationString=null;
    if(!tf.getText().matches("[a-z A-Z]+")){
    isAlphabet=false;
    
    }
    lb.setText(errorMessage);
        System.out.println(tf.getText().matches("[a-z A-Z]+"));
        return isAlphabet;
    
}
    
    
        public static boolean texNum(TextField tf ,Label lb,String errorMessage)
    {
    boolean isNum =true;
    
    String validationString=null;
    if(!tf.getText().matches("[0-9]+")){
    isNum=false;
    
    }
    lb.setText(errorMessage);
        System.out.println(tf.getText().matches("[0-9]+"));
        return isNum;
    
}
    
                public static boolean tex8Num(TextField tf ,Label lb,String errorMessage)
    {
    boolean isNum =true;
    
    
    String validationString=null;
    if((!tf.getText().matches("[0-9]+"))||((tf.getText()).length()!=8)){
    isNum=false;
    
    }
    lb.setText(errorMessage);
        System.out.println(tf.getText().matches("[0-9]+"));
        return isNum;
    
}
    //[a-z0-9]
    
      public static boolean texAlphNum(TextField tf ,Label lb,String errorMessage)
    {
    boolean isAlphNum =true;
    
    String validationString=null;
    if(!tf.getText().matches("[a-z0-9]+")){
    isAlphNum=false;
    
    }
    lb.setText(errorMessage);
        System.out.println(tf.getText().matches("[a-z0-9]+"));
        return isAlphNum;
    
}
      public static boolean texMail(TextField tf ,Label lb,String errorMessage)
    {
    boolean isMail =true;
    
    String validationString=null;
    if(!tf.getText().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$")){
    isMail=false;
    
    }
    lb.setText(errorMessage);
        System.out.println(tf.getText().matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$"));
        return isMail;
    
}
}
