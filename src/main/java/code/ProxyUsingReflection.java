package code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUsingReflection {

	public static void main(String[] args) {
		Task task = (Task)ProxyFactory.newInstance(new TaskImpl());
		task.setData("Test");
		System.out.println(task.getCalData(5));
	}
}

interface Task {
	public void setData(String data);
	public int getCalData(int x);
}

class TaskImpl implements Task {
	@Override
	public void setData(String data) {
		System.out.println(data+ " Data is saved");
	}
	@Override
	public int getCalData(int x) {
		return x*10;
	}
}

class MyInvocationHandler  implements InvocationHandler{
    private Object obj;
    public  MyInvocationHandler(Object obj) {
        this.obj = obj;
    }
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        try{
        	if(m.getName().indexOf("get")>-1){
	           System.out.println("...get Method Executing...");
        	}else{
        		System.out.println("...set Method Executing...");
        	}
        	result = m.invoke(obj, args);
	    } catch (InvocationTargetException e) {
	        throw e;
	    } catch (Exception e) {
	        throw e;
	    }
        return result;
    }
}

class ProxyFactory {
	public static Object newInstance(Object ob) {
		return Proxy.newProxyInstance(ob.getClass().getClassLoader(),
				new Class<?>[] { Task.class }, new MyInvocationHandler(ob));
	}
}



