package akanshaapp.com.geu.akanshaapp.akanshaapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by GB on 11/13/2015.
 */
public class PrefClass {

    // app_pref - manual info

    public static void setValue(Context context,String prefname,String key,String value)
    {
        SharedPreferences sp=context.getSharedPreferences(prefname,Context.MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        ed.putString(key,value);
        ed.commit();
    }

    public static String getValue(Context context,String prefname,String key)
    {
        SharedPreferences sp=context.getSharedPreferences(prefname,Context.MODE_PRIVATE);

      return  sp.getString(key,null);

    }
}
