/// <reference types="@capacitor/cli" />

declare module '@capacitor/cli' {
  export interface PluginsConfig {
    /**
     * These configuration values are available:
     */
    Bugly?: {
      /**
       * Bugly App ID
       */
      appId?: string;

      /**
       * Debug Mode
       */
      debug?: boolean;
    };
  }
}

export interface BuglyPlugin {
  /**
   * Test Bugly
   */
  echo(): Promise<void>;
}
