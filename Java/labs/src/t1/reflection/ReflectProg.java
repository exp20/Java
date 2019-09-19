package t1.reflection;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectProg {
    /*В параметрах командной строки приложения указывается имя класса, имя вызываемого метода класса (метод статический), и числовые параметры для этого метода (тип double).
На экран должен быть выведен результат выполнения этого метода.
     */

    public static double summ ( double a, double b){
        return a + b;
    }

    public static void main(String... args) {
        String class_name = args[0], method_name = args[1];
        double a = Double.parseDouble(args[2]), b = Double.parseDouble(args[3]);

        try {
            Class some_class = Class.forName(class_name);
            Method[] all_mehtods = some_class.getDeclaredMethods();
            for(Method m : all_mehtods){
                System.out.println(m.getName());
            }
                //Method some_method = all_mehtods[0];
                Method some_method = some_class.getMethod(method_name, new Class[] {double.class, double.class}); //указывается метод
            // с конкретной сигнатурой: summ(double a, double b);
                System.out.println(some_method.invoke(null,a,b)); //Перный параметр метода неявный остальные явные. У статических методов не учитывается неявный параметр

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}

