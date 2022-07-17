package com.salary.project.business.level.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.salary.framework.aspectj.lang.annotation.Excel;
import com.salary.framework.web.domain.BaseEntity;

/**
 * 请假对象 tbl_level
 * 
 * @author au
 * @date 2021-07-08
 */
public class Level extends BaseEntity
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

    /** 请假开始日期 */
    @Excel(name = "请假开始日期")
    private String dateStart;

    /** 请假结束日期 */
    @Excel(name = "请假结束日期")
    private String dateEnd;

    /** 请假原因 */
    @Excel(name = "请假原因")
    private String reason;

    /** 审批人id */
    @Excel(name = "审批人id")
    private Long approvalId;

    /** 审批人姓名 */
    @Excel(name = "审批人姓名")
    private String approvalName;

    /** 审批结果 */
    @Excel(name = "审批结果")
    private String result;

    /** 审批意见 */
    @Excel(name = "审批意见")
    private String approvalReason;

    /** 审批时间 */
    @Excel(name = "审批时间")
    private String approvalDate;

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
    public void setDateStart(String dateStart)
    {
        this.dateStart = dateStart;
    }

    public String getDateStart()
    {
        return dateStart;
    }
    public void setDateEnd(String dateEnd)
    {
        this.dateEnd = dateEnd;
    }

    public String getDateEnd()
    {
        return dateEnd;
    }
    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }
    public void setApprovalId(Long approvalId)
    {
        this.approvalId = approvalId;
    }

    public Long getApprovalId()
    {
        return approvalId;
    }
    public void setApprovalName(String approvalName)
    {
        this.approvalName = approvalName;
    }

    public String getApprovalName()
    {
        return approvalName;
    }
    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }
    public void setApprovalReason(String approvalReason)
    {
        this.approvalReason = approvalReason;
    }

    public String getApprovalReason()
    {
        return approvalReason;
    }
    public void setApprovalDate(String approvalDate)
    {
        this.approvalDate = approvalDate;
    }

    public String getApprovalDate()
    {
        return approvalDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("dateStart", getDateStart())
            .append("dateEnd", getDateEnd())
            .append("reason", getReason())
            .append("approvalId", getApprovalId())
            .append("approvalName", getApprovalName())
            .append("result", getResult())
            .append("approvalReason", getApprovalReason())
            .append("approvalDate", getApprovalDate())
            .append("createTime", getCreateTime())
            .toString();
    }
}
