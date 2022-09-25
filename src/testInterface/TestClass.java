package testInterface;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-09-19 9:31
 */
public class TestClass {

    public static void main(String[] args) {
        GateWayService gateWayService = new ActQueryService();
        gateWayService.dectptyParam();
    }
}
