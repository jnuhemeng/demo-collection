package my.netty.demo.diy.ondemand.server;

import java.util.Date;

public class DateServiceProvider implements ServiceProvider {

    public String provide() {
        return new Date().toString();
    }

}
