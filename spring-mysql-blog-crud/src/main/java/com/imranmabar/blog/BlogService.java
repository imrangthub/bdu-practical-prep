package com.imranmabar.blog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
    private static final String UPLOAD_DIR = "src/main/resources/static/img/";

	public List<BlogEntity> getAllBlogs() {
		return blogRepository.findAll();
	}

	public Optional<BlogEntity> getBlogById(Long id) {
		return blogRepository.findById(id);
	}



    public BlogEntity createPost(String title, String body, MultipartFile image) throws IOException {
        String imagePath = null;

        if (image != null && !image.isEmpty()) {
            String originalFilename = image.getOriginalFilename();
            if (originalFilename != null) {
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                if (".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension) || ".jpeg".equalsIgnoreCase(fileExtension)) {
                    String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
                    imagePath = UPLOAD_DIR + uniqueFilename;
                    Path path = Paths.get(imagePath);
                    Files.createDirectories(path.getParent());
                    Files.write(path, image.getBytes());
                    imagePath = uniqueFilename; // Save only the unique filename in the database
                } else {
                    throw new IllegalArgumentException("Invalid file format");
                }
            }
        }

        BlogEntity post = new BlogEntity();
        post.setPostTitle(title);
        post.setPostBody(body);
        post.setPostImage(imagePath);

        return blogRepository.save(post);
    }

    public BlogEntity getPostById(Long postId) {
        return blogRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + postId));
    }
    
    public BlogEntity updatePost(Long postId, String title, String body, MultipartFile image) throws IOException {
        BlogEntity post = getPostById(postId);

        String imagePath = post.getPostImage();

        if (image != null && !image.isEmpty()) {
            String originalFilename = image.getOriginalFilename();
            if (originalFilename != null) {
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                if (".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension) || ".jpeg".equalsIgnoreCase(fileExtension)) {
                    String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
                    String updatedImagePath = UPLOAD_DIR + uniqueFilename;
                    Path path = Paths.get(updatedImagePath);
                    Files.createDirectories(path.getParent());
                    Files.write(path, image.getBytes());
                    imagePath = uniqueFilename; // Update the image path if a new image is uploaded
                } else {
                    throw new IllegalArgumentException("Invalid file format");
                }
            }
        }

        post.setPostTitle(title);
        post.setPostBody(body);
        post.setPostImage(imagePath);

        return blogRepository.save(post);
    }

	public void deleteBlog(Long id) {
		BlogEntity blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
		blogRepository.delete(blog);
	}
}
