package org.example.io.channel.Enum;

/**
 * @author Xsj
 * @Descript:读取文件内容类型
 * @date 2022年06月24日 18:40
 */
public enum FileTypeEnum {

    FILE_NAME_LENGTH(1,"文件名称长度"),
    FILE_NAME(2,"文件名称"),
    FILE_CONTENT_LENGTH(3,"文件内容长度"),
    FILE_CONTENT(4,"文件内容"),

    ;

    /**
     * 类型code
     */
    private final int type;

    /**
     * 类型名称
     */
    private final String name;

    FileTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
