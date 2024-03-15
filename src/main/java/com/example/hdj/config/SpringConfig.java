package com.example.hdj.config;

import com.example.hdj.utils.Utils;
import org.modelmapper.AbstractCondition;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setSkipNullEnabled(true)
                .setPropertyCondition(isStringBlank)
                .setSourceNamingConvention(NamingConventions.NONE)
                .setDestinationNamingConvention(NamingConventions.NONE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public Utils utils(ModelMapper modelMapper) {
        Utils.setModelMapper(modelMapper);
        return new Utils();
    }

    Condition<?, ?> isStringBlank = new AbstractCondition<Object, Object>() {
        @Override
        public boolean applies(MappingContext<Object, Object> context) {
            if (context.getSource() instanceof String) {
                return null != context.getSource() && !"".equals(context.getSource());
            } else {
                return context.getSource() != null;
            }
        }
    };
}
