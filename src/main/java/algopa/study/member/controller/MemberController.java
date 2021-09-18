package algopa.study.member.controller;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import algopa.study.member.dto.MemberIdDto;
import algopa.study.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor // lombok
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/")
    public String home(Model model){
        // 현재 엔티티를 반환중
        List<MemberIdDto> members = memberService.findAllMembers();
        model.addAttribute("members", members);
        return "index";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(){
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

            Long id = memberService.findIdByName(name);
            MemberDto memberDto = memberService.findByName(name);

            model.addAttribute("id", id);
            model.addAttribute("member", memberDto);
        } catch(Exception e){
            e.printStackTrace();
            return "error/notFound404Page";
        }
        return "myInfo";
    }

    @GetMapping("/createMemberForm")
    public String createForm(Model model, MemberDto memberDto) {
        return "createMemberForm";
    }

    @PostMapping("/createMemberForm")
    public String create(MemberDto memberDto){
        Long memberId = memberService.join(memberDto);
        if(memberId==-1L)
            return "error/duplicateErrorPage";
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        // 현재 로그인한 유저 정보가 관리자인지 확인하는 코드
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authMemberName = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // 수정하려는 유저정보
        MemberDto memberDto = memberService.findById(id);
        String name=memberDto.getName();

        // 관리자가 아닌데도, 타인의 정보를 수정하는 버그가 발생할 경우
        if(!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) && !authMemberName.equals(name)){
            return "error/notFound404Page";
        }
        model.addAttribute("id", id);
        model.addAttribute("memberDto", memberDto);
        return "editMember";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, MemberDto memberDto){
        log.info("MemberName: {}", memberDto.getName());
        memberService.edit(id, memberDto);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        System.out.println(id+"번 삭제");
        memberService.deleteMember(id);
        if(SecurityContextHolder.getContext().getAuthentication().getName().equals("kth990303"))
            return "redirect:/";
        return "redirect:/logout";
    }

    @GetMapping({"/*", "/error"})
    public String NotFound(){
        return "error/notFound404Page";
    }
}
