package com.salary.project.business.account.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.salary.common.utils.security.ShiroUtils;
import com.salary.project.business.detail.domain.AccountDetail;
import com.salary.project.business.detail.service.IAccountDetailService;
import com.salary.project.business.work.service.ICheckWorkService;
import com.salary.project.system.user.domain.User;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.salary.framework.aspectj.lang.annotation.Log;
import com.salary.framework.aspectj.lang.enums.BusinessType;
import com.salary.project.business.account.domain.Account;
import com.salary.project.business.account.service.IAccountService;
import com.salary.framework.web.controller.BaseController;
import com.salary.framework.web.domain.AjaxResult;
import com.salary.common.utils.poi.ExcelUtil;
import com.salary.framework.web.page.TableDataInfo;

/**
 * 工资核算Controller
 * 
 * @author au
 * @date 2021-07-12
 */
@Controller
@RequestMapping("/business/account")
public class AccountController extends BaseController
{
    private String prefix = "business/account";

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IAccountDetailService accountDetailService ;

    @Autowired
    private ICheckWorkService checkWorkService ;


    /**
     * 工资条页面
     * @return
     */
    @RequiresPermissions("business:accountPer:view")
    @GetMapping("/accountPer")
    public String accountPer()
    {
        return prefix + "/account_per";
    }

    /**
     * 工资条数据
     */
    @PostMapping("/accountPerData")
    @ResponseBody
    public TableDataInfo accountPerData(Account account)
    {
        //个人信息
        User user =  ShiroUtils.getSysUser() ;
        account.setUserId(user.getUserId());
        startPage();
        List<Account> list = accountService.selectAccountList(account);
        return getDataTable(list);
    }


    @RequiresPermissions("business:account:view")
    @GetMapping()
    public String account()
    {
        return prefix + "/account";
    }


