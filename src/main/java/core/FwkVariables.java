package core;


public class FwkVariables {
    private static ThreadLocal<String> platformName = new ThreadLocal<>();
    private static ThreadLocal<String> deviceName = new ThreadLocal<>();
    private static ThreadLocal<String> platformVersion = new ThreadLocal<>();
    private static ThreadLocal<String> app = new ThreadLocal<>();
    private static ThreadLocal<String> isRealmobile = new ThreadLocal<>();


    public String getPlatformName() {
        return platformName.get();
    }

    public void setPlatformName(String platformName) {
        FwkVariables.platformName.set(platformName);
    }

    public void removePlatformName() {
        FwkVariables.platformName.remove();
    }

    public String getPlatformVersion() {
        return platformVersion.get();
    }

    public void setPlatformVersion(String platformVersion) {
        FwkVariables.platformVersion.set(platformVersion);
    }

    public String getDeviceName() {
        return deviceName.get();
    }

    public void setDeviceName(String deviceName) {
        FwkVariables.deviceName.set(deviceName);
    }

    public String getApp() {
        return app.get();
    }

    public void setApp(String app) {
        FwkVariables.app.set(app);
    }

    public String getIsRealmobile() {
        return isRealmobile.get();
    }

    public void setIsRealmobile(String isRealmobile) {
        FwkVariables.isRealmobile.set(isRealmobile);
    }

    public void initFwkVariables(String deviceName) {
        FwkVariables fwkVariables = new FwkVariables();
        fwkVariables.setPlatformName(System.getProperty("platformName","ios"));
        fwkVariables.setDeviceName(System.getProperty("deviceName","iPhone 15 Pro"));
        fwkVariables.setPlatformVersion(System.getProperty("platformVersion","17"));
        fwkVariables.setApp(System.getProperty("app","lt://APP10160572041754932035940687"));
        fwkVariables.setIsRealmobile(System.getProperty("isRealMobile","true"));

    }

}
