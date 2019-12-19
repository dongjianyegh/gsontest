package com.example.gsontest.pingback;

import com.example.eventtrack.EventDefine;
import com.example.eventtrack.InstantGetter;
import com.example.eventtrack.internal.record.CreatorByOperation;
import com.example.eventtrack.internal.record.GsonRecord;
import com.example.gsontest.pingback.bean.Ec36Operation;
import com.example.gsontest.pingback.bean.LanguageLayoutOperation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// TODO 做成注解
public class MainDefine implements EventDefine {

    public static final String STR_CLICK_SHIFT_FROM_FIRST_TO_SEC = "ec1";
    public static final String STR_CLICK_SHIFT_FROM_SEC_TO_FIRST = "ec2";
    public static final String STR_CLICK_ANYKEY_TIME = "ec3";
    public static final String STR_SHOW_SYMBO_KEYBOARD_TIME = "ec4";
    public static final String STR_CLICK_NUMBER_IN_SYMBO_KEYBOARD = "ec5";
    public static final String STR_CLICK_SYMBO_IN_SYMBO_KEYBOARD = "ec6";
    public static final String STR_CLICK_BACK_IN_SYMBO_KEYBOARD = "ec7";
    public static final String STR_LONGPRESS_COMMIT_IN_SYMBO_KEYBOARD = "ec8";
    public static final String STR_SLIDE_UP_NUM_IN_ALPHA_KEYBOARD = "ec9";
    public static final String STR_SLIDE_UP_SYMBO_IN_ALPHA_KEYBOARD = "ec10";
    public static final String STR_SLIDE_UP_ALPHA_IN_ALPHA_KEYBOARD = "ec11";
    public static final String STR_LONG_PRESS_NUM_IN_ALPHA_KEYBOARD = "ec12";
    public static final String STR_LONG_PRESS_SYMBO_IN_ALPHA_KEYBOARD = "ec13";
    public static final String STR_LONG_PRESS_ALPHA_IN_ALPHA_KEYBOARD = "ec14";

    /**EC15 上屏总字数 **/
    public static final String STR_EC_COMMIT_CHARQUENCE_LENGTH = "ec15";
    /**EC16 上屏总次数 **/
    public static final String STR_EC_COMMIT_TIMES = "ec16";
    /**EC17 候选首选上屏总次数 **/
    public static final String STR_EC_COMMIT_FIRST_CANDIDATE_TIMES = "ec17";
    /**EC18 候选二选上屏总次数 **/
    public static final String STR_EC_COMMIT_SECOND_CANDIDATE_TIMES = "ec18";
    /**EC19 候选三选上屏总次数 **/
    public static final String STR_EC_COMMIT_THIRD_CANDIDATE_TIMES = "ec19";
    /**EC20 空格上屏候选总次数 **/
    public static final String STR_EC_COMMIT_FROM_SPACE_TIMES = "ec20";
    /**EC21 inline状态时点击退格次数 **/
    public static final String STR_EC_PRESS_BACK_TIMES_INLINE = "ec21";
    /**EC22 非inline状态时点击退格次数 **/
    public static final String STR_EC_PRESS_BACK_TIMES_UNINLINE = "ec22";
    /**EC23 按键导致退inline上屏的次数 （对应候选上屏）**/
    public static final String STR_EC_COMMIT_FROM_OTHER_KEY = "ec23";
    /**EC24 多候选时点击更多按钮次数**/
    public static final String STR_EC_MORE_CANDIDATE_MORE_BUTTON_CLICK_TIMES = "ec24";
    /**EC25 更多候选界面点击候选上屏的次数**/
    public static final String STR_EC_MORE_CANDIDATE_WORDS_COMMIT_TIMES = "ec25";
    /**EC26 更多候选界面点击[返回]按钮的次数**/
    public static final String STR_EC_MORE_CANDIDATE_BACK_BUTTON_CLICK_TIMES = "ec26";
    /**EC27 更多候选界面点击[删除]按钮的次数**/
    public static final String STR_EC_MORE_CANDIDATE_BACKSPACE_BUTTON_CLICK_TIMES = "ec27";
    /**EC28 更多候选界面点击[↑]按钮的次数**/
    public static final String STR_EC_MORE_CANDIDATE_UP_BUTTON_CLICK_TIMES = "ec28";
    /**EC29 更多候选界面点击[↓]按钮的次数**/
    public static final String STR_EC_MORE_CANDIDATE_DOWN_BUTTON_CLICK_TIMES = "ec29";
    /**EC30 展示联想 **/
    public static final String STR_EC_SHOW_ASSOCIATION = "ec30";
    /**EC31 联想上屏次数 **/
    public static final String STR_EC_COMMIT_FROM_ASSOCIATION = "ec31";
    /**EC32 点击关闭按钮关闭联想次数（不区分三候选或多候选）**/
    public static final String STR_EC_CLOSE_ASSOCIATION_BUTTON_CLICK_TIMES = "ec32";
    /**EC33 点击按键关闭联想次数 **/
    public static final String STR_EC_CLOSE_ASSOCIATION_FROM_OTHERS = "ec33";
    /**EC34 lock the shift*/
    public static final String STR_EC_LOCK_SHIFT_TIMES = "ec34";
    /**EC35 slip clear candidate*/
    public static final String STR_EC_LEFT_SLIP_CLEAR_CANDIDATES_TIMES = "ec35";

    /*Ec36 比较特殊，特殊处理,从不同入口进入语言管理页面后，完成多语言启用的漏斗情况*/
    public static final String STR_EC_MULTI_LANGUAGE_SETTING_DLD_AND_SELECT = "ec36";

