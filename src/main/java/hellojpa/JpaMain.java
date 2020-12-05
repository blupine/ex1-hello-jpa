package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        updateName();
    }

    public static void createMember(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("helloA");
            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void findMember(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            Member member = em.find(Member.class, 1L);
            System.out.println("found id   : " + member.getId());
            System.out.println("found name : " + member.getName());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }
        emf.close();
    }

    public static void updateName() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            Member member = em.find(Member.class, 1L);
            System.out.println("before id   : " + member.getId());
            System.out.println("before name : " + member.getName());

            member.setName(member.getName() + "B");

            Member member2 = em.find(Member.class, 1L);
            System.out.println("after id   : " + member2.getId());
            System.out.println("after name : " + member2.getName());

            tx.commit(); // 커밋 직전에 update 쿼리가 만들어져서 알아서 변경사항을 반영해줌
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }
        emf.close();
    }
}
