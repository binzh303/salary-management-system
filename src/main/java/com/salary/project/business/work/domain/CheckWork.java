package com.salary.project.business.work.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.salary.framework.aspectj.lang.annotation.Excel;
import com.salary.framework.web.domain.BaseEntity;

/**
 * 考勤对象 tbl_check_work
 * 
 * @author au
 * @date 2021-07-07
 */
public class CheckWork extends BaseEntity
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

    /** 出勤日期 */
    @Excel(name = "出勤日期")
    private String date;

    /** 上班时间 */
    @Excel(name = "上班时间")
    private String workTime;

    /** 下班时间 */
    @Excel(name = "下班时间")
    private String closeTime;

    /** 上班状态 */
    @Excel(name = "上班状态")
    private String state;

    /** 请假表id */
    @Excel(name = "请假表id")
    private Long levelId;

    /** 请假是否批准 */
    @Excel(name = "请假是否批准")
    private String levelState;

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
    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDate()
    {
        return date;
    }
    public void setWorkTime(String workTime)
    {
        this.workTime = workTime;
    }

    public String getWorkTime()
    {
        return workTime;
    }
    public void setCloseTime(String closeTime)
    {
        this.closeTime = closeTime;
    }

    public String getCloseTime()
    {
        return closeTime;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }
    public void setLevelId(Long levelId)
    {
        this.levelId = levelId;
    }

    public Long getLevelId()
    {
        return levelId;
    }
    public void setLevelState(String levelState)
    {
        this.levelState = levelState;
    }

    public String getLevelState()
    {
        return levelState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("date", getDate())
            .append("workTime", getWorkTime())
            .append("closeTime", getCloseTime())
            .append("state", getState())
            .append("levelId", getLevelId())
            .append("levelState", getLevelState())
            .toString();
    }
}
