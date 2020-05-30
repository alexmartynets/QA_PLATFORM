package com.javamentor.qa.platform.webapp.configs.swagger;

import com.google.common.base.Optional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.bean.validators.plugins.Validators;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import javax.validation.constraints.Email;

import static springfox.bean.validators.plugins.Validators.annotationFromBean;

@Component
@Order(Validators.BEAN_VALIDATOR_PLUGIN_ORDER)
public class EmailAnnotationPlugin implements ModelPropertyBuilderPlugin {

    @Override
    public void apply(ModelPropertyContext modelPropertyContext) {
        Optional<Email> email = annotationFromBean(modelPropertyContext, Email.class);
        if (email.isPresent()) {
            modelPropertyContext.getBuilder().pattern(email.get().regexp());
            modelPropertyContext.getBuilder().example("email@email.com");
        }
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }
}
