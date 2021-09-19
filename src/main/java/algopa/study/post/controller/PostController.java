package algopa.study.post.controller;

import algopa.study.post.dto.PostDto;
import algopa.study.post.dto.PostIdDto;
import algopa.study.post.dto.PostNameDto;
import algopa.study.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public String home(Model model){
        List<PostIdDto> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "post/postList";
    }

    @GetMapping("/enroll")
    public String enrollPost(){
        log.debug("enrollPost 호출");
        return "post/addPost";
    }
    @PostMapping("/enroll")
    public String enroll(PostDto postDto){
        postService.enroll(postDto);
        return "redirect:/post/";
    }
    @GetMapping("/{id}")
    public String enterPost(@PathVariable Long id, Model model){
        PostNameDto post = postService.findPostNameDtoById(id);
        model.addAttribute("id", id);
        model.addAttribute("post", post);
        return "post/postContent";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id){
        postService.delete(id);
        return "redirect:/post/";
    }
}
