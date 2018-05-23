package com.imooc.vat.serviceImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.imooc.vat.service.ThrowingConsumer;
import com.imooc.vat.serviceImpl.tool.RunnableExecutorService;
import com.imooc.vat.util.ExcelExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.vat.dao.OrdersMapper;
import com.imooc.vat.entity.OrderExportResult;
import com.imooc.vat.entity.Orders;
import com.imooc.vat.entity.OrdersExample;
import com.imooc.vat.entity.OrdersExample.Criteria;
import com.imooc.vat.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	static final Logger LOGGER= LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	private OrdersMapper orderMapper;
	@Resource
	private RunnableExecutorService runnableExecutorService;

	@Override
	public List<Orders> getAllOrders() {
		List<Orders> list=new ArrayList<Orders>();
		OrdersExample example=new OrdersExample();
		Criteria criteria=example.createCriteria();
		criteria.andShipmentsNoIsNotNull();
		list=orderMapper.selectByExample(example);
		return list;
	}
	@Override
	public List<OrderExportResult> getExportList() throws IllegalAccessException, InvocationTargetException {
		List<Orders> list=new ArrayList<Orders>();
		OrdersExample example=new OrdersExample();
		Criteria criteria=example.createCriteria();
		criteria.andShipmentsNoIsNotNull();
		list=orderMapper.selectByExample(example);
		
		List<OrderExportResult> listEx=new ArrayList<>();
		for(Orders item:list){
			OrderExportResult result=new OrderExportResult();
			//BeanUtils.copyProperties(result, item);
			result.setCreateTime(item.getCreateTime());
			result.setCustName(item.getCustName());
			result.setMobile(item.getMobile());
			result.setShipmentsNo(item.getShipmentsNo());
			result.setTotalPrice(item.getTotalPrice());
			listEx.add(result);
		}

		
		return listEx;
	}


	@Override
	public String ExportList(HttpServletRequest request) throws IllegalAccessException, InvocationTargetException {
		List<OrderExportResult> list=this.getExportList();
		String path="";
		//导出文件Excel
		ExcelExporter<OrderExportResult> exporter = new ExcelExporter<>();
		exporter.setTitle("InvoiceExcel");
		exporter.setEntityType(OrderExportResult.class);
		exporter.setEntities(list);
		try {
			path=exporter.exportExcel(request);
		} catch (Exception e) {
			LOGGER.error("happening exception ...",e);

			// 如果发生异常，可以使用线程重试工具，去做一些回滚，重试的操作
			// 这里仅仅做一个例子，尽管不是很恰当
			// 这里未来可能是一个外部第三方接口发生了异常，我们可以在这里尝试调用对方的冲正、撤销等接口
			runnableExecutorService.executeRetry((ThrowingConsumer)p->orderMapper.deleteByPrimaryKey(1));
		}
		return path;
	}


}
