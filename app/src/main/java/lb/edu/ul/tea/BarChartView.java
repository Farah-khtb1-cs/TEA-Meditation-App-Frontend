package lb.edu.ul.tea;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class BarChartView extends View {

    private float[] dataPoints = new float[]{};
    private Paint barPaint, axisPaint, textPaint;

    // Bar and spacing dimensions (Fixed Size)
    private final int BAR_WIDTH_DP = 15;
    private final int BAR_SPACING_DP = 8;

    // Converted pixel values
    private float barWidthPx;
    private float barSpacingPx;

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        // Convert dp to pixels
        barWidthPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, BAR_WIDTH_DP, context.getResources().getDisplayMetrics());
        barSpacingPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, BAR_SPACING_DP, context.getResources().getDisplayMetrics());

        barPaint = new Paint();
        barPaint.setColor(Color.parseColor("#FFB74D"));  // Bar color
        barPaint.setStyle(Paint.Style.FILL);

        axisPaint = new Paint();
        axisPaint.setColor(Color.BLACK);  // Axis color
        axisPaint.setStrokeWidth(4);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);  // Text color
        textPaint.setTextSize(40);
        textPaint.setAntiAlias(true);
    }

    public void setDataPoints(float[] dataPoints) {
        this.dataPoints = dataPoints;
        requestLayout();  // Recalculate size
        invalidate();  // Refresh the view
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = (int) ((barWidthPx + barSpacingPx) * dataPoints.length) + 200; // Ensure extra space
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int finalWidth = (widthMode == MeasureSpec.EXACTLY) ? widthSize : Math.max(desiredWidth, widthSize);
        int finalHeight = MeasureSpec.getSize(heightMeasureSpec);  // Keep height fixed

        setMeasuredDimension(finalWidth, finalHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int padding = 100;

        // Draw Y-axis and X-axis
        canvas.drawLine(padding, padding, padding, height - padding, axisPaint); // Y-axis
        canvas.drawLine(padding, height - padding, width - padding, height - padding, axisPaint); // X-axis

        // Y-Axis Label (Rotated Text)
        canvas.save();
        canvas.rotate(-90, padding / 4f, height / 2f);
        canvas.drawText("Mo3adal Nafseyte", -height / 2f, padding / 2f, textPaint);
        canvas.restore();

        // Draw bars
        if (dataPoints.length > 0) {
            float maxValue = getMaxValue();
            float currentX = padding + barSpacingPx; // Starting X position

            for (float dataPoint : dataPoints) {
                float barHeight = (dataPoint / maxValue) * (height - 2 * padding);
                float top = (height - padding) - barHeight;
                float bottom = height - padding;

                // Draw bar
                canvas.drawRect(currentX, top, currentX + barWidthPx, bottom, barPaint);

                // Adjust text size relative to bar width
                textPaint.setTextSize(barWidthPx * 0.6f);

                // Center text on the bar
                String text = String.valueOf(dataPoint);
                float textWidth = textPaint.measureText(text);
                canvas.drawText(text, currentX + (barWidthPx - textWidth) / 2, top - 10, textPaint);

                // Move to the next bar position
                currentX += barWidthPx + barSpacingPx;
            }
        }
    }

    private float getMaxValue() {
        float max = 0;
        for (float value : dataPoints) {
            if (value > max) {
                max = value;
            }
        }
        return max > 0 ? max : 1;  // Avoid division by zero
    }
}

