public class MyClassForTestTests {

    private MyClassForTest myClassForTest;

    @BeforeSuite
    public void beforeTest() {
        System.out.println("before test");
        myClassForTest = new MyClassForTest();
    }

    @AfterSuite
    public void afterTest() {
        System.out.println("after test");
        myClassForTest = null;
    }

    @Test(priority = 1)
    public void test1(){
        test(1, 1);
    }
    @Test(priority = 2)
    public void test2(){
        test(2, 2);
    }

    @Test(priority = 3)
    public void test3(){
        test(3, 3);
    }

    @Test(priority = 3)
    public void test3_1(){
        test(4, 3);
    }

    @Test(priority = 4)
    public void test4(){
        test(4, 4);
    }

    private void test(int expectedNumber, int number)  {
        System.out.printf("run test priority: %d\n\r", number);
        if(expectedNumber != myClassForTest.getNumber(number)) {
            throw new RuntimeException();
        } ;
    }


}
