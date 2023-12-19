import { WebPlugin } from '@capacitor/core';

import type { BuglyPlugin } from './definitions';

export class BuglyWeb extends WebPlugin implements BuglyPlugin {
  initCrashReport(): Promise<void> {
    throw new Error('Method not implemented.');
  }
  setUserValue(): Promise<void> {
    throw new Error('Method not implemented.');
  }
  setUserSceneTag(): Promise<void> {
    throw new Error('Method not implemented.');
  }
}
