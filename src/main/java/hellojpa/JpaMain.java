package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        findMembers();

    }

    public static void createMember(String name){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setId(3L);
            member.setName(name);
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
        tx.begin();
        try {
            Member member = em.find(Member.class, 1L);
            System.out.println("found id   : " + member.getId());
            System.out.println("found name : " + member.getName());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    public static void updateName() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = em.find(Member.class, 1L);
            System.out.println("before id   : " + member.getId());
            System.out.println("before name : " + member.getName());

            member.setName(member.getName() + "B");

            Member member2 = em.find(Member.class, 1L);
            System.out.println("after id   : " + member2.getId());
            System.out.println("after name : " + member2.getName());

            tx.commit(); // 커밋 직전에 update 쿼리가 만들어져서 알아서 변경사항을 반영해
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    public static void findMembers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            List<Member> resultList = em.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member item : resultList) {
                System.out.println("name : " + item.getName());
            }

        } catch (Exception e) {

        }finally {
            em.close();
        }
        emf.close();
    }
}
