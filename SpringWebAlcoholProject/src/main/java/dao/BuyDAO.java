package dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.FullViewVO;
import vo.OrderListVO;
import vo.ProducerVO;

@Repository
public class BuyDAO {
	@Autowired
	private SqlSessionTemplate session;
	
	public FullViewVO selectProduct(int product_idx){
		return session.selectOne("b.select_product", product_idx);
	}
	public List<FullViewVO> selectProducts(List<OrderListVO> cart) {
		
		return session.selectList("b.select_products",cart);
	}
	public ProducerVO selectProducer(int idx) {
		return session.selectOne("b.select_producer_name",idx);
	}
	public int insertOrder(List<OrderListVO> list) {
		int res=0;
		for (OrderListVO vo : list) {			
			res+=session.insert("b.insertOrder",vo);
		}
		return res;
	}
	public Timestamp Sysdate() {
		return session.selectOne("b.sysdate");
	}
	public List<OrderListVO> selectOrderList(Timestamp orderdate, int user_idx) {
		OrderListVO vo= new OrderListVO();
		vo.setOrderlist_date(orderdate);
		vo.setUser_idx(user_idx);
		return session.selectList("b.selectOrder", vo);
		
	}
	public List<OrderListVO> selectOrderList(int user_idx) {
		return session.selectList("b.selectOrderAll", user_idx);
		
	}
	public int updateOrderList(OrderListVO vo) {
		return session.update("b.updateOrder",vo);
		
	}
	public int updateOrderlistPaid(int orderlist_idx, UUID id, Timestamp paidDate) {
		OrderListVO vo= new OrderListVO();
		vo.setOrderlist_idx(orderlist_idx);
		vo.setPay_id(id);
		vo.setOrderlist_date(paidDate);
		return session.update("b.updateOrderPaid",vo);
		
	}
	public List<OrderListVO> selectDate(int user_idx) {
		return session.selectList("b.selectdate",user_idx);
	}
	
	public String selectProductName(int product_idx) {
		return session.selectOne("b.selectProductName", product_idx);
	}
}
