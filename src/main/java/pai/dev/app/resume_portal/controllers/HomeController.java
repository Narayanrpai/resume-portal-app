package pai.dev.app.resume_portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Arrays;
import pai.dev.app.resume_portal.models.Education;
import pai.dev.app.resume_portal.models.Job;
import pai.dev.app.resume_portal.models.UserProfile;
import pai.dev.app.resume_portal.repository.UserProfileRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/edit")
    public String edit(Model model, Principal principal, @RequestParam(required = false) String add) {
        String userName = principal.getName();
        model.addAttribute("userId", userName);
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(()-> new RuntimeException("Not found: " + userName));
        UserProfile userProfile = userProfileOptional.get();
        if("job".equals(add)) {
            userProfile.getJobs().add(new Job());
        } else if("education".equals(add)) {
            userProfile.getEducations().add(new Education());
        } else if("skill".equals(add)) {
            userProfile.getSkills().add("");
        }
        model.addAttribute("userProfile", userProfile);
        return "profile-edit";
    }

    @GetMapping("/delete")
    public String delete(Model model, Principal principal, @RequestParam String type, @RequestParam int index) {
        String userName = principal.getName();
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(()-> new RuntimeException("Not found: " + userName));
        UserProfile userProfile = userProfileOptional.get();
        if("job".equals(type)) {
            userProfile.getJobs().remove(index);
        } else if("education".equals(type)) {
            userProfile.getEducations().remove(index);
        } else if("skill".equals(type)) {
            userProfile.getSkills().remove(index);
        }
        userProfileRepository.save(userProfile);
        return "redirect:/edit";
    }

    @PostMapping("/edit")
    public String postEdit(Model model, Principal principal, @ModelAttribute UserProfile userProfile) {
        String userName = principal.getName();
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(()-> new RuntimeException("Not found: " + userName));
        UserProfile savedUserProfile = userProfileOptional.get();
        userProfile.setId(savedUserProfile.getId());
        userProfile.setUserName(userName);
        userProfileRepository.save(userProfile);
        return "redirect:/view/" + userName;
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model, Principal principal) {
        String userName = principal.getName();
        if(principal != null && principal.getName() != "") {
            boolean currentUsersProfile = principal.getName().equals(userId);
            model.addAttribute("currentUsersProfile", currentUsersProfile);
        }
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(()-> new RuntimeException("Not found: " + userId));

        model.addAttribute("userId", userId);
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile",userProfile);
        System.out.println(userProfile.getJobs());
        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
