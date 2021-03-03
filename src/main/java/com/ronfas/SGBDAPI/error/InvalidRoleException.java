package com.ronfas.SGBDAPI.error;

import com.ronfas.SGBDAPI.role.RoleType;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class InvalidRoleException  extends RuntimeException{

    public InvalidRoleException(Class<?> clazz, String... searchParamsMap) {
        super(InvalidRoleException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, (Object[]) searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " does not exist :  " +
                searchParams +
                ". Valid roles are : " + Arrays.toString(RoleType.values());
    }

    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries
    ) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");

        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}
