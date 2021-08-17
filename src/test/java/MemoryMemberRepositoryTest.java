import algopa.study.domain.Member;
import algopa.study.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository=new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        //given
        Member member=new Member();
        member.setName("kth990303");
        //when
        repository.save(member);
        //then
        Member result=repository.findByName(member.getName()).get();
        assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName(){
        //given
        Member member1=new Member();
        member1.setName("kth990303");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("2sms22rjt");
        repository.save(member2);
        //when
        Member result = repository.findByName("kth990303").get();
        //then
        assertThat(member1).isEqualTo(result);
    }
    @Test
    public void findAll(){
        //given
        Member member1=new Member();
        member1.setName("kth990303");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("2sms22rjt");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
