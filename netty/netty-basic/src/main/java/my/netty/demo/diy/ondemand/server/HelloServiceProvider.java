package my.netty.demo.diy.ondemand.server;

public class HelloServiceProvider implements ServiceProvider {

    public String provide() {
        return "hello, world";
    }

}
