package com.example.passaydemo;

import org.passay.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class PasswordController {

    @Value("${any.key1}")
    private String characters;
    @GetMapping("/password")
    public String get(Model model) {
        model.addAttribute("passwordForm", new PasswordForm());
        model.addAttribute("createPasswordForm", new PasswordForm());
        return "password";
    }

    @PostMapping("/check")
    public String check(@ModelAttribute PasswordForm passwordForm){
        boolean result = _checkPassword(passwordForm.getPassword());
        PasswordForm form = new PasswordForm();
        if (result) {
            form.setResult("OK");
            passwordForm.setResult("OK");
        } else {
            form.setResult("NG");
            passwordForm.setResult("NG");
        }
//        model.addAttribute("passwordForm", form);
        return "password";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute CreatePasswordForm createPasswordForm) {
        String pass = _createPassword();
        createPasswordForm.setPassword(pass);
        return "password";

    }

    @RequestMapping("/password/check/{password}")
    String checkPassword(@PathVariable("password") String password) {

        boolean result = _checkPassword(password);
        if (result) {
            return "OKOKOKOK";
        }
        return "ngngngng";
    }

    @RequestMapping("/password/create")
    @ResponseBody
    String createPassword() {
        return _createPassword();
    }

    private static boolean _checkPassword(String password) {
        char[] allowedCharacter = {'X'};
        PasswordValidator validator = new PasswordValidator(
                // 長さは8文字から16文字まで
                new LengthRule(8, 16),

                // 1文字以上の大文字のアルファベットが必須
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // 1文字以上の小文字のアルファベットが必須
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // 1文字以上の数字が必須
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // 1文字以上の記号が必須
                new CharacterRule(EnglishCharacterData.Special, 1),

//                new AllowedCharacterRule(allowedCharacter),

                // 空白やタブはない
                new WhitespaceRule());

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            System.out.println("妥当なパスワード");
            return true;
        } else {
            System.out.println("不当なパスワード:");
            for (String msg : validator.getMessages(result)) {
                System.out.println(msg);
            }
            return false;
        }
    }

    private String _createPassword() {
        SpecialCharacterData specialCharacterData;
        List<CharacterRule> rules = Arrays.asList(
                // 1文字以上の大文字のアルファベットが必須
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // 1文字以上の小文字のアルファベットが必須
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

//                new CharacterRule(new ExCharacterData(characters), 1),
                new CharacterRule(SpecialCharacterData.Special, 1),

                // 1文字以上の数字が必須
                new CharacterRule(EnglishCharacterData.Digit, 1));

        PasswordGenerator generator = new PasswordGenerator();

        // 上で設定したルールを満たす8文字のパスワードを生成
        String password = generator.generatePassword(8, rules);
        return password;
    }
}
