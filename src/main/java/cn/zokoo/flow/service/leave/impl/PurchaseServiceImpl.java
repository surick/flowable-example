package cn.zokoo.flow.service.leave.impl;

import cn.zokoo.flow.mapper.leave.PurchaseMapper;
import cn.zokoo.flow.service.flowable.FlowableProcessInstanceService;
import cn.zokoo.flow.service.leave.PurchaseService;
import cn.zokoo.flow.entity.Purchase;
import com.dragon.tools.pager.PagerModel;
import com.dragon.tools.pager.Query;
import com.dragon.tools.utils.StringTools;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @author : admin
 * @date : 2019-12-09 10:00:54
 * description : 采购Service实现
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseMapper purchaseMapper;
	@Autowired
	private FlowableProcessInstanceService flowableProcessInstanceService;

	@Override
	public Purchase getPurchaseById(String id) throws Exception {
		return StringUtils.isNotBlank(id) ? this.purchaseMapper.getPurchaseById(id.trim()) : null;
	}

	@Override
	public List<Purchase> getPurchaseByIds(String ids) throws Exception {
		ids = StringTools.converString(ids);
		return StringUtils.isNotBlank(ids) ? this.purchaseMapper.getPurchaseByIds(ids) : null;
	}

	@Override
	public List<Purchase> getPurchaseByIdsList(List<String> ids) throws Exception {
		return CollectionUtils.isNotEmpty(ids) ? this.purchaseMapper.getPurchaseByIdsList(ids) : null;
	}

	@Override
	public List<Purchase> getAll(Purchase purchase) throws Exception {
		return null != purchase ? this.purchaseMapper.getAll(purchase) : null;
	}

	@Override
	public PagerModel<Purchase> getPagerModelByQuery(Purchase purchase, Query query)
			throws Exception {
		PageHelper.startPage(query.getPageIndex(),query.getPageSize());
		Page<Purchase> page = this.purchaseMapper.getPagerModelByQuery(purchase);
		return new PagerModel<Purchase>(page);
	}

	@Override
	public int getByPageCount(Purchase purchase)throws Exception {
		return (null != purchase) ? this.purchaseMapper.getByPageCount(purchase) : 0;
	}

	@Override
	public void insertPurchase(Purchase purchase) throws Exception {
		if (StringUtils.isBlank(purchase.getId())){
			purchase.setId(UUID.randomUUID().toString().replace("-",""));
		}
		purchase.setCreateTime(new Date());
		purchase.setUpdateTime(new Date());
		this.purchaseMapper.insertPurchase(purchase);
	}

	@Override
	public void insertPurchaseBatch(List<Purchase> purchases) throws Exception {
		for (Purchase purchase : purchases) {
			if (StringUtils.isBlank(purchase.getId())){
				purchase.setId(UUID.randomUUID().toString().replace("-",""));
			}
			purchase.setCreateTime(new Date());
			purchase.setUpdateTime(new Date());
		}
		this.purchaseMapper.insertPurchaseBatch(purchases);
	}

	@Override
	public void delPurchaseById(String id) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			Purchase purchase = purchaseMapper.getPurchaseById(id);
			this.purchaseMapper.delPurchaseById(id.trim());
//			flowableProcessInstanceService.deleteProcessInstanceById(purchase.getProcessInstanceId());
		}
	}

	@Override
	public void delPurchaseByIds(String ids) throws Exception {
		ids = StringTools.converString(ids);
		if(StringUtils.isNotBlank(ids)){
			this.purchaseMapper.delPurchaseByIds(ids);
		}
	}

	@Override
	public void delPurchaseByIdsList(List<String> ids) throws Exception {
		if(CollectionUtils.isNotEmpty(ids)){
			this.purchaseMapper.delPurchaseByIdsList(ids);
		}
	}

	@Override
	public int updatePurchase(Purchase purchase) throws Exception {
		purchase.setUpdateTime(new Date());
		return this.purchaseMapper.updatePurchase(purchase);
	}

	@Override
	public int updatePurchaseByIds(String ids, Purchase purchase) throws Exception {
		purchase.setUpdateTime(new Date());
		return this.purchaseMapper.updatePurchaseByIds(StringTools.converString(ids), purchase);
	}

	@Override
	public int updatePurchaseByIdsList(List<String> ids, Purchase purchase) throws Exception {
		purchase.setUpdateTime(new Date());
		return this.purchaseMapper.updatePurchaseByIdsList(ids, purchase);
	}

	@Override
	public int updatePurchaseList(List<Purchase> purchases) throws Exception {
		return this.purchaseMapper.updatePurchaseList(purchases);
	}

	//------------api------------

}

