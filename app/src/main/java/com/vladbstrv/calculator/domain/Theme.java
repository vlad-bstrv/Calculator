package com.vladbstrv.calculator.domain;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.vladbstrv.calculator.R;

public enum Theme {

    THEME_ONE(R.string.theme_one, R.drawable.ic_5g, R.style.Theme_Calculator, "one"),
    THEME_TWO(R.string.theme_two, R.drawable.ic_alarm, R.style.Theme_Calculator_Version2, "two"),
    THEME_THREE(R.string.theme_three, R.drawable.ic_air, R.style.Theme_Calculator_Version3, "three"),
    THEME_FOUR(R.string.theme_four, R.drawable.ic_add, R.style.Theme_Calculator_Version4, "four");


    @StringRes
    private final int title;
    @DrawableRes
    private final int img;
    @StyleRes
    private final int theme;

    private final String key;

    private Theme(int title, int img, int theme, String key) {
        this.title = title;
        this.img = img;
        this.theme = theme;
        this.key = key;
    }


    public int getTitle() {
        return title;
    }

    public int getImg() {
        return img;
    }

    public int getTheme() {
        return theme;
    }

    public String getKey() {
        return key;
    }
}
