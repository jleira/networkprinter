import { registerPlugin } from '@capacitor/core';

import type { MainPrinterPlugin } from './definitions';

const MainPrinter = registerPlugin<MainPrinterPlugin>('MainPrinter', {
  web: () => import('./web').then(m => new m.MainPrinterWeb()),
});

export * from './definitions';
export { MainPrinter };
