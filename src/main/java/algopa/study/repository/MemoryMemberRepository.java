package algopa.study.repository;

import algopa.study.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store=new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member edit(Long id, String tier) {
        Optional<Member> member = findById(id);
        if(member.isPresent()){
            member.get().setTier(tier);
            store.put(id, member.get());
            return member.get();
        }
        else throw new NoSuchElementException("회원이 존재하지 않습니다.");
    }

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }



    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return store.values().stream()
                .filter(member -> member.getId().equals(id))
                .findAny();
    }
    @Override
    public void deleteById(Long id) {
        store.remove(findById(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
