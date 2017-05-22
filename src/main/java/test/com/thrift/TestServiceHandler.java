package test.com.thrift;

import org.apache.thrift.TException;

/**
 * Created by Administrator on 2017/5/22.
 */
public class TestServiceHandler  implements TestService.Iface {
    @Override
    public String getStruct(int num, String name) throws TException {

        return name + num;
    }
}
