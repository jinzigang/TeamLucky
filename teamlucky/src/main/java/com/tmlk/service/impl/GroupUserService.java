package com.tmlk.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tmlk.framework.mybatis.ICondition;
import com.tmlk.framework.mybatis.Order;
import com.tmlk.framework.util.Pagination;
import com.tmlk.framework.util.Constants;

import com.tmlk.dao.IGroupUserDao;
import com.tmlk.po.GroupUserExt;
import com.tmlk.service.IGroupUserService;

public class GroupUserService implements IGroupUserService{
	
	private IGroupUserDao groupUserDao;
	
	public IGroupUserDao getGroupUserDao() {
		return groupUserDao;
	}

	public void setGroupUserDao(IGroupUserDao groupUserDao) {
		this.groupUserDao = groupUserDao;
	}

	@Override
	public GroupUserExt create(GroupUserExt groupUser) {
		int res = groupUserDao.create(groupUser);
		if(res == 1)
			return groupUser;
		
		return null;
	}

	@Override
	public void update(GroupUserExt groupUser) {
		groupUserDao.update(groupUser);
	}
	
	@Override
	public GroupUserExt load(Long id) {
		return groupUserDao.load(id);
	}

	@Override
	public void delete(Long  id) {
		groupUserDao.delete(id);
	}
	
	
	@Override
	public List<GroupUserExt> criteriaQuery(List<ICondition> conditions) {
		return this.criteriaQuery(conditions, null, null);
	}
	
	@Override
	public List<GroupUserExt> criteriaQuery(List<ICondition> conditions, List<Order> orders) {
		return this.criteriaQuery(conditions, orders, null);
	}
	
	@Override
	public List<GroupUserExt> criteriaQuery(List<ICondition> conditions, List<Order> orders, Pagination pp) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> mqlList = new ArrayList<String>();
		if(conditions != null){
			for(ICondition condition : conditions){
				mqlList.add(condition.generateExpression(params));
			}
		}
		
		List<String> mortList = new ArrayList<String>();
		if(orders != null){
			for(Order order : orders){
				mortList.add(order.toSqlString());
			}
		}
		
		return groupUserDao.criteriaQuery(mqlList, mortList, params, pp);
	}

	@Override
	public int count(List<ICondition> conditions) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> mqlList = new ArrayList<String>();
		if(conditions != null){
			for(ICondition condition : conditions){
				mqlList.add(condition.generateExpression(params));
			}
		}
		
		return groupUserDao.criteriaCount(mqlList,params);
	}
	
}
