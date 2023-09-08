package coms309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class WelcomeController {

    @GetMapping("/")
    public String hi() {
        return "Hello user.";
    }

    @GetMapping("/{name}")
    public String welcomeuser(@PathVariable String name) {
        return "Hello " + name + ".";
    }

    @GetMapping("/bye/{name}")
    public String bye(@PathVariable String name) {
        return "Goodbye " + name + ".";
    }

    @GetMapping("/hello/{name}/{name2}")
    public String byeuser(@PathVariable String name, @PathVariable String name2) {
        return "Hello " + name + ", I am " + name2 + ".";
    }

}
