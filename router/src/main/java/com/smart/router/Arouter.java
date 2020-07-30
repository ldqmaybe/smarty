package com.smart.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

/**
 * @author LinDingQiang
 * @time 2020/7/29 14:58
 * @email dingqiang.l@verifone.cn
 */
public class Arouter {
    private Map<String, Class<? extends Activity>> map;
    private Context context;

    private static class SingletonHolder {
        private static final Arouter INSTANCE = new Arouter();
    }

    private Arouter() {
        map = new HashMap<>();
    }

    public static Arouter getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(Context context) {
        this.context = context;
        List<String> classNames = getClassName("com.smart.utils");
        try {
            for (String className : classNames) {
                Class<?> utilName = Class.forName(className);
                if (IRouter.class.isAssignableFrom(utilName)) {
                    IRouter iRouter = (IRouter) utilName.newInstance();
                    iRouter.putActivity();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getClassName(String packageName) {
        List<String> classList = new ArrayList<>();
        try {
            DexFile dexFile = new DexFile(context.getPackageCodePath());
            Enumeration<String> entries = dexFile.entries();
            while (entries.hasMoreElements()) {
                String className = entries.nextElement();
                if (className.contains(packageName)) {
                    classList.add(className);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }

    public void addActivity(String key, Class<? extends Activity> clazz) {
        if (null != key && null != clazz && !map.containsKey(key)) {
            map.put(key, clazz);
        }
    }

    public void jumpActivity(String key, Bundle bundle) {
        Class<? extends Activity> activityClazz = map.get(key);
        if (null != activityClazz) {
            Intent intent = new Intent(context, activityClazz);
            if (null != bundle) {
                intent.putExtras(bundle);
            }
            context.startActivity(intent);
        }
    }
}
