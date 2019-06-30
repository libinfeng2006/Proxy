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
    		// 2. Ȼ������Ҫʹ��Hello��ʱ��ͨ��JDK��̬�����ȡHello�Ĵ������
    		Hello hello = (Hello)Proxy.newProxyInstance(
    		    Hello.class.getClassLoader(), // 1. �������
    		    new Class<?>[] {Hello.class}, // 2. ������Ҫʵ�ֵĽӿڣ������ж��
    		    new LogInvocationHandler(new Hello() {
					@Override
					public void sayHello(String message) {
						 System.out.println(message);
						
					}
				}));// 3. �������õ�ʵ�ʴ�����
    		hello.sayHello("I love you!");
		}
    }
}
