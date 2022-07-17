package com.salary.project.salary.domain;

import java.math.BigDecimal;

import com.salary.framework.aspectj.lang.annotation.Excel;
import com.salary.framework.web.domain.BaseEntity;

/**
 * 薪资配置对象 tbl_salary_allocation
 * 
 * @author au
 * @date 2021-07-05
 */
public class SalaryAllocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String name;

    /** 项目类型 */
    @Excel(name = "项目类型")
    private String type;

    /** 计算规则 */
    @Excel(name = "计算规则")
    private String rule;

    /** 涉及金额 */
    @Excel(name = "涉及金额")
    private BigDecimal money;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    /** 等级 */
    @Excel(name = "等级")
    private String grade;

    /** 岗位是否存在此配置项 默认不存在 */
    private boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "SalaryAllocation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", rule='" + rule + '\'' +
                ", money=" + money +
                ", state='" + state + '\'' +
                ", grade='" + grade + '\'' +
                ", flag=" + flag +
                '}';
    }


    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setRule(String rule)
    {
        this.rule = rule;
    }

    public String getRule()
    {
        return rule;
    }
    public void setMoney(BigDecimal money)
    {
        this.money = money;
    }

    public BigDecimal getMoney()
    {
        return money;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }
    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public String getGrade()
    {
        return grade;
    }

}
