///*
// *Copyright © 2018 anji-plus
// *http://www.anji-plus.com
// *All rights reserved.
// */
//package rebue.scx.cap.commom;
//
//import java.io.Serializable;
//
//import rebue.scx.cap.util.StringUtils;
//
//
//public class ResponseModel implements Serializable {
//
//    private static final long serialVersionUID = 8445617032523881407L;
//
//    private String            repCode;
//
//    private String            repMsg;
//
//    private Object            repData;
//
//    public ResponseModel() {
//        repCode = RepCodeEnum.SUCCESS.getCode();
//    }
//
//    public ResponseModel(final RepCodeEnum repCodeEnum) {
//       setRepCodeEnum(repCodeEnum);
//    }
//
//    //成功
//    public static ResponseModel success(){
//        return ResponseModel.successMsg("成功");
//    }
//    public static ResponseModel successMsg(final String message){
//        final ResponseModel responseModel = new ResponseModel();
//        responseModel.setRepMsg(message);
//        return responseModel;
//    }
//    public static ResponseModel successData(final Object data){
//        final ResponseModel responseModel = new ResponseModel();
//        responseModel.setRepCode(RepCodeEnum.SUCCESS.getCode());
//        responseModel.setRepData(data);
//        return responseModel;
//    }
//
//    //失败
//    public static ResponseModel errorMsg(final RepCodeEnum message){
//        final ResponseModel responseModel = new ResponseModel();
//        responseModel.setRepCodeEnum(message);
//        return responseModel;
//    }
//    public static ResponseModel errorMsg(final String message){
//        final ResponseModel responseModel = new ResponseModel();
//        responseModel.setRepCode(RepCodeEnum.ERROR.getCode());
//        responseModel.setRepMsg(message);
//        return responseModel;
//    }
//    public static ResponseModel errorMsg(final RepCodeEnum repCodeEnum, final String message){
//        final ResponseModel responseModel = new ResponseModel();
//        responseModel.setRepCode(repCodeEnum.getCode());
//        responseModel.setRepMsg(message);
//        return responseModel;
//    }
//    public static ResponseModel exceptionMsg(final String message){
//        final ResponseModel responseModel = new ResponseModel();
//        responseModel.setRepCode(RepCodeEnum.EXCEPTION.getCode());
//        responseModel.setRepMsg(RepCodeEnum.EXCEPTION.getDesc() + ": " + message);
//        return responseModel;
//    }
//
//    @Override
//    public String toString() {
//        return "ResponseModel{" + "repCode='" + repCode + '\'' + ", repMsg='"
//                + repMsg + '\'' + ", repData=" + repData + '}';
//    }
//
//    public boolean isSuccess(){
//        return StringUtils.equals(repCode, RepCodeEnum.SUCCESS.getCode());
//    }
//
//    public String getRepCode() {
//        return repCode;
//    }
//
//    public void setRepCode(final String repCode) {
//        this.repCode = repCode;
//    }
//    public void setRepCodeEnum(final RepCodeEnum repCodeEnum) {
//        repCode=repCodeEnum.getCode();
//        repMsg=repCodeEnum.getDesc();
//    }
//
//    public String getRepMsg() {
//        return repMsg;
//    }
//
//    public void setRepMsg(final String repMsg) {
//        this.repMsg = repMsg;
//    }
//
//    public Object getRepData() {
//        return repData;
//    }
//
//    public void setRepData(final Object repData) {
//        this.repData = repData;
//    }
//
//
//}
