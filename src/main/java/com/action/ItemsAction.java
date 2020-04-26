package com.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Items;
import com.service.ItemsService;
import com.entity.Goods;
import com.service.GoodsService;
import com.util.PageHelper;

//定义为控制器
@Controller
// 设置路径
public class ItemsAction extends BaseAction {
	// 注入Service 由于标签的存在 所以不需要getter setter
	@Autowired
	private ItemsService itemsService;
	@Autowired
	private GoodsService goodsService;

	// 准备添加数据
	@RequestMapping("items_createItems.action")
	public String createItems() {
		List<Goods> goodsList = this.goodsService.getAllGoods();
		this.getRequest().setAttribute("goodsList", goodsList);
		return "admin/additems";
	}

	// 添加数据
	@RequestMapping("items_addItems.action")
	public String addItems(Items items) {
		this.itemsService.insertItems(items);
		return "redirect:/items_createItems.action";
	}

	// 通过主键删除数据
	@RequestMapping("items_deleteItems.action")
	public String deleteItems(String id) {
		this.itemsService.deleteItems(id);
		return "redirect:/items_getAllItems.action";
	}

	// 批量删除数据
	@RequestMapping("items_deleteItemsByIds.action")
	public String deleteItemsByIds() {
		String[] ids = this.getRequest().getParameterValues("itemsid");
		for (String itemsid : ids) {
			this.itemsService.deleteItems(itemsid);
		}
		return "redirect:/items_getAllItems.action";
	}

	// 更新数据
	@RequestMapping("items_updateItems.action")
	public String updateItems(Items items) {
		this.itemsService.updateItems(items);
		return "redirect:/items_getAllItems.action";
	}

	// 显示全部数据
	@RequestMapping("items_getAllItems.action")
	public String getAllItems(String number) {
		List<Items> itemsList = this.itemsService.getAllItems();
		PageHelper.getPage(itemsList, "items", null, null, 10, number, this.getRequest(), null);
		return "admin/listitems";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("items_queryItemsByCond.action")
	public String queryItemsByCond(String cond, String name, String number) {
		Items items = new Items();
		if (cond != null) {
			if ("ordercode".equals(cond)) {
				items.setOrdercode(name);
			}
			if ("goodsid".equals(cond)) {
				items.setGoodsid(name);
			}
			if ("price".equals(cond)) {
				items.setPrice(name);
			}
		}

		List<String> nameList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		nameList.add(cond);
		valueList.add(name);
		PageHelper.getPage(this.itemsService.getItemsByLike(items), "items", nameList, valueList, 10, number, this.getRequest(), "query");
		name = null;
		cond = null;
		return "admin/queryitems";
	}

	// 按主键查询数据
	@RequestMapping("items_getItemsById.action")
	public String getItemsById(String id) {
		Items items = this.itemsService.getItemsById(id);
		this.getRequest().setAttribute("items", items);
		List<Goods> goodsList = this.goodsService.getAllGoods();
		this.getRequest().setAttribute("goodsList", goodsList);
		return "admin/edititems";
	}

	public ItemsService getItemsService() {
		return itemsService;
	}

	public void setItemsService(ItemsService itemsService) {
		this.itemsService = itemsService;
	}

}
