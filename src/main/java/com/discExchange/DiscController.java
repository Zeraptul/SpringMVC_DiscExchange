package com.discExchange;

import com.discExchange.vm.AddingDiscVm;
import com.discExchange.vm.Menu;
import com.discExchange.vm.UserVm;
import com.discExchange.model.DiscEntity;
import com.discExchange.model.TakenDiscEntity;
import com.discExchange.model.UserEntity;
import com.discExchange.path.DiscPath;
import com.discExchange.path.TakenDiscPath;
import com.discExchange.path.UserPath;
import com.discExchange.service.DiscService;
import com.discExchange.service.TakenDiscService;
import com.discExchange.service.UserService;
import com.discExchange.utility.AuthUtils;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by adMin on 10.08.2017.
 */
@Controller
public class DiscController {

    @Setter
    private DiscService discService;
    @Setter
    private TakenDiscService takenDiscService;
    @Setter
    private UserService userService;

    @RequestMapping(value = DiscPath.Add)
    public String addDisc(Model model, HttpSession session)
    {
        UserVm user = AuthUtils.getAuthUser(session);
        if (user == null) {
            return "redirect:" + UserPath.Login;
        }
        else {
            model.addAttribute(UserVm.MODEL_NAME, user);
        }

        if (!model.containsAttribute("discVm")) {
            AddingDiscVm discVm = new AddingDiscVm();
            model.addAttribute("discVm", discVm);
        }

        Menu menu = new Menu();
        menu.setAddDisc( Menu.ACTIVE);
        model.addAttribute(Menu.MODEL_NAME, menu);
        return "addDisc";
    }

    @RequestMapping(value = DiscPath.DoAdd, method = RequestMethod.POST)
    public String doAddDisc(Model model, HttpSession session,
                            @RequestParam String discName, @RequestParam String authorName,
                            RedirectAttributes redirectAttributes) {
        UserVm user = AuthUtils.getAuthUser(session);
        if (user == null) {
            return "redirect:" + UserPath.Login;
        }
        else {
            model.addAttribute(UserVm.MODEL_NAME, user);
        }

        if (StringUtils.isEmpty(discName) || StringUtils.isEmpty(authorName)) {
            AddingDiscVm discVm = new AddingDiscVm();
            discVm.setName(discName);
            discVm.setAuthor(authorName);

            redirectAttributes.addFlashAttribute("discVm", discVm);
            redirectAttributes.addFlashAttribute("errorString", "Disc name or author name you entered is incorrect. Please try again.");
            return "redirect:" + DiscPath.Add;
        }

        int ownerUserId = user.getId();
        DiscEntity disc = discService.addNewDisc(discName, authorName);
        UserEntity ownerUser = userService.getUserById(ownerUserId);
        String description = "";
        TakenDiscEntity takenDisc = takenDiscService.addDisc(ownerUser, disc, null, description );
        return "redirect:" + TakenDiscPath.AllAvailable;
    }

    /*@RequestMapping(value = TakenDiscPath.ListAll)
    public String listAllTakenDiscs(Model model)
    {
        List<TakenDiscEntity> discList = takenDiscService.listAll();
        model.addAttribute("takenDiscList", discList);

        Menu menu = new Menu();
        menu.setTakenDiscAll( Menu.ACTIVE);
        model.addAttribute(Menu.MODEL_NAME, menu);
        return "takenDiscList";
    }*/

    @RequestMapping(value = TakenDiscPath.AllAvailable)
    public String listAllFreeDiscs(Model model, HttpSession session)
    {

        UserVm user = AuthUtils.getAuthUser(session);
        if (user == null) {
            return "redirect:" + UserPath.Login;
        }
        else {
            model.addAttribute(UserVm.MODEL_NAME, user);
        }
        int currentUserId = user.getId();
        model.addAttribute("currentUserId", currentUserId);

        List<TakenDiscEntity> discList = takenDiscService.listAllFree();
        model.addAttribute("takenDiscList", discList);

        Menu menu = new Menu();
        menu.setTakenDiscAllAvailable(Menu.ACTIVE);
        model.addAttribute(Menu.MODEL_NAME, menu);

        return "availableDiscs";
    }

    @RequestMapping(value = TakenDiscPath.Take + "//{takenDiscId}")
    public String takeDisc(@PathVariable("takenDiscId") int takenDiscId, Model model, HttpSession session)
    {
        UserVm user = AuthUtils.getAuthUser(session);
        if (user == null) {
            return "redirect:" + UserPath.Login;
        }
        else {
            model.addAttribute(UserVm.MODEL_NAME, user);
        }

        int takerUserId = user.getId();
        takenDiscService.takeDiscByIdAndUser(takenDiscId, takerUserId);
        return "redirect:" + TakenDiscPath.AllAvailable;
    }


    @RequestMapping(value = TakenDiscPath.AllUserTaken)
    public String listAllUserTakenDiscs(Model model, HttpSession session)
    {
        UserVm user = AuthUtils.getAuthUser(session);
        if (user == null) {
            return "redirect:" + UserPath.Login;
        }
        else {
            model.addAttribute(UserVm.MODEL_NAME, user);
        }

        int currentUserId = user.getId();
        UserEntity takerUser = userService.getUserById(currentUserId);
        List<TakenDiscEntity> discList = takenDiscService.listAllUserTaken(takerUser);
        model.addAttribute("takenDiscList", discList);

        Menu menu = new Menu();
        menu.setTakenDiscAllUserTaken( Menu.ACTIVE );
        model.addAttribute(Menu.MODEL_NAME, menu);

        return "userTakenDiscs";
    }

    @RequestMapping(value = TakenDiscPath.Return +"//{takenDiscId}")
    public String returnUserTakenDisc(@PathVariable("takenDiscId") int takenDiscId, Model model, HttpSession session)
    {
        UserVm user = AuthUtils.getAuthUser(session);
        if (user == null) {
            return "redirect:" + UserPath.Login;
        }
        else {
            model.addAttribute(UserVm.MODEL_NAME, user);
        }

        int currentUserId = user.getId();
        takenDiscService.ReturnTakenDiscById(takenDiscId);
        return "redirect:"+ TakenDiscPath.AllUserTaken;
    }

    @RequestMapping(value = TakenDiscPath.AllTakenFromUser)
    public String listAllTakenFromUserDiscs(Model model, HttpSession session)
    {
        UserVm user = AuthUtils.getAuthUser(session);
        if (user == null) {
            return "redirect:" + UserPath.Login;
        }
        else {
            model.addAttribute(UserVm.MODEL_NAME, user);
        }

        int currentUserId = user.getId();
        UserEntity ownerUser = userService.getUserById(currentUserId);
        List<TakenDiscEntity> discList = takenDiscService.listAllTakenFromUser(ownerUser);
        model.addAttribute("takenDiscList", discList);

        Menu menu = new Menu();
        menu.setTakenDiscAllTakenFromUser( Menu.ACTIVE);
        model.addAttribute(Menu.MODEL_NAME, menu);

        return "takenDiscList";
    }


}
