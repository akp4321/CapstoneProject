package core;

import org.openqa.selenium.remote.DesiredCapabilities;


import java.util.HashMap;

public class CapabilityManger {
    public static DesiredCapabilities getCapability(String deviceName) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        FwkVariables fwkVariables1 = new FwkVariables();
        ltOptions.put("w3c", true);
        ltOptions.put("platformName", fwkVariables1.getPlatformName());
        ltOptions.put("deviceName", fwkVariables1.getDeviceName());
        ltOptions.put("platformVersion", fwkVariables1.getPlatformVersion());
        ltOptions.put("isRealMobile", true);
        ltOptions.put("build", "Parallel Testing");
        ltOptions.put("name", "Mobile Automation");
        ltOptions.put("app", fwkVariables1.getApp());
        ltOptions.put("video", true);
        desiredCapabilities.setCapability("lt:options", ltOptions);

        return desiredCapabilities;
    }

}
