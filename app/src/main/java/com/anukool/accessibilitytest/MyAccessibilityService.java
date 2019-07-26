package com.anukool.accessibilitytest;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class MyAccessibilityService extends AccessibilityService {
    public static String currentPackageName = "com.anukool.accessibilitytest";

    @Override
    protected void onServiceConnected() {
        try {
            AccessibilityServiceInfo info = new AccessibilityServiceInfo();

            info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
            info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
            info.notificationTimeout = 0;
            /* Package that you want Events */
            info.packageNames = new String[]{"", ""};
            info.flags |= AccessibilityServiceInfo.DEFAULT;
            info.flags |= AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS;
            info.flags |= AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS;

            setServiceInfo(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if(event != null){
            Utils.printLog(event);

            // Ignore notification events
            if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED
                    || event.getEventType() == AccessibilityEvent.TYPE_ANNOUNCEMENT) return;
            String packageName = (String) event.getPackageName();

            if (packageName != null && !packageName.equals(currentPackageName)) {
                currentPackageName = packageName;
                Log.e("currentPackageName :", currentPackageName);
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}
