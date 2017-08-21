package com.discExchange;

import com.discExchange.path.TakenDiscPath;
import com.discExchange.vm.Menu;
import com.discExchange.vm.UserVm;
import com.discExchange.model.UserEntity;
import com.discExchange.path.UserPath;
import com.discExchange.service.UserService;
import com.discExchange.utility.AuthUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private UserService userService;

    //@Autowired(required=true)
    //@Qualifier(value="userService")
    public void setUserService(UserService ps) {
        this.userService = ps;
    }

    @RequestMapping(value = {"/", UserPath.Login}, method = RequestMethod.GET)
    public String init(Model model) {
        Menu menu = new Menu();
        menu.setLogin(Menu.ACTIVE);
        model.addAttribute(Menu.MODEL_NAME, menu);
        return "loginView";
    }

    @RequestMapping(value = UserPath.DoLogin, method = RequestMethod.POST)
    public String doLogin(Model model,
                          @ModelAttribute("userName") String userName,
                          @ModelAttribute("password") String password,
                          @ModelAttribute("rememberMe") String rememberMeStr,
                          RedirectAttributes redirectAttributes,
                          HttpSession session,
                          HttpServletResponse response
    ) {

        String errorString = null;


        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            errorString = "Username and password are required.";
            redirectAttributes.addFlashAttribute("errorString", errorString);
            redirectAttributes.addFlashAttribute(UserVm.MODEL_NAME, new UserVm(userName, null, null, 0));
            return "redirect:" + UserPath.Login;
        }


        UserEntity user = userService.findUserByNamePassword(userName, password);
        if (user == null) {
            errorString = "Username or password you entered is incorrect. Please try again.";
            redirectAttributes.addFlashAttribute("errorString", errorString);
            redirectAttributes.addFlashAttribute(UserVm.MODEL_NAME, new UserVm(userName, null, null, 0));
            return "redirect:" + UserPath.Login;
        }

        UserVm userVm = new UserVm();
        userVm.setName(userName);
        userVm.setId(user.getId());

        AuthUtils.storeAuthUserInSession(session, userVm);

		/*boolean shouldRemember= "Y".equals(rememberMeStr);
        if (shouldRemember)
			AuthUtils.storeUserNameCookie(response, userVm);
		else
			AuthUtils.deleteUserCookie(response);
		*/
        return "redirect:" + TakenDiscPath.AllAvailable;
    }

    @RequestMapping(value = UserPath.Info, method = RequestMethod.GET)
    public String onSuccessGet(Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        UserVm user = AuthUtils.getAuthUser(session);
        if (user == null) {
            return "redirect:" + UserPath.Login;
        } else {
            model.addAttribute(UserVm.MODEL_NAME, user);
        }
        return "loginInfoView";
    }

    @RequestMapping(value = UserPath.Logout)
    public String onLogout(HttpSession session, HttpServletResponse response) {
        AuthUtils.storeAuthUserInSession(session, null);
        AuthUtils.deleteUserCookie(response);
        return "redirect:" + UserPath.Login;
    }

}
