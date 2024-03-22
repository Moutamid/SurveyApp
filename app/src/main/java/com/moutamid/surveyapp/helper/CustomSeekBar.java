package com.moutamid.surveyapp.helper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatSeekBar;

public class CustomSeekBar extends AppCompatSeekBar {

    private Paint textPaint;
    private Rect textBounds = new Rect();
    private String text = "";

    public CustomSeekBar(Context context) {
        super(context);
        init();
    }

    public CustomSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setTypeface(Typeface.SANS_SERIF);
        textPaint.setColor(Color.BLACK);
        setMax(100);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int progress = getProgress();
        text = String.valueOf(progress);

        float width = getWidth();
        float height = getHeight();

        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        textPaint.setTextSize(45);
        textPaint.getTextBounds(text, 0, text.length(), textBounds);

        float position = (width / getMax()) * getProgress();

        float textXStart = position - textBounds.centerX();
        float textXEnd = position + textBounds.centerX();

        if (textXStart <= 1) textXStart = 1;

        if (textXEnd > width) {
            textXStart -= (textXEnd - width + 30);
        }

        float yPosition = height;

        canvas.drawText(text, textXStart, yPosition, textPaint);
    }

    public synchronized void setTextColor(int color) {
        textPaint.setColor(color);
        invalidate();
    }
}
