package in.ds.android.clickboot;

import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;

public class ClickBootWindowView extends View
{

	private Display mDisplay;
	private Context mContext;
	private Matrix mMatrix = new Matrix();
    private Paint mPaint = new Paint();
    private Resources res;
	
	public ClickBootWindowView(Context context) {
        super(context);
        init(context);
    }

    public ClickBootWindowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ClickBootWindowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
	
	private void init(Context context) {
        this.mDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        this.mContext = context;
        this.res = context.getResources();
    }
	
	protected void onDraw(Canvas canvas) {
        boolean mHasNavigationBar = false;
        int screenshotWidth;
        int screenshotHeight;
        int mShotTo;
        Bitmap screenshotBitmap = null;
        int width = getWidth();
        int height = getHeight();
        int orientation = this.mDisplay.getOrientation();
        if (orientation == 0) {
            screenshotWidth = width;
            screenshotHeight = height;
            mShotTo = (int) (((float) this.res.getDimensionPixelSize(R.dimen.abc_action_bar_content_inset_material)));
        } else {
            screenshotWidth = height;
            screenshotHeight = width;
            mShotTo = (int) (((float) this.res.getDimensionPixelSize(R.dimen.abc_action_bar_content_inset_material)));
        }
        if (mHasNavigationBar) {
			if (screenshotBitmap != null) {
                if (orientation == 3) {
                    screenshotBitmap = Bitmap.createBitmap(screenshotBitmap, 0, mShotTo, screenshotWidth, screenshotHeight);
                } else {
                    screenshotBitmap = Bitmap.createBitmap(screenshotBitmap, 0, 0, screenshotWidth, screenshotHeight);
                }
            }
        } 
        if (screenshotBitmap != null) {
            if (orientation != 0) {
                
                if (orientation == 1) {
                    this.mMatrix.reset();
                    this.mMatrix.postRotate(270.0f);
                    this.mMatrix.postTranslate(0.0f, (float) height);
                } else if (orientation == 3) {
                    this.mMatrix.reset();
                    this.mMatrix.postRotate(90.0f);
                    this.mMatrix.postTranslate((float) width, 0.0f);
                }
                canvas.drawBitmap(screenshotBitmap, this.mMatrix, this.mPaint);
            } else {
                canvas.drawBitmap(screenshotBitmap, 0.0f, 0.0f, this.mPaint);
            }
            screenshotBitmap.recycle();
        }
        
    }
}
