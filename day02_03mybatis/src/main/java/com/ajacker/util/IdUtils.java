package com.ajacker.util;

import org.junit.Test;

import java.util.UUID;

/**
 * @author ajacker
 */
public class IdUtils {
    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Test
    public void testUUID() {
        System.out.println(getId());
    }
}
