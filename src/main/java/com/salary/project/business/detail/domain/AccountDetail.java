package com.salary.project.business.detail.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.salary.framework.aspectj.lang.annotation.Excel;
import com.salary.framework.web.domain.BaseEntity;

/**
 * 工资核算详情对象 tbl_account_detail
 * 
 * @author au
 * @date 2021-07-12
 */
public class AccountDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 关联表id */
    @Excel(name = "关联表id")
    private Long accountId;

    /** 项目id */
    @Excel(name = "项目id")
    private Long allocationId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String allocationName;

    /** 计算规则 */
    @Excel(name = "计算规则")
    private String type;

    /** 涉及金额 */
    @Excel(name = "涉及金额")
    private Float money;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAccountId(Long accountId)
    {
        this.accountId = accountId;
    }

    public Long getAccountId()
    {
        return accountId;
    }
    public void setAllocationId(Long allocationId)
    {
        this.allocationId = allocationId;
    }

    public Long getAllocationId()
    {
        return allocationId;
    }
    public void setAllocationName(String allocationName)
    {
        this.allocationName = allocationName;
    }

    public String getAllocationName()
    {
        return allocationName;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setMoney(Float money)
    {
        this.money = money;
    }

    public Float getMoney()
    {
        return money;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("accountId", getAccountId())
            .append("allocationId", getAllocationId())
            .append("allocationName", getAllocationName())
            .append("type", getType())
            .append("money", getMoney())
            .append("createTime", getCreateTime())
            .toString();
    }
}
