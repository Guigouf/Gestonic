package com.guibou.gestonic;

import java.lang.reflect.InvocationTargetException;

public class ControllerFactory {
    /**
     * Method to be called when loading the scene by ViewLoader Class to inject
     *
     * @param clazz      Class
     * @param            <T> Generic
     * @return ControllerInstance
     */
    public static <T> T controllerForClass(Class<T> clazz) {
        try {
            // controllerInstance#someArbitraryMethodCall if necessary
            // will be triggered *before* the initialize method!
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
