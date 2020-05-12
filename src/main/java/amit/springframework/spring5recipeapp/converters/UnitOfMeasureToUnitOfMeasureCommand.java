package amit.springframework.spring5recipeapp.converters;

import amit.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import amit.springframework.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * created by KUAM on 5/11/2020
 */
@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if(source == null){
            return null;
        }

        final UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(source.getId());
        uomCommand.setDescription(source.getDescription());
        return uomCommand;
    }
}
