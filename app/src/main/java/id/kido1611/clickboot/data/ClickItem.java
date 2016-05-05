package id.kido1611.clickboot.data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;

/**
 * Created by Kido1611 on 05-May-16.
 */
public class ClickItem {

    int titleRes;
    Drawable icon;
    String command;
    Effectstype effect;

    public Effectstype getEffect() {
        return effect;
    }

    public void setEffect(Effectstype effect) {
        this.effect = effect;
    }

    public ClickItem(int titleRes, Drawable icon, String command, Effectstype efek){
        this.titleRes = titleRes;
        this.icon = icon;
        this.command = command;
        this.effect = efek;
    }

    public int getTitleRes() {
        return titleRes;
    }

    public void setTitleRes(int titleRes) {
        this.titleRes = titleRes;
    }

    public Drawable getIcon(Context ctx) {
        if(icon==null) return TextDrawable.builder().buildRound(String.valueOf(getFirstChar(ctx)), ColorGenerator.MATERIAL.getRandomColor());
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    private char getFirstChar(Context ctx){
        String text = ctx.getString(titleRes);

        return text.charAt(0);
    }

    public String getTitle(Context ctx){
        return ctx.getString(titleRes);
    }
}
