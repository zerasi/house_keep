package com.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entity.Goods;
import com.service.GoodsService;
import com.entity.Cate;
import com.service.CateService;
import com.util.PageHelper;
import com.util.VeDate;

//定义为控制器
@Controller
// 设置路径
public class GoodsAction extends BaseAction {
	// 注入Service 由于标签的存在 所以不需要getter setter
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CateService cateService;

	// 准备添加数据
	@RequestMapping("goods_createGoods.action")
	public String createGoods() {
		List<Cate> cateList = this.cateService.getAllCate();
		this.getRequest().setAttribute("cateList", cateList);
		return "admin/addgoods";
	}

	// 添加数据
	@RequestMapping("goods_addGoods.action")
	public String addGoods(Goods goods) {
		goods.setAddtime(VeDate.getStringDateShort());
		goods.setHits("0");
		goods.setSellnum("0");
		this.goodsService.insertGoods(goods);
		return "redirect:/goods_createGoods.action";
	}

	// 通过主键删除数据
	@RequestMapping("goods_deleteGoods.action")
	public String deleteGoods(String id) {
		this.goodsService.deleteGoods(id);
		return "redirect:/goods_getAllGoods.action";
	}

	// 批量删除数据
	@RequestMapping("goods_deleteGoodsByIds.action")
	public String deleteGoodsByIds() {
		String[] ids = this.getRequest().getParameterValues("goodsid");
		for (String goodsid : ids) {
			this.goodsService.deleteGoods(goodsid);
		}
		return "redirect:/goods_getAllGoods.action";
	}

	// 更新数据
	@RequestMapping("goods_updateGoods.action")
	public String updateGoods(Goods goods) {
		this.goodsService.updateGoods(goods);
		return "redirect:/goods_getAllGoods.action";
	}

	// 显示全部数据
	@RequestMapping("goods_getAllGoods.action")
	public String getAllGoods(String number) {
		List<Goods> goodsList = this.goodsService.getAllGoods();
		PageHelper.getPage(goodsList, "goods", null, null, 10, number, this.getRequest(), null);
		return "admin/listgoods";
	}

	// 按条件查询数据 (模糊查询)
	@RequestMapping("goods_queryGoodsByCond.action")
	public String queryGoodsByCond(String cond, String name, String number) {
		Goods goods = new Goods();
		if (cond != null) {
			if ("goodsname".equals(cond)) {
				goods.setGoodsname(name);
			}
			if ("image".equals(cond)) {
				goods.setImage(name);
			}
			if ("cateid".equals(cond)) {
				goods.setCateid(name);
			}
			if ("price".equals(cond)) {
				goods.setPrice(name);
			}
			if ("addtime".equals(cond)) {
				goods.setAddtime(name);
			}
			if ("hits".equals(cond)) {
				goods.setHits(name);
			}
			if ("sellnum".equals(cond)) {
				goods.setSellnum(name);
			}
			if ("contents".equals(cond)) {
				goods.setContents(name);
			}
		}

		List<String> nameList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		nameList.add(cond);
		valueList.add(name);
		PageHelper.getPage(this.goodsService.getGoodsByLike(goods), "goods", nameList, valueList, 10, number, this.getRequest(), "query");
		name = null;
		cond = null;
		return "admin/querygoods";
	}

	// 按主键查询数据
	@RequestMapping("goods_getGoodsById.action")
	public String getGoodsById(String id) {
		Goods goods = this.goodsService.getGoodsById(id);
		this.getRequest().setAttribute("goods", goods);
		List<Cate> cateList = this.cateService.getAllCate();
		this.getRequest().setAttribute("cateList", cateList);
		return "admin/editgoods";
	}

	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

}
