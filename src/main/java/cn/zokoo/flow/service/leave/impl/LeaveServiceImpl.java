package cn.zokoo.flow.service.leave.impl;

import cn.zokoo.flow.mapper.leave.LeaveMapper;
import cn.zokoo.flow.service.leave.LeaveService;
import cn.zokoo.flow.entity.Leave;
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
 * @date : 2019-11-20 19:06:48
 * description : 请假单Service实现
 */
@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveMapper leaveMapper;

	@Override
	public Leave getLeaveById(String id) throws Exception {
		return StringUtils.isNotBlank(id) ? this.leaveMapper.getLeaveById(id.trim()) : null;
	}

	@Override
	public List<Leave> getLeaveByIds(String ids) throws Exception {
		ids = StringTools.converString(ids);
		return StringUtils.isNotBlank(ids) ? this.leaveMapper.getLeaveByIds(ids) : null;
	}

	@Override
	public List<Leave> getLeaveByIdsList(List<String> ids) throws Exception {
		return CollectionUtils.isNotEmpty(ids) ? this.leaveMapper.getLeaveByIdsList(ids) : null;
	}

	@Override
	public List<Leave> getAll(Leave Leave) throws Exception {
		return null != Leave ? this.leaveMapper.getAll(Leave) : null;
	}

	@Override
	public PagerModel<Leave> getPagerModelByQuery(Leave Leave, Query query)
			throws Exception {
		PageHelper.startPage(query.getPageNum(),query.getPageSize());
		Page<Leave> page = this.leaveMapper.getPagerModelByQuery(Leave);
		return new PagerModel<>(page);
	}

	@Override
	public int getByPageCount(Leave Leave)throws Exception {
		return (null != Leave) ? this.leaveMapper.getByPageCount(Leave) : 0;
	}

	@Override
	public Leave insertLeave(Leave Leave) throws Exception {
		if (StringUtils.isBlank(Leave.getId())){
			Leave.setId(UUID.randomUUID().toString().replace("-",""));
		}
		Leave.setCreateTime(new Date());
		Leave.setUpdateTime(new Date());
		this.leaveMapper.insertLeave(Leave);
		return Leave;
	}

	@Override
	public void insertLeaveBatch(List<Leave> Leaves) throws Exception {
		for (cn.zokoo.flow.entity.Leave Leave : Leaves) {
			if (StringUtils.isBlank(Leave.getId())){
				Leave.setId(UUID.randomUUID().toString().replace("-",""));
			}
			Leave.setCreateTime(new Date());
			Leave.setUpdateTime(new Date());
		}
		this.leaveMapper.insertLeaveBatch(Leaves);
	}

	@Override
	public void delLeaveById(String id) throws Exception {
		if (StringUtils.isNotBlank(id)) {
			this.leaveMapper.delLeaveById(id.trim());
		}
	}

	@Override
	public void delLeaveByIds(String ids) throws Exception {
		ids = StringTools.converString(ids);
		if(StringUtils.isNotBlank(ids)){
			this.leaveMapper.delLeaveByIds(ids);
		}
	}

	@Override
	public void delLeaveByIdsList(List<String> ids) throws Exception {
		if(CollectionUtils.isNotEmpty(ids)){
			this.leaveMapper.delLeaveByIdsList(ids);
		}
	}

	@Override
	public int updateLeave(Leave Leave) throws Exception {
		Leave.setUpdateTime(new Date());
		return this.leaveMapper.updateLeave(Leave);
	}

	@Override
	public int updateLeaveByIds(String ids, Leave Leave) throws Exception {
		Leave.setUpdateTime(new Date());
		return this.leaveMapper.updateLeaveByIds(StringTools.converString(ids), Leave);
	}

	@Override
	public int updateLeaveByIdsList(List<String> ids, Leave Leave) throws Exception {
		Leave.setUpdateTime(new Date());
		return this.leaveMapper.updateLeaveByIdsList(ids, Leave);
	}

	@Override
	public int updateLeaveList(List<Leave> Leaves) throws Exception {
		return this.leaveMapper.updateLeaveList(Leaves);
	}

	//------------api------------

}

