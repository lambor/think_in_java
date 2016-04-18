package annotation.unit;

import org.junit.Test;

/**
 * Created by lambor on 16-4-14.
 */
public class AtUnitExample1 {
    public String methodOne() {
        return "this is method one.";
    }

    public int methodTwo() {
        System.out.println("this is method two.");
        return 2;
    }

    @Test boolean methodOneTest() {
        return methodOne().equals("this is method one.");
    }

    @Test boolean m2() {
        return methodTwo() == 2;
    }

    @Test private boolean m3() {
        return  true;
    }

    @Test boolean failureTest() {
        return false;
    }

    @Test boolean anotherDisappointment() {
        return false;
    }

    public static void main(String[] args) {
        OSExecute
    }
}
