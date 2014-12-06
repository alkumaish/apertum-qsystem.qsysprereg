package ru.apertum.qsystem.prereg;

import java.util.Map;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class ClientFormValidator extends AbstractValidator {

    @Override
    public void validate(ValidationContext ctx) {
        //all the bean properties
        Map<String, Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());

        validateName(ctx, (String) beanProps.get("name").getValue());
        validateSourname(ctx, (String) beanProps.get("sourname").getValue());
        validateMiddlename(ctx, (String) beanProps.get("middlename").getValue());
        validateEmail(ctx, (String) beanProps.get("email").getValue());

        validateCaptcha(ctx, (String) ctx.getValidatorArg("captcha"), (String) ctx.getValidatorArg("captchaInput"));
        
    }

    private void validateEmail(ValidationContext ctx, String email) {
        if (email != null && !email.isEmpty() && !email.matches(".+@.+\\.[a-z]+")) {
           this.addInvalidMessage(ctx, "email", "Некорректный email!");
        }
    }

    private void validateCaptcha(ValidationContext ctx, String captcha, String captchaInput) {
        if (captchaInput == null || !captcha.equals(captchaInput)) {
            this.addInvalidMessage(ctx, "captcha", "Не совпадение!");
        }
    }

    private void validateMiddlename(ValidationContext ctx, String string) {
        if (string == null || string.isEmpty() || string.length() < 3) {
            this.addInvalidMessage(ctx, "middlename", "Укажите отчество!");
        }
    }

    private void validateName(ValidationContext ctx, String string) {
        if (string == null || string.isEmpty() || string.length() < 2) {
            this.addInvalidMessage(ctx, "name", "Укажите имя!");
        }
    }

    private void validateSourname(ValidationContext ctx, String string) {
        if (string == null || string.isEmpty() || string.length() < 2) {
            this.addInvalidMessage(ctx, "sourname", "Укажите фамилию!");
        }
    }
}
