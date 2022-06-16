package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        ArrayList<Job> jobs = new ArrayList<>();

        if (searchTerm.toLowerCase().equals("all")) {
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        }
        else {
            jobs = JobData.findByColumnAndValue( searchTerm, searchType);
            model.addAttribute("title", "Jobs With " + columnChoices.get(searchType) + ": " + searchTerm);
        }

        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", ListController.columnChoices);
        return "search";

    }
}
