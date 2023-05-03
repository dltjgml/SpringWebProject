package controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.BuyDAO;

@Controller
public class BuyController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ServletContext app;
	
	@Autowired
	BuyDAO buydao;
	
	@RequestMapping("/buy_product.do")
	@ResponseBody
	public int buyProduct(int idx) {
		return buydao.selectProduct(idx).getProduct_price();
	}
}