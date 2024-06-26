import { WebPlugin } from '@capacitor/core';

import type { MainPrinterPlugin } from './definitions';

export class MainPrinterWeb extends WebPlugin implements MainPrinterPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
