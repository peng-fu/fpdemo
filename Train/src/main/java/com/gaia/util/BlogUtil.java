package com.gaia.util;

import java.util.List;

import com.gaia.pojo.BlogData;

public class BlogUtil {

	public static PageBean<BlogData> paging(Integer page, Integer size, List<BlogData> list){
		PageBean<BlogData> blogList = new PageBean<BlogData>();
		try {
			int totalSize = list.size();
			int totalPage = (int) ((totalSize%size==0)?(totalSize/size):(totalSize/size+1));
			page = page < 1 ? 1 : page;
			page = page > totalPage ? totalPage : page;
			int i = (page - 1) * size;
			i = i < 0 ? 0 : i;
			int j = page * size;
			j = j > totalSize ? totalSize : j;
			blogList.setDatas(list.subList(i, j));
			blogList.setIndexPage(page);
			blogList.setPageSize(size);
			blogList.setTotalSize((long) totalSize);
			return blogList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
