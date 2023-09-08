package coms309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "change the path to be of format: /noun/verb/adjective/noun";
    }

    @GetMapping("/{noun}/{verb}/{adjective}/{noun2}")
    public String welcome(@PathVariable String noun, @PathVariable String verb,
                          @PathVariable String adjective, @PathVariable String noun2)
    {
        return "The " + adjective + " " + noun + " " + verb + " with a " + noun2;
    }
}