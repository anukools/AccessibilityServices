package com.anukool.accessibilitytest;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class Utils {


    public static boolean isAccessibilityEnabled(Context context, String id) {
        try {
            AccessibilityManager am = (AccessibilityManager) context
                    .getSystemService(Context.ACCESSIBILITY_SERVICE);
            if (am != null) {
                List<AccessibilityServiceInfo> runningServices = am.getEnabledAccessibilityServiceList(AccessibilityEvent.TYPES_ALL_MASK);
                if (runningServices != null) {
                    for (AccessibilityServiceInfo service : runningServices) {
                        if (id.equals(service.getId())) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void printLog(AccessibilityEvent event) {
        if (event == null) return;

        try {

            String className = "NA";
            AccessibilityNodeInfo nodeInfo;
            String nodeClass = "NA";
            String classContent = "NA";
            String classText = "NA";
            String nodeContent = "NA";
            String nodeId = "NA";
            String nodeText = "NA";
            String nodeFocus = "NA";

            if (event.getClassName() != null) className = event.getClassName().toString();

            if (event.getContentDescription() != null)
                classContent = event.getContentDescription().toString();

            if (event.getText() != null) classText = event.getText().toString();

            if (event.getSource() != null) {
                nodeInfo = event.getSource();

                if (nodeInfo.getClassName() != null) nodeClass = nodeInfo.getClassName().toString();

                if (nodeInfo.getContentDescription() != null)
                    nodeContent = nodeInfo.getContentDescription().toString();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    if (nodeInfo.getViewIdResourceName() != null)
                        nodeId = nodeInfo.getViewIdResourceName();
                }

                if (nodeInfo.getText() != null) nodeText = nodeInfo.getText().toString();

                nodeFocus = String.valueOf(nodeInfo.isFocused());
            }
            System.out.println("event_info: " + event.getPackageName() + className + "  " + classContent + "  " + classText + "  " +
                    nodeClass + "  " + nodeId + "  " + nodeContent + "  " + nodeText + " "
                    + getEventType(event) + " nodeFocus: " + nodeFocus);

        } catch (Exception e) {
        }
    }

    public static String getEventType(AccessibilityEvent event) {
        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                return "TYPE_NOTIFICATION_STATE_CHANGED";
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                return "TYPE_VIEW_CLICKED";
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                return "TYPE_VIEW_FOCUSED";
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                return "TYPE_VIEW_LONG_CLICKED";
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                return "TYPE_VIEW_SELECTED";
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                return "TYPE_WINDOW_STATE_CHANGED";
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                return "TYPE_VIEW_TEXT_CHANGED";
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                return "TYPE_WINDOW_CONTENT_CHANGED";
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                return "TYPE_VIEW_SCROLLED";
            case AccessibilityEvent.CONTENT_CHANGE_TYPE_UNDEFINED:
                return "CONTENT_CHANGE_TYPE_UNDEFINED";
            case AccessibilityEvent.TYPE_WINDOWS_CHANGED:
                return "TYPE_WINDOWS_CHANGED";
            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_END:
                return "TYPE_TOUCH_INTERACTION_END";
            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_START:
                return "TYPE_TOUCH_INTERACTION_START";
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
                return "TYPE_GESTURE_DETECTION_END";
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
                return "TYPE_GESTURE_DETECTION_START";
            case AccessibilityEvent.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY:
                return "TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY";
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED:
                return "TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED";
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED:
                return "TYPE_VIEW_ACCESSIBILITY_FOCUSED";
            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
                return "TYPE_ANNOUNCEMENT";
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                return "TYPE_VIEW_TEXT_SELECTION_CHANGED";
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
                return "TYPE_TOUCH_EXPLORATION_GESTURE_END";
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
                return "TYPE_TOUCH_EXPLORATION_GESTURE_START";
            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
                return "TYPE_VIEW_HOVER_EXIT";
            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                return "TYPE_VIEW_HOVER_ENTER";
        }
        return "default";
    }

}
