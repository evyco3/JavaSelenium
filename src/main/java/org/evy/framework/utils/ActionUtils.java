package org.evy.framework.utils;

import io.qameta.allure.Allure;
import org.evy.framework.enums.LogType;

import java.util.function.Supplier;

/**
 * Utility class for executing actions and functions with integrated logging and Allure reporting.
 * This class provides methods to execute actions and functions while handling exceptions,
 * logging success and error messages, and reporting steps to Allure.
 * <p>
 * This class is intended to be used in the {@code BasePage} class to ensure consistent
 * logging and reporting for page interactions.
 * <p>This class is designed to be used statically and should not be instantiated.</p>
 *
 * @see org.evy.framework.pages.BasePage
 */
public final class ActionUtils {

    private ActionUtils(){}

    public static void execAction(Class<?> Tclass, Runnable execution, String successMessage, String errorMessage) {
        try {
            execution.run();
            logAction(Tclass, successMessage);
        } catch (Exception e) {
            logActionError(Tclass, errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public static String execFunction(Class<?> Tclass, Supplier<String> function, String successMessage, String errorMessage) {
        try {
            String result = function.get();
            logAction(Tclass, successMessage);
            return result;
        } catch (Exception e) {
            logActionError(Tclass, errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }
    private static void logAction(Class<?> Tclass, String message) {
        LoggerUtils.log(Tclass, LogType.INFO, message);
        Allure.step(message);
    }

    private static void logActionError(Class<?> Tclass, String errorMessage, Exception e) {
        LoggerUtils.log(Tclass, LogType.ERROR, errorMessage + ": " + e.getMessage());
        Allure.step(errorMessage);
    }
}
