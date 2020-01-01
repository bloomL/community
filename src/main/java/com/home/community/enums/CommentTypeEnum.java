package com.home.community.enums;

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static Boolean isExist(Integer type){
        for ( CommentTypeEnum commentTypeEnum:CommentTypeEnum.values()) {
            if (commentTypeEnum.type == type){
                return true;
            }
        }
        return false;
    }
}
