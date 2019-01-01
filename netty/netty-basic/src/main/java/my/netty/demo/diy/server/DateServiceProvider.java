package my.netty.demo.diy.server;

import java.util.Date;

public class DateServiceProvider implements ServiceProvider {

    public String provide() {
        return new Date().toString();
    }

}
