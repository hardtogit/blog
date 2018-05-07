/**
 * 
 */
package com.thon.controller.rest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thon.commons.mapper.BeanUtils;
import com.thon.commons.persistence.Page;
import com.thon.commons.utils.StringUtils;
import com.thon.controller.util.BaseController;
import com.thon.entity.post.Comment;
import com.thon.entity.post.Post;
import com.thon.entity.post.Term;
import com.thon.security.CaptchaServlet;
import com.thon.service.post.PostService;
import com.thon.service.post.TermService;
import com.thon.service.utils.UserUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @email thon.ju@gmail.com
 * @date Aug 24, 2014 7:46:21 PM
 * @description
 */
@Controller
@RequestMapping("/api/post")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PostRestController extends BaseController{
	
	@Autowired
	private PostService postService;
    @Autowired
    private TermService termService;
	
	private static final Log log = LogFactory.getLog(PostRestController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> list(@RequestParam(value = "p", defaultValue = "1") int pageNo,
                                  @RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize,
                                  Post post) {

        Page<Post> page = new Page<Post>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        page = postService.findPosts(page, post, null);

        return new ResponseEntity(page, HttpStatus.OK);
    }

	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<?> apiSave(@Valid Post post,@RequestParam(value = "postTags",required = false) String postTags) throws InvocationTargetException, IllegalAccessException {

        // 保存标签
        List<Term> terms = Lists.newArrayList();
        if (StringUtils.isNotBlank(postTags)){
            String[] tags = StringUtils.split(postTags,",");
            for (String tag : tags) {
                Term term = termService.getTerm(tag, Term.TYPE_POST_TAG);
                if (term == null){
                    term = new Term();
                    term.setName(tag);
                    term.setType(Term.TYPE_POST_TAG);
                    term.setParentIds("0,");
                    termService.saveTerm(term);
                }
                terms.add(term);
            }
        }

        Post oldPost = new Post();
        if(post.getId() != null){
            oldPost = postService.getPost(post.getId());
            oldPost.setPostModified(new Date());
        }else {
            oldPost.setCommentCount(0);
            oldPost.setPostDate(new Date());
        }

        BeanUtilsBean bean = new BeanUtils();
        bean.copyProperties(oldPost, post);
        oldPost.setTermList(terms);

        postService.savePost(oldPost);
		return new ResponseEntity(true, HttpStatus.OK);
	}

    @RequestMapping(value="/del", method = RequestMethod.POST)
    public ResponseEntity<?> apiDel(@RequestParam("id") int id) {
        Post post = postService.getPost(id);
        postService.delPost(post);

        return new ResponseEntity("true", HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/list", method = RequestMethod.GET)
    public ResponseEntity<?> commentList(@RequestParam(value = "p", defaultValue = "1") int pageNo,
                                  @RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize,
                                  @RequestParam(value = "exchild", defaultValue = "true") Boolean exchild,
                                  @RequestParam(value = "pid", required = false) Integer postId) {

        Page<Comment> page = new Page<Comment>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        page = postService.findComments(page, postId, exchild);

        return new ResponseEntity(page, HttpStatus.OK);
    }

    @RequestMapping(value="/comment/save", method = RequestMethod.POST)
    public ResponseEntity<?> apiCommentSave(@Valid Comment comment,
                                            @RequestParam(value = "captcha", required = true) String captcha,
                                            HttpSession session) throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = Maps.newHashMap();

        String exitCode = (String) session.getAttribute(CaptchaServlet.KEY_CAPTCHA);
        if (captcha.equalsIgnoreCase(exitCode)){
            Comment oldComment = new Comment();
            if (comment.getId() != null){
                oldComment = postService.getComment(comment.getId());
            }else {
                oldComment.setCommentDate(new Date());
                comment.setAuthor(UserUtils.getUser());
            }

            BeanUtilsBean bean = new BeanUtils();
            bean.copyProperties(oldComment, comment);

            if (oldComment.getParent() == null || oldComment.getParent().getId() == null){
                oldComment.setParent(null);
            }
            if (oldComment.getAuthor() == null || oldComment.getAuthor().getId() == null){
                oldComment.setAuthor(null);
            }

            postService.saveComment(oldComment);

            map.put("result","true");
        }else{
            map.put("result","false");
            map.put("reason","验证码错误");
        }


        return new ResponseEntity(map, HttpStatus.OK);
    }

    @RequestMapping(value="/comment/del", method = RequestMethod.POST)
    public ResponseEntity<?> apiCommentDel(@RequestParam("id") int id) {
        Comment comment = postService.getComment(id);
        comment.setAuthor(UserUtils.getUser());
        postService.delComment(comment);

        return new ResponseEntity("true", HttpStatus.OK);
    }

}
