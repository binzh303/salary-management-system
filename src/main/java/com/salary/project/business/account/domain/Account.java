package com.salary.project.business.account.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.salary.framework.aspectj.lang.annotation.Excel;
import com.salary.framework.web.domain.BaseEntity;

/**
 * 工资核算对象 tbl_account
 * 
 * @author au
 * @date 2021-07-12
 */
public class Account extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 员工id */
    @Excel(name = "员工id")
    private Long userId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String userName;

    /** 岗位id */
    @Excel(name = "岗位id")
    private Long portId;

    /** 岗位名称 */
    @Excel(name = "岗位名称")
    private String portName;

    /** 核算月份 */
    @Excel(name = "核算月份")
    private String date;

    /** 核算状态 */
    @Excel(name = "核算状态")
    private String state;

    /** 应发工资 */
    @Excel(name = "应发工资")
    private Float yingfa;

    /** 实发工资 */
    @Excel(name = "实发工资")
    private Float shifa;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setPortId(Long portId)
    {
        this.portId = portId;
    }

    public Long getPortId()
    {
        return portId;
    }
    public void setPortName(String portName)
    {
        this.portName = portName;
    }

    public String getPortName()
    {
        return portName;
    }
    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDate()
    {
        return date;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }
    public void setYingfa(Float yingfa)
    {
        this.yingfa = yingfa;
    }

    public Float getYingfa()
    {
        return yingfa;
    }
    public void setShifa(Float shifa)
    {
        this.shifa = shifa;
    }

    public Float getShifa()
    {
        return shifa;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("portId", getPortId())
            .append("portName", getPortName())
            .append("date", getDate())
            .append("state", getState())
            .append("yingfa", getYingfa())
            .append("shifa", getShifa())
            .append("createTime", getCreateTime())
            .toString();
    }
}
