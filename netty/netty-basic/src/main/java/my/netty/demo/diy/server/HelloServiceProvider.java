package my.netty.demo.diy.server;

public class HelloServiceProvider implements ServiceProvider {

    public String provide() {
        return "hello, world";
    }

}
