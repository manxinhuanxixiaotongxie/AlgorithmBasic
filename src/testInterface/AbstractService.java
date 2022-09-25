package testInterface;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-09-19 9:29
 */
public abstract class AbstractService implements GateWayService{

    @Override
    public void dectptyParam() {
        testAbstract();
        System.out.println("抽象类实现");
    }

    protected void testAbstract() {
        System.out.println("抽象类方法");
    }
}
