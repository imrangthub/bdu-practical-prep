package com.imranmabar.blog;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@GetMapping("/list")
	public String Home(Model model) {
		 model.addAttribute("postList",blogService.getAllBlogs());
			return "blog/postList";
	}


	
	@GetMapping("/create-view")
	public String create(Model model, HttpSession session) {
		BlogEntity postObj = new BlogEntity();
		model.addAttribute("postObj", postObj);
		session.setAttribute("storeSuccessMsg", null);
		session.setAttribute("storeErrorMsg", null);

		return "blog/createView";
	}

	@PostMapping("/post-save")
	public String createPost(@RequestParam("title") String title, @RequestParam("body") String body,
			@RequestParam("image") MultipartFile image, HttpSession session, Model model) {
		try {
			if (title == null || title.isEmpty() || body == null || body.isEmpty()) {
				session.setAttribute("storeErrorMsg", "Required field is empty..!");
				return "redirect:/blog/create-view";
			}

			blogService.createPost(title, body, image);
			session.setAttribute("storeSuccessMsg", "Successfully added your post..!");
			return "redirect:/blog/create-view";
		} catch (IllegalArgumentException e) {
			session.setAttribute("storeErrorMsg", e.getMessage());
			return "redirect:/blog/create-view";
		} catch (IOException e) {
			session.setAttribute("storeErrorMsg", "Error saving image");
			return "blog/createView";
		}
	}
	
	
    @GetMapping("/update-view/{postId}")
    public String updatePostView(@PathVariable Long postId, Model model) {
    	Optional<BlogEntity>  obj = blogService.getBlogById(postId);
        if(obj.isPresent())
        model.addAttribute("post", obj.get());
        return "blog/updateView";
    }

    @PostMapping("/update/{postId}")
    public String updatePost(@PathVariable Long postId, @RequestParam("title") String title,
            @RequestParam("body") String body, @RequestParam("image") MultipartFile image,
            HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            blogService.updatePost(postId, title, body, image);
            redirectAttributes.addFlashAttribute("storeSuccessMsg", "Post updated successfully");
        } catch (IllegalArgumentException | IOException e) {
            redirectAttributes.addFlashAttribute("storeErrorMsg", e.getMessage());
        }
        return "redirect:/blog/list";
    }




	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<BlogEntity> getBlogById(@PathVariable Long id) {
		return ResponseEntity.ok(blogService.getBlogById(id).orElseThrow(() -> new RuntimeException("Blog not found")));
	}



    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long postId, RedirectAttributes redirectAttributes) {
        blogService.deleteBlog(postId);
        redirectAttributes.addFlashAttribute("storeSuccessMsg", "Post deleted successfully");
        return "redirect:/blog/list";
    }

}
