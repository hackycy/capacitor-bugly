#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(BuglyPlugin, "Bugly",
           CAP_PLUGIN_METHOD(initCrashReport, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setUserValue, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(setUserSceneTag, CAPPluginReturnPromise);
)
