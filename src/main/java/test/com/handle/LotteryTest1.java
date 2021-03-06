package test.com.handle;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LotteryTest1 {

public static void main(String[] args) {
	
	 final Map<String, Integer> awardStockMap = new ConcurrentHashMap<>(); 
     awardStockMap.put("5", 1200000);
     awardStockMap.put("10", 50000);
     awardStockMap.put("20", 30000);
     awardStockMap.put("T", 200);
     awardStockMap.put("D", 50);
     
	 final Map<String, Integer> awardStockMap1 = new ConcurrentHashMap<>(); 
	 awardStockMap1.put("T", 200);
	 awardStockMap1.put("D", 50);
     // 权重默认等于库存
     final Map<String, Integer> awardWeightMap = new ConcurrentHashMap<>(awardStockMap); 
     final Map<String, Integer> initAwardStockMap = new ConcurrentHashMap<>(awardStockMap1); 

     int drawNum = 50780; // 理论可以抽完所有奖品所需抽奖次数 = 奖品数×中奖概率导数 = 7617*100/15
     Map<String, Integer> dailyWinCountMap = new ConcurrentHashMap<>(); // 每天实际中奖计数

     for (int j = 0; j < 200000; j++) { // 模拟每次抽奖
         //确定是否中奖
         
         //中奖 确定是哪个奖品
         //排除掉库存为0的奖品
         Map<String, Integer> awardWeightHaveStockMap = awardWeightMap.entrySet().stream().filter(e->awardStockMap.get(e.getKey())>0).collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));
         if(awardWeightHaveStockMap.isEmpty()){ //奖池已为空
             System.out.printf("第%d次抽奖 奖品已被抽完%n",j);
             break;
         }
         int totalWeight = (int) awardWeightHaveStockMap.values().stream().collect(Collectors.summarizingInt(i->i)).getSum();
         int randNum = new Random().nextInt(totalWeight); 
         int prev=0;
         String choosedAward = null;
         for(Entry<String,Integer> e : awardWeightHaveStockMap.entrySet() ){
             if(randNum>=prev && randNum<prev+e.getValue()){
                 choosedAward = e.getKey(); //落入此区间 中奖
                 dailyWinCountMap.compute(choosedAward, (k,v)->v==null?1:v+1);
                 break;
             }
             prev = prev+e.getValue();
         }
         //减小库存
         awardStockMap.compute(choosedAward, (k,v)->v-1);
     }
     System.out.println("每日各奖品中奖计数: "); // 每日各奖品中奖计数
     dailyWinCountMap.entrySet().stream().sorted((e1,e2)->e2.getValue()-e1.getValue()).forEach(System.out::println);
     awardStockMap.forEach((k,v)->{if(v>0){
         System.out.printf("奖品：%s, 总库存： %d, 剩余库存： %d%n",k,initAwardStockMap.get(k),v);
     }});
}	
}
