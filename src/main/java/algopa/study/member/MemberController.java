package algopa.study.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
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

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(Member member){
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    @GetMapping("/myInfo")
    public String MemberInfo(Model model){
        try{
            String name = SecurityContextHolder.getContext().getAuthentication().getName();

            Member member = memberService.findByName(name);
            model.addAttribute("member", member);
        } catch(Exception e){
            e.printStackTrace();
            return "error/notFound404Page";
        }
        return "myInfo";
    }

    @GetMapping("/createMemberForm")
    public String createForm() {
        return "createMemberForm";
    }

    @PostMapping("/createMemberForm")
    public String create(Member member){
        Long memberId = memberService.join(member);
        log.info("memberId= {}", memberId);
        if(memberId==-1L)
            return "error/duplicateErrorPage";
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Member authMember = memberService.findByName(name);
        Member member = memberService.findById(id);
        if(!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) && authMember!=member){
            return "error/notFound404Page";
        }
        model.addAttribute("member", member);
        return "editMember";
    }

    @PostMapping("/edit/{id}")
    public String edit(Member member){
        log.info("MemberId: {}, name: {}", member.getId(), member.getName());
        memberService.edit(member.getId(), member);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        System.out.println(id+"번 삭제");
        memberService.deleteMember(id);
        return "redirect:/logout";
    }

    @GetMapping({"/*", "/error"})
    public String NotFound(){
        return "error/notFound404Page";
    }
}
