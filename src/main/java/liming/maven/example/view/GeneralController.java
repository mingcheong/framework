package liming.maven.example.view;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cigteam.framework.security.service.IPubResourcesService;
import com.whl.domain.User;
import com.whl.service.UserService;

@Controller
public class GeneralController {

	@Resource
	UserService userService;
	
	@Autowired
	private IPubResourcesService pubResourcesService;

	@RequestMapping("/success.do")
	public String index_jsp(ModelMap map) {
		map.addAttribute("liming", "黎明你好");
		System.out.println("index.jsp");
		return "success";
	}

	@RequestMapping("/add.do")
	public String addUser(ModelMap map) {
		User u = new User();
		u.setName("你姨在工");
		u.setAge(1);
		userService.addUser(u);
		return "success";
	}

	@RequestMapping("/list.do")
	public String list(ModelMap map) {
		List<User> userList = userService.getUserList();
		map.addAttribute("userList", userList);
		return "list";
	}
}
