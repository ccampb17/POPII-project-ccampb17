package fraction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class FractionTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Fraction.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @org.junit.Test
    public void add() {


    }

    @org.junit.Test
    public void subtract() {
    }

    @org.junit.Test
    public void multiply() {
    }

    @org.junit.Test
    public void divide() {
    }

    @org.junit.Test
    public void abs() {
    }

    @org.junit.Test
    public void negate() {
    }

    @org.junit.Test
    public void inverse() {
    }

    @org.junit.Test
    public void equals1() {
    }

    @org.junit.Test
    public void compareTo() {
    }

    @org.junit.Test
    public void toString1() {
    }
}
