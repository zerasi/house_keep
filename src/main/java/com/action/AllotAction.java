package com.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Allot;
import com.service.AllotService;
import com.entity.Orders;
import com.entity.Employ;
import com.service.OrdersService;
import com.service.EmployService;
import com.util.PageHelper;
import com.util.VeDate;

//定义为控制器
@Controller
// 设置路径
public class AllotAction extends BaseAction {
	// 注入Service 由于标签的存在 所以不需要getter setter
	@Autowired
	private AllotService allotService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private EmployService employService;

	// 准备添加数据
	@RequestMapping("allot_createAllot.action")
	public String createAllot() {
		Orders orders = new Orders();
		orders.setStatus("已付款");
		List<Orders> ordersList = this.ordersService.getOrdersByCond(orders);
		this.getRequest().setAttribute("ordersList", ordersList);
		List<Employ> employList = this.employService.getAllEmploy();
		this.getRequest().setAttribute("employList", employList);
		return "admin/addallot";
	}

	// 添加数据
	@RequestMapping("allot_addAllot.action")
	public String addAllot(Allot allot) {
		allot.setAddtime(VeDate.getStringDateShort());
		allot.setStatus("进行中");
		this.allotService.insertAllot(allot);
		Orders orders = this.ordersService.getOrdersById(allot.getOrdersid());
		orders.setStatus("进行中");
		this.ordersService.updateOrders(orders);
		return "redirect:/allot_createAllot.action";
	}

	// 通过主键删除数据
	@RequestMapping("allot_deleteAllot.action")
	public String deleteAllot(String id) {
		this.allotService.deleteAllot(id);
		return "redirect:/allot_getAllAllot.action";
	}

	// 批量删除数据
	@RequestMapping("allot_deleteAllotByIds.action")
	public String deleteAllotByIds() {
		String[] ids = this.getRequest().getParameterValues("allotid");
		for (String allotid : ids) {
			this.allotService.deleteAllot(allotid);
		}
		return "redirect:/allot_getAllAllot.action";
	}

	// 更新数据
	@RequestMapping("allot_updateAllot.action")
	public String updateAllot(Allot allot) {
		this.allotService.updateAllot(allot);
		return "redirect:/allot_getAllAllot.action";
	}

	@RequestMapping("allot_over.action")
	public String over(String id) {
		Allot allot = this.allotService.getAllotById(id);
		allot.setStatus("完成");
		this.allotService.updateAllot(allot);
		Orders orders = this.ordersService.getOrdersById(allot.getOrdersid());
		orders.setStatus("完成");
		this.ordersService.updateOrders(orders);
		return "redirect:/allot_getAllAllot.action";
	}

	// 显示全部数据
	@RequestMapping("allot_getAllAllot.action")
	public String getAllAllot(String number) {
		List<Allot> allotList = this.allotService.getAllAllot();
		PageHelper.getPage(allotList, "allot", null, null, 10, number, this.getRequest(), null);
		return "admin/listallot";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("allot_queryAllotByCond.action")
	public String queryAllotByCond(String cond, String name, String number) {
		Allot allot = new Allot();
		if (cond != null) {
			if ("ordersid".equals(cond)) {
				allot.setOrdersid(name);
			}
			if ("employid".equals(cond)) {
				allot.setEmployid(name);
			}
			if ("addtime".equals(cond)) {
				allot.setAddtime(name);
			}
			if ("status".equals(cond)) {
				allot.setStatus(name);
			}
		}

		List<String> nameList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		nameList.add(cond);
		valueList.add(name);
		PageHelper.getPage(this.allotService.getAllotByLike(allot), "allot", nameList, valueList, 10, number, this.getRequest(), "query");
		name = null;
		cond = null;
		return "admin/queryallot";
	}

	// 按主键查询数据
	@RequestMapping("allot_getAllotById.action")
	public String getAllotById(String id) {
		Allot allot = this.allotService.getAllotById(id);
		this.getRequest().setAttribute("allot", allot);
		List<Orders> ordersList = this.ordersService.getAllOrders();
		this.getRequest().setAttribute("ordersList", ordersList);
		List<Employ> employList = this.employService.getAllEmploy();
		this.getRequest().setAttribute("employList", employList);
		return "admin/editallot";
	}

	public AllotService getAllotService() {
		return allotService;
	}

	public void setAllotService(AllotService allotService) {
		this.allotService = allotService;
	}

}
