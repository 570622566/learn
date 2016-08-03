package test.com.enums;

public enum  KjtOrderStatus {
	
	 	SYSTEM_CANCEL("-4","系统作废"),
	    CANCEL("-1","作废"),
	    FOR_CHECK("0","待审核"),
	    FOR_OUTBOUND("1","待出库"),
	    HAVE_OUTBOUND_TO_DECLARE("4","已出库待申报"),
	    HAVE_DECLARE_TO_CLEARANCE("41","已申报待通关"),
	    HAVE_CLEARANCE_TO_CUSTOMER("45"," 已通关发往顾客"),
	    ORDER_FINISHED("5","订单完成"),
	    DECLARE_INVALID_FAILURE_ORDER("6","申报失败订单作废"),
	    CLEARANCE_FAILED_ORDER_INVALID("65","通关失败订单作废"),
	    ORDER_REJECTED("7","订单拒收");
	
		private String code;
	    private String desc;
	    private KjtOrderStatus(String code, String desc){
	        this.code=code;
	        this.desc=desc;
	    }
	    public String getCode() {
	        return code;
	    }

	    public String getDesc() {
	        return desc;
	    }
	    
	    public static KjtOrderStatus valueOfCode(String code){
	        for(KjtOrderStatus value:KjtOrderStatus.values()){
	            if(value.getCode().equals(code)){
	                return value;
	            }
	        }
	        throw new RuntimeException("无匹配的OrderStatus");
	    }
	    

}
