package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import java.lang.ArithmeticException
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

   private var parentview : TextView?=null ;
   private var childview : TextView?=null ;
   private var lastnumeric :Boolean = false;
    private var lastdot :Boolean = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContentView(R.layout.activity_main)

parentview = findViewById(R.id.textViewparent) ;
        childview = findViewById(R.id.textViewchild) ;






    }
fun  onDigit(view : View){
childview?.append((view as Button).text);
lastnumeric=true ;
    lastdot=false;

}


    





fun onoperator(view: View){
    childview?.text?.let {
        if (lastnumeric && !operatorchecker(it.toString())){
            childview?.append((view as Button).text);
        }
    }
lastnumeric=false;
    lastdot=false;


}
fun clear(view:View){
    childview?.text = "";
    parentview?.text= "";
}
fun decimalpoint(view:View){
    if(lastnumeric && !lastdot){
        childview?.append(".");
        lastnumeric=false;
        lastdot=true ;
    }

}
fun operatorchecker(value :String):Boolean{
    if(value.startsWith("-"))return false
    else{
        if(value.startsWith("+"))return true;
        if(value.startsWith("*"))return true;
        if(value.startsWith("/"))return true;

    }
return false;
}


fun onequal(view :View){
    if(lastnumeric){
        var inputval = childview?.text.toString();
        parentview?.text =childview?.text.toString();
       val prefix = "";
        if(inputval.startsWith("-")){
            val prefix = "-";
            inputval = inputval.substring(1);
        }
        try{
          if(inputval.contains("-")){
            val splitValue = inputval.split('-');
        var one = splitValue[0];
            var two =splitValue[1];
            if(prefix.isNotEmpty()){
                one = one+prefix ;
            }
            childview?.text = (one.toDouble() - two.toDouble()).toString();}


            if(inputval.contains("*")){
                val splitValue = inputval.split('*');
                var one = splitValue[0];
                var two =splitValue[1];
                if(prefix.isNotEmpty()){
                    one = one+prefix ;
                }
                childview?.text = (one.toDouble() * two.toDouble()).toString();}

            if(inputval.contains("/")){
                val splitValue = inputval.split('/');
                var one = splitValue[0];
                var two =splitValue[1];
                if(prefix.isNotEmpty()){
                    one = one+prefix ;
                }
                childview?.text = (one.toDouble() / two.toDouble()).toString();}


            if(inputval.contains("+")){
                val splitValue = inputval.split('+');
                var one = splitValue[0];
                var two =splitValue[1];
                if(prefix.isNotEmpty()){
                    one = one+prefix ;
                }
                childview?.text = (one.toDouble() + two.toDouble()).toString();}


        }catch (e: ArithmeticException){
            e.printStackTrace();
        }
    }
}






}