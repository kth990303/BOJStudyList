package algopa.study.post.controller;

import algopa.study.member.domain.Member;
import algopa.study.member.service.MemberService;
import algopa.study.post.dto.PostDto;
import algopa.study.post.dto.PostIdDto;
import algopa.study.post.dto.PostNameDto;
import algopa.study.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/")
    public String home(Model model){
        List<PostIdDto> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "post/postList";
    }

    @GetMapping("/enroll")
    public String enrollPost(@ModelAttribute("post") PostDto postDto){
        log.debug("enrollPost 호출");
        return "post/addPost";
    }
    @PostMapping("/enroll")
    public String enroll(@Validated @ModelAttribute("post") PostDto postDto,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "post/addPost";
        }
        String loginMemberName = SecurityContextHolder.getContext().getAuthentication().getName();
        postService.enroll(postDto, loginMemberName);
        return "redirect:/post/";
    }
    @GetMapping("/{id}")
    public String enterPost(@PathVariable Long id, Model model){
        PostNameDto post = postService.findPostNameDtoById(id);
        model.addAttribute("id", id);
        model.addAttribute("post", post);
        return "post/postContent";
    }
    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Long id, Model model){
        PostDto postDto = postService.findById(id);
        model.addAttribute("id", id);
        model.addAttribute("post", postDto);
        return "post/editPost";
    }
    @PostMapping("/edit/{id}")
    public String edit(@Validated @ModelAttribute("post") PostDto postDto,
                       BindingResult bindingResult, @PathVariable Long id){
        if(bindingResult.hasErrors()){
            return "post/editPost";
        }
        postService.edit(postDto, id);
        return "redirect:/post/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id){
        postService.delete(id);
        return "redirect:/post/";
    }
}
