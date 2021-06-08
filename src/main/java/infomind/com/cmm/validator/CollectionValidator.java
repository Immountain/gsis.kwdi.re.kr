package infomind.com.cmm.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Collection;
import java.util.Iterator;

public class CollectionValidator implements Validator {

    private final Validator validator;

    public CollectionValidator(LocalValidatorFactoryBean validatorFactory) {
        this.validator = validatorFactory;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

    public void validate(Object target, Errors errors) {
        if (target instanceof Collection) {
            Collection collection = (Collection)target;
            Iterator var4 = collection.iterator();

            while(var4.hasNext()) {
                Object object = var4.next();
                ValidationUtils.invokeValidator(this.validator, object, errors);
            }
        }

    }

}
