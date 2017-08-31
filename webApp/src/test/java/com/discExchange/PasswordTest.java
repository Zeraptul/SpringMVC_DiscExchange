package test.java.com.discExchange;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.discExchange.utility.PasswordUtils.checkPassword;
import static com.discExchange.utility.PasswordUtils.hashPassword;

@RunWith(JUnit4.class)
public class PasswordTest {

    @Test
    public void compareHashes()
    {
        String test_passwd = "abcdefghijklmnopqrstuvwxyz";
        String test_hash = "$2a$06$.rCVZVOThsIa97pEDOxvGuRRgzG64bvtJ0938xuqzv18d3ZpQhstC";

        System.out.println("Test password: " + test_passwd);
        System.out.println("Test stored hash: " + test_hash);
        String computed_hash = hashPassword(test_passwd);
        System.out.println("Test computed hash: " + computed_hash);

        boolean compare_test_hash = checkPassword(test_passwd, test_hash);
        Assert.assertTrue(compare_test_hash);

        boolean compare_computed = checkPassword(test_passwd, computed_hash);
        Assert.assertTrue(compare_computed);
    }

    @Test
    //@Ignore()
    public void generatePasswordHash()
    {
        String myPass = "test";
        String resultHash = hashPassword(myPass);
        System.out.println("pass:" + myPass);
        System.out.println("hash:" + resultHash);
    }

    @Test
    public void third(){}

    @Test
    public void another(){}
}
