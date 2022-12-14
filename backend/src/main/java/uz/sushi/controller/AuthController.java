package uz.sushi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.sushi.payload.ApiResult;
import uz.sushi.payload.sign.SignDTO;
import uz.sushi.payload.sign.SignIn;
import uz.sushi.service.AuthService;

import javax.validation.Valid;
import java.util.Map;


@RequestMapping(path = AuthController.AUTH_CONTROLLER_BASE_PATH)
@RequiredArgsConstructor
@RestController
public class AuthController {
    public static final String AUTH_CONTROLLER_BASE_PATH = "/api/auth";
    public static final String SIGN_IN_PATH = "/sign-in";
    public static final String SIGN_UP_PATH = "/sign-up";

    private final AuthService authService;

    @PostMapping(value = SIGN_UP_PATH)
    ApiResult<String> signUp(@RequestBody @Valid SignDTO signDTO) {
        return authService.signUp(signDTO);
    }

    @PostMapping(value = SIGN_IN_PATH)
    ApiResult<SignIn> signIn(@RequestBody @Valid SignDTO signDTO) {
        return authService.signIn(signDTO);
    }

}
