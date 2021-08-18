package algopa.study.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();
        System.out.println(result);
        return result.stream().findAny();
    }

    @Override
    public Member edit(Long id, String tier) {
        Optional<Member> member = findById(id);
        if(member.isPresent()){
            member.get().setTier(tier);
            em.persist(member.get());
            return member.get();
        }
        else throw new NoSuchElementException("회원이 존재하지 않습니다.");
    }

    @Override
    public void deleteById(Long id) {
        Optional<Member> member = findById(id);
        if(member.isPresent()){
            em.remove(findById(id).get());
        }
        else throw new NoSuchElementException("회원이 존재하지 않습니다.");
    }
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
