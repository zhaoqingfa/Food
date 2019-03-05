package com.zqf.food;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.ArrayMap;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.zqf.food", appContext.getPackageName());
    }

    @Test
    public void testMap() {
        HashMap<String, String> map = new LinkedHashMap();
        map.put("2", "b");
        map.put("1", "a");
        map.put("1", "b");
        map.put("3", "c");
        Log.e("================","size:" + map.size());
        for (Map.Entry entry : map.entrySet()) {
            Log.e("================","key:" + entry.getKey() + " ==>value:" + entry.getValue());

        }
    }
}
