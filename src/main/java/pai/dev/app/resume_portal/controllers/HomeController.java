package pai.dev.app.resume_portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.expression.Arrays;
import pai.dev.app.resume_portal.models.Job;
import pai.dev.app.resume_portal.models.UserProfile;
import pai.dev.app.resume_portal.repository.UserProfileRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @GetMapping
    public String home() {
        Optional<UserProfile> profileOptional = userProfileRepository.findByUserName("einstein");
        profileOptional.orElseThrow(()-> new RuntimeException("Not found "));

        UserProfile profile1 = profileOptional.get();
        Job job1 = new Job();
        job1.setId(1);
        job1.setCompany("Company 1");
        job1.setDesignation("Designation");
        job1.setStartDate(LocalDate.of(2024, 1, 1));
        job1.setEndDate(LocalDate.of(2025, 3, 5));
        job1.setCurrentJob(true);
        Job job2 = new Job();
        job2.setId(2);
        job2.setCompany("Company 2");
        job2.setDesignation("Designation");
        job2.setStartDate(LocalDate.of(2022, 4, 3));
        job2.setEndDate(LocalDate.of(2023, 6, 11));
        profile1.getJobs().clear();
        profile1.getJobs().add(job1);
        profile1.getJobs().add(job2);
        userProfileRepository.save(profile1);
        return "profile";
    }
    @GetMapping("/edit")
    public String edit() {
        return "edit page";
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(()-> new RuntimeException("Not found: " + userId));

        model.addAttribute("userId", userId);
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userProfile",userProfile);
        System.out.println(userProfile.getJobs());
        return "profile-templates/" + userProfile.getId() + "/index";
    }
}
