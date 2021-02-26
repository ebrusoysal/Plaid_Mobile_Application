package Base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.appmanagement.ApplicationState;

public class CommonActions extends Base {
    public void androidBackButton() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public ApplicationState getAppState() {
        return driver.queryAppState("io.plaidapp");
    }
}
