package rebue.scx.cap.commom;

public enum CaptchaTypeEnum {
    /**
     * 滑块拼图.
     */
    BLOCKPUZZLE("blockPuzzle","滑块拼图"),
    /**
     * 文字点选.
     */
    CLICKWORD("clickWord","文字点选"),
    /**
     * 默认.
     */
    DEFAULT("default","默认");

    private String codeValue;
    private String codeDesc;

    CaptchaTypeEnum(final String  codeValue, final String codeDesc) {
        this.codeValue = codeValue;
        this.codeDesc = codeDesc;
    }

    public String   getCodeValue(){ return codeValue;}

    public String getCodeDesc(){ return codeDesc;}

    //根据codeValue获取枚举
    public static CaptchaTypeEnum parseFromCodeValue(final String codeValue){
        for (final CaptchaTypeEnum e : CaptchaTypeEnum.values()){
            if(e.codeValue.equals(codeValue)){ return e;}
        }
        return null;
    }

    //根据codeValue获取描述
    public static String getCodeDescByCodeBalue(final String codeValue){
        final CaptchaTypeEnum enumItem = parseFromCodeValue(codeValue);
        return enumItem == null ? "" : enumItem.getCodeDesc();
    }

    //验证codeValue是否有效
    public static boolean validateCodeValue(final String codeValue){ return parseFromCodeValue(codeValue)!=null;}

    //列出所有值字符串
    public static String getString(){
        final StringBuffer buffer = new StringBuffer();
        for (final CaptchaTypeEnum e : CaptchaTypeEnum.values()){
            buffer.append(e.codeValue).append("--").append(e.getCodeDesc()).append(", ");
        }
        buffer.deleteCharAt(buffer.lastIndexOf(","));
        return buffer.toString().trim();
    }

}
