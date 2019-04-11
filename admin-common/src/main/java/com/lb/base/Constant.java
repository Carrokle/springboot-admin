package com.lb.base;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liub
 * @since 2018-05-03
 */
public class Constant {

    public static final int BYTE_BUFFER = 1024;

    public static Set<String>  METHOD_URL_SET = new HashSet<>();

    /**
     * 用户注册默认角色
     */
    // public static final int DEFAULT_REGISTER_ROLE = 5;

    public static final int BUFFER_MULTIPLE = 10;

    //验证码过期时间
    public static final Long PASS_TIME =  30 * 60 *1000L;

    //根菜单节点
    public static final Long ROOT_MENU = 0L;

    //菜单类型，1：菜单  2：按钮操作
    public static final int TYPE_MENU = 1;

    //菜单类型，1：菜单  2：按钮操作
    public static final int TYPE_BUTTON = 2;

    public static ThreadLocal<Boolean> isPass = new ThreadLocal<>();

    /**当前用户*/

    public static final String CURRENT_USER = "currentUser";

    /**登录名称*/
    public static final String LOGIN_NAME = "mobile";

    public static final String PASSWORD = "password";
    //启用
    public static final int ENABLE = 1;
    //禁用
    public static final int DISABLE = 0;

    /**
     * 在字典表中注册用户时的角色的类型
     */
    public static final String DICT_DEFAULT_REGISTER_ROLE = "default_register_role";
    /**
     * 在字典表中存储用户默认密码的类型
     */
    public static final String DICT_DEFAULT_PASSWORD = "default_password";

    /**
     * 超级管理员ID
     */
    public static final Long SUPER_ADMIN_ID = 1L;

    public static class FilePostFix{
        public static final String ZIP_FILE =".zip";

        public static final String [] IMAGES ={"jpg", "jpeg", "JPG", "JPEG", "gif", "GIF", "bmp", "BMP", "png"};
        public static final String [] ZIP ={"ZIP","zip","rar","RAR"};
        public static final String [] VIDEO ={"mp4","MP4","mpg","mpe","mpa","m15","m1v", "mp2","rmvb"};
        public static final String [] APK ={"apk","exe"};
        public static final String [] OFFICE ={"xls","xlsx","docx","doc","ppt","pptx"};

    }
    public class FileType{
        public static final int FILE_IMG = 1;
        public static final int FILE_ZIP = 2;
        public static final int FILE_VEDIO= 3;
        public static final int FILE_APK = 4;
        public static final int FIVE_OFFICE = 5;
        public static final String FILE_IMG_DIR= "/img/";
        public static final String FILE_ZIP_DIR= "/zip/";
        public static final String FILE_VEDIO_DIR= "/video/";
        public static final String FILE_APK_DIR= "/apk/";
        public static final String FIVE_OFFICE_DIR= "/office/";
    }

    public class RoleType{
        //超级管理员
        public static final String SYS_ASMIN_ROLE= "sysadmin";
        //管理员
        public static final String ADMIN= "admin";
        //普通用户
        public static final String USER= "user";
    }


}
