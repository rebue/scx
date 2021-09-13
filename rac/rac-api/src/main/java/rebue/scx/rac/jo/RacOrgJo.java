package rebue.scx.rac.jo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 组织
 *
 * The persistent class for the RAC_ORG database table.
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "RAC_ORG")
@Getter
@Setter
@ToString
public class RacOrgJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 组织ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 20)
    private Long              id;

    /**
     * 组织名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 30)
    private String            name;

    /**
     * 组织类型(1.集团;20.政府单位;21.公司;80.部门;90.小组)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "ORG_TYPE", nullable = false, length = 3)
    private Byte              orgType;

    /**
     * 组织全名
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "FULL_NAME", nullable = true, length = 80)
    private String            fullName;

    /**
     * 组织简介
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "INTRODUCTION", nullable = true, length = 200)
    private String            introduction;

    /**
     * 组织备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "REMARK", nullable = true, length = 100)
    private String            remark;

    /**
     * 上级组织
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    @ManyToOne()
    private RacOrgJo          parent;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RacOrgJo other = (RacOrgJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * 树编码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "TREE_CODE", nullable = false, length = 50)
    private String     treeCode;

    /**
     * 组织属性类型(字典项KEY)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "ATTR_TYPE", nullable = true, length = 32)
    private String     attrType;

    /**
     * 地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "ADDR", nullable = true, length = 500)
    private String     addr;

    /**
     * 联系人
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "CONTACT_PERSON", nullable = true, length = 30)
    private String     contactPerson;

    /**
     * 联系方式
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "CONTACT_WAY", nullable = true, length = 30)
    private String     contactWay;

    /**
     * 邮箱
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "EMAIL", nullable = true, length = 50)
    private String     email;

    /**
     * 领域
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "REALM_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RacRealmJo realm;

    /**
     * 组织编码
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "CODE", nullable = true, length = 32)
    private String     code;
}
