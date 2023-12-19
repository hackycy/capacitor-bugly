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
        
        let appId = getConfig().getString("appId")
        let debug = getConfig().getBoolean("debug", false)
        
        let config = BuglyConfig()
        config.debugMode = debug
        config.deviceIdentifier = deviceID
        config.unexpectedTerminatingDetectionEnable = true
        
        Bugly.start(withAppId: appId, config: config)
    }

    @objc func echo(_ call: CAPPluginCall) {
        call.resolve()
    }

    private func randomString(length: Int) -> String {
        let letters: String = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return String((0..<length).map { _ in letters.randomElement()! })
    }
}
