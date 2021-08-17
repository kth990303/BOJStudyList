import algopa.study.domain.Member;
import algopa.study.repository.MemoryMemberRepository;
import algopa.study.service.MemberServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberServiceImplTest {
    MemberServiceImpl service;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach(){
        repository=new MemoryMemberRepository();
        service=new MemberServiceImpl(repository);
    }

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void join(){
        //given
        Member member=new Member();
        member.setName("kth990303");
        member.setTier("Platinum II");
        //when
        Long saveId=service.join(member);
        //then
        Member result = repository.findByName("kth990303").get();
        assertEquals(member.getName(), result.getName());
        assertEquals(member.getTier(), result.getTier());
    }

    @Test
    public void duplicateMember() throws Exception{
        Member m1=new Member();
        Member m2=new Member();
        m1.setName("kth990303");
        m1.setTier("Platinum II");
        m2.setName("kth990303");
        m2.setTier("Platinum III");
        service.join(m1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(m2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}
