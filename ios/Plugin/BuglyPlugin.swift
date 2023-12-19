import Foundation
import Capacitor
import Bugly

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(BuglyPlugin)
public class BuglyPlugin: CAPPlugin {
    
    public var deviceID = UIDevice.current.identifierForVendor?.uuidString ?? ""
    
    public override func load() {
        super.load()
        
        let autoInit = getConfig().getBoolean("autoInit", true)
        if (autoInit) {
            self.initBugly()
        }
    }

    @objc func initCrashReport(_ call: CAPPluginCall) {
        self.initBugly()
        call.resolve()
    }
    
    @objc func setUserValue(_ call: CAPPluginCall) {
        guard let key = call.getString("key"), let value = call.getString("value") else {
            call.reject("User Data is Empty")
            return
        }
        
        Bugly.setUserValue(value, forKey: key)
        call.resolve()
    }
    
    @objc func setUserSceneTag(_ call: CAPPluginCall) {
        guard let tag = call.getInt("tag") else {
            call.reject("Tag is empty")
            return
        }
        
        Bugly.setTag(UInt(tag))
        call.resolve()
    }

    private func randomString(length: Int) -> String {
        let letters: String = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return String((0..<length).map { _ in letters.randomElement()! })
    }
    
    private func initBugly() {
        let appId = getConfig().getString("iOSAppId")
        let debug = getConfig().getBoolean("debug", false)
        let enableUnexpectedTerminatingDetection = getConfig().getBoolean("enableUnexpectedTerminatingDetection", false)
        let enableViewControllerTracking = getConfig().getBoolean("enableViewControllerTracking", true)
        let enableSymbolicateInProcess = getConfig().getBoolean("enableSymbolicateInProcess", true)
        let enableBlockMonitor = getConfig().getBoolean("enableBlockMonitor", false)
        let blockMonitorTimeout = getConfig().getInt("blockMonitorTimeout", 15)
        
        let config = BuglyConfig()
        config.debugMode = debug
        config.deviceIdentifier = deviceID
        config.unexpectedTerminatingDetectionEnable = enableUnexpectedTerminatingDetection
        config.viewControllerTrackingEnable = enableViewControllerTracking
        config.symbolicateInProcessEnable = enableSymbolicateInProcess
        config.blockMonitorEnable = enableBlockMonitor
        config.blockMonitorTimeout = TimeInterval(blockMonitorTimeout)
        
        Bugly.start(withAppId: appId, config: config)
    }
}
