import { registerPlugin } from '@capacitor/core';

import type { BuglyPlugin } from './definitions';

const Bugly = registerPlugin<BuglyPlugin>('Bugly', {
  web: () => import('./web').then(m => new m.BuglyWeb()),
});

export * from './definitions';
export { Bugly };
