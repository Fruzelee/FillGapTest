package com.annanovas.fillgaptest;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    TextView textView1, textView2, textView3;

    String speech = "This is a string";

    String[] result;
    private ArrayList<WordObject> wordArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView1 = (TextView) findViewById(R.id.textView1);
//        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        wordArrayList = getAllWords();

//        //Converting string to char array.
//        data = value.toCharArray();
//
//        //Showing string on screen using textView.
//        textView1.setText("String = " + value);
//
//        //Showing char array on screen with Comma.
//
//        textView2.setText("Char Array = ");
//
//        for (int i = 0; i < data.length; i++) {
//
//            textView2.setText(textView2.getText() + "" + data[i] + ", ");
//        }
//
//        //Showing char array on screen without Comma.
//
//        textView3.setText("Char Array without Comma = ");
//
//        for (int i = 0; i < data.length; i++) {
//
//            textView3.setText(textView3.getText() + "" + data[i]);


//        for (int x = 0; x < result.length; x++) {
//            System.out.println(result[x]);
//
//            textView3.setText(textView3.getText() + " " + result[x]);
//
//            Log.e("Result", result[x]);
//
//            Paint paint = new Paint();
//            Rect bounds = new Rect();
//
//            int text_height = 0;
//            int text_width = 0;
//
//            paint.setTypeface(Typeface.DEFAULT);// your preference here
//            paint.setTextSize(25);// have this the same as your text size
//
//            //String text = "Some random text";
//
//            paint.getTextBounds(result[x], 0, result[x].length(), bounds);
//
//            text_height = bounds.height();
//            text_width = bounds.width();
//
//            Log.e("Index", String.valueOf(text_height));
//            Log.e("Index", String.valueOf(text_width));
//
//        }


    }

    private ArrayList<WordObject> getAllWords() {
        ArrayList<WordObject> words = new ArrayList<>();
        result = speech.split("\\s");
        for (int x = 0; x < result.length; x++) {
            System.out.println(result[x]);
            Log.e("Result", result[x]);
            Paint paint = new Paint();
            Rect bounds = new Rect();
            int word_height = 0;
            int word_width = 0;
//            paint.setTypeface(Typeface.DEFAULT);// your preference here
//            paint.setTextSize(25);// have this the same as your text size

            //String text = "Some random text";
            paint.getTextBounds(result[x], 0, result[x].length(), bounds);
            word_height = bounds.height();
            word_width = bounds.width();
            Log.e("Index", String.valueOf(word_height));
            Log.e("Index", String.valueOf(word_width));
            WordObject wordObject = new WordObject();
            wordObject.setText(result[x]);
            wordObject.setHeight(word_height);
            wordObject.setWidth(word_width);
            words.add(wordObject);
        }
        return words;
    }


//        Paint p = new Paint();
//        Rect bounds = new Rect();
//
//        for (float f = 10; f < 40; f += 1f) {
//            p.setTextSize(f);
//            p.getTextBounds(value, 0, value.length(), bounds);
//            Log.d("Test", String.format(Size %f, measureText %f, getTextBounds %d), f, p.measureText(value), bounds.width());
//        }

//
//        String Str = new String(value);
//        System.out.print("Found Index :");
//        System.out.println(Str.indexOf('_'));
//
//        Log.e("Index", String.valueOf(Str.indexOf('_')));
//
//
//        Rect bounds = new Rect();
//        Paint textPaint = textView3.getPaint();
//        textPaint.getTextBounds(value, 0, Str.indexOf('_'), bounds);
//        int height = bounds.height();
//        int firstWidth = bounds.width();
//        textPaint.getTextBounds(value, Str.indexOf('_') + 3, value.length(), bounds);
//        int secondWidth = bounds.width();
//
//
//        Log.e("String Bounds", "firstWidth " + firstWidth + " secondWidth " + secondWidth);


}



