package com.home.community.enums;

public enum NotificationTypeEnum {
    REPLY_QUESTION(1, "回复了问题"),
    REPLY_COMMENT(2, "回复了评论");

    private Integer type;
    private String name;

    NotificationTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }


    public static String nameOfType(Integer type){
        for (NotificationTypeEnum notificationTypeEnumEnum:NotificationTypeEnum.values()) {
            if (notificationTypeEnumEnum.getType() == type){
                return notificationTypeEnumEnum.getName();
            }
        }
        return "";
    }
}
