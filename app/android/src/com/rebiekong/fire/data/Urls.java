package com.rebiekong.fire.data;

/**
 * Created by 18029 on 2015/12/26.
 */
public class Urls {
    private static String PROTOCOL = "http://";
    private static String NOTEPAD = PROTOCOL + "notepad.rebiekong.com/";
    private static String IDEA_SINK = NOTEPAD + "idea/";
    private static String GET_IDEA = "get_idea";
    private static String SET_GP = "set_gp";
    private static String ADD_IDEA = "add_idea";
    private static String CHECK_ACCESS = "check_access";
    private static String ADD_COMMENT = "add_comment";

    public static void setPROTOCOL(String newProtocol) {
        PROTOCOL = newProtocol;
    }

    public static void setNOTEPAD(String newNotepad) {
        NOTEPAD = newNotepad;
    }

    public static String getGET_IDEA() {
        return IDEA_SINK + GET_IDEA;
    }

    public static String getSET_GP() {
        return IDEA_SINK + SET_GP;
    }

    public static String getCHECK_ACCESS() {
        return IDEA_SINK + CHECK_ACCESS;
    }

    public static String getADD_IDEA() {
        return IDEA_SINK + ADD_IDEA;
    }

    public static String getADD_COMMENT() {
        return IDEA_SINK + ADD_COMMENT;
    }
}
