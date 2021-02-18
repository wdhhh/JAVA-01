package work.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.starter.School;

@RestController
public class Work03Controller {

    @Autowired
    private School school;

    @RequestMapping("/school")
    public String school() {
        return school.ding();
    }
}
