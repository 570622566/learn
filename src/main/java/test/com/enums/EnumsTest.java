package test.com.enums;

public class EnumsTest {
	public static void main(String[] args) {
        KjtOrderStatus orderStatus = KjtOrderStatus.CANCEL;
        System.out.println(orderStatus);
        System.out.println(orderStatus.getCode());

        switch (orderStatus){
            case CANCEL:
                System.out.println("测试--"+orderStatus.getDesc());
                break;
                default:
                    System.out.println("测试---"+orderStatus.getDesc());
        }
        
        
        for (ReportStatusEnum reportStatusEnum : ReportStatusEnum.values()){
        	System.out.println(reportStatusEnum);
        	
        	System.out.println(reportStatusEnum.getCode());
        	System.out.println(reportStatusEnum.getName());
        }
        
        
        System.out.println("getByCode:"+ReportStatusEnum.getByCode(1)+"  "+ReportStatusEnum.getByCode(1).getCode()+" "+ReportStatusEnum.getByCode(1).getName());
	
	}
}
