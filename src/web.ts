import { WebPlugin } from '@capacitor/core';

import type { BuglyPlugin } from './definitions';

export class BuglyWeb extends WebPlugin implements BuglyPlugin {
  echo(): Promise<void> {
    throw new Error('Method not implemented.');
  }
}
