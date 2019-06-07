package com.techtalentsouth.TechTalentBlog;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class BlogPostController {
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	//private static List<BlogPost> posts = new ArrayList<>();
	
	@GetMapping(value="/")
	public String index(BlogPost blogPost, Model model) {
		model.addAttribute("posts", blogPostRepository.findAll());
		return "blogpost/index";
	    }
	
	private BlogPost blogPost;
	@PostMapping(value = "/")
	public String create(BlogPost blogPost, Model model) {
		blogPostRepository.save(blogPost);
	    
			model.addAttribute("title", blogPost.getTitle());
			model.addAttribute("author", blogPost.getAuthor());
	        model.addAttribute("blogEntry", blogPost.getBlogEntry());
	        return "blogpost/result";
	}
	
		@GetMapping(value = "/new")
	    public String newBlog (BlogPost blogPost) {
	        return "blogpost/new";
	    }
		
	    @RequestMapping(value = "/blog_posts/{id}", method = RequestMethod.DELETE)
	    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
	        blogPostRepository.deleteById(id);
	        model.addAttribute("posts", blogPostRepository.findAll());
	        return "blogpost/index";
	    }
	    
	    @RequestMapping(value = "/blog_posts/show/{id}", method = RequestMethod.GET)
	    public String getPostWithId(@PathVariable Long id, Model model) {
	    	BlogPost blogpost = blogPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID:" + id));
	    	model.addAttribute("blogpost", blogpost);
	        return "blogpost/show";
	    }
	   
	    @RequestMapping(value = "/blog_posts/edit/{id}", method = RequestMethod.GET)
	    public String editPostWithId(@PathVariable Long id, Model model) {
	    	BlogPost blogpost = blogPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
	    	model.addAttribute("blogpost", blogpost);
	        return "blogpost/edit";
	    }
	    
	    @RequestMapping(value="/blogpost/edit/{id}", method=RequestMethod.PUT)
	    public String updatePostById(@PathVariable Long id, Model model, BlogPost formData) {
	    	BlogPost EditedWumbology = blogPostRepository.findById(id).orElse(null);
	    	EditedWumbology.setAuthor(formData.getAuthor());
	    	EditedWumbology.setTitle(formData.getTitle());
	    	EditedWumbology.setBlogEntry(formData.getBlogEntry());
	    	blogPostRepository.save(EditedWumbology);
	    	model.addAttribute("title", EditedWumbology.getTitle());
	    	model.addAttribute("author", EditedWumbology.getAuthor());
	    	model.addAttribute("blogEntry", EditedWumbology.getBlogEntry());
	    	model.addAttribute("id", EditedWumbology.getId());
	    	return "blogpost/result";
	    }
	    
//	    @PutMapping("/blogpost/edit/{id}")
//	    public String replaceBlogPost(BlogPost newPost, Model model, @PathVariable Long id) {
//	      return blogPostRepository.findById(id).map(blogPost -> {
//	    	    blogPost.setTitle(newPost.getTitle());
//	        	blogPost.setAuthor(newPost.getAuthor());
//	        	blogPost.setBlogEntry(newPost.getBlogEntry());
//	            blogPostRepository.save(newPost);
//	    		model.addAttribute("title", blogPost.getTitle());
//	    		model.addAttribute("author", blogPost.getAuthor());
//	    		model.addAttribute("blogEntry", blogPost.getBlogEntry());
//	    		model.addAttribute("id", blogPost.getId());
//	            return "blogpost/result";
//	        })
//	        .orElseGet(() -> {
//	          newPost.setId(id);
//	         blogPostRepository.save(newPost);
//	         return "blogpost/result";
//	         
//	        });
//	    }
	    
	    

	    
	 

		

	    

	    
	    
	    
	    
	    

	
}
