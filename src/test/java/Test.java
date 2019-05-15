import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import db.middleware.EntityBuilder;
import db.middleware.HibernateUtil;
import game.user.login.entity.UserEnt;
import game.user.login.model.Person;
import net.utils.ProtoStuffUtil;
import utils.TimeUtil;

/**
 * @author : ddv
 * @since : 2019/4/30 下午2:28
 */

public class Test {

    @org.junit.Test
    public void run() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        UserEnt userEnt = new UserEnt();
        userEnt.setAccountId("accountId_1");
        userEnt.setUsername("username_1");
        userEnt.setPassword("password_1");

        Person person = new Person();
        person.setIdCard("341226199704111054");
        person.setName("张杰");

        userEnt.setPerson(person);
        userEnt.setPersonData(ProtoStuffUtil.serialize(person));
        System.out.println(userEnt.getPersonData().length);

        session.save(userEnt);

        userEnt.setPassword("11");
        session.getTransaction().commit();

        run1();
    }

    @org.junit.Test
    public void run1() {
        HibernateUtil util = new HibernateUtil();
        UserEnt entity = (UserEnt)util.loadOrCreate(UserEnt.class, "accountId_2", new EntityBuilder<String, UserEnt>() {
            @Override
            public UserEnt newInstance(String accountId) {
                return UserEnt.valueOf(accountId);
            }
        });
        System.out.println(entity);
        Person person = ProtoStuffUtil.deserialize(entity.getPersonData(), Person.class);
        System.out.println(person);
    }

    @org.junit.Test
    public void run3() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(s);
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(TimeUtil.now());
    }
}
