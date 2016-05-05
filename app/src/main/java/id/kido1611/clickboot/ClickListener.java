package id.kido1611.clickboot;

import android.view.View;

/**
 * Created by Kido1611 on 01-May-16.
 */
public interface ClickListener {
    public void onClick(View view, int position);

    public void onLongClick(View view, int position);
}
