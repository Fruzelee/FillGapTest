package com.annanovas.fillgaptest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    String speech = "Bangladesh ### our motherland";

    String[] result;
    EditText ed;
    List<EditText> allEds = new ArrayList<EditText>();
    private TextView tvWord;
    private LinearLayout layoutRoot;
    private RelativeLayout childRelativeLayout;
    private Button buttonFillGap;

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
                Log.e("Log", "Width: " + layoutWidth);
                Log.e("Log", "Height: " + layoutHeight);


                childRelativeLayout = new RelativeLayout(MainActivity.this);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                //childRelativeLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                childRelativeLayout.setBackgroundColor(getRandomColor());
                int x = 0, y = 0;
                for (int i = 0; i < wordArrayList.size(); i++) {
                    if (!wordArrayList.get(i).getText().equals("###")) {
                        tvWord = new TextView(MainActivity.this);
                        tvWord.setTextSize(25);
                        //tvWord.setTextColor(getResources().getColor(R.color.colorAccent));
                        tvWord.setTextColor(getRandomColor());
                        tvWord.setText(wordArrayList.get(i).getText());
                        tvWord.append(" ");
                        tvWord.measure(0, 0);
                        tvWord.getMeasuredWidth();
                        tvWord.getMeasuredHeight();
                        Log.e("Text View width: ", String.valueOf(tvWord.getMeasuredWidth()));
                        Log.e("Text View height: ", String.valueOf(tvWord.getMeasuredHeight()));
                        Log.e("Value of X", "X " + x);
                        Log.e("Value of Y", "Y " + y);
                        if ((x + tvWord.getMeasuredWidth()) > layoutWidth - 2 * getResources().getDimension(R.dimen.activity_horizontal_margin)) {
                            y = y + tvWord.getMeasuredHeight();
                            x = 0;
                        }
                        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);//(RelativeLayout.LayoutParams) tvWord.getLayoutParams();
                        lp.setMargins(x, y, 0, 0);

                        tvWord.setLayoutParams(lp);
                        childRelativeLayout.addView(tvWord);

                        x = x + tvWord.getMeasuredWidth();
                    } else {
                        buttonFillGap = new Button(MainActivity.this);
                        ed = new EditText(MainActivity.this);
                        ed.setVisibility(View.GONE);
//                        ed.post(new Runnable() {
//                            public void run() {
//                                ed.requestFocusFromTouch();
//                                InputMethodManager lManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                                lManager.showSoftInput(ed, 0);
//                            }
//                        });
                        buttonFillGap.setText("Enter");
                        buttonFillGap.measure(0, 0);
                        buttonFillGap.getMeasuredWidth();
                        buttonFillGap.getMeasuredHeight();
                        Log.e("Button width: ", String.valueOf(buttonFillGap.getMeasuredWidth()));
                        Log.e("Button height: ", String.valueOf(buttonFillGap.getMeasuredHeight()));
                        Log.e("Value of X", "X " + x);
                        Log.e("Value of Y", "Y " + y);
                        if ((x + buttonFillGap.getMeasuredWidth()) > layoutWidth - 2 * getResources().getDimension(R.dimen.activity_horizontal_margin)) {
                            y = y + buttonFillGap.getMeasuredHeight();
                            x = 0;
                        }
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);//(RelativeLayout.LayoutParams) tvWord.getLayoutParams();
                        RelativeLayout.LayoutParams layoutParamsForEditText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);//(RelativeLayout.LayoutParams) tvWord.getLayoutParams();
                        layoutParams.setMargins(x, y, 0, 0);
                        layoutParamsForEditText.setMargins(x, y, 0, 0);
                        buttonFillGap.setLayoutParams(layoutParams);
                        ed.setLayoutParams(layoutParams);
                        ed.setWidth(350);
                        ed.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        ed.setSingleLine(true);
                        /*
                              This will provide Scroll option for EditText,
                              but it will not show the scroll bar on EditText
                        */
                        ed.setScroller(new Scroller(getApplicationContext()));
                        ed.setVerticalScrollBarEnabled(false);

                        childRelativeLayout.addView(buttonFillGap);
                        childRelativeLayout.addView(ed);

                        x = x + buttonFillGap.getMeasuredWidth();

                        buttonFillGap.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("Hi, i'm a toast.");
                                ed.setVisibility(View.VISIBLE);
                                ed.setEnabled(true);
                                buttonFillGap.setVisibility(View.GONE);
                            }
                        });
                    }
                }


                layoutRoot.addView(childRelativeLayout, params);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layoutRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    layoutRoot.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }

            private Context getActivity() {
                return null;
            }
        });

    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
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
            paint.getTextBounds(result[x], 0, result[x].length(), bounds);
            word_width = dpToPx(bounds.width());
            word_height = dpToPx(bounds.height());
            Log.d("String width in dp: ", String.valueOf(word_width));
            Log.d("String height in dp: ", String.valueOf(word_height));
            WordObject wordObject = new WordObject();
            wordObject.setText(result[x]);
            wordObject.setWidth(word_width);
            wordObject.setHeight(word_height);
            words.add(wordObject);
        }
        return words;
    }

}