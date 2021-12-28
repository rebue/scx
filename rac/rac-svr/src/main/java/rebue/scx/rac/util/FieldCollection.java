package rebue.scx.rac.util;

/**
 * 信息导入字段
 * 
 * @author yuanman
 *
 */
public class FieldCollection {

    /**
     * 账户信息字段
     */
    public static String[] getAccountInformationCol() {
        String[] col = new String[10];
        col[0] = "index";
        col[1] = "signInName";
        col[2] = "signInNickname";
        col[3] = "idCard";
        col[4] = "signInMobile";
        col[5] = "signInEmail";
        col[6] = "orgId";
        col[7] = "orgName";
        col[8] = "roleId";
        col[9] = "roleName";
        return col;
    }

    /**
     * 账户信息字段名
     */
    public static String[] getAccountNameInformationCol() {
        String[] col = new String[10];
        col[0] = "序号";
        col[1] = "登录帐号(必填)";
        col[2] = "姓名(必填)";
        col[3] = "身份证号";
        col[4] = "手机号码";
        col[5] = "电子邮箱";
        col[6] = "组织ID/部门代码";
        col[7] = "组织名称/部门名称";
        col[8] = "角色ID/岗位代码";
        col[9] = "角色名称/岗位名称";
        return col;
    }

    /**
     * 组织信息字段
     */
    public static String[] getOrgInformationCol() {
        String[] col = new String[6];
        col[0] = "index";
        col[1] = "orgId";
        col[2] = "orgName";
        col[3] = "parentId";
        col[4] = "parentName";
        col[5] = "orgType";
        return col;
    }

    /**
     * 组织信息字段名
     */
    public static String[] getOrgNameInformationCol() {
        String[] col = new String[6];
        col[0] = "序号";
        col[1] = "组织ID/部门代码";
        col[2] = "组织名称/部门名称";
        col[3] = "上级组织ID/上级部门代码";
        col[4] = "上级组织名称/上级部门名称";
        col[5] = "组织类型/部门类型(1,20,21,80,90)";
        return col;
    }
}
