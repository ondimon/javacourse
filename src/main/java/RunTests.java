import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class RunTests {
    public static void start(Class testClass) {
        Method[] methods = testClass.getMethods();
        ArrayList<Method> testMethods = new ArrayList<>();
        Method beforeTest = null;
        Method afterTest = null;
        for (Method method : methods) {
            Test testAnnotation = method.getAnnotation(Test.class);
            if(testAnnotation != null) {
                testMethods.add(method);
            }

            if(method.getAnnotation(AfterSuite.class) != null) {
                if(afterTest == null) {
                    afterTest = method;
                }else{
                    throw new RuntimeException("after suite must be present in a single copy");
                }
            }

            if(method.getAnnotation(BeforeSuite.class) != null) {
                if(beforeTest == null) {
                    beforeTest = method;
                }else{
                    throw new RuntimeException("before suite must be present in a single copy");
                }
            }
        }

        Collections.sort(testMethods, (m1, m2) -> {
            int p1 = m1.getAnnotation(Test.class).priority();
            int p2 = m2.getAnnotation(Test.class).priority();
            if(p1 == p2) {
                return 0;
            }else if (p1 > p2){
                return 1;
            }else{
                return -1;
            }
        });

        int pass = 0;
        int fail = 0;

        try {
            Constructor constructor  = testClass.getConstructor(null);
            Object testInstance = constructor.newInstance(null);
            if(beforeTest != null) {
                beforeTest.invoke(testInstance);
            }

            for(Method testMethod : testMethods) {

                try {
                    testMethod.invoke(testInstance);
                    pass ++;
                }catch (Exception e){
                    fail ++;
                    e.printStackTrace();
                }
            }

            if(afterTest != null) {
                afterTest.invoke(testInstance);
            }

        } catch (NoSuchMethodException | IllegalAccessException| InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.printf("All test: %d\n- pass: %d\n- fails: %d", testMethods.size(), pass, fail);

    }


}
