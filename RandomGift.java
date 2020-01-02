package com.sino.kuandai.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;


/**
   * 使用示例
 * 			RandomGift<Integer> t = new RandomGift<>();
 *			t.addGift(0, 1)//特等奖
 *				.addGift(1, 50)
 *				.initRandomGiftPool();
 *				
 * 			System.out.println("开始抽奖"+t.randomGift());
 * 
 * 
 * @author AMD
 *
 * @param <T>
 */
public class RandomGift <T>{
	
	/**
	  * 设置奖品配置
	  * 
	  * 	key:randomGift()返回的奖品,key也可以根据其他需求返回不同类型
	  * 	value: 中将的比率，分母是所有的中将之和
	 */
	Map<T,Integer> giftMap= new HashMap<>(); //
	List<T> giftPool = new ArrayList<>();
	
	int maxIndex = 0;
	Random random = new Random(); 
	
	
	public RandomGift<T> addGift(T key,int value){
		this.giftMap.put(key, value);
		return this;
	}
	
	/**
	 * 初始化奖池
	 * @return
	 */
	public RandomGift<T> initRandomGiftPool() {
		
		//根据配额比初始化奖品池奖品
		Iterator<Entry<T, Integer>> iter = giftMap.entrySet().iterator();
		Entry<T, Integer> ent = null;
		while(iter.hasNext()) {
			ent = iter.next();
			T key = ent.getKey();
			int val = ent.getValue();
			while(val-->0) {
				giftPool.add(key);
			}
		}
		maxIndex = giftPool.size();
		
		return this;
	};
	

	
	/**
	 *    抽奖
	 * @return
	 */
	public T randomGift() {
		int randomIndex = random.nextInt(maxIndex);
		maxIndex=maxIndex-1;
//		System.out.println("最大索引："+maxIndex+" 随机索引："+randomIndex);
		
		T tmp = giftPool.get(randomIndex);
		T last = giftPool.get(maxIndex);
		
		giftPool.set(maxIndex, tmp);
		giftPool.set(randomIndex,last);
		
		if(maxIndex==0) {
			maxIndex = giftPool.size();
		}
		
		return tmp;
	}
	
	
	
}
