package test.java.com.discExchange.dao;

import com.discExchange.dao.DiscDAO;
import com.discExchange.model.DiscEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adMin on 13.08.2017.
 */
//@PropertySource("classpath:jdbc-test.properties ")
@ContextConfiguration("classpath:spring/servlet-context-test_2.xml")
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class DiscDaoTest {

    @Autowired
    @Qualifier("discDAO")
    DiscDAO discDAO;

    @Transactional
    @Test
    public void listDiscs_HasAny_ShouldSuccess() {
        List<DiscEntity> discs = discDAO.listDiscs();
        Assert.assertEquals(3, discs.size());
        DiscEntity firstDisc = discs.get(0);

        Assert.assertEquals(1, firstDisc.getId());
    }

    @Transactional
    @Test
    public void addDisc_New_ShouldSuccess() {
        DiscEntity newDisc = new DiscEntity();
        newDisc.setName("1");
        newDisc.setAuthor("A");

        DiscEntity addedDisc = discDAO.addDisc(newDisc);
        Assert.assertNotNull(addedDisc);

        Assert.assertEquals(addedDisc.getName(), newDisc.getName());
        Assert.assertEquals(addedDisc.getAuthor(), newDisc.getAuthor());
    }
}
