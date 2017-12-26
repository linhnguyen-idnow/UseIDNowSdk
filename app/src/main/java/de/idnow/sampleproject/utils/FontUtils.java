package de.idnow.sampleproject.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by nfqlinhnguyen on 12/26/17.
 */

public class FontUtils {
    public static Typeface sofiaRegular;
    public static Typeface serifRegular;

    public static Typeface setSofiaRegular(Context context) {
        if (sofiaRegular == null)
            sofiaRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Sofia-Pro-Regular.OTF");

        return sofiaRegular;
    }

    public static Typeface setSerifRegular(Context context) {
        if (serifRegular == null)
            serifRegular = Typeface.createFromAsset(context.getAssets(), "fonts/AftaSerifThin-Regular.otf");

        return serifRegular;
    }
}
