import interfaces.SomeInterface;
import interfaces.SomeOtherInterface;
import implementations.SomeImpl;
import implementations.OtherImpl;
import implementations.SODoer;

public class Main {
    public static void main(String[] args) {
        // Тестируем реализации вручную
        SomeInterface impl1 = new SomeImpl();
        SomeInterface impl2 = new OtherImpl();
        SomeOtherInterface soImpl = new SODoer();

        System.out.println("Testing implementations:");
        impl1.doSomething();  // A
        impl2.doSomething();  // B
        soImpl.doSomeOther(); // C

        // Первая версия SomeBean без DI
        SomeBean bean = new SomeBean();
        System.out.println("Warning: SomeBean not properly initialized yet");
    }
}