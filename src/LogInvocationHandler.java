import java.lang.reflect.*;
import java.util.Arrays;

public class LogInvocationHandler implements InvocationHandler{
    private Hello hello;
    public LogInvocationHandler(Hello hello) {
        this.hello = hello;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("sayHello".equals(method.getName())) {
        	System.out.println("You said: " + Arrays.toString(args));
        }
        return method.invoke(hello, args);
    }
    
    interface Hello {
    	void sayHello(String message);
    }
    
    static class Test {
    	public static void main(String[] args) {
    		// 2. 然后在需要使用Hello的时候，通过JDK动态代理获取Hello的代理对象。
    		Hello hello = (Hello)Proxy.newProxyInstance(
    		    Hello.class.getClassLoader(), // 1. 类加载器
    		    new Class<?>[] {Hello.class}, // 2. 代理需要实现的接口，可以有多个
    		    new LogInvocationHandler(new Hello() {
					@Override
					public void sayHello(String message) {
						 System.out.println(message);
						
					}
				}));// 3. 方法调用的实际处理者
    		hello.sayHello("I love you!");
		}
    }
}
