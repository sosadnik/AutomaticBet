package org.automaticbet;

import lombok.RequiredArgsConstructor;
import org.automaticbet.service.AutomaticBetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/bet")
public class RestController {

    private AutomaticBetService automaticBetService;

    @GetMapping("/placeCoupon")
    public void placeCoupon(@PathVariable String username, @PathVariable String password) throws IOException, InterruptedException {
        automaticBetService.createCoupon(username, password);
    }
}
