package com.annanovas.fillgaptest;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    String speech = "Fazle Rabbi";
    String[] result;
    private LinearLayout layoutRoot, childLinearLayout;
    private int layoutWidth, layoutHeight;
    private ArrayList<WordObject> wordArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutRoot = (LinearLayout) findViewById(R.id.layout_root);
        final ViewTreeObserver observer = layoutRoot.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                wordArrayList = getAllWords();
                layoutWidth = layoutRoot.getWidth();
                layoutHeight = layoutRoot.getHeight();

                //DisplayMetrics displaymetrics = new DisplayMetrics();


                float ht_dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, layoutWidth, getResources().getDisplayMetrics());
                float wt_dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, layoutHeight, getResources().getDisplayMetrics());



                Log.d("Log", "Width: " + layoutWidth);
                Log.d("Log", "Height: " + layoutHeight);

                Log.d("Log", "Converted Width: " + ht_dp);
                Log.d("Log", "Converted Height: " + wt_dp);


                int x = 0, y = 0;
                for (int i = 0; i < wordArrayList.size(); i++) {
                    if (x == 0) {
                        childLinearLayout = new LinearLayout(MainActivity.this);
                        childLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    }

                    x = x + wordArrayList.get(i).getWidth();
                    Log.d("Log", "X: " + x);

                    if (x < ht_dp) {
                        TextView DisplayStringArray = new TextView(MainActivity.this);
                        DisplayStringArray.setTextSize(25);
                        DisplayStringArray.setGravity(Gravity.CENTER);
                        DisplayStringArray.setTextColor(getResources().getColor(R.color.colorAccent));
                        DisplayStringArray.setText(wordArrayList.get(i).getText());
                        childLinearLayout.addView(DisplayStringArray);
                        DisplayStringArray.append(" ");
                        Log.e("If is here: ", wordArrayList.get(i).getText());
                    } else {
                        layoutRoot.addView(childLinearLayout);
                        x = 0;
                        y = y + wordArrayList.get(i).getHeight();
                    }

                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layoutRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    layoutRoot.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
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
            word_width = bounds.width();
            word_height = bounds.height();


            float converted_word_width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, word_width, getResources().getDisplayMetrics());
            float converted_word_height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, word_height, getResources().getDisplayMetrics());


            Log.e("width in dp: ", String.valueOf(converted_word_width));
            Log.e("height in dp: ", String.valueOf(converted_word_height));
            WordObject wordObject = new WordObject();
            wordObject.setText(result[x]);
            wordObject.setWidth((int) converted_word_width);
            wordObject.setHeight((int) converted_word_height);

            words.add(wordObject);

        }
        return words;
    }

}