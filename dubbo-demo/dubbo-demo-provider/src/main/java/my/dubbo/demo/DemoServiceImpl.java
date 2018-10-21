package my.dubbo.demo;

import my.dubbo.demo.DemoService;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        System.out.println("[Provider:" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name);
        return "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name;
    }

}
