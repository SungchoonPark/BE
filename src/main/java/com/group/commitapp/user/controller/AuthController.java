package com.group.commitapp.user.controller;

import com.group.commitapp.oauth.dto.AccessToken;
import com.group.commitapp.oauth.dto.OAuthInfo;
import com.group.commitapp.user.dto.response.LoginResponse;
import com.group.commitapp.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public RedirectView getCode(RedirectAttributes redirectAttributes) {
        return authService.requestCode(redirectAttributes);
    }

    @GetMapping("/login/oauth")
    public ResponseEntity<LoginResponse> getToken(
            @RequestParam String code,
            @RequestParam String state) {

        AccessToken accessTokenInfo = authService.getAccessToken(code, state);
        OAuthInfo gitHubUserInfo = authService.getGitHubUserInfo(accessTokenInfo);

        LoginResponse login = authService.login(gitHubUserInfo);
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.OK.value())).ok().body(login);
    }


}
