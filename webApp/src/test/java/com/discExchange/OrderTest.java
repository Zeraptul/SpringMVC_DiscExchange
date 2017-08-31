package test.java.com.discExchange;

import com.discExchange.model.OrderEntity;
import com.discExchange.model.OrderLineEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;

// http://joel-costigliola.github.io/assertj/assertj-core-quick-start.html
import static org.assertj.core.api.Assertions.assertThat;  // main one
import static org.assertj.core.api.Assertions.fail; // use when writing exception tests
import static org.assertj.core.api.Assertions.filter; // for Iterable/Array assertions
import static org.assertj.core.api.Assertions.offset; // for floating number assertions
import static org.assertj.core.api.Assertions.anyOf; // use with Condition
import static org.assertj.core.api.Assertions.contentOf; // use with File assertions

@RunWith(JUnit4.class)
public class OrderTest {

    private EmbeddedDatabase db;

    //https://www.mkyong.com/spring/spring-embedded-database-examples/
    @Before
    public void setUp() {
        //db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:db/sql/create-db.sql")
                .addScript("classpath:db/sql/insert-data.sql")
                .build();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getAllOrders_manualHibernateFactoryAnnotated_success() {
        SessionFactory sf = getSessionFactory();

        Session session = sf.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from OrderEntity order by id");
        List<OrderEntity> orderEntities =  query.list();
        session.getTransaction().commit();

        assertThat(orderEntities.size()).isGreaterThanOrEqualTo(1);
    }

    private SessionFactory getSessionFactory() {
        return new Configuration()
                    .configure() // load options from classpath:hibernate.cfg.xml
                    .addAnnotatedClass(OrderEntity.class)
                    .addAnnotatedClass(OrderLineEntity.class)
                    .buildSessionFactory();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getAllOrders_manualHibernateFactoryHbm_success() {
        SessionFactory sf = getSessionFactory_hbm();

        Session session = sf.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from OrderEntity order by id");
        List<OrderEntity> orderEntities =  query.list();
        session.getTransaction().commit();

        assertThat(orderEntities.size()).isGreaterThanOrEqualTo(1);


        OrderEntity order = orderEntities.get(0);
        assertThat(order).isNotNull();

        //Note: иначе исключение - не может загрузить lazy load без привязки к сессии
        //https://stackoverflow.com/questions/11746499/solve-failed-to-lazily-initialize-a-collection-of-role-exception
        session.beginTransaction();
        assertThat(order.getOrderLines().size()).isGreaterThan(0);
        session.getTransaction().commit();
        //Assert.assertTrue(orderEntities.size() > 0);
    }

    private SessionFactory getSessionFactory_hbm() {
        return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addResource("orderEntity.hbm.xml")
                    .addResource("orderLineEntity.hbm.xml")

                    .buildSessionFactory();
    }



    @After
    public void tearDown(){
        db.shutdown();
    }
}


