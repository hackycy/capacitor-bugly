/// <reference types="@capacitor/cli" />

declare module '@capacitor/cli' {
  export interface PluginsConfig {
    /**
     * These configuration values are available:
     */
    Bugly?: {
      /**
       * Auto init @default true
       */
      autoInit?: boolean;

      /**
       * Bugly App ID (Andoid)
       */
      androidAppId?: string;

      /**
       * Bugly App ID (iOS)
       */
      iOSAppId?: string;

      /**
       * Whether to retrieve the system trace file when setting ANR @default false (Andoid)
       */
      enableCatchAnrTrace?: boolean;

      /**
       * Set whether to obtain the mainline stack during the ANR process @default true (Andoid)
       */
      enableRecordAnrMainStack?: boolean;

      /**
       * Abnormal exit event recording switch @default false (iOS)
       */
      enableUnexpectedTerminatingDetection?: boolean;

      /**
       * Page information recording switch @default true (iOS)
       */
      enableViewControllerTracking?: boolean;

      /**
       * In process restore switch @default true (iOS)
       */
      enableSymbolicateInProcess?: boolean;

      /**
       * Stuck monitoring switch @default false (iOS)
       */
      enableBlockMonitor?: boolean;

      /**
       * Check interval for lag monitoring, in seconds @default 15 (iOS)
       */
      blockMonitorTimeout?: number;

      /**
       * Debug Mode @default false
       */
      debug?: boolean;
    };
  }
}

export interface SetUserValueOptions {
  key: string;
  value: string;
}

export interface SetUserSceneTagOptions {
  tag: number;
}

export interface BuglyPlugin {
  /**
   * Manually initializing Bugly
   */
  initCrashReport(): Promise<void>;

  /**
   * Custom Map parameters can save some custom environment information when a crash occurs
   */
  setUserValue(options: SetUserValueOptions): Promise<void>;

  /**
   * Custom tags are used to indicate a certain "scene" in the app. When a crash occurs,
   * the "scene" where the crash occurs will be displayed,
   * based on the last set label, and the label ID must be greater than 0
   */
  setUserSceneTag(options: SetUserSceneTagOptions): Promise<void>;
}
