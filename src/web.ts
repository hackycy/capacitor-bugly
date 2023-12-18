import { WebPlugin } from '@capacitor/core';

import type { BuglyPlugin } from './definitions';

export class BuglyWeb extends WebPlugin implements BuglyPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