    /**
     * 查询工资核算列表
     */
    @RequiresPermissions("business:account:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Account account)
    {
        startPage();
        List<Account> list = accountService.selectAccountList(account);
        return getDataTable(list);
    }

    /**
     * 导出工资核算列表
     */
    @RequiresPermissions("business:account:export")
    @Log(title = "工资核算", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Account account)
    {
        List<Account> list = accountService.selectAccountList(account);
        ExcelUtil<Account> util = new ExcelUtil<Account>(Account.class);
        return util.exportExcel(list, "工资核算数据");
    }

    /**
     * 新增工资核算
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存工资核算
     */
    @RequiresPermissions("business:account:add")
    @Log(title = "工资核算", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
//    @Transactional()//事务
    public AjaxResult addSave(Account account) throws ParseException {
        String user_gl = account.getDate().substring(0,7) + "-01" ;
        String firstDay = getFirstDay(user_gl) ;//本月第一天
        String lastDay = getLastDay(user_gl) ;//本月第一天

        //1.先判断本月是否已核算过
        Account isCheck = new Account() ;
        isCheck.setDate(account.getDate());
        List<Account> isChecks = accountService.selectAccountList(isCheck) ;
        if(isChecks.size() > 0){
            return error("本月薪资已核算，重新核算请先删除所有核算数据") ;
        }
        //2.获取所有员工信息
        List<Map<String,Object>> users = accountService.selectUserList() ;
        //3.获取岗位对应的薪资配置
        for(Map<String,Object> user : users){
            Account accountSet = new Account() ; //保存用的
            accountSet.setDate(account.getDate().substring(0,7));
            //01、保存核算表，获取保存的信息
            accountSet.setUserId(Long.parseLong(user.get("user_id").toString()));
            accountSet.setUserName(user.get("user_name").toString());
            accountSet.setPortId(Long.parseLong(user.get("post_id").toString()));
            accountSet.setPortName(user.get("post_name").toString());
            accountService.insertAccount(accountSet) ;
            List<Map<String,Object>> accountUses =  accountService.selectAccount(accountSet) ;
            Map<String,Object> accountUser = accountUses.get(0) ;
            accountSet.setId(Long.parseLong(accountUser.get("id").toString()));
            //02、获取当前员工岗位对应的配置信息
            List<Map<String,Object>> allocations = accountService.setlectAllocations(user.get("post_id").toString()) ;
            //03、计算工资，保存详细核算表信息，更新核算表信息
            float salary_yf = 0 ;//应发工资
            float salary_sf = 0 ;//实发工资
            for(Map<String,Object> allocation : allocations){
                String allocationId = allocation.get("id").toString() ;
                String allocationName = allocation.get("name").toString() ;
                Float money = Float.parseFloat(allocation.get("money").toString()) ;//设计金额
                String type = allocation.get("type").toString() ;//项目类型 1增加 2减少
                String rule = allocation.get("rule").toString() ;//计算规则 1直接加减 2需要计算
                AccountDetail accountDetail = new AccountDetail() ;
                accountDetail.setAccountId(Long.parseLong(accountUser.get("id").toString()));
                accountDetail.setAllocationId(Long.parseLong(allocationId));
                accountDetail.setAllocationName(allocationName);
                accountDetail.setType(type);
                accountDetail.setMoney(money);
                if("1".equals(rule)){
                    if("1".equals(type)){
                        salary_sf = salary_sf + money ;
                        accountDetailService.insertAccountDetail(accountDetail) ;
                        salary_yf = salary_yf +money;
                    }else{
                        salary_sf = salary_sf - money ;
                        accountDetailService.insertAccountDetail(accountDetail) ;
                    }
                    //保存详细表信息
                }else{
                    if("2".equals(allocationId)){
                        //工龄工资
                        String user_rz = user.get("create_time").toString().substring(0,10) ;//入职时间
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
                        Date user_gl_date = simpleDateFormat.parse(user_gl);
                        Date user_rz_date = simpleDateFormat.parse(user_rz);
                        Long mon = getMonthDiff(user_gl_date,user_rz_date) ;
                        money = money * mon ;
                        salary_sf = salary_sf + money ;
                        accountDetail.setMoney(money);
                        accountDetailService.insertAccountDetail(accountDetail) ;
                    }else if("16".equals(allocationId)){
                        //迟到
                        int cd_day = checkWorkService.getDays(user.get("user_id").toString(),firstDay,lastDay,"2") ;
                        float cd_kc = cd_day * money ;//迟到扣除
                        salary_sf = salary_sf - cd_kc ;
                        accountDetail.setMoney(cd_kc);
                        accountDetailService.insertAccountDetail(accountDetail) ;
                    }else if("18".equals(allocationId)){
                        //早退
                        int cd_day = checkWorkService.getDays(user.get("user_id").toString(),firstDay,lastDay,"3") ;
                        float cd_kc = cd_day * money ;//早退扣除
                        salary_sf = salary_sf - cd_kc ;
                        accountDetail.setMoney(cd_kc);
                        accountDetailService.insertAccountDetail(accountDetail) ;
                    }else if("17".equals(allocationId)){
                        //请假
                        int cd_day = checkWorkService.getDays(user.get("user_id").toString(),firstDay,lastDay,"0") ;
                        float cd_kc = cd_day * money ;//请假扣除
                        salary_sf = salary_sf - cd_kc ;
                        accountDetail.setMoney(cd_kc);
                        accountDetailService.insertAccountDetail(accountDetail) ;
                    }
                }
             }

            accountSet.setState("1");
            accountSet.setShifa(salary_sf);
            accountSet.setYingfa(salary_yf);
            accountService.updateAccount(accountSet) ;
        }
        return success();
    }



    /**
     * 当月第一天
     * @return
     */
    private static String getFirstDay(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date theDate = df.parse(date+"-01 00:00:00");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        return str.toString().substring(0,10);

    }

    public static void main(String[] args) throws ParseException {
        String d = getFirstDay("2021-07") ;
        String e = getLastDay("2021-07") ;
        System.out.println(d);
        System.out.println(e);
    }
    private static Calendar calendar=Calendar.getInstance();


    /**
     * 当月最后一天
     * @return
     */
    private static String getLastDay(String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate=sdf.parse(date+"-01 00:00:00");
        calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String time = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
        return time.substring(0,10);
    }

    /**
     * 获取两个日期相差的月数
     */
    public static long getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) {
            yearInterval--;
        }
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) {
            monthInterval--;
        }
        monthInterval %= 12;
        System.out.println(yearInterval);
        System.out.println(monthInterval);
        int monthsDiff = Math.abs(yearInterval * 12 + monthInterval)-1;
        return monthsDiff;
    }

    /**
     * 修改工资核算
     */
    @GetMapping("/getDetail/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setAccountId(id);
        List<AccountDetail> list = accountDetailService.selectAccountDetailList(accountDetail) ;
        mmap.put("accountDetail", list);
        return   "business/detail/detail";
    }

    /**
     * 修改保存工资核算
     */
    @RequiresPermissions("business:account:edit")
    @Log(title = "工资核算", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Account account)
    {
        return toAjax(accountService.updateAccount(account));
    }

    /**
     * 删除工资核算
     */
    @RequiresPermissions("business:account:remove")
    @Log(title = "工资核算", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(Long ids)
    {
        //先删除关联表
        //删除关联表
//        String[] split = ids.split(",");
//        for (int i = 0; i < split.length; i++) {
//            String id = split[i] ;
        accountService.deleteAccountDetail(ids) ;
//        }
        return toAjax(accountService.deleteAccountById(ids));
    }
}
