package test.com.handle;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LotteryTest {

	
	public static void main(String[] args) {

		    int userNum = 50000; // 日活用户数
		    int drawNum = userNum * 4;
			final Map<String, Integer> awardStock = new ConcurrentHashMap<>(); 
			awardStock.put("D", 50); //0--5000
			awardStock.put("T", 200); //5000--6000
			
			
			final Map<String, Integer> awardStockMap = new ConcurrentHashMap<>(); // 奖品 <--> 奖品库存
			awardStockMap.put("5", 120000); //0--5000
			awardStockMap.put("10", 50000); //5000--6000
			awardStockMap.put("20", 30000);  //6000-6500
			awardStockMap.put("T", 200);  //6500--6600
			awardStockMap.put("D", 50); //6600--6601
			//awardStockMap.put("5", 5000); //6601--11101
			//权重默认等于库存      
			final Map<String, Integer> awardWeightMap = new ConcurrentHashMap<>(awardStockMap); // 奖品 <--> 奖品权重
			
			//排除掉库存为0的奖品
			Map<String, Integer> awardWeightHaveStockMap = awardWeightMap.entrySet().stream().filter(e->awardStockMap.get(e.getKey())>0).collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));
			int totalWeight = (int) awardWeightHaveStockMap.values().stream().collect(Collectors.summarizingInt(j->j)).getSum();
			System.out.println(totalWeight);
		    for(int i = 0; i<drawNum;i++){
				
		    	
		    int randNum = new Random().nextInt(totalWeight); //生成一个随机数
		    int prev = 0;
		    String choosedAward = null;
		    // 按照权重计算中奖区间
		    for(Entry<String,Integer> e : awardWeightHaveStockMap.entrySet() ){
		        if(randNum>=prev && randNum<prev+e.getValue()){
		            choosedAward = e.getKey(); //落入该奖品区间
		          
		            if("D".equals(choosedAward)||"T".equals(choosedAward)){
		            	int total = awardStock.get(choosedAward);
		            	System.out.println(choosedAward+"剩余数量:"+total);
		            	if(total>0){
		            		awardStock.put(choosedAward, total-1);
		            	}else if(total==0){
		            		//选取
		            		if("T".equals(choosedAward)){
		            			choosedAward="10";
		            		}else{
		            			choosedAward="20";
		            		}
		            	}

		            }
		            System.out.println("random's value:"+randNum+" 您中奖了..."+choosedAward+",第多少次抽奖:"+i);
		            break;
		        }
		        prev = prev+e.getValue();
		    }
		 
	}
		  	
    }
	
	
	
}
