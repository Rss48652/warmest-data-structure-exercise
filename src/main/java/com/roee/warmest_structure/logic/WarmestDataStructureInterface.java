package com.roee.warmest_structure.logic;

public interface WarmestDataStructureInterface {
    Integer put(String key, int value);
    Integer get(String key);
    Integer remove(String key);
    String getWarmest();
}