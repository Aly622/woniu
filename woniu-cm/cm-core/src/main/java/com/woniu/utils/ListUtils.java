package com.woniu.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtils {

	/**
	 * 按照limit分割soure为子列表集合
	 * 
	 * @param source
	 * @param limit
	 * @return
	 */
	public static <T> List<List<T>> splitList(List<T> source, int limit) {
		// 分割次数
		int counts =getCounts(source.size(),limit);
		List<List<T>> splitResults = new ArrayList<List<T>>();
		Stream.iterate(0, n -> n + 1).limit(counts).forEach(i -> {
			splitResults.add(source.stream().skip(i * limit).limit(limit).collect(Collectors.toList()));
		});
		return splitResults;
	}

	
	public static int getCounts(long size, int limit) {
		int counts = Long.valueOf(size / limit).intValue();
		return (size % limit == 0) ? counts : counts + 1;
	}

}
