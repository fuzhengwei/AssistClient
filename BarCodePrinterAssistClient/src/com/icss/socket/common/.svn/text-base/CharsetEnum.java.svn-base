package com.icss.socket.common;

public enum CharsetEnum {
	
	/**
	 * GBK
	 */
	GBK("GBK",1),
	
	/**
	 * GB2312
	 */
	GB2312("GB2312",2),
	
	/**
	 * GB18030
	 */
	GB18030("GB18030",3),
	
	/**
	 * UTF-8
	 */
	UTF8("UTF-8",4),
	
	/**
	 * UTF-16
	 */
	UTF16("UTF-16",5),
	
	/**
	 * ISO-8859-1
	 */
	ISO88591("ISO-8859-1",6);
	
	// 成员变量
    private String name;
    private int index;
    
    // 构造方法
    private CharsetEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    
    // 普通方法
    public static String getName(int index) {
        for (CharsetEnum c : CharsetEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

}
