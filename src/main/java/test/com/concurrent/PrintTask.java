package test.com.concurrent;

import java.util.concurrent.RecursiveAction;

//RecursiveAction为ForkJoinTask的抽象子类，没有返回值的任务  
public class PrintTask extends RecursiveAction {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 3925031773902591257L;

	// 每个"小任务"最多只打印50个数  
    private static final int MAX = 50;  
	
    private int start;  
    private int end;  
    
    PrintTask(int start, int end) {  
        this.start = start;  
        this.end = end;  
    }  
    
	@Override
	protected void compute() {
		// TODO Auto-generated method stub
		//当end-start的值小于max时候,开始打印
		
		if((end-start)<MAX){
			for(int i = start;i<end;i++){
				 System.out.println(Thread.currentThread().getName() + "的i值:" + i);  
			}
		}else{
			 // 将大任务分解成两个小任务  
            int middle = (start + end) / 2;  
            PrintTask left = new PrintTask(start, middle);  
            PrintTask right = new PrintTask(middle, end);  
            // 并行执行两个小任务  
            left.fork();  
            right.fork();  
		}
	}

}