    // 联想调用次数
    public static final String STR_EC_ASSOC_CALLED_TIMES = "ec37";
    /*Ec38 比较特殊，特殊处理,从不同入口进入语言管理页面后，完成多语言启用的漏斗情况*/
    public static final String STR_EC_LANGUAGE_LAYOUT_SET_LOG = "ec38";
    public static final String STR_EC_CLICK_CHARACTER_NUM_SYMBOL_TIMES = "ec39";
    public static final String STR_EC_MORE_SYMBOL_CLICK_SYMBOL_COUNT = "ec40";

    @Override
    public Map<String, GsonRecord> getJsonDefines() {
        final Map<String, GsonRecord> result = new HashMap<>();
        final GsonRecord commonGsonRecord = new CreatorByOperation<LanguageLayoutOperation>(){}.createGsonRecord();

        result.put(STR_CLICK_SHIFT_FROM_FIRST_TO_SEC, commonGsonRecord);
        result.put(STR_CLICK_SHIFT_FROM_SEC_TO_FIRST, commonGsonRecord);
        result.put(STR_CLICK_ANYKEY_TIME, commonGsonRecord);
        
        result.put(STR_SHOW_SYMBO_KEYBOARD_TIME, commonGsonRecord);
        result.put(STR_CLICK_NUMBER_IN_SYMBO_KEYBOARD, commonGsonRecord);
        result.put(STR_CLICK_SYMBO_IN_SYMBO_KEYBOARD, commonGsonRecord);
        result.put(STR_CLICK_BACK_IN_SYMBO_KEYBOARD, commonGsonRecord);
        result.put(STR_LONGPRESS_COMMIT_IN_SYMBO_KEYBOARD, commonGsonRecord);
        result.put(STR_SLIDE_UP_NUM_IN_ALPHA_KEYBOARD, commonGsonRecord);
        result.put(STR_SLIDE_UP_SYMBO_IN_ALPHA_KEYBOARD, commonGsonRecord);
        result.put(STR_SLIDE_UP_ALPHA_IN_ALPHA_KEYBOARD, commonGsonRecord);
        result.put(STR_LONG_PRESS_NUM_IN_ALPHA_KEYBOARD, commonGsonRecord);
        result.put(STR_LONG_PRESS_SYMBO_IN_ALPHA_KEYBOARD, commonGsonRecord);
        result.put(STR_LONG_PRESS_ALPHA_IN_ALPHA_KEYBOARD, commonGsonRecord);

        result.put(STR_EC_COMMIT_CHARQUENCE_LENGTH, commonGsonRecord);
        result.put(STR_EC_COMMIT_TIMES, commonGsonRecord);
        result.put(STR_EC_COMMIT_FIRST_CANDIDATE_TIMES, commonGsonRecord);
        result.put(STR_EC_COMMIT_SECOND_CANDIDATE_TIMES, commonGsonRecord);
        result.put(STR_EC_COMMIT_THIRD_CANDIDATE_TIMES, commonGsonRecord);
        result.put(STR_EC_COMMIT_FROM_SPACE_TIMES, commonGsonRecord);

        result.put(STR_EC_PRESS_BACK_TIMES_INLINE, commonGsonRecord);
        result.put(STR_EC_PRESS_BACK_TIMES_UNINLINE, commonGsonRecord);
        result.put(STR_EC_COMMIT_FROM_OTHER_KEY, commonGsonRecord);
        result.put(STR_EC_MORE_CANDIDATE_MORE_BUTTON_CLICK_TIMES, commonGsonRecord);
        result.put(STR_EC_MORE_CANDIDATE_WORDS_COMMIT_TIMES, commonGsonRecord);
        result.put(STR_EC_MORE_CANDIDATE_BACK_BUTTON_CLICK_TIMES, commonGsonRecord);
        result.put(STR_EC_MORE_CANDIDATE_BACKSPACE_BUTTON_CLICK_TIMES, commonGsonRecord);
        result.put(STR_EC_MORE_CANDIDATE_UP_BUTTON_CLICK_TIMES, commonGsonRecord);
        result.put(STR_EC_MORE_CANDIDATE_DOWN_BUTTON_CLICK_TIMES, commonGsonRecord);
        result.put(STR_EC_SHOW_ASSOCIATION, commonGsonRecord);

        result.put(STR_EC_COMMIT_FROM_ASSOCIATION, commonGsonRecord);
        result.put(STR_EC_CLOSE_ASSOCIATION_BUTTON_CLICK_TIMES, commonGsonRecord);
        result.put(STR_EC_CLOSE_ASSOCIATION_FROM_OTHERS, commonGsonRecord);
        result.put(STR_EC_LOCK_SHIFT_TIMES, commonGsonRecord);
        result.put(STR_EC_LEFT_SLIP_CLEAR_CANDIDATES_TIMES, commonGsonRecord);

        result.put(STR_EC_ASSOC_CALLED_TIMES, commonGsonRecord);
        result.put(STR_EC_CLICK_CHARACTER_NUM_SYMBOL_TIMES, commonGsonRecord);
        result.put(STR_EC_MORE_SYMBOL_CLICK_SYMBOL_COUNT, commonGsonRecord);

        result.put(STR_EC_MULTI_LANGUAGE_SETTING_DLD_AND_SELECT, new CreatorByOperation<Ec36Operation>(){}.createGsonRecord());

        return result;

    }

    @Override
    public Set<String> getNumberDefines() {
        return null;
    }

    @Override
    public InstantGetter getInstanceGetter() {
        return new ATestGetter();
    }
}
