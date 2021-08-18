package algopa.study.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor // lombok
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/")
    public String home(Model model){
        List<Member> members= memberService.findAllMembers();
        model.addAttribute("members", members);
        return "index";
    }

    @GetMapping(value = "/createMemberForm")
    public String createForm() {
        return "createMemberForm";
    }

    @PostMapping("/createMemberForm")
    public String create(Member member){
        Long memberId = memberService.join(member);
        log.info("memberId= {}", memberId);
        if(memberId==-1L)
            return "errorPage";
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id){
        return "editMember";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Member member){
        memberService.edit(id, member);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        System.out.println(id+"번 삭제");
        memberService.deleteMember(id);
        return "redirect:/";
    }
}