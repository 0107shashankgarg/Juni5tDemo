package demo.ui.conditions;

public class ContinuouslyConditions {

    /*private static final Logger LOG = LogManager.getLogger(ContinuouslyConditions.class);

    public static Condition hiddenFor(final int timeConditionContinuouslyMeetsMS) {
        return new Condition("hiddenFor", true) {
            @Override
            public boolean apply(Driver driver, WebElement element) {
                return checkConditionMeet( driver, hidden, element, timeConditionContinuouslyMeetsMS);
            }
        };
    }

    public static Condition enabledFor(final int timeConditionContinuouslyMeetsMS) {
        return new Condition("enabledFor") {
            @Override
            public boolean apply(Driver driver, WebElement webElement) {
                return checkConditionMeet( driver, enabled, webElement, timeConditionContinuouslyMeetsMS);
            }

        };
    }

    private static boolean checkConditionMeet(Driver driver,Condition conditionToMeet, WebElement element, int timeConditionContinuouslyMeetsMS) {
        final int POLLING_PERIOD = 10;
        final int NUMBER_OF_PERIODS = timeConditionContinuouslyMeetsMS / POLLING_PERIOD;
        final int NUMBER_OF_ATTEMPTS = 100;
        int periodNumber = 0;
        int attemptNumber = 1;
        LOG.info("Condition " + conditionToMeet.toString() + " started");
        SelenideElement elementToControl = WebElementWrapper.wrap(driver ,element);
        while (periodNumber < NUMBER_OF_PERIODS) {
            try {
                elementToControl.waitUntil(conditionToMeet, 1);
                periodNumber++;
            } catch (UIAssertionError e) {
                periodNumber = 0;
                attemptNumber++;
            }
            if (attemptNumber > NUMBER_OF_ATTEMPTS) {
                LOG.info("Condition " + conditionToMeet.toString() + " failed");
                return false;
            }
            Selenide.sleep(POLLING_PERIOD);
        }
        LOG.info("Condition " + conditionToMeet.toString() + " passed");
        return true;
    }*/
}