package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.Item;
import com.app.model.OrderMethod;
import com.app.model.Uom;
import com.app.model.WhUserType;
import com.app.service.IItemService;
import com.app.service.IOrderMethodService;
import com.app.service.IUomService;
import com.app.service.IWhUserTypeService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private IItemService service;
	@Autowired
	private IUomService uomservice;

	@Autowired
	private IOrderMethodService omservice;

	@Autowired
	private IWhUserTypeService whservice;


	@RequestMapping("/show")
	public String showItem(ModelMap map) {
		map.addAttribute("item",new Item());
		List<Uom> uoms=uomservice.getAllUoms();
		map.addAttribute("uoms",uoms);

		List<OrderMethod> sales=omservice.getOrderMethodByMode("sale");
		map.addAttribute("sales",sales);

		List<OrderMethod> purchas=omservice.getOrderMethodByMode("purchase");
		map.addAttribute("purchas", purchas);

		List<WhUserType> whusertype=whservice.getWhUsersTypeByType("vendor");
		map.addAttribute("vendors", whusertype);
		List<WhUserType> usertype=whservice.getWhUsersTypeByType("customer");
		map.addAttribute("customers", usertype);

		return "CreateItem";




	}
	@RequestMapping(value="/createitem",method=RequestMethod.POST)
	public String itemSave(@ModelAttribute Item item,ModelMap map) {
		Integer id=service.saveItem(item);
		Object msg="Item'"+id+"'SavedSucessFully";
		map.addAttribute("message",msg);
		map.addAttribute("item",new Item());
		List<Uom> uoms=uomservice.getAllUoms();
		map.addAttribute("uoms",uoms);

		List<OrderMethod> sales=omservice.getOrderMethodByMode("sale");
		map.addAttribute("sales",sales);

		List<OrderMethod> purchas=omservice.getOrderMethodByMode("purchase");
		map.addAttribute("purchas", purchas);

		List<WhUserType> whusertype=whservice.getWhUsersTypeByType("Vendor");
		map.addAttribute("vendors", whusertype);
		List<WhUserType> usertype=whservice.getWhUsersTypeByType("Customer");
		map.addAttribute("customers", usertype);

		return "CreateItem";
	}
	@RequestMapping("/getitem")
	public String getAllItems(ModelMap map) {
		List<Item> list=service.getAllItems();
		map.addAttribute("list",list);
		return "ItemData";	
	}
	@RequestMapping("/delete")
	public String deleteItem(@RequestParam("id")Integer id,ModelMap map) {

		service.deleteItem(id);
		Object msg="Item'"+id+"'DeletedSucessFully";
		map.addAttribute("message",msg);
		List<Item> item=service.getAllItems();
		map.addAttribute("list",item);
		return "ItemData";
	}
	@RequestMapping("/edit")
	public String upadateItem(@RequestParam Integer id,ModelMap map) {
		Item item=service.getOneByItem(id);
		map.addAttribute("item",item);
		List<Uom> uoms=uomservice.getAllUoms();
		map.addAttribute("uoms",uoms);

		List<OrderMethod> sales=omservice.getOrderMethodByMode("sale");
		map.addAttribute("sales",sales);

		List<OrderMethod> purchas=omservice.getOrderMethodByMode("purchase");
		map.addAttribute("purchas", purchas);


		List<WhUserType> whusertype=whservice.getWhUsersTypeByType("Vendor");
		map.addAttribute("vendors", whusertype);
		List<WhUserType> usertype=whservice.getWhUsersTypeByType("Customer");
		map.addAttribute("customers", usertype);


		return "ItemUpdate";	
	}
	@RequestMapping(value="/edititem",method=RequestMethod.POST)
	public String editItem(@ModelAttribute("item")Item item,ModelMap map) {
		service.updateItem(item);
		Object msg="Item'"+item.getId()+"'UpdatedSucessfully";
		map.addAttribute("message",msg);
		List<Item> item1=service.getAllItems();
		map.addAttribute("list",item1);
		return "ItemData";	
	}
}
