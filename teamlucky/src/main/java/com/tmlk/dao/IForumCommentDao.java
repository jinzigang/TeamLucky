package com.tmlk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.tmlk.framework.util.Pagination;
import com.tmlk.po.ForumCommentExt;

@Component("forumCommentDao")
public interface IForumCommentDao {

	int create(ForumCommentExt forumComment);
	
	void update(ForumCommentExt forumComment);
	
	ForumCommentExt load(@Param("id") Long  id);
	
	void delete(@Param("id") Long  id);

	List<ForumCommentExt> criteriaQuery(@Param("mqlList") List<String> mqlList, @Param("mortList") List<String> mortList, @Param("p") Map<String, Object> p, @Param("pp") Pagination pp);
	
	int criteriaCount(@Param("mqlList") List<String> mqlList, @Param("p") Map<String, Object> p);

}
