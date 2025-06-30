package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

@Disabled
public class UserArgumenetProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(Arguments.of(User.builder().username("Paraknci").password("Pari").build()),
                       Arguments.of (User.builder().username("Pdavm").password("").build())
        );
    }
}
