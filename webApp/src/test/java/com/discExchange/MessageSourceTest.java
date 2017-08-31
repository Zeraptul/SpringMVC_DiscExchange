package test.java.com.discExchange;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Locale;


@ContextConfiguration("classpath:spring/servlet-context-test_2.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    public void getMessage_noOneFound_success(){
        String defaultValue = "empty";
        String message = messageSource.getMessage("noOne", null, defaultValue, Locale.ENGLISH);
        Assert.assertEquals(defaultValue, message);
    }

    @Test
    public void getMessage_found_success(){
        String defaultValue = "empty";
        String message = messageSource.getMessage("first", null, defaultValue, Locale.ENGLISH);
        Assert.assertEquals("english", message);
    }

    @Test
    public void getMessage_foundTwoLocale_success(){
        String defaultValue = "empty";
        String message = messageSource.getMessage("first", null, defaultValue, Locale.US);
        Assert.assertEquals("USA", message);
    }

    @Test
    public void getMessage_foundDefaultLocale_success(){
        String defaultValue = "empty";
        String message = messageSource.getMessage("first", null, defaultValue, Locale.ROOT);
        Assert.assertEquals("defaultLanguage", message);
    }

    @Test
    public void getMessage_foundDefaultLocaleWithArgument_success(){
        String defaultValue = "empty";
        String message = messageSource.getMessage("argument.required", new Object[] {"some"}, defaultValue, Locale.ROOT);
        String expected = "The some argument is required.";
        Assert.assertEquals(expected, message);
    }

}
