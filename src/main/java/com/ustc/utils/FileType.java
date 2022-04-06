package com.ustc.utils;

/**
 * 描述文件类型
 * @author 田宝宁
 * @date 2021/6/21
 */
public enum FileType {
    /**
     * 0文件夹, 1文件
     */
    FOLDER(0, "文件夹"),
    FILE(1, "普通文件");

    /**
     * 类型码
     */
    private Integer typeCode;
    /**
     * 类型名
     */
    private String typeName;

    FileType(Integer typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return typeName;
    }
}
