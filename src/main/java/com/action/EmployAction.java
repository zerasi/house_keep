package com.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Employ;
import com.service.EmployService;
import com.util.PageHelper;

//定义为控制器
@Controller
// 设置路径
public class EmployAction extends BaseAction {
	// 注入Service 由于标签的存在 所以不需要getter setter
	@Autowired
	private EmployService employService;

	// 准备添加数据
	@RequestMapping("employ_createEmploy.action")
	public String createEmploy() {
		return "admin/addemploy";
	}

	// 添加数据
	@RequestMapping("employ_addEmploy.action")
	public String addEmploy(Employ employ) {
		this.employService.insertEmploy(employ);
		return "redirect:/employ_createEmploy.action";
	}

	// 通过主键删除数据
	@RequestMapping("employ_deleteEmploy.action")
	public String deleteEmploy(String id) {
		this.employService.deleteEmploy(id);
		return "redirect:/employ_getAllEmploy.action";
	}

	// 批量删除数据
	@RequestMapping("employ_deleteEmployByIds.action")
	public String deleteEmployByIds() {
		String[] ids = this.getRequest().getParameterValues("employid");
		for (String employid : ids) {
			this.employService.deleteEmploy(employid);
		}
		return "redirect:/employ_getAllEmploy.action";
	}

	// 更新数据
	@RequestMapping("employ_updateEmploy.action")
	public String updateEmploy(Employ employ) {
		this.employService.updateEmploy(employ);
		return "redirect:/employ_getAllEmploy.action";
	}

	// 显示全部数据
	@RequestMapping("employ_getAllEmploy.action")
	public String getAllEmploy(String number) {
		List<Employ> employList = this.employService.getAllEmploy();
		PageHelper.getPage(employList, "employ", null, null, 10, number, this.getRequest(), null);
		return "admin/listemploy";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("employ_queryEmployByCond.action")
	public String queryEmployByCond(String cond, String name, String number) {
		Employ employ = new Employ();
		if (cond != null) {
			if ("realname".equals(cond)) {
				employ.setRealname(name);
			}
			if ("sex".equals(cond)) {
				employ.setSex(name);
			}
			if ("birthday".equals(cond)) {
				employ.setBirthday(name);
			}
			if ("idcard".equals(cond)) {
				employ.setIdcard(name);
			}
			if ("jiguan".equals(cond)) {
				employ.setJiguan(name);
			}
			if ("minzu".equals(cond)) {
				employ.setMinzu(name);
			}
			if ("workdate".equals(cond)) {
				employ.setWorkdate(name);
			}
			if ("contact".equals(cond)) {
				employ.setContact(name);
			}
			if ("memo".equals(cond)) {
				employ.setMemo(name);
			}
		}

		List<String> nameList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		nameList.add(cond);
		valueList.add(name);
		PageHelper.getPage(this.employService.getEmployByLike(employ), "employ", nameList, valueList, 10, number, this.getRequest(), "query");
		name = null;
		cond = null;
		return "admin/queryemploy";
	}

	// 按主键查询数据
	@RequestMapping("employ_getEmployById.action")
	public String getEmployById(String id) {
		Employ employ = this.employService.getEmployById(id);
		this.getRequest().setAttribute("employ", employ);
		return "admin/editemploy";
	}

	public EmployService getEmployService() {
		return employService;
	}

	public void setEmployService(EmployService employService) {
		this.employService = employService;
	}

}
